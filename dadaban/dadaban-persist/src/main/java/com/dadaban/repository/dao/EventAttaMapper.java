package com.dadaban.repository.dao;

import com.dadaban.repository.model.EventAtta;
import com.dadaban.repository.model.EventAttaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventAttaMapper {
    int countByExample(EventAttaExample example);

    int deleteByExample(EventAttaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventAtta record);

    int insertSelective(EventAtta record);

    List<EventAtta> selectByExample(EventAttaExample example);

    EventAtta selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventAtta record, @Param("example") EventAttaExample example);

    int updateByExample(@Param("record") EventAtta record, @Param("example") EventAttaExample example);

    int updateByPrimaryKeySelective(EventAtta record);

    int updateByPrimaryKey(EventAtta record);
}