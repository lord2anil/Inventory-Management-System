
package com.accenture.lkm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.service.MaterialService;

@RestController
public class MaterialController {

	private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);
	
	
	/*
	 * Autowire the MaterialService object
	 * 
	 * */
	
	@Autowired
	private MaterialService materialService;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "Hello,Welcome to Spring Boot Material Service API!";
	}
	
	
	/* 
	 * Method - getMaterialCategories()
	 * Fetch all the material categories details using MaterialService and store it inside a List
	 * Return a ResponseEntity object passing the list of material categories
	 * 
	 */
		
	@RequestMapping(value = "/material/controller/getMaterialCategories", 
			method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialCategoryBean>> getMaterialCategories() {
				
		List<MaterialCategoryBean> listMaterialCategory = materialService.getMaterialCategories();
		return new ResponseEntity<List<MaterialCategoryBean>>(listMaterialCategory, HttpStatus.OK);
	}

	
	/* 
	 * Method - getMaterialCategoryById()
	 * Fetch a single MaterialCategoryBean using MaterialService object and passing --> categoryId
	 * Return a ResponseEntity object passing the MaterialCategoryBean object
	 * 
	 */
			
	@RequestMapping(value = "/material/controller/getMaterialCategoryById/{categoryId}", 
			method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MaterialCategoryBean> getMaterialCategoryById(@PathVariable String categoryId) {
		MaterialCategoryBean materialCategory = materialService.getMaterialCategoryById(categoryId);
		return new ResponseEntity<MaterialCategoryBean>(materialCategory, HttpStatus.OK);
	}
	
	
}

