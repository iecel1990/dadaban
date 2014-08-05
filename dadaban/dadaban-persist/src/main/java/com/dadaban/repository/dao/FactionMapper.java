package com.dadaban.repository.dao;

import com.dadaban.repository.model.Faction;
import com.dadaban.repository.model.FactionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FactionMapper {
    int countByExample(FactionExample example);

    int deleteByExample(FactionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Faction record);

    int insertSelective(Faction record);

    List<Faction> selectByExample(FactionExample example);

    Faction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Faction record, @Param("example") FactionExample example);

    int updateByExample(@Param("record") Faction record, @Param("example") FactionExample example);

    int updateByPrimaryKeySelective(Faction record);

    int updateByPrimaryKey(Faction record);
}