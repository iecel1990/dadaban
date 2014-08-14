/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.web.province;

import com.dadaban.repository.model.Province;
import com.dadaban.repository.util.Page;
import com.dadaban.service.account.ShiroDbRealm.ShiroUser;
import com.dadaban.service.province.ProvinceService;
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
 * Province管理的Controller, 使用Restful风格的Urls:
 *
 * List page : GET /province/
 * Create page : GET /province/create
 * Create action : POST /province/create
 * Update page : GET /province/update/{id}
 * Update action : POST /province/update
 * Delete action : GET /province/delete/{id}
 *
 * @author jrose
 */
@Controller
@RequestMapping(value = "/province")
public class ProvinceController {

    private static final String PAGE_SIZE = "3";

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("name", "标题");
    }

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Province> list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                       ServletRequest request) {

        List<Province> provinces = provinceService.findAll();

        return provinces;
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        provinceService.delete(id, getCurrentUserId());
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/province/";
    }


    @ModelAttribute
    public void getProvince(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        if (id != -1) {
            model.addAttribute("province", provinceService.get(id));
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