package com.bpedroso.challenge.repository;

import org.springframework.data.gemfire.repository.GemfireRepository;

import com.bpedroso.challenge.contracts.controller.User;

public interface UserRepository extends GemfireRepository<User, String> {
	
	User findByEmail(String email);

}
