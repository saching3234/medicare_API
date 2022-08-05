package com.to.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Product")
public class Product {
	 @Id
	 @GeneratedValue
	private int pid;
	private int cid; 
	private String pname;
	private String pdescription;
	private int available_quantity;
	private int price_per_unit;
	private boolean active;
	private String img_name;
	private String brand;
	
	
	
//getter and setters
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public int getPid() {
		return pid;
	}
	public int getCid() {
		return cid;
	}
	public String getPname() {
		return pname;
	}
	public String getPdescription() {
		return pdescription;
	}
	public int getAvailable_quantity() {
		return available_quantity;
	}
	public int getPrice_per_unit() {
		return price_per_unit;
	}
	public boolean isActive() {
		return active;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public void setAvailable_quantity(int available_quantity) {
		this.available_quantity = available_quantity;
	}
	public void setPrice_per_unit(int price_per_unit) {
		this.price_per_unit = price_per_unit;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", cid=" + cid + ", pname=" + pname + ", pdescription=" + pdescription
				+ ", available_quantity=" + available_quantity + ", price_per_unit=" + price_per_unit + ", active="
				+ active + ", img_name=" + img_name + "]";
	}	
	
	
}
