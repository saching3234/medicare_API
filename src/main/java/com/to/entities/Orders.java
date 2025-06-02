package com.to.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;

@Entity
@Table(name = "Orders")
public class Orders {
	@Id
	@GeneratedValue
	private int orderId;
	private int userId;
	private String userName ;
	private String img_name;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String pdescription;
	private int pid;
	private String pname;
	private int price_per_unit;
	private int quantity;
	private int total;
	private Date orderDate=new Date();
	private String deliveryStatus="pending";
	private Date deliveryDate=null;
	private String brand;
	
	
	
	//getter and setters	
	
	
	
	
	public String getBrand() {
		return brand;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public int getUserId() {
		return userId;
	}
	public String getImg_name() {
		return img_name;
	}
	public String getPdescription() {
		return pdescription;
	}
	public int getPid() {
		return pid;
	}
	public String getPname() {
		return pname;
	}
	public int getPrice_per_unit() {
		return price_per_unit;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getTotal() {
		return total;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setPrice_per_unit(int price_per_unit) {
		this.price_per_unit = price_per_unit;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setTotal(int total) {
		this.total = price_per_unit*quantity;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	
	
	
	

}
