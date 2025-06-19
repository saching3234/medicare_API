package com.to.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_status")
public class DeliveryStatus {
	@Id
	private int osid;
	private String status;

	// getter and setters
	public int getOsid() {
		return osid;
	}

	public String getStatus() {
		return status;
	}

	public void setOsid(int osid) {
		this.osid = osid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DeliveryStatus [osid=" + osid + ", status=" + status + "]";
	}

}
