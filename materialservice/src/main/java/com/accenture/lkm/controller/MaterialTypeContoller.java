package com.accenture.lkm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.service.MaterialTypeService;

@RestController
public class MaterialTypeContoller {
	
	@Autowired
	private MaterialTypeService materialTypeService;
	
	@RequestMapping(value="type/controller/getTypeDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialTypeBean>> getTypeDetails(){
		List <MaterialTypeBean> listUnit = materialTypeService.getMaterialTypes();
		return new ResponseEntity<List<MaterialTypeBean>>(listUnit, HttpStatus.OK);
	}
	
	@RequestMapping(value="type/controller/getTypeDetailsByCategoryId/{categoryId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialTypeBean>> getTypesBasedOnCategoryId(@PathVariable("categoryId") String categoryId){
		List <MaterialTypeBean> listUnit = materialTypeService.getMaterialTypesBasedOnCategoryId(categoryId);
		return new ResponseEntity<List<MaterialTypeBean>>(listUnit, HttpStatus.OK);
	}

}
