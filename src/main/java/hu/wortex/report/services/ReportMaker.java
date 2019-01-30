package hu.wortex.report.services;

import hu.wortex.report.entities.Listing;
import hu.wortex.report.repositories.ListingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.xml.validation.ValidatorHandler;
import java.util.List;

@Component
@Order(5)
public class ReportMaker implements CommandLineRunner  {
    @Override
    public void run(String... args) throws Exception {

    }
}
