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

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MaterialCategoryConsumer {

	private static Logger LOGGER = Logger.getLogger(MaterialCategoryConsumer.class);

	@Value("${MaterialServiceConsumer.serviceURL}")
	private String serviceURL;
	@Value("${MaterialCategoryConsumer.apiURL}")
	private String apiURL;
	@Value("${MaterialCategoryConsumer.apiURLForById}")
	private String apiURLForById;
	private RestTemplate restTemplate;
	private List<MaterialCategoryBean> materialCategoryBeanList;
	private Map<String, String> categoryMap;

	public Map<String, String> getCategoryMap() throws MicroServiceException {
		return categoryMap;
	}

	public List<MaterialCategoryBean> getMaterialCategoryBeanList() throws MicroServiceException {
		hitGetMaterialCategories();
		return materialCategoryBeanList;
	}

	public MaterialCategoryConsumer() {
		restTemplate = new RestTemplate();
	}

	/**
	 * This method hits material microservice to get the list of Material
	 * category.
	 * 
	 * @return
	 * @throws MicroServiceException
	 */
	private void hitGetMaterialCategories() throws MicroServiceException {
     
		ResponseEntity<List> categoryEntityList = restTemplate.exchange(serviceURL +apiURL, HttpMethod.GET, null,List.class); 
		List<LinkedHashMap<String, Object>>  categoryMap = categoryEntityList.getBody();
		
		ObjectMapper mapper =  new ObjectMapper();
		List<MaterialCategoryBean> list  = new ArrayList<MaterialCategoryBean>();
		
		if (categoryMap != null) {
			for (LinkedHashMap<String, Object> map : categoryMap) {
				//Map object should be converted to Employee type 
				MaterialCategoryBean ven=mapper.convertValue(map, MaterialCategoryBean.class);
				list.add(ven);
			}
			//System.out.println("Category Details are: "+list);
		} else {
			System.out.println("No category exist----------");
		}
		
       this.materialCategoryBeanList=list;
       
       Map<String, String> mp1= new LinkedHashMap<String,String>();
       for (MaterialCategoryBean ctBean :list) {
    	   mp1.put( ctBean.getCategoryId(),ctBean.getCategoryName());
    	   
       }
       this.categoryMap=mp1;
       
       materialCategoryBeanList=list;

       
		
		
	}

	/**
	 * This method hits material microservice to get the details of Material
	 * category for given category id.
	 * 
	 * @param categoryId
	 * @return MaterialCategoryBean
	 * @throws MicroServiceException
	 */
	public MaterialCategoryBean hitGetMaterialCategoryById(String categoryId) throws MicroServiceException {
		
		ResponseEntity<MaterialCategoryBean> categoryEntityList = restTemplate.exchange(serviceURL +apiURLForById+"/"+categoryId, HttpMethod.GET, null,MaterialCategoryBean.class); 
		MaterialCategoryBean mtBean=categoryEntityList.getBody();
		return mtBean;
	}

}
