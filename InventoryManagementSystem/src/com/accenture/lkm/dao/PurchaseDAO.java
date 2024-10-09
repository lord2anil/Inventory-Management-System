package com.accenture.lkm.dao;

import java.util.Date;
import java.util.List;

import com.accenture.lkm.entity.PurchaseEntity;

public interface PurchaseDAO{
	public PurchaseEntity savePurchaseDetail(PurchaseEntity purchaseEntity) throws Exception;
	  public List<PurchaseEntity> getPurchaseDataBasedonDateRangeAndVendorName(String vendorName,Date StartData,Date endDate) throws Exception ;


}
