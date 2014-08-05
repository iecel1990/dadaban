package com.dadaban.repository.util;

import com.dadaban.utils.ObjectUtil;

/**
 * Created by jrose on 8/5/14.
 */
public class LikeOperatorAction implements OperatorAction {
    @Override
    public Object process(Object param) {
        if (ObjectUtil.isNotBlank(param)) {
            return "%" + param.toString() + "%";
        }
        return null;
    }

    @Override
    public String getAction() {
        return "Like";
    }
}
