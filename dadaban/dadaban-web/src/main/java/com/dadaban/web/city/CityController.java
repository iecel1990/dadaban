/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.web.city;

import com.dadaban.repository.model.City;
import com.dadaban.repository.util.Page;
import com.dadaban.service.account.ShiroDbRealm.ShiroUser;
import com.dadaban.service.city.CityService;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * City管理的Controller, 使用Restful风格的Urls:
 *
 * List page : GET /city/
 * Create page : GET /city/create
 * Create action : POST /city/create
 * Update page : GET /city/update/{id}
 * Update action : POST /city/update
 * Delete action : GET /city/delete/{id}
 *
 * @author jrose
 */
@Controller
@RequestMapping(value = "/city")
public class CityController {

    private static final String PAGE_SIZE = "3";

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("name", "标题");
    }

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<City> list(@RequestParam(value = "provinceCode", defaultValue = "310000") String provinceCode, Model model,
                       ServletRequest request) {

        List<City> cities = cityService.findCitiesByProvinceCode(provinceCode);
        return cities;
    }

    @ModelAttribute
    public void getCity(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        if (id != -1) {
            model.addAttribute("city", cityService.get(id));
        }
    }

    /**
     * 取出Shiro中的当前用户Id.
     */
    private Integer getCurrentUserId() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return Integer.parseInt(user.id.toString());
    }
}