package com.bpedroso.challenge.usecases;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bpedroso.challenge.contracts.controller.Campaign;
import com.bpedroso.challenge.repository.CampaignRepository;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCampaignTest {
	
	@Mock
	private CampaignRepository campaignRepository;
	
	@InjectMocks
	public DeleteCampaign deleteCampaign;

	@Test
	public void delete() {
		doNothing().when(campaignRepository).delete(any(Campaign.class));
		deleteCampaign.delete(1);
		verify(campaignRepository, only()).delete(any(Campaign.class));
	}

}
