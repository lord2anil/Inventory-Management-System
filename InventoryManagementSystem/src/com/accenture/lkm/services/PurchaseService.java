package com.accenture.lkm.services;

import java.util.Date;
import java.util.List;

import com.accenture.lkm.business.bean.PurchaseBean;


public interface PurchaseService {
	PurchaseBean addPurchaseDetails(PurchaseBean purchaseBean,String ctName) throws Exception;
	  public List<PurchaseBean> getPurchaseDataBasedonDateRangeAndVendorName(String vendorName,Date StartData,Date endDate) throws Exception ;
}
