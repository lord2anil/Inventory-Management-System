package com.accenture.lkm.test.service;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
 
import java.util.List;
 
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.service.VendorService;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorServiceTest {
 
	private static final Logger logger = LoggerFactory.getLogger(VendorServiceTest.class);
 
	/*
	 * Autowire the VendorService object below
	 * 
	 */
	// Your Code Here
	@Autowired
	VendorService vendorService;
 
	/*
	 * Method - notNullVendorServiceTest()
	 * Assert only that VendorService object is Not null
	 * 
	 */
 
	@Test
	public void notNullVendorServiceTest() {
		// Your Code Here
		assertNotNull(vendorService);
	}
 
	/*
	 * Method - notNullGetVendorDetailsTest()
	 * Assert only that VendorService getVendorDetails is not returning a null value
	 * 
	 */
 
	@Test
	public void notNullGetVendorDetailsTest() {
		// Your Code Here
		List<VendorBean>lst=vendorService.getVendorDetails();
		assertNotNull(lst);
	}
 
	/*
	 * Method - countGetVendorDetailsTest()
	 * Assert only that VendorService getVendorDetails list size matches to 5
	 * 
	 */
 
	@Test
	public void countGetVendorDetailsTest() {
		// Your Code Here
		List<VendorBean>lst=vendorService.getVendorDetails();
		int sz=lst.size();
		assertEquals(sz,5);
	}
 
	/*
	 * Method - recordGetVendorDetailsTest()
	 * Assert only that VendorService getVendorDetails first bean name matches --> "Only Vimal"
	 * 
	 */
	@Test
	public void recordGetVendorDetailsTest() {
		List<VendorBean>lst=vendorService.getVendorDetails();
	    String name=lst.get(0).getVendorName();
	    assertEquals(name,"Only Vimal");
	}
 
}