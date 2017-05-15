package com.bpedroso.challenge.repository;

import org.springframework.data.gemfire.repository.GemfireRepository;

import com.bpedroso.challenge.contracts.controller.Team;

public interface TeamRepository extends GemfireRepository<Team, String> {
	
}
