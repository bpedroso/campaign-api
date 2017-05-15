package com.bpedroso.challenge.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpedroso.challenge.contracts.controller.User;
import com.bpedroso.challenge.repository.UserRepository;

@Component
public class IncludeUser {

	private final Logger log = LoggerFactory.getLogger(IncludeUser.class);

	private UserRepository userRepository;

	@Autowired
	public IncludeUser(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void update(User payLoad) {
		this.log.info("User saved:  {}", payLoad);
		this.userRepository.save(payLoad);
	}

}
