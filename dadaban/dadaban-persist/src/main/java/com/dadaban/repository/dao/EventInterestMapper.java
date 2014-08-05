package com.dadaban.repository.dao;

import com.dadaban.repository.model.EventInterest;
import com.dadaban.repository.model.EventInterestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventInterestMapper {
    int countByExample(EventInterestExample example);

    int deleteByExample(EventInterestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventInterest record);

    int insertSelective(EventInterest record);

    List<EventInterest> selectByExample(EventInterestExample example);

    EventInterest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventInterest record, @Param("example") EventInterestExample example);

    int updateByExample(@Param("record") EventInterest record, @Param("example") EventInterestExample example);

    int updateByPrimaryKeySelective(EventInterest record);

    int updateByPrimaryKey(EventInterest record);
}