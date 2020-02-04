package com.cbcho.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbcho.shop.common.domain.CodeLabelValue;
import com.cbcho.shop.domain.Member;
import com.cbcho.shop.service.CodeService;
import com.cbcho.shop.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class MemberController {

	private final MemberService memberService;
	private final CodeService codeService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService memberService, CodeService codeService, 
			PasswordEncoder passwordEncoder) {
		
		this.memberService = memberService;
		this.codeService = codeService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Member member, Model model) throws Exception {
		
		log.info("registerForm()...");
		
		String classCode = "A01";
		List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
		model.addAttribute("jobList", jobList);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Validated Member member, BindingResult result, Model model, RedirectAttributes rttr) throws Exception {
		
		log.info("register()...");
		
		if(result.hasErrors()) {
			String classCode = "A01";
			List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
			
			model.addAttribute("jobList", jobList);
			
			return "user/register";
		}
		
		String inputPassword = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPassword));
		
		memberService.register(member);
		
		rttr.addFlashAttribute("userName", member.getUserName());
		
		return "redirect:/user/registerSuccess";
	}
	
	@RequestMapping(value = "/registerSuccess", method = RequestMethod.GET)
	public void registerSuccess(Model model) throws Exception {
		
		log.info("registerSuccess()...");
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		
		log.info("list()...");
		model.addAttribute("list", memberService.list()); 
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(int userNo, Model model) throws Exception {
		
		log.info("read()...");
		
		String classCode = "A01";
		List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
		
		model.addAttribute("jobList", jobList);
		model.addAttribute(memberService.read(userNo));
		
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyForm(int userNo, Model model) throws Exception {
		
		log.info("modifyForm()...");
		
		String classCode = "A01";
		List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
		
		model.addAttribute("jobList", jobList);
		model.addAttribute(memberService.read(userNo));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Member meber, RedirectAttributes rttr) throws Exception {
		
		log.info("modify()...");
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/user/list";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(int userNo, RedirectAttributes rttr) throws Exception {
		
		log.info("remove()...");
		
		memberService.remove(userNo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/user/list";
	}
	
	/**
	 * 최초 관리자 생성
	 * @param member
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	public String setupAdminForm(Member member, Model model) throws Exception {
		
		log.info("setupAdminForm()...");
		
		if(memberService.countAll() == 0) {
			return "user/setup";
		}
		
		return "user/setupFailure";
	}
	
	
	@RequestMapping(value = "/setup", method = RequestMethod.POST)
	public String setupAdmin(Member member, RedirectAttributes rttr) throws Exception {
		
		if(memberService.countAll() == 0) {
			String inputPassword = member.getUserPw();
			member.setUserPw(passwordEncoder.encode(inputPassword));
			
			member.setJob("00");
			
			memberService.setupAdmin(member);
			
			rttr.addFlashAttribute("userName", member.getUserName());
			
			return "redirect:/user/registerSuccess";
		}
		
		return "redirect:/user/setupFailure";
	}
	
	
}
