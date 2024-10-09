package com.accenture.lkm.business.bean;

/*
 *Declare fields to set or get purchaseid, transactionId, vendor name,material category id,material type id,
 *brand name, unit id,quantity,purchase amount, balance, purchase date,material category name,
  material type name,material unit name and status.
  Validate the fields using spring validation annotations.
 *Generate toString method. Add default and parameterized constructors.
 */



import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;



/*
 *Declare fields to set or get purchaseid, transactionId, vendor name,material category id,material type id,
 *brand name, unit id,quantity,purchase amount, purchase date and status.
 *Generate toString method. Add default and parameterized constructors.
 */


public class PurchaseBean {
	
	
	public Integer getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(Integer purchase_id) {
		this.purchase_id = purchase_id;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getMaterial_category_id() {
		return material_category_id;
	}

	public void setMaterial_category_id(String material_category_id) {
		this.material_category_id = material_category_id;
	}

	public String getMaterial_type_id() {
		return material_type_id;
	}

	public void setMaterial_type_id(String material_type_id) {
		this.material_type_id = material_type_id;
	}

	public Double getPurchase_amount() {
		return purchase_amount;
	}

	public void setPurchase_amount(Double purchase_amount) {
		this.purchase_amount = purchase_amount;
	}

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	
	
     
	
	private Integer purchase_id;
	
	@NotBlank
	private  String brandname;
	
	@NotNull
	private String material_category_id;
	
	
	@NotNull
	private String material_type_id;
	
	
	@NotNull
	private Double purchase_amount;
	
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchase_date;
	
	
	private Integer quantity;
	
	@NotBlank
	private String status;
	
	private  String transaction_id;
	
	private String unit_id;
	
	private String vendor_name;
	
	

}
