package com.to.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TempProduct {
	 
	private int pid;
	private int cid; 
	private String pname;
	private String pdescription;
	private int available_quantity;
	private int price_per_unit;
	private boolean active;
	private String img_name;
	private String brand;
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
	public String getBrand() {
		return brand;
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
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "TempProduct [pid=" + pid + ", cid=" + cid + ", pname=" + pname + ", pdescription=" + pdescription
				+ ", available_quantity=" + available_quantity + ", price_per_unit=" + price_per_unit + ", active="
				+ active + ", img_name=" + img_name + ", brand=" + brand + "]";
	}
	
	
	
}
