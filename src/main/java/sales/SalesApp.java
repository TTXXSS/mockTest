package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {
    SalesDao salesDao;
	EcmService ecmService;
	public void generateSalesActivityReport(String salesId,boolean isNatTrade,SalesReportDao salesReportDao) {

		List<SalesReportData> reportDataList = null;
		Sales sales = getSalesBySalesId(salesId);
        boolean isBetweenEffectiveDay = isBetweenEffectiveDay(sales);
		if(isBetweenEffectiveDay)
		reportDataList = salesReportDao.getReportData(sales);
		else
			return;
		List<String> header = getHeader(isNatTrade);
		generateReport(header,reportDataList);



	}
    public Sales getSalesBySalesId(String salesId){
		if (salesId == null) {
			return null;
		}
		Sales sales = salesDao.getSalesBySalesId(salesId);
		return sales;
	}

	public boolean isBetweenEffectiveDay(Sales sales){
		//SalesReportDao salesReportDao = new SalesReportDao();
		Date today = new Date();
		if (today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom())){
			return false;
		}

		return true;
	}


	public List<String> getHeader(boolean isNatTrade){
		List<String> headers = null;
		if (isNatTrade) {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		} else {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		}
		return headers;
	}

	public void upLoad(SalesActivityReport report){
		ecmService.uploadDocument(report.toXml());
	}

	public SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
