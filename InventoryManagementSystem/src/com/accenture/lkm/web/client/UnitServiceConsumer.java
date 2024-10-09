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

import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UnitServiceConsumer {

	private static Logger LOGGER = Logger.getLogger(UnitServiceConsumer.class);

	@Value("${MaterialServiceConsumer.serviceURL}")
	private String serviceURL;

	@Value("${UnitServiceConsumer.apiURL}")
	private String apiURL;

	@Value("${UnitServiceConsumer.apiURLByCategoryId}")
	private String apiURLByCategoryId;

	private List<UnitBean> unitBeanList;

	private Map<String, String> unitMap;
	
	
	private Map<String, String> unitMap_all;

	private RestTemplate restTemplate;

	public List<UnitBean> getUnitBeanList() throws MicroServiceException {
		return unitBeanList;
	}

	public Map<String, String> getUnitMap() throws MicroServiceException {
		return unitMap;
	}
	
	public Map<String, String> getUnitMapAll() throws MicroServiceException {
	   hitGetUnitDetails();
		return unitMap_all;
	}

	public UnitServiceConsumer() {
		restTemplate = new RestTemplate();
	}

	/**
	 * This method hits material microservice to get the list of unit.
	 * 
	 * @return
	 * @throws MicroServiceException
	 */
	private void hitGetUnitDetails() throws MicroServiceException {
		
		ResponseEntity<List> unitEntityList = restTemplate.exchange(serviceURL +apiURL, HttpMethod.GET, null,List.class); 
		List<LinkedHashMap<String, Object>>  unitMap = unitEntityList.getBody();
		
		ObjectMapper mapper =  new ObjectMapper();
		List<UnitBean> list  = new ArrayList<UnitBean>();
		
		if (unitMap != null) {
			for (LinkedHashMap<String, Object> map : unitMap) {
				//Map object should be converted to Employee type 
				UnitBean un=mapper.convertValue(map, UnitBean.class);
				list.add(un);
			}
		//	System.out.println("Unit Details are: "+list);
		} else {
			System.out.println("No Unit exist----------");
		}
		
		this.unitBeanList=list;
		
		Map<String, String> unitMap2=new LinkedHashMap<String, String>();
		for( UnitBean un:list) {
			unitMap2.put(un.getUnitId(),un.getUnitName());
		}
		this.unitMap_all=unitMap2;
		
		

	}

	/**
	 * This method hits material microservice to get the list of unit available
	 * for a given category id.
	 * 
	 * @param categoryId
	 * @return List<UnitBean>
	 * @throws MicroServiceException
	 */
	public List<UnitBean> hitGetUnitsByCategoryId(String categoryId) throws MicroServiceException {
		
		ResponseEntity<List> unitEntityList = restTemplate.exchange(serviceURL +apiURLByCategoryId+"/"+categoryId, HttpMethod.GET, null,List.class); 
		List<LinkedHashMap<String, Object>>  unitMap = unitEntityList.getBody();
		
		ObjectMapper mapper =  new ObjectMapper();
		List<UnitBean> list  = new ArrayList<UnitBean>();
		
		if (unitMap != null) {
			for (LinkedHashMap<String, Object> map : unitMap) {
				//Map object should be converted to Employee type 
				UnitBean un=mapper.convertValue(map, UnitBean.class);
				list.add(un);
			}
			//System.out.println("unit type "+list);
		} else {
			System.out.println("NO unit exist");
		}
		
		this.unitBeanList=list;
		
		Map<String, String> unitMap2=new LinkedHashMap<String, String>();
		for( UnitBean un:list) {
			unitMap2.put(un.getUnitId(),un.getUnitName());
			
		}
		this.unitMap=unitMap2;
		
		return list;
	}

}
