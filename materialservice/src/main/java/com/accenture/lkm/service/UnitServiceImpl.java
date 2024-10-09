package com.accenture.lkm.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.dao.UnitDAO;
import com.accenture.lkm.entity.UnitEntity;

@Service
public class UnitServiceImpl implements UnitService{	
	
	@Autowired
	private UnitDAO unitDAO;
	
	public List<UnitBean> getUnitsBasedOnCategoryId(String categoryId) {

		List<UnitEntity> unitEntities = unitDAO.findByCategoryId(categoryId);
		List<UnitBean> unitList = null;
		if(unitEntities != null) {
			unitList = new ArrayList<UnitBean>();
			UnitBean unit = null;
		for (UnitEntity unitEntity : unitEntities) {
			unit = new UnitBean();
			BeanUtils.copyProperties(unitEntity, unit);
			unit.setCategoryId(unitEntity.getMaterialCategoryEntity().getCategoryId());
			unitList.add(unit);
			}
		}
		return unitList;		
	}

	@Override
	public List<UnitBean> getUnits() {

		List<UnitEntity> unitEntities = unitDAO.findAll();
		List<UnitBean> unitList = null;
		if(unitEntities != null) {
			unitList = new ArrayList<UnitBean>();
			UnitBean unit = null;
		for (UnitEntity unitEntity : unitEntities) {
			unit = new UnitBean();
			BeanUtils.copyProperties(unitEntity, unit);
			unit.setCategoryId(unitEntity.getMaterialCategoryEntity().getCategoryId());
			unitList.add(unit);
			}
		}
		return unitList;		
	}
	
}
