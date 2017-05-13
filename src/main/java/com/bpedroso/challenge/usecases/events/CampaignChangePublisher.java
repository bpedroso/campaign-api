package com.bpedroso.challenge.usecases.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class CampaignChangePublisher implements ApplicationEventPublisherAware{

	private ApplicationEventPublisher publisher;

	@Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

	public void publish(CampaignChangeEvent event) {
        this.publisher.publishEvent(event);
    }

}
