package com.accenture.lkm.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.entity.PurchaseEntity;


/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for PurchaseDAO to perform the CRUD operation on
 * Purchase table <br/>
 *
 */
@Repository
public class PurchaseDAOImpl implements PurchaseDAO {
	private static Logger LOGGER = Logger.getLogger(PurchaseDAOImpl.class);

	// Auto wire EntityManagerFactory here

	/*
	 * This method inserts the Purchase Data into the Purchase table.
	 * 
	 * @param purchaseEntity
	 * 
	 * @return PurchaseEntity
	 */
	@Autowired
	 private EntityManagerFactory entityManagerFactory;
	
	
	@Override
	public PurchaseEntity savePurchaseDetail(PurchaseEntity purchaseEntity) throws Exception {
		
		EntityManager  entityManager =null;
	
		try {
			entityManager =entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
				entityManager.persist(purchaseEntity);
				entityManager.getTransaction().commit();;
			
		} catch (Exception exception) {
			throw exception;
		}finally{
			if(entityManager!=null){
				entityManager.close();
			}
		}
		return purchaseEntity;
	}
	
   public List<PurchaseEntity> getPurchaseDataBasedonDateRangeAndVendorName(String vendorName,Date StartDate,Date endDate) throws Exception {
	   
	   
	   
	   EntityManager  entityManager =null;
	   List<PurchaseEntity> listpurhaseEntityRes = null;
		try {
			entityManager =entityManagerFactory.createEntityManager();
			

			Query query = entityManager.createQuery(
				    "SELECT p FROM PurchaseEntity p " +
				    "WHERE p.vendor_name = :vendorName " + // Use correct field name
				    "AND p.purchase_date BETWEEN :startDate AND :endDate"); // Use correct field names

				// Set parameters
				query.setParameter("vendorName", vendorName);
				query.setParameter("startDate", StartDate);
				query.setParameter("endDate", endDate);
			
			List<PurchaseEntity> listPurchaseEntity=(List<PurchaseEntity>) query.getResultList();
			listpurhaseEntityRes=listPurchaseEntity;
			
			
			
		} catch (Exception exception) {
			throw exception;
		}finally{
			if(entityManager!=null){
				entityManager.close();
			}
		}
		
	   
	   return listpurhaseEntityRes;
	
}

}
