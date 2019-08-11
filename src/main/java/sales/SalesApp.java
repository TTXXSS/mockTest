package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {
    SalesDao salesDao;
	EcmService ecmService;
	public void generateSalesActivityReport() {

//		SalesDao salesDao = new SalesDao();
//		SalesReportDao salesReportDao = new SalesReportDao();
//		List<String> headers = null;
//
//		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();


//		Date today = new Date();
//		if (today.after(sales.getEffectiveTo())
//				|| today.before(sales.getEffectiveFrom())){
//			return;
//		}
//
//		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);

//		for (SalesReportData data : reportDataList) {
//			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
//				if (data.isConfidential()) {
//					if (isSupervisor) {
//						filteredReportDataList.add(data);
//					}
//				}else {
//					filteredReportDataList.add(data);
//				}
//			}
//		}

//		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
//		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
//			tempList.add(reportDataList.get(i));
//		}
//		filteredReportDataList = tempList;

//		if (isNatTrade) {
//			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
//		} else {
//			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
//		}
//
//		SalesActivityReport report = this.generateReport(headers, reportDataList);

		
	}
    public Sales getSalesBySalesId(String salesId){
		if (salesId == null) {
			return null;
		}
		Sales sales = salesDao.getSalesBySalesId(salesId);
		return sales;
	}

	public boolean isBetweenEffectiveDay(Sales sales,SalesReportDao salesReportDao){
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

	private SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
