/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.web.files;

import com.dadaban.repository.model.Files;
import com.dadaban.repository.util.Page;
import com.dadaban.service.account.ShiroDbRealm.ShiroUser;
import com.dadaban.service.files.FilesService;
import com.dadaban.utils.ConfigUtil;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Files管理的Controller, 使用Restful风格的Urls:
 *
 * List page : GET /files/
 * Create page : GET /files/create
 * Create action : POST /files/create
 * Update page : GET /files/update/{id}
 * Update action : POST /files/update
 * Delete action : GET /files/delete/{id}
 *
 * @author jrose
 */
@Controller
@RequestMapping(value = "/files")
public class FilesController {

    private static final String PAGE_SIZE = "3";

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("name", "标题");
    }

    @Autowired
    private FilesService filesService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                       ServletRequest request) {

        Page<Files> page = new Page<>(pageNumber);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        filesService.find(page, searchParams, sortType);

        model.addAttribute("filess",page);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);

        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "files/filesList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("files", new Files());
        model.addAttribute("action", "create");
        return "files/filesForm";
    }


    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Files create(@RequestParam("file") CommonsMultipartFile file, RedirectAttributes redirectAttributes) {
        Files files = filesService.save(file, getCurrentUserId());
        return files;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("files", filesService.get(id));
        model.addAttribute("action", "update");
        return "files/filesForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("files") Files files, RedirectAttributes redirectAttributes) {

        filesService.update(files);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/files/";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        filesService.delete(id, getCurrentUserId());
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/files/";
    }


    @ModelAttribute
    public void getFiles(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        if (id != -1) {
            model.addAttribute("files", filesService.get(id));
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