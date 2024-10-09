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

import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.service.UnitService;


@RestController
public class UnitController {
	
	@Autowired
	private UnitService unitService;
	
	@RequestMapping(value="unit/controller/getUnitDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UnitBean>> getUnitDetails(){
		List<UnitBean> listUnit = unitService.getUnits();
		return new ResponseEntity<List<UnitBean>>(listUnit, HttpStatus.OK);
	}
	
	@RequestMapping(value="unit/controller/getUnitsByCategoryId/{categoryId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UnitBean>> getUnitsByCategoryId(@PathVariable("categoryId") String categoryId){
		List <UnitBean> listUnit = unitService.getUnitsBasedOnCategoryId(categoryId);
		return new ResponseEntity<List<UnitBean>>(listUnit, HttpStatus.OK);
	}	

}
