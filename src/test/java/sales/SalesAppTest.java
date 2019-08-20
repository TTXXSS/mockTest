package sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class SalesAppTest {
	@Mock
	SalesDao salesDao;
	@InjectMocks
    SalesApp salesApp = new SalesApp();
	@Mock
	EcmService ecmService;
	@InjectMocks
	SalesApp injectSalesApp = new SalesApp();

	@Test
	public void testGenerateReport_givenRightSalesId_thenGenerateReport() {
		SalesApp salesApp = spy(new SalesApp());
		SalesReportDao salesReportDao = mock(SalesReportDao.class);
		Sales sales = new Sales();
		List<String> header = new ArrayList<>();
        List<SalesReportData> reportDataList = new ArrayList<>();
        when(salesDao.getReportData(sales)).thenReturn(reportDataList);
        doReturn(sales).when(salesApp).getSalesBySalesId(anyString());
        doReturn(header).when(salesApp).getHeader(anyBoolean());
        doReturn(true).when(salesApp).isBetweenEffectiveDay(sales);
		doReturn(new SalesActivityReport()).when(salesApp).generateReport(header,reportDataList);

		salesApp.generateSalesActivityReport("111",true,salesReportDao);
		verify(salesApp,times(1)).generateReport(header,reportDataList);
		//SalesApp salesApp = new SalesApp();
		//salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
		
	}

	@Test
	public void should_return_null_sales_when_call_get_sales_by_sales_id_with_given_a_null_sales_id_(){
		//Sales sales = new Sales();
		when(salesDao.getSalesBySalesId(anyString())).thenReturn(null);
		Sales sales1 = salesApp.getSalesBySalesId("110");
		assertSame(sales1,null);
	}
	@Test
	public void should_return_sales_when_call_get_sales_by_sales_id_with_given_a_no_null_sales_id(){

		Sales sales = new Sales();
		when(salesDao.getSalesBySalesId(anyString())).thenReturn(sales);
		Sales sales1 = salesApp.getSalesBySalesId("110");

		assertSame(sales1,sales);
	}

	@Test
	public void testUpLoad_givenReport_thenUploadDocument(){
		SalesActivityReport report = mock(SalesActivityReport.class);
		when(report.toXml()).thenReturn("100");

		injectSalesApp.upLoad(report);
		verify(ecmService,times(1)).uploadDocument(report.toXml());
	}

	@Test
	public void should_return_headers_when_call_get_header_given_true(){
		SalesApp spySalesApp = spy(new SalesApp());
		List<String> headers = new ArrayList<>();
        headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");

		List<String> getHeaders = spySalesApp.getHeader(true);
	    assertEquals(getHeaders,headers);
	}

    @Test
    public void should_return_headers_when_call_get_header_given_false(){
        SalesApp spySalesApp = spy(new SalesApp());
        List<String> headers = new ArrayList<>();
        headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");

        List<String> getHeaders = spySalesApp.getHeader(false);
        assertEquals(getHeaders,headers);
    }

    @Test
    public void testGetSalesReportData_givenNotBetweenEffectiveDay_thenReturnFalse(){
        Calendar beforeToday = Calendar.getInstance();
        beforeToday.set(Calendar.DATE,+1);
        Calendar afterToday = Calendar.getInstance();
        afterToday.set(Calendar.DATE,-1);
       // System.out.println(calendar1.getTime());
        Sales sales = mock(Sales.class);
        SalesReportDao salesReportDao = mock(SalesReportDao.class);
        when(sales.getEffectiveFrom()).thenReturn(beforeToday.getTime());
        when(sales.getEffectiveTo()).thenReturn(afterToday.getTime());

        SalesApp salesApp = new SalesApp();
        boolean isEffectiveDay = salesApp.isBetweenEffectiveDay(sales);
        assertEquals(isEffectiveDay,false);
    }

    @Test
    public void testGetSalesReportData_givenBetweenEffectiveDay_thenReturnTrue(){
        Calendar beforeToday = Calendar.getInstance();
        beforeToday.set(Calendar.DAY_OF_MONTH,-1);

        Calendar afterToday = Calendar.getInstance();
        afterToday.set(Calendar.DAY_OF_MONTH,30);

        System.out.println(beforeToday.getTime());
        System.out.println(new Date());
        System.out.println(afterToday.getTime());

        // System.out.println(calendar1.getTime());
        Sales sales = mock(Sales.class);

        when(sales.getEffectiveFrom()).thenReturn(beforeToday.getTime());
        when(sales.getEffectiveTo()).thenReturn(afterToday.getTime());

        SalesApp salesApp = new SalesApp();
        boolean isEffectiveDay = salesApp.isBetweenEffectiveDay(sales);
        assertEquals(isEffectiveDay,true);
    }

}
