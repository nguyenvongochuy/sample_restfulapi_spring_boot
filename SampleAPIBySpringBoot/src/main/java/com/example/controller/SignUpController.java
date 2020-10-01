package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.SignUpRequest;
import com.example.domain.SignUpResponse;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

	
	@RequestMapping(value = "", method = RequestMethod.POST)	
	public @ResponseBody SignUpResponse signup(@RequestBody SignUpRequest requestData) {
		//write data from request
		//TODO
		
		SignUpResponse response = new SignUpResponse();
		
		
		response.setId(1);
		response.setUserEmail(requestData.getEmail());
		return response;
	}
	
}
