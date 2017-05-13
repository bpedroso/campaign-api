package com.bpedroso.challenge.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpedroso.challenge.contracts.controller.Campaign;
import com.bpedroso.challenge.repository.CampaignRepository;

@Component
public class DeleteCampaign {
	
	private final Logger log = LoggerFactory.getLogger(DeleteCampaign.class);
	
	private CampaignRepository campaignRepository;

	@Autowired
	public DeleteCampaign(CampaignRepository talbeDataCampaignGateway) {
		this.campaignRepository = talbeDataCampaignGateway;
	}
	
	public void delete(Integer code) {
		log.info("Deleting campaign {}", code);
		this.campaignRepository.delete(new Campaign(code));
	}

}
