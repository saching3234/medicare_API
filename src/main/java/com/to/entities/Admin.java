package com.to.entities;

import javax.persistence.Entity;
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
@Table(name="Admin")
public class Admin {
	@Id
	private int id;
	private String adminId;
	private String password;
	
	
	
	public Admin(int id, String adminId, String password) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.password = password;
	}
	
	
	
	public Admin() {
		super();
	}



	//getter and setter
	public int getId() {
		return id;
	}
	public String getAdminId() {
		return adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//to string method
	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminId=" + adminId + ", password=" + password + "]";
	}
	

	
	

}
