package com.bezkoder.spring.hibernate.onetomany.model;

public class Request {
	
	int requestid;
	Vehicles vehicle;
	Users user;
	String status;
	
	public Request(int requestid, Vehicles vehicle, Users user, String status) {
		super();
		this.requestid = requestid;
		this.vehicle = vehicle;
		this.user = user;
		this.status = status;
	}

	public int getRequestid() {
		return requestid;
	}

	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}

	public Vehicles getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicles vehicle) {
		this.vehicle = vehicle;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Request [requestid=" + requestid + ", vehicle=" + vehicle + ", user=" + user + ", status=" + status
				+ "]";
	}
	
    
}
