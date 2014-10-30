/*******************************************************************************
 * Copyright (c) 2005, 2014 github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dadaban.web.event;

import com.dadaban.repository.model.*;
import com.dadaban.repository.util.Page;
import com.dadaban.service.account.ShiroDbRealm.ShiroUser;
import com.dadaban.service.content.ContentService;
import com.dadaban.service.event.EventAttaService;
import com.dadaban.service.event.EventService;
import com.dadaban.service.event.TicketService;
import com.dadaban.service.files.FilesService;
import com.dadaban.service.province.ProvinceService;
import com.dadaban.utils.ConfigUtil;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.*;

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

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private EventAttaService eventAttaService;

    @Autowired
    private FilesService filesService;

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

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String upload() {
        return "event/upload";
    }

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create( @RequestParam(value = "editorValue", defaultValue = "") String editorValue, @Valid Event event,RedirectAttributes redirectAttributes) {
        eventService.save(event);
        Content content = new Content();
        content.setContent(editorValue);
        content.setEventId(event.getId());
        content.setName("活动内容");
        contentService.save(content);
		redirectAttributes.addFlashAttribute("message", "创建成功");
        String url = "redirect:/event/update/" + event.getId() + "?tab=" + 1;
        return url;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, @RequestParam(value = "tab", defaultValue = "0") String tab, Model model) {

        Event event = eventService.get(id);
        List<Ticket> tickets = ticketService.findTickets(id);
        List<Content> contents = contentService.findContents(id);
        List<Province> provinces = provinceService.findAll();

        model.addAttribute("event", event);
		model.addAttribute("action", "update");
        model.addAttribute("content", CollectionUtils.isNotEmpty(contents) ? contents.get(0) : null);
        model.addAttribute("tickets", tickets);
        model.addAttribute("provinces", provinces);
        model.addAttribute("tab", tab);
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

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.get(id);
        List<Ticket> tickets = ticketService.findTickets(id);
        List<Content> contents = contentService.findContents(id);
        List<EventAtta> eventAttas = eventAttaService.findByEventId(id);

        Collection<Integer> integerCollection = Collections2.transform(eventAttas, new Function<EventAtta, Integer>() {
            @Override
            public Integer apply(EventAtta input) {
                return input.getAttaId();
            }
        });
        List<Files> focusImgs = filesService.findByAttaIds(new ArrayList<>(integerCollection));
        model.addAttribute("event", event);
        model.addAttribute("tickets", tickets);
        model.addAttribute("contents", contents);
        model.addAttribute("focusImgs", focusImgs);

        model.addAttribute("action", "update");
        return "event/eventShow";
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
