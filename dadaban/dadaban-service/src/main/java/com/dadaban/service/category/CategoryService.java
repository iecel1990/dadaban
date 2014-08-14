/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.category;

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.CategoryMapper;
import com.dadaban.repository.model.Category;
import com.dadaban.repository.model.CategoryExample;
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
import java.util.Map;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    private static final Logger logger = LoggerFactory.make();

    public Page<Category> find(Page<Category> page, Map<String, Object> searchParams, String sortType) {
        CategoryExample example = new CategoryExample();
        CategoryExample countExample = new CategoryExample();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(categoryMapper.selectByExample(example));
        page.setTotalRecords(categoryMapper.countByExample(countExample));
        return page;
    }


    public int save(Category category) {
        Date now = DateUtils.getNow();
        category.setStatus(StatusEnum.valid.getCode());
        category.setCreatetime(now);
        category.setUpdatetime(now);
        return categoryMapper.insert(category);
    }

    public int update(Category category) {
        category.setUpdatetime(DateUtils.getNow());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    public Category get(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(category)) {
            Category newCategory = new Category();
            newCategory.setId(category.getId());
            newCategory.setStatus(StatusEnum.invalid.getCode());
            newCategory.setUpdatetime(DateUtils.getNow());
            newCategory.setUpdateby(operatorId);
            return categoryMapper.updateByPrimaryKeySelective(newCategory);
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

}