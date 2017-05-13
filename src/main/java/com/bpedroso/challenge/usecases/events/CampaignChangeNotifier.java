package com.bpedroso.challenge.usecases.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CampaignChangeNotifier implements ApplicationListener<CampaignChangeEvent> {

	private final Logger log = LoggerFactory.getLogger(CampaignChangeNotifier.class);
	
	@Override
	public void onApplicationEvent(CampaignChangeEvent event) {
		// TODO Gerar streaming de campanhas alteradas
		log.info("STREAMING CHANGED CAMPAIGNS!\n{}", event.getSource());
		
	}

}
