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

    private static final String EBAY = "EBAY";
    private static final String AMAZON = "AMAZON";

    public MainReportDTO getReport() {

        MainReportDTO mainReportDTO = new MainReportDTO();

        Marketplace amazon = marketplaceRepository.findByMarketplaceName(EBAY);
        Marketplace ebay = marketplaceRepository.findByMarketplaceName(AMAZON);

        mainReportDTO.setTotalEbayListingCount(listingRepository.countAllByMarketPlace(ebay));
        mainReportDTO.setTotalAmazonListingCount(listingRepository.countAllByMarketPlace(amazon));

        mainReportDTO.setTotalAmazonListingPrice(listingRepository.findListingPriceSumByMarketplace(amazon.getId()));
        mainReportDTO.setTotalEbayListingPrice(listingRepository.findListingPriceSumByMarketplace(ebay.getId()));

        mainReportDTO.setAverageAmazonListingPrice(listingRepository.findAverageCountNumberByMarketPlace(amazon.getId()));
        mainReportDTO.setAverageEbayListingPrice(listingRepository.findAverageCountNumberByMarketPlace(ebay.getId()));

        mainReportDTO.setTotalListingCount(listingRepository.count());

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
