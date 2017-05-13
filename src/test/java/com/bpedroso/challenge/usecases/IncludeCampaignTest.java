package com.bpedroso.challenge.usecases;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import com.bpedroso.challenge.repository.CampaignRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(MockitoJUnitRunner.class)
public class IncludeCampaignTest {

	@Mock
	private CampaignRepository campaignRepository;

	@Mock
	private ApplicationEventPublisher publisher;

	@InjectMocks
	public IncludeCampaign includeCampaign;

	/*
	 * 1 - campanha nova 2 - campanha dentro do range
	 */

	@Test(expected = IOException.class)
	public void updateCampaign_wrongMessage_IOException() throws JsonParseException, JsonMappingException, IOException {
		String campaign = "teste";
		includeCampaign.updateCampaign(campaign);
	}

	@Test(expected = JsonMappingException.class)
	public void updateCampaign_anyFieldWithNullValue_IOException()
			throws JsonParseException, JsonMappingException, IOException {
		String campaign = "{\"code\":0,\"endDate\":\"2017-05-13\",\"idTeam\":0,\"name\":\"string\"}";
		includeCampaign.updateCampaign(campaign);
	}

}
