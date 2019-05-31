package com.cbcho.boot.ch7.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cbcho.boot.ch7.component.MyDataBean;
import com.cbcho.boot.ch7.model.MyData;
import com.cbcho.boot.ch7.repository.MyDataRepository;
import com.cbcho.boot.ch7.service.MyDataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HeloController {
	
	@Autowired
	MyDataRepository repository;
	
	@Autowired
	private MyDataService service;
	
	@Autowired
	MyDataBean myDataBean;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		
		mav.setViewName("index");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData의 예제입니다.");
		
		List<MyData> list = service.getAll();
		mav.addObject("datalist", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView indexById(@PathVariable long id, ModelAndView mav) {
		
		mav.setViewName("pickup");
		mav.addObject("title", "Pickup Page");
		String table = "<table>" + myDataBean.getTableTagById(id) + "</table>";
		mav.addObject("msg", "pickup data id = " + id);
		mav.addObject("data", table);
		
		return mav;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		
		mav.setViewName("find");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData의 예제 입니다.");
		mav.addObject("value", "");
		
		List<MyData> list = service.getAll();
		
		mav.addObject("datalist", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
		
		mav.setViewName("find");
		
		String param = request.getParameter("fstr");
		
		if(param.equals("")) {
			mav = new ModelAndView("redirect:/find");
		} else {
			
			mav.addObject("title", "Find Result");
			mav.addObject("msg", param + "의 검색 결과");
			mav.addObject("value", param);
			
			List<MyData> list = service.find(param);
			mav.addObject("datalist", list);
		}
				
		return mav;
	}
	
	/////////////////////////////////////////////////////////////////////////
	@PostConstruct
	public void init() {
		
		log.info("init()...");
		
		MyData d1 = new MyData();
		d1.setName("kim");
		d1.setAge(123);
		d1.setMail("kim@test.com");
		d1.setMemo("this is kim data");
		repository.save(d1);
		
		MyData d2 = new MyData();
		d2.setName("lee");
		d2.setAge(15);
		d2.setMail("lee@test.com");
		d2.setMemo("this is lee data");
		repository.save(d2);
		
		MyData d3 = new MyData();
		d3.setName("cho");
		d3.setAge(37);
		d3.setMail("cho@test.com");
		d3.setMemo("this is cho data");
		repository.save(d3);
	}
}
