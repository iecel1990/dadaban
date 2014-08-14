/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.web.category;

import com.dadaban.repository.model.Category;
import com.dadaban.repository.util.Page;
import com.dadaban.service.account.ShiroDbRealm.ShiroUser;
import com.dadaban.service.category.CategoryService;
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
 * Category管理的Controller, 使用Restful风格的Urls:
 *
 * List page : GET /category/
 * Create page : GET /category/create
 * Create action : POST /category/create
 * Update page : GET /category/update/{id}
 * Update action : POST /category/update
 * Delete action : GET /category/delete/{id}
 *
 * @author jrose
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    private static final String PAGE_SIZE = "3";

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("name", "标题");
    }

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                       ServletRequest request) {

        Page<Category> page = new Page<>(pageNumber);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        categoryService.find(page, searchParams, sortType);

        model.addAttribute("categorys",page);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);

        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "category/categoryList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "create");
        return "category/categoryForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "创建成功");
        return "redirect:/category";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.get(id));
        model.addAttribute("action", "update");
        return "category/categoryForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {

        categoryService.update(category);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/category/";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        categoryService.delete(id, getCurrentUserId());
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/category/";
    }


    @ModelAttribute
    public void getCategory(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        if (id != -1) {
            model.addAttribute("category", categoryService.get(id));
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