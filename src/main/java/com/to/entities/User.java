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
@Table(name="User")
public class User {
	    @Id
	    @GeneratedValue
	    private int uid;
	    private String email;
	    private String password;
	    private String name;
	    private String phone;
	    private String address;
	    private String pincode;
	    private String gender;
	    private int age;
	    
	    //getter and setters 
	    
	    public String getAddress() {
			return address;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getPincode() {
			return pincode;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}   
		public int getUid() {
			return uid;
		}
		public String getEmail() {
			return email;
		}
		public String getPassword() {
			return password;
		}
		public String getName() {
			return name;
		}
		public String getPhone() {
			return phone;
		}
		public void setUid(int uid) {
			this.uid = uid;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}	    
}
