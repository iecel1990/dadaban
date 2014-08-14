/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.web.event;

import com.dadaban.repository.model.Ticket;
import com.dadaban.repository.util.Page;
import com.dadaban.service.account.ShiroDbRealm.ShiroUser;
import com.dadaban.service.event.TicketService;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Ticket管理的Controller, 使用Restful风格的Urls:
 *
 * List page : GET /ticket/
 * Create page : GET /ticket/create
 * Create action : POST /ticket/create
 * Update page : GET /ticket/update/{id}
 * Update action : POST /ticket/update
 * Delete action : GET /ticket/delete/{id}
 *
 * @author jrose
 */
@Controller
@RequestMapping(value = "/ticket")
public class TicketController {

    private static final String PAGE_SIZE = "3";

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("name", "标题");
    }

    @Autowired
    private TicketService ticketService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                       ServletRequest request) {

        Page<Ticket> page = new Page<>(pageNumber);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        ticketService.find(page, searchParams, sortType);

        model.addAttribute("tickets",page);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);

        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "ticket/ticketList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("action", "create");
        return "ticket/ticketForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Ticket create(@Valid Ticket ticket, RedirectAttributes redirectAttributes) {
        ticketService.save(ticket);
        return ticket;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ticket", ticketService.get(id));
        model.addAttribute("action", "update");
        return "ticket/ticketForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("ticket") Ticket ticket, RedirectAttributes redirectAttributes) {

        ticketService.update(ticket);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/ticket/";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ticketService.delete(id, getCurrentUserId());
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/ticket/";
    }


    @ModelAttribute
    public void getTicket(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        if (id != -1) {
            model.addAttribute("ticket", ticketService.get(id));
        }
    }

    /**
     * 取出Shiro中的当前用户Id.
     */
    private Integer getCurrentUserId() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return Integer.parseInt(user.id.toString());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}