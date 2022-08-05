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
@Table(name="Category")
public class Category {
	
	@Id
	@GeneratedValue
	private int cid;
	private String cname;
	private boolean active;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	//getter and setters
	public int getCid() {
		return cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	//to string method
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	

	
	

}
