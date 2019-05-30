package com.cbcho.boot.ch5.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cbcho.boot.ch5.model.MyData;
import com.cbcho.boot.ch5.service.MyDataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HeloController {
		
	@Autowired
	MyDataService myDataService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mv) {
		
		mv.setViewName("index");
		mv.addObject("msg", "this is sample conent.");
		mv.addObject("formModel", mydata);
		
		Iterable<MyData> list = myDataService.findAll();
		mv.addObject("datalist", list);
		
		return mv;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata, 
			BindingResult result, ModelAndView mv) {
		
		ModelAndView res = null;
		
		if(!result.hasErrors()) {			
			myDataService.save(mydata);
			res = new ModelAndView("redirect:/");
		} else {
			mv.setViewName("index");
			mv.addObject("msg", "sorry, error is occured...");
			Iterable<MyData> list = myDataService.findAll();
			mv.addObject("datalist", list);
			res = mv;
		}
		
		return res;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mv) {
		
		mv.setViewName("edit");
		mv.addObject("title", "edit mydata.");
		
		MyData data = myDataService.findById((long)id);
		mv.addObject("formModel", data);
		
		return mv;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute MyData mydata, ModelAndView mv) {
		
		myDataService.save(mydata);
		
		return new ModelAndView("redirect:/"); 
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id, ModelAndView mv) {
		
		mv.setViewName("delete");
		mv.addObject("title", "delete myData.");
		
		MyData data = myDataService.findById((long)id);
		mv.addObject("formModel", data);
		
		return mv;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView remove(@RequestParam long id, ModelAndView mv) {
		
		myDataService.deleteById(id); 
		
		return new ModelAndView("redirect:/");
	}
	
	/////////////////////////////////////////////////////////////////////////
	@PostConstruct
	public void init() {
		
		MyData d1 = new MyData();
		d1.setName("kim");
		d1.setAge(123);
		d1.setMail("kim@test.com");
		d1.setMemo("this is kim data");
		myDataService.save(d1);
		
		MyData d2 = new MyData();
		d2.setName("lee");
		d2.setAge(15);
		d2.setMail("lee@test.com");
		d2.setMemo("this is lee data");
		myDataService.save(d2);
		
		MyData d3 = new MyData();
		d3.setName("cho");
		d3.setAge(37);
		d3.setMail("cho@test.com");
		d3.setMemo("this is cho data");
		myDataService.save(d3);
	}
}
