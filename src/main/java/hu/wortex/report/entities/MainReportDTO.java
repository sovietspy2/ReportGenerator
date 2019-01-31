package hu.wortex.report.entities;

import lombok.Data;

import java.util.List;

@Data
public class MainReportDTO extends ReportBaseDTO {
    private Long totalListingCount;
    private List<MonthlyReportDTO> monthlyReports;
}
