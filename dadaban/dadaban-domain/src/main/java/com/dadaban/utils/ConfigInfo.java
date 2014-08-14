package com.dadaban.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by jrose on 8/13/14.
 */
@Component
public class ConfigInfo {
    @Value("${file.dir}")
    protected String fileDir;
}
