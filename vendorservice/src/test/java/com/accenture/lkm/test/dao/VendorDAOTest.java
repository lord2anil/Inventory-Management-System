package com.accenture.lkm.test.dao;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.dao.VendorDAO;
import com.accenture.lkm.entity.VendorEntity;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorDAOTest {
 
	private static final Logger logger = LoggerFactory.getLogger(VendorDAOTest.class);
 
	/*
	 * Autowire the VendorDAO object below
	 */
	// Your Code Here
	@Autowired
	VendorDAO vendorDAO;
	/*
	 * Method - notNullVendorDAOTest()
	 * Assert only that VendorDAO object is Not null
	 */
	@Test
	public void notNullVendorDAOTest() {
		// Your Code Here
		assertNotNull(vendorDAO);
	}
 
	/*
	 * Method - findByIdVendorDAOTest()
	 * Using VendorDAO fetch an entity by its ID --> "V001" 
	 * Assert that the entity fetch and it is Not null 
	 * Assert that the name of the vendor entity fetch is equal to --> "Only Vimal"
	 */
 
	@Test
	public void findByIdVendorDAOTest() {
		// Your Code Here
		VendorEntity entity=vendorDAO.findOne("V001");
		assertNotNull(entity);
		assertEquals(entity.getVendorName(),"Only Vimal");
	}
}