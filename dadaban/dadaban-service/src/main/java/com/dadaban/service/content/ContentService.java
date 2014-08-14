/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.content;

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.ContentMapper;
import com.dadaban.repository.model.Content;
import com.dadaban.repository.model.ContentExample;
import com.dadaban.repository.model.Ticket;
import com.dadaban.repository.model.TicketExample;
import com.dadaban.repository.util.CriteriaUtil;
import com.dadaban.repository.util.Page;
import com.dadaban.utils.DateUtils;
import com.dadaban.utils.LoggerFactory;
import com.dadaban.utils.ObjectUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ContentService {

    @Autowired
    private ContentMapper contentMapper;

    private static final Logger logger = LoggerFactory.make();

    public Page<Content> find(Page<Content> page, Map<String, Object> searchParams, String sortType) {
        ContentExample example = new ContentExample();
        ContentExample countExample = new ContentExample();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(contentMapper.selectByExample(example));
        page.setTotalRecords(contentMapper.countByExample(countExample));
        return page;
    }

    public List<Content> findContents(Integer eventId) {
        ContentExample example = new ContentExample();
        example.createCriteria().andEventIdEqualTo(eventId);
        return contentMapper.selectByExampleWithBLOBs(example);
    }

    public int save(Content content) {
        Date now = DateUtils.getNow();
        content.setStatus(StatusEnum.valid.getCode());
        content.setCreatetime(now);
        content.setUpdatetime(now);
        return contentMapper.insert(content);
    }

    public int update(Content content) {
        content.setUpdatetime(DateUtils.getNow());
        return contentMapper.updateByPrimaryKeySelective(content);
    }

    public Content get(Integer id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        Content content = contentMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(content)) {
            Content newContent = new Content();
            newContent.setId(content.getId());
            newContent.setStatus(StatusEnum.invalid.getCode());
            newContent.setUpdatetime(DateUtils.getNow());
            newContent.setUpdateby(operatorId);
            return contentMapper.updateByPrimaryKeySelective(newContent);
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return contentMapper.deleteByPrimaryKey(id);
    }

}