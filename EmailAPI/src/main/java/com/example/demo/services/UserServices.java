package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Userdto;

public interface UserServices {

	ResponseEntity<String> save(Userdto userdto);


ResponseEntity<String> otpsend(String email);


ResponseEntity<String> verify(Userdto userdto, long otp);





	

}
 