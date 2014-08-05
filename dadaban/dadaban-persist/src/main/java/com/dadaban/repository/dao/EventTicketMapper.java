package com.dadaban.repository.dao;

import com.dadaban.repository.model.EventTicket;
import com.dadaban.repository.model.EventTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventTicketMapper {
    int countByExample(EventTicketExample example);

    int deleteByExample(EventTicketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventTicket record);

    int insertSelective(EventTicket record);

    List<EventTicket> selectByExample(EventTicketExample example);

    EventTicket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventTicket record, @Param("example") EventTicketExample example);

    int updateByExample(@Param("record") EventTicket record, @Param("example") EventTicketExample example);

    int updateByPrimaryKeySelective(EventTicket record);

    int updateByPrimaryKey(EventTicket record);
}