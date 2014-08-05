package com.dadaban.repository.util;

/**
 * Created by jrose on 8/5/14.
 */
public interface OperatorAction {
    public Object process(Object params);

    public String getAction();
}
