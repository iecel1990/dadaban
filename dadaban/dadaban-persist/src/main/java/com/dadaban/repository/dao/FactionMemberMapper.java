package com.dadaban.repository.dao;

import com.dadaban.repository.model.FactionMember;
import com.dadaban.repository.model.FactionMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FactionMemberMapper {
    int countByExample(FactionMemberExample example);

    int deleteByExample(FactionMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FactionMember record);

    int insertSelective(FactionMember record);

    List<FactionMember> selectByExample(FactionMemberExample example);

    FactionMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FactionMember record, @Param("example") FactionMemberExample example);

    int updateByExample(@Param("record") FactionMember record, @Param("example") FactionMemberExample example);

    int updateByPrimaryKeySelective(FactionMember record);

    int updateByPrimaryKey(FactionMember record);
}