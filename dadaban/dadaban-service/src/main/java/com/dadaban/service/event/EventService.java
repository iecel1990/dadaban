/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.event;

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.EventMapper;
import com.dadaban.repository.model.Event;
import com.dadaban.repository.model.EventExample;
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
public class EventService {

    @Autowired
	private EventMapper eventMapper;

    private static final Logger logger = LoggerFactory.make();

    public Page<Event> find(Page<Event> page, Map<String, Object> searchParams, String sortType) {
        EventExample example = new EventExample();
        EventExample countExample = new EventExample();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(eventMapper.selectByExample(example));
        page.setTotalRecords(eventMapper.countByExample(countExample));
        return page;
    }

    public int save(Event event) {
        Date now = DateUtils.getNow();
        event.setStatus(StatusEnum.valid.getCode());
        event.setCreatetime(now);
        event.setUpdatetime(now);
        return eventMapper.insert(event);
    }

    public int update(Event event) {
        event.setUpdatetime(DateUtils.getNow());
        return eventMapper.updateByPrimaryKeySelective(event);
    }

    public Event get(Integer id) {
        return eventMapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        Event event = eventMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(event)) {
            Event newEvent = new Event();
            newEvent.setId(event.getId());
            newEvent.setStatus(StatusEnum.invalid.getCode());
            newEvent.setUpdatetime(DateUtils.getNow());
            newEvent.setUpdateby(operatorId);
            return eventMapper.updateByPrimaryKeySelective(newEvent);
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return eventMapper.deleteByPrimaryKey(id);
    }

}
