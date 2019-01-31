package hu.wortex.report.services;

import hu.wortex.report.entities.MainReportDTO;
import hu.wortex.report.entities.Marketplace;
import hu.wortex.report.entities.MonthlyReportDTO;
import hu.wortex.report.repositories.ListingRepository;
import hu.wortex.report.repositories.MarketplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReportCreator {

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


        List<MonthlyReportDTO> asd = Stream.of(Month.values()).map(month -> {
                MonthlyReportDTO monthlyReportDTO = new MonthlyReportDTO();

                monthlyReportDTO.setTotalAmazonListingCount(listingRepository.countAllByMarketPlaceAndUploadTimeMonth(amazon.getId(),month.getValue()));
                monthlyReportDTO.setTotalEbayListingCount(listingRepository.countAllByMarketPlaceAndUploadTimeMonth(ebay.getId(),month.getValue()));
                monthlyReportDTO.setBestListerEmailAddress(listingRepository.findBestOwnerEmailAddressByMonth(month.getValue()));
                monthlyReportDTO.setAverageAmazonListingPrice(listingRepository.findAverageCountNumberByMarketPlaceByMonth(amazon.getId(), month.getValue()));
                monthlyReportDTO.setAverageEbayListingPrice(listingRepository.findAverageCountNumberByMarketPlaceByMonth(ebay.getId(), month.getValue()));
                monthlyReportDTO.setTotalEbayListingPrice(listingRepository.findListingPriceSumByMarketplaceByMonth(ebay.getId(), month.getValue()));
                monthlyReportDTO.setTotalAmazonListingPrice(listingRepository.findListingPriceSumByMarketplaceByMonth(amazon.getId(), month.getValue()));


                monthlyReportDTO.setMonth(month.toString());
                return monthlyReportDTO;
        }).collect(Collectors.toList());

        mainReportDTO.setMonthlyReports(asd);

        return mainReportDTO;
    }

}
