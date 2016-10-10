package com.eren.parkinghouse;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.domain.InvoiceDetail;
import com.eren.parkinghouse.domain.Parking;
import com.eren.parkinghouse.domain.ParkingHouse;
import com.eren.parkinghouse.enums.CustomerType;
import com.eren.parkinghouse.service.*;
import com.eren.parkinghouse.util.TimeUtil;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoicingApplicationTests extends TestCase {

	@Autowired
	private ParkingService parkingService;
	@Autowired
	private ParkingHouseService parkingHouseService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private InvoiceDetailService invoiceDetailService;
	@Autowired
	private InvoiceService invoiceService;

	public void prepareTest(){
		ParkingHouse ph = new ParkingHouse();
		ph.setName("Test Parking House");
		parkingHouseService.save(ph);
		assertNotNull(ph.getId());

		Customer c = new Customer();
		c.setName("regular");
		c.setLastName("regular");
		c.setEmail("reg@reg.com");
		c.setCustomerType(CustomerType.REGULAR);
		customerService.save(c);
		assertNotNull(c.getId());

		Customer c2 = new Customer();
		c2.setName("pre");
		c2.setLastName("pre");
		c2.setEmail("pre@pre.com");
		c2.setCustomerType(CustomerType.PREMIUM);
		customerService.save(c);
		assertNotNull(c.getId());
	}


	@Test
	public void parkingServiceTest() {
		prepareTest();
		Calendar startDate = Calendar.getInstance(), endDate = Calendar.getInstance();

		startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH)-2);
		startDate.set(Calendar.HOUR_OF_DAY,8);
		startDate.set(Calendar.MINUTE,12);
		startDate.set(Calendar.SECOND, 0);

		endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH)-2);
		endDate.set(Calendar.HOUR_OF_DAY, 10);
		endDate.set(Calendar.MINUTE, 45);
		endDate.set(Calendar.SECOND, 0);

		Parking p = new Parking();
		p.setParkingHouse(parkingHouseService.findById(1L));
		p.setParkingStartDate(startDate.getTime());
		p.setParkingEndDate(endDate.getTime());
		p.setCustomer(customerService.findById(1L));
		parkingService.save(p);

		assertNotNull(p.getId());

		assertTrue(p.getHalfHourCountAm()==6);

		InvoiceDetail detail = (InvoiceDetail)((List)invoiceDetailService.findByInvoiceCustomerId(1L)).get(0);
		assertEquals(detail.getParkingFee(), new BigDecimal(9).setScale(2));

		assertEquals(invoiceService.generateInvoiceOfCustomer(customerService.findById(1L)).size(), 1);


	}



	@Test
	public void testTimeUtilCalculateHalfHourTest(){
		Calendar startDate = Calendar.getInstance(), endDate = Calendar.getInstance();

		startDate.set(Calendar.HOUR_OF_DAY,8);
		startDate.set(Calendar.MINUTE,12);
		startDate.set(Calendar.SECOND, 0);

		endDate.set(Calendar.HOUR_OF_DAY, 10);
		endDate.set(Calendar.MINUTE, 45);
		endDate.set(Calendar.SECOND, 0);
		assertTrue(TimeUtil.CalculateHalfHours(startDate, endDate) == 6);

		assertTrue(TimeUtil.CalculateAMHalfHours(startDate, endDate) == 6);



		startDate.set(Calendar.HOUR_OF_DAY, 19);
		startDate.set(Calendar.MINUTE,40);
		startDate.set(Calendar.SECOND, 0);

		endDate.set(Calendar.HOUR_OF_DAY, 20);
		endDate.set(Calendar.MINUTE, 35);
		endDate.set(Calendar.SECOND, 0);
		assertTrue(TimeUtil.CalculateHalfHours(startDate, endDate) == 2);
		assertTrue(TimeUtil.CalculatePMHalfHours(startDate, endDate) == 2);



		startDate.set(Calendar.HOUR_OF_DAY, 7);
		startDate.set(Calendar.MINUTE,2);
		startDate.set(Calendar.SECOND, 0);

		endDate.set(Calendar.HOUR_OF_DAY, 11);
		endDate.set(Calendar.MINUTE, 56);
		endDate.set(Calendar.SECOND, 0);
		assertTrue(TimeUtil.CalculateHalfHours(startDate, endDate) == 10);
		assertTrue(TimeUtil.CalculateAMHalfHours(startDate, endDate) == 10);


		startDate.set(Calendar.HOUR_OF_DAY, 22);
		startDate.set(Calendar.MINUTE,10);
		startDate.set(Calendar.SECOND, 0);

		endDate.set(Calendar.HOUR_OF_DAY, 22);
		endDate.set(Calendar.MINUTE, 35);
		endDate.set(Calendar.SECOND, 0);
		assertTrue(TimeUtil.CalculateHalfHours(startDate, endDate) == 1);
		assertTrue(TimeUtil.CalculatePMHalfHours(startDate, endDate) == 1);



		startDate.set(Calendar.HOUR_OF_DAY, 19);
		startDate.set(Calendar.MINUTE,40);
		startDate.set(Calendar.SECOND, 0);

		endDate.set(Calendar.HOUR_OF_DAY, 20);
		endDate.set(Calendar.MINUTE, 35);
		endDate.set(Calendar.SECOND, 0);
		assertTrue(TimeUtil.CalculateHalfHours(startDate, endDate) == 2);
		assertTrue(TimeUtil.CalculatePMHalfHours(startDate, endDate) == 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTimeUtilCalculateHalfHourIllegalArgumentException() {
		Calendar startDate = Calendar.getInstance(), endDate = Calendar.getInstance();
		startDate.set(Calendar.HOUR_OF_DAY, 19);
		startDate.set(Calendar.MINUTE,40);
		startDate.set(Calendar.SECOND, 0);

		endDate.set(Calendar.HOUR_OF_DAY, 20);
		endDate.set(Calendar.MINUTE, 35);
		endDate.set(Calendar.SECOND, 0);
		TimeUtil.CalculateHalfHours(endDate, startDate);
	}


}
