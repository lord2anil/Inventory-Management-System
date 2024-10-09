package com.accenture.lkm.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.dao.MaterialTypeDAO;
import com.accenture.lkm.entity.MaterialTypeEntity;
@Service
public class MaterialTypeServiceImpl implements MaterialTypeService{
	
	@Autowired
	private MaterialTypeDAO materialTypeDAO;

	@Override
	public List<MaterialTypeBean> getMaterialTypesBasedOnCategoryId(String categoryId) {

		List<MaterialTypeEntity> materialTypeEntities = materialTypeDAO.findByCategoryId(categoryId);

		List<MaterialTypeBean> materialTypeList = null;
		if(materialTypeEntities != null) {
			materialTypeList = new ArrayList<MaterialTypeBean>();
			MaterialTypeBean materialTypeBean = null;
		for (MaterialTypeEntity materialTypeEntity : materialTypeEntities) {
			materialTypeBean = new MaterialTypeBean();
			BeanUtils.copyProperties(materialTypeEntity, materialTypeBean);
			materialTypeBean.setCategoryId(materialTypeEntity.getMaterialCategoryEntity().getCategoryId());
			materialTypeList.add(materialTypeBean);
		}
		}
		return materialTypeList;		
	}

	@Override
	public List<MaterialTypeBean> getMaterialTypes() {
		List<MaterialTypeEntity> materialTypeEntities = materialTypeDAO.findAll();
		List<MaterialTypeBean> materialTypeList = null;
		if(materialTypeEntities != null) {
			materialTypeList = new ArrayList<MaterialTypeBean>();
			MaterialTypeBean materialTypeBean = null;
		for (MaterialTypeEntity materialTypeEntity : materialTypeEntities) {
			materialTypeBean = new MaterialTypeBean();
			BeanUtils.copyProperties(materialTypeEntity, materialTypeBean);
			materialTypeBean.setCategoryId(materialTypeEntity.getMaterialCategoryEntity().getCategoryId());
			materialTypeList.add(materialTypeBean);
			}
		}
		return materialTypeList;	
	}

	
}
