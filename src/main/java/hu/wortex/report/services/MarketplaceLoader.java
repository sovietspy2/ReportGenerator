package hu.wortex.report.services;

import hu.wortex.report.entities.Marketplace;
import hu.wortex.report.repositories.MarketplaceRepository;
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

    @Override
    public void run(String... args) throws Exception {

        marketplaceRepository.deleteAll();



        ResponseEntity<List<Marketplace>> response  =
                restTemplate.exchange("https://my.api.mockaroo.com/marketplace?key=63304c70",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Marketplace>>() {
                        });
        List<Marketplace> marketplaces = response.getBody();


        marketplaceRepository.saveAll(marketplaces);

    }
}
