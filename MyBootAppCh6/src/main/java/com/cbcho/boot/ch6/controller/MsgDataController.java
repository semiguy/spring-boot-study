package com.cbcho.boot.ch6.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cbcho.boot.ch6.dao.MsgDataDaoImpl;
import com.cbcho.boot.ch6.model.MsgData;
import com.cbcho.boot.ch6.repository.MsgDataRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MsgDataController {
	
	@Autowired
	MsgDataRepository repository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	MsgDataDaoImpl dao;
	
	@RequestMapping(value = "/msg", method = RequestMethod.GET)
	public ModelAndView msg(ModelAndView mv) {
		
		mv.setViewName("showMsgData");
		mv.addObject("title", "Sample");
		mv.addObject("msg", "MsgData의 예제입니다.");
		
		MsgData msgdata = new MsgData();
		mv.addObject("formModel", msgdata);
		
		List<MsgData> list = (List<MsgData>)dao.getAll();
		mv.addObject("datalist", list);
		
		return mv;
	}
	
	@RequestMapping(value = "/msg", method = RequestMethod.POST)
	public ModelAndView msgform(@Valid @ModelAttribute MsgData msgdata, Errors result, ModelAndView mv) {
		
		if(result.hasErrors()) {
			mv.setViewName("showMsgData");
			mv.addObject("title", "Sample [ERROR]");
			mv.addObject("msg", "값을 다시 확인해 주세요!");
			return mv;
		} else {
			repository.save(msgdata);
			
			return new ModelAndView("redirect:/msg");
		}
	}
	
	@PostConstruct
	public void init() {
		
		log.info("init()...");
		dao = new MsgDataDaoImpl(entityManager);
	}
}
