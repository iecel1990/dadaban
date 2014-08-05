/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.event;

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.EventMapper;
import com.dadaban.repository.model.Event;
import com.dadaban.repository.model.EventExample;
import com.dadaban.repository.util.*;
import com.dadaban.utils.ObjectUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class EventService {

    @Autowired
	private EventMapper eventMapper;


    public Page<Event> findAll(Page<Event> page, Map<String, Object> searchParams) {

        EventExample example = new EventExample();
        example.setPage(page);

        EventExample countExample = new EventExample();
        countExample.createCriteria().andStatusEqualTo(StatusEnum.valid.getCode());

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        page.setContent(eventMapper.selectByExample(example));
        page.setTotalRecords(eventMapper.countByExample(countExample));
        return page;
    }



    public int save(Event event) {
        event.setStatus(StatusEnum.valid.getCode());
        event.setCreatetime(new Date());
        return eventMapper.insert(event);
    }

    public int update(Event event) {
        event.setStatus(StatusEnum.valid.getCode());
        return eventMapper.updateByPrimaryKeySelective(event);
    }

    public Event get(Integer id) {
        return eventMapper.selectByPrimaryKey(id);
    }

}
