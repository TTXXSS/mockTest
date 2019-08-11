package sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.*;

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
	public void testGenerateReport() {
		
		SalesApp salesApp = new SalesApp();
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



//	@Test
//	public void testGetSalesReportData_givenSaleAfterToday_thenReturnNull(){
//		Calendar c = Calendar.getInstance();
//		Sales sales = mock(Sales.class);
//		when(sales.getEffectiveFrom()).thenReturn(new Date());
//	}
}
