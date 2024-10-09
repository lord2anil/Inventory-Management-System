package com.accenture.lkm.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.dao.MaterialCategoryDAO;
import com.accenture.lkm.entity.MaterialCategoryEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialCategoryDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(MaterialCategoryDAOTest.class);
	
	@Autowired
	MaterialCategoryDAO materialCategoryDAO;
	/*
	 * Autowire the MaterialCategoryDAO object below
	 */
	
	// Your Code Here
	
	
	/*
	 * Method - notNullMaterialCategoryDAOTest()
	 * Assert only that MaterialCategoryDAO object is Not null
	 */	
	
	@Test
	public void notNullMaterialCategoryDAOTest() {
		assertNotNull("Null",materialCategoryDAO);
	
	}
	
	
	/*
	 * Method - findByIdMaterialCategoryTest()
	 * Using MaterialCategoryDAO fetch an entity by its ID --> "C001" 
	 * Assert that the entity fetch and it is Not null 
	 * Assert that the name of the material category entity fetch is equal to --> "Thread"
	 */
	
	@Test
	public void findByIdMaterialCategoryTest() {
		// Your Code Here
		MaterialCategoryEntity m1= materialCategoryDAO.findOne("C001");
		assertNotNull("Null",m1);
		assertEquals("Thread", m1.getCategoryName());

	}

//	/*
//	 * Method - findAllMaterialCategoryTest()
//	 * Using MaterialCategoryDAO to fetch all the entities 
//	 * Assert that the list is Not null 
//	 * Assert that the count of entities matches to --> 3
//	 */
			
	@Test
	public void findAllMaterialCategoryTest() {
		List<MaterialCategoryEntity> m1=materialCategoryDAO.findAll();
		assertNotNull("Null",m1);
		assertEquals(3,m1.size());

	}
	
}
