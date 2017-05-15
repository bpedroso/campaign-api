package com.bpedroso.challenge.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.bpedroso.challenge.contracts.controller.Campaign;
import com.gemstone.gemfire.cache.GemFireCache;

@Configuration
@EnableGemfireRepositories(basePackages = "com.bpedroso.challenge.repository")
public class GemFireConfig {

	@Value("${api.region.name:TeamPartner}")
	private String regionName;

    @Bean
    Properties gemfireProperties() {
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name", "CampaignApplication");
        gemfireProperties.setProperty("mcast-port", "0");
        gemfireProperties.setProperty("log-level", "config");
        return gemfireProperties;
    }
	
	@Bean
	CacheFactoryBean gemfireCache() {
		CacheFactoryBean gemfireCache = new CacheFactoryBean();
		gemfireCache.setClose(true);
		gemfireCache.setProperties(gemfireProperties());
		return gemfireCache;
	}

	@Bean
	LocalRegionFactoryBean<String, Campaign> teamPartnerRegion(final GemFireCache cache) {
		LocalRegionFactoryBean<String, Campaign> region = new LocalRegionFactoryBean<>();
		region.setCache(cache);
		region.setClose(false);
		region.setName(this.regionName);
		region.setPersistent(false);
		return region;
	}

}
