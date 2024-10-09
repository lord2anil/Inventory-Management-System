package com.accenture.lkm.web.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MaterialTypeConsumer {

	private static Logger LOGGER = Logger.getLogger(MaterialTypeConsumer.class);

	@Value("${MaterialServiceConsumer.serviceURL}")
	private String serviceURL;

	@Value("${MaterialTypeConsumer.apiURL}")
	private String apiURL;

	@Value("${MaterialTypeConsumer.apiURLByCategoryId}")
	private String apiURLByCategoryId;

	private RestTemplate restTemplate;

	private List<MaterialTypeBean> materialTypeBeanList;

	private Map<String, String> categoryTypeMap;
	
	private Map<String, String> TypeMap_all;
	public Map<String, String> gettypeMapAll() throws MicroServiceException {
		hitGetMaterialType();
		return TypeMap_all;
	}

	public List<MaterialTypeBean> getMaterialTypeBeanList() throws MicroServiceException {
		return materialTypeBeanList;
	}

	public Map<String, String> getCategoryTypeMap() throws MicroServiceException {
		return categoryTypeMap;
	}

	public MaterialTypeConsumer() {
		restTemplate = new RestTemplate();
	}

	/**
	 * This method hits material microservice to get the list of Material type.
	 * 
	 * @return
	 * @throws MicroServiceException
	 */
	private void hitGetMaterialType() throws MicroServiceException {
		
		ResponseEntity<List> materialEntity = restTemplate.exchange(serviceURL +apiURL , HttpMethod.GET, null,List.class); 
		List<LinkedHashMap<String, Object>>  materialMap = materialEntity.getBody();
		
		ObjectMapper mapper =  new ObjectMapper();
		List<MaterialTypeBean> list  = new ArrayList<MaterialTypeBean>();
		
		if (materialMap != null) {
			for (LinkedHashMap<String, Object> map : materialMap) {
				//Map object should be converted to Employee type 
				MaterialTypeBean mat=mapper.convertValue(map, MaterialTypeBean.class);
				list.add(mat);
			}
			/* System.out.println("Material type Details are: "+list); */
		} else {
			System.out.println("No material type exist----------");
		}
		
		materialTypeBeanList=list;
		Map<String, String> typeMap2=new LinkedHashMap<String, String>();
		
		for( MaterialTypeBean mn:list) {
			typeMap2.put( mn.getTypeId(),mn.getTypeName());
			
		}
		this.TypeMap_all=typeMap2;
     


	}

	/**
	 * This method hits material microservice to get the details of Material
	 * type based on category id.
	 * 
	 * @param categoryId
	 * @return List<MaterialTypeBean>
	 * @throws MicroServiceException
	 */
	public List<MaterialTypeBean> hitGetTypesBasedOnCategoryId(String categoryId) throws MicroServiceException {
		
		
		
		ResponseEntity<List> materialEntity = restTemplate.exchange(serviceURL +apiURLByCategoryId +"/"+categoryId, HttpMethod.GET, null,List.class); 
		List<LinkedHashMap<String, Object>>  materialMap = materialEntity.getBody();
		
		ObjectMapper mapper =  new ObjectMapper();
		List<MaterialTypeBean> list  = new ArrayList<MaterialTypeBean>();
		
		if (materialMap != null) {
			for (LinkedHashMap<String, Object> map : materialMap) {
				//Map object should be converted to Employee type 
				MaterialTypeBean mat=mapper.convertValue(map, MaterialTypeBean.class);
				list.add(mat);
			}
			/* System.out.println("Material type Details are: "+list); */
		} else {
			System.out.println("No material type exist----------");
		}
		
		materialTypeBeanList=list;
		Map<String, String> typeMap2=new LinkedHashMap<String, String>();
		
		for( MaterialTypeBean mn:list) {
			typeMap2.put( mn.getTypeId(),mn.getTypeName());
			
		}
		this.categoryTypeMap=typeMap2;
     return list;
	}

}
