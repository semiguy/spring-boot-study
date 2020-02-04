package com.cbcho.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbcho.shop.common.domain.CodeLabelValue;
import com.cbcho.shop.domain.CodeDetail;
import com.cbcho.shop.service.CodeDetailService;
import com.cbcho.shop.service.CodeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/codedetail")
//관리자 권한을 가진 사용자만 접근이 가능하다.
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CodeDetailController {
	
	private final CodeDetailService codeDetailService;
	private final CodeService codeService;
	
	@Autowired
	public CodeDetailController(CodeDetailService codeDetailService, CodeService codeService) {
		
		this.codeDetailService = codeDetailService;
		this.codeService = codeService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model) throws Exception {
		
		log.info("registerForm()...");
		
		CodeDetail codeDetail = new CodeDetail();
		model.addAttribute(codeDetail);
		
		List<CodeLabelValue> classCodeList = codeService.getCodeClassList();
		model.addAttribute("classCodeList", classCodeList);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		
		log.info("register()...");
		
		codeDetailService.register(codeDetail);
		rttr.addAttribute("msg", "SUCCESS");
		
		return "redirect:/codedetail/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		
		log.info("list()...");
		model.addAttribute("list", codeDetailService.list());
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(CodeDetail codeDetail, Model model) throws Exception {
		
		log.info("read()...");
		
		model.addAttribute(codeDetailService.read(codeDetail));
		
		List<CodeLabelValue> classCodeList = codeService.getCodeClassList();
		model.addAttribute("classCodeList", classCodeList);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(CodeDetail codeDetail, Model model) throws Exception {
		
		log.info("modifyForm()...");
		
		model.addAttribute(codeDetailService.read(codeDetail));
		
		List<CodeLabelValue> classCodeList = codeService.getCodeClassList();
		model.addAttribute("classCodeList", classCodeList);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		
		log.info("modify()...");
		
		codeDetailService.modify(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codedetail/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception {
		
		log.info("remove()...");
		
		codeDetailService.remove(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/codedetail/list";
	}
}
