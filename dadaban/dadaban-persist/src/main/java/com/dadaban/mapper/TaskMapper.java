package com.dadaban.mapper;

import com.dadaban.entity.Task;

/**
 * Created by jrose on 14-7-22.
 */
public interface TaskMapper extends BaseMapper<Task>{
    public void insert(Task task);
}
