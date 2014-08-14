package com.dadaban.repository.dao;

import com.dadaban.repository.model.Files;
import com.dadaban.repository.model.FilesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilesMapper {
    int countByExample(FilesExample example);

    int deleteByExample(FilesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Files record);

    int insertSelective(Files record);

    List<Files> selectByExample(FilesExample example);

    Files selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByExample(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);
}