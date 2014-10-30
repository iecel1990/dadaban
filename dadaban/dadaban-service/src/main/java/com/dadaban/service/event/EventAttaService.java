/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.event;

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.EventAttaMapper;
import com.dadaban.repository.model.EventAtta;
import com.dadaban.repository.model.EventAttaExample;
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
public class EventAttaService {

    @Autowired
    private EventAttaMapper eventAttaMapper;

    private static final Logger logger = LoggerFactory.make();

    public Page<EventAtta> find(Page<EventAtta> page, Map<String, Object> searchParams, String sortType) {
        EventAttaExample example = new EventAttaExample();
        EventAttaExample countExample = new EventAttaExample();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(eventAttaMapper.selectByExample(example));
        page.setTotalRecords(eventAttaMapper.countByExample(countExample));
        return page;
    }


    public List<EventAtta> findByEventId(Integer eventId) {
        EventAttaExample example = new EventAttaExample();

        try {
            CriteriaUtil.buildCriteria(example, null, null, null, null);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        return eventAttaMapper.selectByExample(example);
    }


    public int save(EventAtta eventAtta) {
        Date now = DateUtils.getNow();
        eventAtta.setStatus(StatusEnum.valid.getCode());
        eventAtta.setCreatetime(now);
        eventAtta.setUpdatetime(now);
        return eventAttaMapper.insert(eventAtta);
    }

    public int update(EventAtta eventAtta) {
        eventAtta.setUpdatetime(DateUtils.getNow());
        return eventAttaMapper.updateByPrimaryKeySelective(eventAtta);
    }

    public EventAtta get(Integer id) {
        return eventAttaMapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        EventAtta eventAtta = eventAttaMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(eventAtta)) {
            EventAtta newEventAtta = new EventAtta();
            newEventAtta.setId(eventAtta.getId());
            newEventAtta.setStatus(StatusEnum.invalid.getCode());
            newEventAtta.setUpdatetime(DateUtils.getNow());
            newEventAtta.setUpdateby(operatorId);
            return eventAttaMapper.updateByPrimaryKeySelective(newEventAtta);
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return eventAttaMapper.deleteByPrimaryKey(id);
    }

}