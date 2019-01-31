package hu.wortex.report.services;

import hu.wortex.report.entities.MainReportDTO;
import hu.wortex.report.entities.Marketplace;
import hu.wortex.report.entities.MonthlyReportDTO;
import hu.wortex.report.repositories.ListingRepository;
import hu.wortex.report.repositories.MarketplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReportCreator {

    @Autowired
    private Environment env;

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private MarketplaceRepository marketplaceRepository;

    public MainReportDTO getReport() {

        MainReportDTO mainReportDTO = new MainReportDTO();



        Marketplace amazon = marketplaceRepository.findByMarketplaceName("AMAZON");
        Marketplace ebay = marketplaceRepository.findByMarketplaceName("EBAY");


        //Integer totalEbayListingCount =  listingRepository.countAllByMarketPlace(ebay);
        mainReportDTO.setTotalEbayListingCount(listingRepository.countAllByMarketPlace(ebay));
        //Integer totalAmazonListingCount = listingRepository.countAllByMarketPlace(amazon);
        mainReportDTO.setTotalAmazonListingCount(listingRepository.countAllByMarketPlace(amazon));

        //Double totalAmazonListingPrice = listingRepository.findListingPriceSumByMarketplace(amazon.getId());
        mainReportDTO.setTotalAmazonListingPrice(listingRepository.findListingPriceSumByMarketplace(amazon.getId()));
        //Double totalEbayListingPrice = listingRepository.findListingPriceSumByMarketplace(ebay.getId());
        mainReportDTO.setTotalEbayListingPrice(listingRepository.findListingPriceSumByMarketplace(ebay.getId()));


        //Double averageAmazonListingPrice = listingRepository.findAverageCountNumberByMarketPlace(amazon.getId());
        mainReportDTO.setAverageAmazonListingPrice(listingRepository.findAverageCountNumberByMarketPlace(amazon.getId()));
        //Double averageEbayListingPrice = listingRepository.findAverageCountNumberByMarketPlace(ebay.getId());
        mainReportDTO.setAverageEbayListingPrice(listingRepository.findAverageCountNumberByMarketPlace(ebay.getId()));

        //Long totalListingCount = listingRepository.count();
        mainReportDTO.setTotalListingCount(listingRepository.count());

        //String bestListerEmailAddress = listingRepository.findBestOwnerEmailAddress();
        mainReportDTO.setBestListerEmailAddress(listingRepository.findBestOwnerEmailAddress());

        //String monthStr = month < 10 ? "0"+month : String.valueOf(month);
        List<MonthlyReportDTO> asd = Stream.of(Month.values()).map(element -> {
                MonthlyReportDTO monthlyReportDTO = new MonthlyReportDTO();
                monthlyReportDTO.setMonth(element.toString());
                return monthlyReportDTO;
        }).collect(Collectors.toList());

        mainReportDTO.setMonthlyReports(asd);

        return mainReportDTO;
    }

}
