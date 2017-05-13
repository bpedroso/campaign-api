package com.bpedroso.challenge.usecases.events;

import org.springframework.context.ApplicationEvent;

import com.bpedroso.challenge.contracts.controller.Campaign;

public class CampaignChangeEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public CampaignChangeEvent(Campaign source) {
		super(source);
	}
}
