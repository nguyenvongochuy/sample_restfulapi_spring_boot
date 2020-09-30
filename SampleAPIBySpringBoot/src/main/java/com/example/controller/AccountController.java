package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Account;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/account")
public class AccountController {

	private static final String ACC_NO = "Acc12345";
	private static final String ACC_NAME = "John Smith";
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody Account getAccountInfo() {
		
		Account account = new Account();
		account.setAccountNo(ACC_NO);
		account.setAccountName(ACC_NAME);
		account.setBalance(generateRandomBalance());
		

		return account;
	}
	
	private double generateRandomBalance() {
		int min = 1000;
	    int max = 10000;
	    double randomValue = Math.random() * (max - min + 1) + min;
	    return Math.round(randomValue * 100.0) / 100.0;
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public @ResponseBody Account updateAccountInfo(@RequestBody Account requestData) {
		
		Account account = new Account();
		account.setAccountNo(ACC_NO);
		account.setAccountName(requestData.getAccountName());
		account.setBalance(requestData.getBalance());
		

		return account;
	}
	
	
}
