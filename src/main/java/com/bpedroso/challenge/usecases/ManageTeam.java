package com.bpedroso.challenge.usecases;

import static com.google.common.collect.FluentIterable.from;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpedroso.challenge.contracts.controller.Team;
import com.bpedroso.challenge.repository.TeamRepository;

@Component
public class ManageTeam {

	private final Logger log = LoggerFactory.getLogger(ManageTeam.class);

	private TeamRepository teamRepository;

	@Autowired
	public ManageTeam(TeamRepository userRepository) {
		this.teamRepository = userRepository;
	}
	
	public void update(Team payLoad) {
		this.log.info("Team saved:  {}", payLoad);
		this.teamRepository.save(payLoad);
	}

	public List<Team> findAll() {
		return from(this.teamRepository.findAll()).toList();
	}

}
