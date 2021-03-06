package hu.wortex.report.services;

import hu.wortex.report.config.Config;
import hu.wortex.report.entities.Marketplace;
import hu.wortex.report.repositories.MarketplaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Order(3)
public class MarketplaceLoader implements CommandLineRunner {

    @Autowired
    private MarketplaceRepository marketplaceRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Config config;

    private static final Logger log = LoggerFactory.getLogger(MarketplaceLoader.class);

    @Override
    public void run(String... args) {

        log.info("Marketplace loader begin");

        ResponseEntity<List<Marketplace>> response =
                restTemplate.exchange(config.getMarketplaceApiUrl(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Marketplace>>() {
                        });
        List<Marketplace> marketplaces = response.getBody();

        log.info("data fetched from url");

        marketplaceRepository.saveAll(marketplaces);

        log.info("saving all records to MARKETPLACE is done");

    }
}
