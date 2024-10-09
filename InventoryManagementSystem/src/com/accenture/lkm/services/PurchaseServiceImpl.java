package com.accenture.lkm.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.dao.PurchaseDAO;
import com.accenture.lkm.dao.PurchaseDAOImpl;
import com.accenture.lkm.entity.PurchaseEntity;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private static Logger LOGGER = Logger.getLogger(PurchaseServiceImpl.class);

	// Auto wire PurchaseDAO here
	
	@Autowired
	private PurchaseDAO  purchaseDAOImpl;

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to insert purchase details data into the purchase
	 * table. Also, this method will add a single row into the payment table
	 * with paid amount as zero to keep the track of the balance amount for a
	 * specific purchase.
	 * 
	 * @param purchaseBean
	 * @return purchaseBean
	 * @throws Exception
	 */
	@Override
	public PurchaseBean addPurchaseDetails(PurchaseBean purchaseBean,String materialCategoryName ) throws Exception {
		
		String t_idString=transactionIdGenerator(purchaseBean.getVendor_name(),materialCategoryName,purchaseBean.getPurchase_date());
		purchaseBean.setTransaction_id(t_idString);
		
		return insertPurchaseDetails(purchaseBean);
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method will be called by addPurchaseDetails method to insert the
	 * data into the Purchase table.
	 * 
	 * @param purchaseBean
	 * @return purchaseBean
	 * @throws Exception
	 */
	private PurchaseBean insertPurchaseDetails(PurchaseBean purchaseBean) throws Exception {
		
		PurchaseEntity  purchaseEntity= convertBeanToEntity(purchaseBean);
		return convertEntityToBean(purchaseDAOImpl.savePurchaseDetail(purchaseEntity));
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to generate the transaction id as per logic- P_first
	 * 3 characters of vendor name_ purchase date in mmddyyyy format_first 3
	 * characters of material category purchased_primary key of purchase table
	 * 
	 * @param vendorName
	 * @param materialCategoryName
	 * @param purchaseDate
	 * @return String
	 */
	private String transactionIdGenerator(String vendorName, String materialCategoryName, Date purchaseDate) {
		
		
		 String vendorPart = vendorName.substring(0, 3).toUpperCase();
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
	        String datePart = sdf.format(purchaseDate);
	        String categoryPart = materialCategoryName.substring(0, 3).toUpperCase();
	        
	        // Generate the transaction ID
	        String transactionId = String.format("P_%s_%s_%s", vendorPart, datePart, categoryPart);
		
		return transactionId;

	}
	
	public static PurchaseBean convertEntityToBean(PurchaseEntity entity){
		PurchaseBean purchase = new PurchaseBean();
		BeanUtils.copyProperties(entity, purchase);
		return purchase;
	}
	public static PurchaseEntity convertBeanToEntity(PurchaseBean bean){
		PurchaseEntity purchaseEntityBean = new PurchaseEntity();
		BeanUtils.copyProperties(bean,purchaseEntityBean);
		return purchaseEntityBean;
	}

	@Override
	public List<PurchaseBean> getPurchaseDataBasedonDateRangeAndVendorName(String vendorName, Date StartData,
			Date endDate) throws Exception {
		
		List<PurchaseBean> resultList=new ArrayList<PurchaseBean>();
		List<PurchaseEntity> list1=purchaseDAOImpl.getPurchaseDataBasedonDateRangeAndVendorName(vendorName, StartData, endDate);
		for(PurchaseEntity et:list1) {
			resultList.add(convertEntityToBean(et));
		}
		return resultList;
	}

}



