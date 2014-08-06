/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.${beanName};

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.${UpBeanName}Mapper;
import com.dadaban.repository.model.${UpBeanName};
import com.dadaban.repository.model.${UpBeanName}Example;
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
public class ${UpBeanName}Service {

    @Autowired
    private ${UpBeanName}Mapper ${beanName}Mapper;

    private static final Logger logger = LoggerFactory.make();

    public Page<${UpBeanName}> find(Page<${UpBeanName}> page, Map<String, Object> searchParams, String sortType) {
        ${UpBeanName}Example example = new ${UpBeanName}Example();
        ${UpBeanName}Example countExample = new ${UpBeanName}Example();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(${beanName}Mapper.selectByExample(example));
        page.setTotalRecords(${beanName}Mapper.countByExample(countExample));
        return page;
    }


    public int save(${UpBeanName} ${beanName}) {
        Date now = DateUtils.getNow();
        ${beanName}.setStatus(StatusEnum.valid.getCode());
        ${beanName}.setCreatetime(now);
        ${beanName}.setUpdatetime(now);
        return ${beanName}Mapper.insert(${beanName});
    }

    public int update(${UpBeanName} ${beanName}) {
        ${beanName}.setUpdatetime(DateUtils.getNow());
        return ${beanName}Mapper.updateByPrimaryKeySelective(${beanName});
    }

    public ${UpBeanName} get(Integer id) {
        return ${beanName}Mapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        ${UpBeanName} ${beanName} = ${beanName}Mapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(${beanName})) {
            ${UpBeanName} new${UpBeanName} = new ${UpBeanName}();
            new${UpBeanName}.setId(${beanName}.getId());
            new${UpBeanName}.setStatus(StatusEnum.invalid.getCode());
            new${UpBeanName}.setUpdatetime(DateUtils.getNow());
            new${UpBeanName}.setUpdateby(operatorId);
            return ${beanName}Mapper.updateByPrimaryKeySelective(new${UpBeanName});
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return ${beanName}Mapper.deleteByPrimaryKey(id);
    }

}
