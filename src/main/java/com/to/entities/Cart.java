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
@Table(name="Cart")
public class Cart {
	@Id
	@GeneratedValue
	private int cartId;
	private int userId;
	private String userName ;
	private int cid;
	private String img_name;
	private String pdescription;
	private String pid;
	private String pname;
	private int price_per_unit;
	private String brand;
	//extra field
	private int quantity;
	
	//getter and setters methods	
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", cid=" + cid + ", img_name=" + img_name
				+ ", pdescription=" + pdescription + ", pid=" + pid + ", pname=" + pname + ", price_per_unit="
				+ price_per_unit + ", quantity=" + quantity + "]";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}	
	
	public int getCartId() {
		return cartId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCid() {
		return cid;
	}
	public String getImg_name() {
		return img_name;
	}
	public String getPdescription() {
		return pdescription;
	}
	public String getPid() {
		return pid;
	}
	public String getPname() {
		return pname;
	}
	public int getPrice_per_unit() {
		return price_per_unit;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setPrice_per_unit(int price_per_unit) {
		this.price_per_unit = price_per_unit;
	}	

}
