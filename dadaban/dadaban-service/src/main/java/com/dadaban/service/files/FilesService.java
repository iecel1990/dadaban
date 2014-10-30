/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.files;

import com.dadaban.enums.AttaFileType;
import com.dadaban.enums.Constants;
import com.dadaban.enums.FileType;
import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.FilesMapper;
import com.dadaban.repository.model.EventAtta;
import com.dadaban.repository.model.Files;
import com.dadaban.repository.model.FilesExample;
import com.dadaban.repository.util.CriteriaUtil;
import com.dadaban.repository.util.Page;
import com.dadaban.service.event.EventAttaService;
import com.dadaban.utils.ConfigUtil;
import com.dadaban.utils.DateUtils;
import com.dadaban.utils.LoggerFactory;
import com.dadaban.utils.ObjectUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class FilesService {

    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    private EventAttaService eventAttaService;

    private static final Logger logger = LoggerFactory.make();

    public Page<Files> find(Page<Files> page, Map<String, Object> searchParams, String sortType) {
        FilesExample example = new FilesExample();
        FilesExample countExample = new FilesExample();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(filesMapper.selectByExample(example));
        page.setTotalRecords(filesMapper.countByExample(countExample));
        return page;
    }

    public List<Files> findByAttaIds(List<Integer> attaIds) {
        FilesExample example = new FilesExample();

        example.createCriteria().andIdIn(attaIds).andStatusEqualTo(StatusEnum.valid.getCode());
        return filesMapper.selectByExample(example);
    }

    public int save(Files files) {
        Date now = DateUtils.getNow();
        files.setStatus(StatusEnum.valid.getCode());
        files.setCreatetime(now);
        files.setUpdatetime(now);
        return filesMapper.insert(files);
    }


    public Files save(CommonsMultipartFile file, Integer userId, Integer eventId) {
        String fileName = file.getFileItem().getName();

        String suffix = com.google.common.io.Files.getFileExtension(file.getFileItem().getName());

        String root = ConfigUtil.getFileDir();
        String timeDir = ObjectUtil.getTimeDir();
        String dirPath = root + FileType.PIC_POSTER + Constants.DIR_SEPARATOR + timeDir;

        createDir(dirPath);

        String newFileName = getFileName(suffix);

        int result = createFile(file, dirPath + Constants.DIR_SEPARATOR + newFileName);

        if (result > 0) {
            Files files = new Files();
            files.setFileOriginalName(fileName);
            files.setFileName(newFileName);
            files.setFileType(suffix);
            files.setFilePath(FileType.PIC_POSTER + Constants.DIR_SEPARATOR + timeDir);
            files.setSize(String.valueOf(file.getSize()));
            files.setCreateby(userId);
            files.setCreatetime(DateUtils.getNow());
            files.setStatus(StatusEnum.valid.getCode());
            files.setUpdateby(userId);
            files.setUpdatetime(DateUtils.getNow());
            int filesResult = filesMapper.insert(files);
            if (filesResult > 0) {
                saveEventAtta(userId, files.getId(), eventId);
                return files;
            }
        }
        return null;
    }

    private void saveEventAtta(Integer userId, int filesId, Integer eventId) {
            EventAtta eventAtta = new EventAtta();
            eventAtta.setUpdateby(userId);
            eventAtta.setCreateby(userId);
            eventAtta.setCreatetime(DateUtils.getNow());
            eventAtta.setUpdatetime(DateUtils.getNow());
            eventAtta.setStatus(StatusEnum.valid.getCode());
            eventAtta.setType(AttaFileType.FOCUS_IMG);
            eventAtta.setEventId(eventId);
            eventAtta.setAttaId(filesId);
            eventAttaService.save(eventAtta);
    }

    private String getFileName(String suffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(UUID.randomUUID())
                .append(".")
                .append(suffix);
        return sb.toString().replaceAll("-", "");
    }


    private int createFile( CommonsMultipartFile file, String filePath) {
        FileOutputStream fos = null;
        InputStream fis = null;
        try {
            fos = new FileOutputStream(filePath);
            fis = file.getInputStream();

            return IOUtils.copy(fis, fos);
        } catch (FileNotFoundException e) {
            logger.warn("文件未找到", e);
        } catch (IOException e) {
            logger.warn("创建文件错误", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos.flush();
                } catch (IOException e) {
                    logger.warn("关闭文件流错误", e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.warn("关闭文件流错误", e);
                }
            }
        }
        return 0;
    }
    private String createDir(String path) {
        File fileDir = new File(path);

        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir.getAbsolutePath();
    }

    public int update(Files files) {
        files.setUpdatetime(DateUtils.getNow());
        return filesMapper.updateByPrimaryKeySelective(files);
    }

    public Files get(Integer id) {
        return filesMapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        Files files = filesMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(files)) {
            Files newFiles = new Files();
            newFiles.setId(files.getId());
            newFiles.setStatus(StatusEnum.invalid.getCode());
            newFiles.setUpdatetime(DateUtils.getNow());
            newFiles.setUpdateby(operatorId);
            return filesMapper.updateByPrimaryKeySelective(newFiles);
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return filesMapper.deleteByPrimaryKey(id);
    }

}