package com.example.demo.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long > {

	Optional<User> findByEmail(String email);

	User getByEmail(String email);

	Optional<User> findByOtp(long otp);



}
