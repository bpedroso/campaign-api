package com.bpedroso.challenge.usecases;

import static java.time.LocalDate.now;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpedroso.challenge.contracts.controller.Campaign;
import com.bpedroso.challenge.repository.CampaignRepository;

/*
 * O Sistema não deverá retornar campanhas que estão com a data de vigência vencidas;
 */
@Component
public class ListCampaign {
	
	private final Logger log = LoggerFactory.getLogger(ListCampaign.class);
	
	private CampaignRepository campaignRepository;

	@Autowired
	public ListCampaign(CampaignRepository talbeDataCampaignGateway) {
		this.campaignRepository = talbeDataCampaignGateway;
	}

	public List<Campaign> list(final Integer code) {
		final List<Campaign> actualCampaignList = new ArrayList<Campaign>();
		if(code == null) {
			actualCampaignList.addAll(listCampaigns());
		} else {
			actualCampaignList.addAll(findCampaign(code));
		}
		
		if(log.isDebugEnabled()) {
			log.debug("Campaigns found: {}", actualCampaignList.toString());
		}
		
		return actualCampaignList;
	}

	private List<Campaign> listCampaigns() {
		return ofNullable(this.campaignRepository.findByEndDateGreaterThan(now())).orElse(emptyList());
	}

	private List<Campaign> findCampaign(final int code) {
		return singletonList(this.campaignRepository.findByCode(code));
	}
}
