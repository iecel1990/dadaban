/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.service.event;

import com.dadaban.enums.StatusEnum;
import com.dadaban.repository.dao.TicketMapper;
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
public class TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    private static final Logger logger = LoggerFactory.make();

    public Page<Ticket> find(Page<Ticket> page, Map<String, Object> searchParams, String sortType) {
        TicketExample example = new TicketExample();
        TicketExample countExample = new TicketExample();

        try {
            CriteriaUtil.buildCriteria(example, countExample, searchParams, page, sortType);
        } catch (InvocationTargetException e) {
            logger.warn("buildCriteria error:", e);
        } catch (IllegalAccessException e) {
            logger.warn("buildCriteria error:", e);
        }
        page.setContent(ticketMapper.selectByExample(example));
        page.setTotalRecords(ticketMapper.countByExample(countExample));
        return page;
    }

    public List<Ticket> findTickets(Integer eventId) {
        TicketExample example = new TicketExample();
        example.createCriteria().andEventIdEqualTo(eventId);
        return ticketMapper.selectByExample(example);
    }


    public int save(Ticket ticket) {
        Date now = DateUtils.getNow();
        ticket.setStatus(StatusEnum.valid.getCode());
        ticket.setCreatetime(now);
        ticket.setUpdatetime(now);
        return ticketMapper.insert(ticket);
    }

    public int update(Ticket ticket) {
        ticket.setUpdatetime(DateUtils.getNow());
        return ticketMapper.updateByPrimaryKeySelective(ticket);
    }

    public Ticket get(Integer id) {
        return ticketMapper.selectByPrimaryKey(id);
    }

    public int delete(Integer id, Integer operatorId) {
        Ticket ticket = ticketMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(ticket)) {
            Ticket newTicket = new Ticket();
            newTicket.setId(ticket.getId());
            newTicket.setStatus(StatusEnum.invalid.getCode());
            newTicket.setUpdatetime(DateUtils.getNow());
            newTicket.setUpdateby(operatorId);
            return ticketMapper.updateByPrimaryKeySelective(newTicket);
        }
        return 0;
    }

    public int deleteForce(Integer id) {
        return ticketMapper.deleteByPrimaryKey(id);
    }

}