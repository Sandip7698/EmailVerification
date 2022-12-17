package com.example.demo.ServiceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.Userdto;
import com.example.demo.model.User;
import com.example.demo.services.UserServices;

import net.bytebuddy.asm.Advice.Return;
@Service
public class UserServiceImpl implements UserServices {
	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<String> save(Userdto userdto) {
		User user = new User();
		user.setEmail(userdto.getEmail());
		user.setMobileNo(userdto.getMobileNo());
		userRepository.save(user);
		return new ResponseEntity<>("Saved SucessFully......", HttpStatus.OK);
	}

	
	
	@Value("${spring.mail.username}")
	private String send;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public ResponseEntity<String> otpsend(String email) {
		Optional<User> user=userRepository.findByEmail(email);
		if(user.isPresent()) {
		int max = 10000000;
		int min = 20000000;
		Long a = (long) (Math.random() * (max - min + 1) + min);
		try {
			User user2=userRepository.getByEmail(email);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setSubject("Verification OTP");
			simpleMailMessage.setText("Your OTP is" + a);
			simpleMailMessage.setFrom(send);
			simpleMailMessage.setTo(email);
			javaMailSender.send(simpleMailMessage);
			user2.setOtp(a);
			user2.setDate(new Date());
			userRepository.save(user2);
			return new ResponseEntity<>("Email Send SuccessFully...", HttpStatus.OK);
		} catch (MailException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Email not Send SuccessFully...", HttpStatus.BAD_REQUEST);
	}
		return new ResponseEntity<>("User not Found ",HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<String> verify(Userdto userdto, long otp) {
		Optional<User> email=userRepository.findByOtp(userdto.getOtp());
		long currentTimeInMillis = System.currentTimeMillis();
		long expire=email.get().getDate().getTime()+1 * 60 * 1000;
		System.out.println(currentTimeInMillis);
		if(currentTimeInMillis<=expire) {
		
		return new ResponseEntity<>("otp is verfied Sucessfully",HttpStatus.ACCEPTED);
	}else {
		return new ResponseEntity<>("otp is Invalid",HttpStatus.BAD_REQUEST);
	}
	}


	
	}


