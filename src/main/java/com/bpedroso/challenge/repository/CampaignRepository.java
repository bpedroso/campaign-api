package com.bpedroso.challenge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.gemfire.repository.GemfireRepository;

import com.bpedroso.challenge.contracts.controller.Campaign;

public interface CampaignRepository extends GemfireRepository<Campaign, String> {

	Campaign findByCode(int code);
	
	List<Campaign> findByIdTeam(int idTeam);
	
	List<Campaign> findAll();
	
	List<Campaign> findByEndDateGreaterThan(LocalDate endDate);
	
	List<Campaign> findByBeginDateGreaterThanEqualOrEndDateLessThanEqual(LocalDate beginDate, LocalDate endDate);

}
