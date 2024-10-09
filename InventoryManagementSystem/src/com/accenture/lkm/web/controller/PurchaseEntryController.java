
package com.accenture.lkm.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.PurchaseService;
import com.accenture.lkm.services.PurchaseServiceImpl;
import com.accenture.lkm.web.client.MaterialCategoryConsumer;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.UnitServiceConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * A controller class for receiving and handling all material purchase related
 * transactions from the User Interface. <br/>
 *
 */

@Controller

@SessionAttributes({ "purchaseBean","unitmap","TypeMap" })
public class PurchaseEntryController {

	private static Logger LOGGER = Logger.getLogger(PurchaseEntryController.class);

	// Auto wire PurchaseService here

	@Autowired
	private PurchaseServiceImpl purchaseServiceImpl;

	@Autowired
	private VendorServiceConsumer vendorServiceConsumer;

	@Autowired
	private MaterialCategoryConsumer materialCategoryConsumer;

	@Autowired
	private UnitServiceConsumer unitServiceConsumer;

	@Autowired
	private MaterialTypeConsumer materialTypeConsumer;

	// Auto wire VendorServiceConsumer here

	// Auto wire MaterialCategoryConsumer here

	// Auto wire UnitServiceConsumer here

	// Auto wire MaterialTypeConsumer here

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method sets PurchaseBean into the model attribute and redirects to
	 * PurchaseEntry.jsp.
	 * 
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "purchaseEntry.html", method = RequestMethod.GET)
	public ModelAndView purchaseEntry() throws Exception {
		ModelAndView m1 = new ModelAndView();
		m1.setViewName("PurchaseEntry");
		m1.addObject("purchaseBean", new PurchaseBean());
		m1.addObject("categoryList", materialCategoryConsumer.getCategoryMap());
		m1.addObject("vendorList", vendorServiceConsumer.getVendorList());
		

		return m1;
	}
	
	
	@RequestMapping(value = "ReportEntry.html", method = RequestMethod.GET)
	public ModelAndView ReportEntry() throws Exception {
		ModelAndView m1 = new ModelAndView();
		m1.setViewName("purchaseReport");
		
		m1.addObject("vendorList", vendorServiceConsumer.getVendorList());
		

		return m1;
	}


	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the vendor list to be populated on the PurchasEntry.jsp.
	 * getVendorBeanList method of VendorServiceConsumer is called to get the vendor
	 * list.
	 * 
	 * @return vendorList - List of vendor values
	 * @throws MicroServiceException
	 */

	@ModelAttribute("vendorList")
	public List<VendorBean> generateVendorList() throws MicroServiceException {

		return vendorServiceConsumer.getVendorBeanList();
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the material unit and type list to be populated in
	 * PurchaseEntry.jsp for chosen material category. hitGetUnitsByCategoryId
	 * method of UnitServiceConsumer class to be called to get the list of material
	 * unit. hitGetTypesBasedOnCategoryId method of MaterialTypeConsumer class to be
	 * called to get the list of material type.
	 * 
	 * @param purchaseBean
	 * @param HttpSession
	 * @return ModelAndView
	 * @throws MicroServiceException
	 */

	@RequestMapping(value = "getUnitAndTypeList.html", method = RequestMethod.POST)
	public ModelAndView generateUnitAndTypeList(@ModelAttribute("purchaseBean") PurchaseBean purchaseBean,
			HttpSession session) throws MicroServiceException {
		ModelAndView m1 = new ModelAndView();
		
		MaterialCategoryBean beanct = materialCategoryConsumer
				.hitGetMaterialCategoryById(purchaseBean.getMaterial_category_id());
		  unitServiceConsumer.hitGetUnitsByCategoryId(purchaseBean.
		  getMaterial_category_id()); 
		  
		  m1.addObject("unitmap",unitServiceConsumer.getUnitMap());
		  materialTypeConsumer.hitGetTypesBasedOnCategoryId(purchaseBean.getMaterial_category_id());
		  
		  m1.addObject("TypeMap",materialTypeConsumer.getCategoryTypeMap());
		  m1.addObject("category_name",beanct.getCategoryName());
		 
		
		m1.setViewName("PurchaseEntry2");
		
		return m1;
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the material category list to be populated on the
	 * PurchasEntry.jsp. getMaterialCategoryBeanList method of
	 * MaterialCategoryConsumer is called to get the material category list.
	 * 
	 * @return List - MaterialCategoryBean
	 * @throws MicroServiceException
	 */

	@ModelAttribute("categoryList")
	public List<MaterialCategoryBean> generateCategoryList() throws MicroServiceException {

		return materialCategoryConsumer.getMaterialCategoryBeanList();
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to insert purchase details filled on PurchaseEntry.jsp in
	 * to the purchase and payment table. Upon successful insert redirects to
	 * PurchaseSuccess.jsp
	 * 
	 * @param purchaseBean
	 * @param BindingResult
	 * @param ModelMap
	 * @param HttpSession
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "addPurchaseDetail.html", method = RequestMethod.POST)
	public ModelAndView addPurchaseDetail(@ModelAttribute("purchaseBean") @Valid PurchaseBean purchaseBean,
			BindingResult result, ModelMap map, HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		
		System.out.println(purchaseBean.getPurchase_date());
		if (result.hasErrors()) {
			modelAndView.setViewName("PurchaseEntry2");
		} else {
			MaterialCategoryBean m1 = materialCategoryConsumer
					.hitGetMaterialCategoryById(purchaseBean.getMaterial_category_id());

			purchaseBean = purchaseServiceImpl.addPurchaseDetails(purchaseBean, m1.getCategoryName());
             
			modelAndView.setViewName("PurchaseSuccess");
		}
		return modelAndView;
	}

	@RequestMapping(value = "report.html", method = RequestMethod.POST) public
		  ModelAndView purchaseReport(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView(); 
		
		  
		  String vendorName = request.getParameter("vendor_name");
		  String startDateStr = request.getParameter("StartDate"); 
		  String endDateStr =
		  request.getParameter("EndDate"); 
		  modelAndView.addObject("vendorName",vendorName);
		  modelAndView.addObject("startDateStr",startDateStr);
		  modelAndView.addObject("endDateStr",endDateStr);
		  SimpleDateFormat dateFormat = new
		  SimpleDateFormat("yyyy-MM-dd"); 
		 
		  Date startDate = null; Date endDate = null;
		  try { if (startDateStr != null && !startDateStr.isEmpty()) 
		  { startDate =
		  dateFormat.parse(startDateStr); }
		  if (endDateStr != null &&!endDateStr.isEmpty()) 
		  { endDate = dateFormat.parse(endDateStr); }
		  }
		  catch (Exception e) {
			// TODO: handle exception
			  throw e;
		}
		  
		 
		  List<PurchaseBean>resultBeans=purchaseServiceImpl.getPurchaseDataBasedonDateRangeAndVendorName(
		  vendorName, startDate, endDate); 
		
		
		 
		  
		  
		  modelAndView.setViewName("ShowReport");
		/*
		 * for(PurchaseBean pt:resultBeans) {
		 * System.out.println(pt.getUnit_id()+"  before");
		 * System.out.println(pt.getMaterial_type_id()+"  before"); }
		 */
		  
		  Map<String,String > mp_allMap=unitServiceConsumer.getUnitMapAll();
		  
		  for(PurchaseBean pb:resultBeans) {
			
		  pb.setUnit_id(mp_allMap.get(pb.getUnit_id()));
		  
		  
		
		  }
		  
		  Map<String,String > type_allMap=materialTypeConsumer.gettypeMapAll();
		  
		  for(PurchaseBean pb:resultBeans) {
		  pb.setMaterial_type_id(type_allMap.get(pb.getMaterial_type_id())); 
		  }
		  
		/*
		 * for(PurchaseBean pt:resultBeans) {
		 * System.out.println(pt.getUnit_id()+"  After");
		 * System.out.println(pt.getMaterial_type_id()+"  After"); }
		 */

		  
		  // todo 
		  modelAndView.addObject("resObj", resultBeans); VendorBean
		  vendorBean=vendorServiceConsumer.getVendorMap().get(vendorName);
		  modelAndView.addObject("vendorObj", vendorBean); 
		  
		  
		  return modelAndView;
		  
		  }

}
