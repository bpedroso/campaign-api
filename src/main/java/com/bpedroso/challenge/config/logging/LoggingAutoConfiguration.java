package com.bpedroso.challenge.config.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bpedroso.challenge.config.logging.mdc.MDCFilter;

@Configuration
public class LoggingAutoConfiguration {

	@Bean
	public MDCFilter mdcFilter() {
		return new MDCFilter();
	}
}
