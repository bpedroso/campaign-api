package com.bpedroso.challenge.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bpedroso.challenge.contracts.controller.Campaign;
import com.bpedroso.challenge.repository.CampaignRepository;

@RunWith(MockitoJUnitRunner.class)
public class ListCampaignTest {
	
	private static final int EXPECTED_CAMPAIGNS_SIZE_WITH_CODE = 1;
	private static final int EXPECTED_CAMPAIGNS_SIZE_WITHOUT_CODE = 2;

	@Mock
	private CampaignRepository campaignRepository;
	
	@InjectMocks
	public ListCampaign listCampaign;
	
	@Test
	public void list_withCode_onlyOneCampaignAndCallfindByCode() {
		doReturn(new Campaign()).when(campaignRepository).findByCode(any(Integer.class));
		
		List<Campaign> actualCampaigns = listCampaign.listByCode(1);
		assertNotNull(actualCampaigns);
		assertEquals(EXPECTED_CAMPAIGNS_SIZE_WITH_CODE, actualCampaigns.size());
		
		verify(campaignRepository, never()).findByEndDateGreaterThan(any(LocalDate.class));
		verify(campaignRepository, only()).findByCode(any(Integer.class));
	}
	
	@Test
	public void list_withoutCode_twoCampaignsAndCallfindByEndDateGreaterThan() {
		doReturn(Arrays.asList(new Campaign(), new Campaign())).when(campaignRepository).findByEndDateGreaterThan(any(LocalDate.class));
		
		List<Campaign> actualCampaigns = listCampaign.listByCode(null);
		assertNotNull(actualCampaigns);
		assertEquals(EXPECTED_CAMPAIGNS_SIZE_WITHOUT_CODE, actualCampaigns.size());
		
		verify(campaignRepository, only()).findByEndDateGreaterThan(any(LocalDate.class));
		verify(campaignRepository, never()).findByCode(any(Integer.class));
	}

}
