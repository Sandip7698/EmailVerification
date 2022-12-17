package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Userdto;
import com.example.demo.services.UserServices;

@RestController
public class UserController {
	@Autowired
    UserServices userServices;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Userdto userdto){
		return userServices.save(userdto);
	}
	
	@PostMapping("/otpemail")
	public ResponseEntity<String> emailotp(String email){
		return userServices.otpsend(email);
		
	}
	@PostMapping("/verifyotp")
	public ResponseEntity<String> verify(@RequestBody Userdto userdto,long otp){
		return userServices.verify(userdto,otp);
	}

}
