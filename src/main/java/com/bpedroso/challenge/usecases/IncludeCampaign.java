package com.bpedroso.challenge.usecases;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpedroso.challenge.contracts.controller.Campaign;
import com.bpedroso.challenge.repository.CampaignRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * No cadastramento de uma nova campanha, deve-se verificar se já existe uma campanha ativa para aquele período (vigência), 
 * caso exista uma campanha ou N campanhas associadas naquele período, o sistema deverá somar um dia no término da vigência 
 * de cada campanha já existente. Caso a data final da vigência seja igual a outra campanha, deverá ser acrescido um dia a mais
 * de forma que as campanhas não tenham a mesma data de término de vigência. 
 * Por fim, efetuar o cadastramento da nova campanha.
 */
@Component
public class IncludeCampaign {
	
	private final Logger log = LoggerFactory.getLogger(IncludeCampaign.class);
	
	private CampaignRepository campaignRepository;

	@Autowired
	public IncludeCampaign(CampaignRepository talbeDataCampaignGateway) {
		this.campaignRepository = talbeDataCampaignGateway;
	}

	public void updateCampaign(String campaign) throws JsonParseException, JsonMappingException, IOException {
		this.log.info("Updating campaigns: {}", campaign);
		Campaign actualCampaign = new ObjectMapper().readValue(campaign, Campaign.class);

		final Campaign storedCampaign = this.campaignRepository.findByCode(actualCampaign.getCode());
		if(storedCampaign==null) {
			this.campaignRepository.save(actualCampaign);
		} else {
			this.campaignRepository.save(actualCampaign);
		}
	}

}
