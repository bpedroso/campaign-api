package com.bpedroso.challenge.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpedroso.challenge.contracts.controller.User;
import com.bpedroso.challenge.repository.UserRepository;

@Component
public class ListUser {

	private final Logger log = LoggerFactory.getLogger(ListUser.class);

	private UserRepository userRepository;

	@Autowired
	public ListUser(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User listByEmail(final String email) {
		if (log.isDebugEnabled()) {
			log.debug("User found: {}", email);
		}
		return this.userRepository.findByEmail(email);
	}
	
}
