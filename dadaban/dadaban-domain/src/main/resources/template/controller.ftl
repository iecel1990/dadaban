/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.web.${beanName};

import com.dadaban.repository.model.${UpBeanName};
import com.dadaban.repository.util.Page;
import com.dadaban.service.account.ShiroDbRealm.ShiroUser;
import com.dadaban.service.${beanName}.${UpBeanName}Service;
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
import java.util.Map;

/**
 * ${UpBeanName}管理的Controller, 使用Restful风格的Urls:
 *
 * List page : GET /${beanName}/
 * Create page : GET /${beanName}/create
 * Create action : POST /${beanName}/create
 * Update page : GET /${beanName}/update/{id}
 * Update action : POST /${beanName}/update
 * Delete action : GET /${beanName}/delete/{id}
 *
 * @author jrose
 */
@Controller
@RequestMapping(value = "/${beanName}")
public class ${UpBeanName}Controller {

    private static final String PAGE_SIZE = "3";

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("name", "标题");
    }

    @Autowired
    private ${UpBeanName}Service ${beanName}Service;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {

        Page<${UpBeanName}> page = new Page<>(pageNumber);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        ${beanName}Service.find(page, searchParams, sortType);

        model.addAttribute("${beanName}s",page);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);

        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "${beanName}/${beanName}List";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("${beanName}", new ${UpBeanName}());
        model.addAttribute("action", "create");
        return "${beanName}/${beanName}Form";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid ${UpBeanName} ${beanName}, RedirectAttributes redirectAttributes) {
        ${beanName}Service.save(${beanName});
        redirectAttributes.addFlashAttribute("message", "创建成功");
        return "redirect:/${beanName}/";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("${beanName}", ${beanName}Service.get(id));
        model.addAttribute("action", "update");
        return "${beanName}/${beanName}Form";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("${beanName}") ${UpBeanName} ${beanName}, RedirectAttributes redirectAttributes) {

        ${beanName}Service.update(${beanName});
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/${beanName}/";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ${beanName}Service.delete(id, getCurrentUserId());
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/${beanName}/";
    }


    @ModelAttribute
    public void get${UpBeanName}(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        if (id != -1) {
            model.addAttribute("${beanName}", ${beanName}Service.get(id));
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
