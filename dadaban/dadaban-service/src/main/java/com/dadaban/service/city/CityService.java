/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.city;

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.CityMapper;
import com.dadaban.repository.model.City;
import com.dadaban.repository.model.CityExample;
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
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    private static final Logger logger = LoggerFactory.make();

    public Page<City> find(Page<City> page, Map<String, Object> searchParams, String sortType) {
        CityExample example = new CityExample();
        CityExample countExample = new CityExample();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(cityMapper.selectByExample(example));
        page.setTotalRecords(cityMapper.countByExample(countExample));
        return page;
    }

    public List<City> findAll() {
        CityExample example = new CityExample();
        return cityMapper.selectByExample(example);
    }
    public List<City> findCitiesByProvinceCode(String provinceCode) {
        CityExample example = new CityExample();
        example.createCriteria().andProvinceCodeEqualTo(provinceCode);
        return cityMapper.selectByExample(example);
    }

    public City get(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        City city = cityMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(city)) {
            City newCity = new City();
            return cityMapper.updateByPrimaryKeySelective(newCity);
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return cityMapper.deleteByPrimaryKey(id);
    }

}