/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.web.event;

import com.dadaban.repository.model.Event;
import com.dadaban.repository.util.Page;
import com.dadaban.service.account.ShiroDbRealm.ShiroUser;
import com.dadaban.service.event.EventService;
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
 * Event管理的Controller, 使用Restful风格的Urls:
 *
 * List page : GET /event/
 * Create page : GET /event/create
 * Create action : POST /event/create
 * Update page : GET /event/update/{id}
 * Update action : POST /event/update
 * Delete action : GET /event/delete/{id}
 *
 * @author jrose
 */
@Controller
@RequestMapping(value = "/event")
public class EventController {

	private static final String PAGE_SIZE = "3";

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("name", "标题");
	}

    @Autowired
    private EventService eventService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {

        Page<Event> page = new Page<>(pageNumber);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        eventService.find(page, searchParams, sortType);

        model.addAttribute("events",page);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);

        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "event/eventList";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("action", "create");
		return "event/eventForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Event event, RedirectAttributes redirectAttributes) {
        eventService.save(event);
		redirectAttributes.addFlashAttribute("message", "创建成功");
		return "redirect:/event/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("event", eventService.get(id));
		model.addAttribute("action", "update");
		return "event/eventForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("event") Event event, RedirectAttributes redirectAttributes) {

        eventService.update(event);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/event/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        eventService.delete(id, getCurrentUserId());
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/event/";
	}


	@ModelAttribute
	public void getEvent(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("event", eventService.get(id));
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
