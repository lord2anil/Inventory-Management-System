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

import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VendorServiceConsumer {

	private static Logger LOGGER = Logger.getLogger(VendorServiceConsumer.class);

	@Value("${VendorServiceConsumer.serviceURL}")
	private String serviceURL;

	@Value("${VendorServiceConsumer.apiURL}")
	private String apiURL;

	private List<VendorBean> vendorBeanList;

	private Map<String, VendorBean> vendorMap;
	private List<String> vendorlist;

	private RestTemplate restTemplate;

	public List<VendorBean> getVendorBeanList() throws MicroServiceException {
		hitGetVendorDetails();
		return vendorBeanList;
	}
	

	public Map<String, VendorBean> getVendorMap() {
		return vendorMap;
	}
	
	public List<String> getVendorList() {
		return vendorlist;
	}

	public VendorServiceConsumer() {
		restTemplate = new RestTemplate();
	}

	/**
	 * This method is hitting vendor microservice to get the list of vendors
	 * 
	 * @return
	 * @throws MicroServiceException
	 */
	private void hitGetVendorDetails() throws MicroServiceException {
		
		ResponseEntity<List> vendorBeanEntityList = restTemplate.exchange(serviceURL +apiURL, HttpMethod.GET, null,List.class); 
		List<LinkedHashMap<String, Object>>  vendorMap = vendorBeanEntityList.getBody();
		
		ObjectMapper mapper =  new ObjectMapper();
		List<VendorBean> list  = new ArrayList<VendorBean>();
		
		if (vendorMap != null) {
			for (LinkedHashMap<String, Object> map : vendorMap) {
				//Map object should be converted to Employee type 
				VendorBean ven=mapper.convertValue(map, VendorBean.class);
				list.add(ven);
			}
			//System.out.println("Vendor Details are: "+list);
		} else {
			System.out.println("No Vendor exist----------");
		}
		
       this.vendorBeanList=list;
       
       Map<String, VendorBean> mp1= new LinkedHashMap<String,VendorBean>();
       
       List<String> list2= new ArrayList<String>();
       for (VendorBean vnBean :list) {
    	   mp1.put(vnBean.getVendorName(), vnBean);
    	   list2.add(vnBean.getVendorName());
    	   
       }
       this.vendorlist=list2;
       this.vendorMap=mp1;
       

	}
}
