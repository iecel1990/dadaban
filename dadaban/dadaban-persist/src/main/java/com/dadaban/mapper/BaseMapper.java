package com.dadaban.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by jrose on 14-7-22.
 */
public interface BaseMapper<T> {
    public T findOne(Long id);

    public void save(T t);

    public Page<T> findAll(Specification<T> spec, PageRequest pageRequest);

    public List<T> findAll();

    public void delete(Long id);


}
