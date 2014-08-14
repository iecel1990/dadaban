/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.province;

import com.dadaban.repository.dao.ProvinceMapper;
import com.dadaban.repository.model.Province;
import com.dadaban.repository.model.ProvinceExample;
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
public class ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    private static final Logger logger = LoggerFactory.make();

    public Page<Province> find(Page<Province> page, Map<String, Object> searchParams, String sortType) {
        ProvinceExample example = new ProvinceExample();
        ProvinceExample countExample = new ProvinceExample();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(provinceMapper.selectByExample(example));
        page.setTotalRecords(provinceMapper.countByExample(countExample));
        return page;
    }

    public List<Province> findAll() {
        ProvinceExample example = new ProvinceExample();
        return provinceMapper.selectByExample(example);
    }
    public Province get(Integer id) {
        return provinceMapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        Province province = provinceMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(province)) {
            Province newProvince = new Province();
            return provinceMapper.updateByPrimaryKeySelective(newProvince);
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return provinceMapper.deleteByPrimaryKey(id);
    }

}