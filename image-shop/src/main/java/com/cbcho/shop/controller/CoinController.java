package com.cbcho.shop.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbcho.shop.common.security.domain.CustomUser;
import com.cbcho.shop.domain.ChargeCoin;
import com.cbcho.shop.domain.Member;
import com.cbcho.shop.service.CoinService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/coin")
public class CoinController {
	
	private final CoinService coinService;
	private final MessageSource messageSource;
	
	@Autowired
	public CoinController(CoinService coinService, MessageSource messageSource) {
		
		this.coinService = coinService;
		this.messageSource = messageSource;
	}
	
	@RequestMapping(value = "/charge", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void chargeForm(Model model) throws Exception {
		
		log.info("chargeForm()...");
		
		ChargeCoin chargeCoin = new ChargeCoin();
		chargeCoin.setAmount(1000);
		
		model.addAttribute(chargeCoin);
	}
	
	@RequestMapping(value = "/charge", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String charge(int amount, RedirectAttributes rttr, Authentication authentication) throws Exception {
		
		log.info("charge()...");
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		int userNo = member.getUserNo();
		
		ChargeCoin chargeCoin = new ChargeCoin();
		
		chargeCoin.setUserNo(userNo); 
		chargeCoin.setAmount(amount);
		
		coinService.charge(chargeCoin);
		
		String message = messageSource.getMessage("coin.chargingComplete", null, Locale.KOREAN);
		
		rttr.addFlashAttribute("msg", message);
		
		return "redirect:/coin/success";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() throws Exception {
		
		log.info("success()...");
		return "coin/success";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void list(Model model, Authentication authentication) throws Exception {
		
		log.info("list()...");
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		int userNo = member.getUserNo();
		
		model.addAttribute("list", coinService.list(userNo));
	}
	
	// 사용자의 구매 내역 보기 요청 처리
	@RequestMapping(value = "/listPay", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void listPayHistory(Model model, Authentication authentication) throws Exception {
		
		log.info("listPayHistory()...");
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		int userNo = member.getUserNo();
		
		model.addAttribute("list", coinService.listPayHistory(userNo)); 
	}
	
	// 코인 부족 메시지를 보여주는 화면을 생성
	@RequestMapping(value = "/notEnoughCoin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void notEnoughCoin(Model model) throws Exception {
		
	}
}
