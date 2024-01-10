package com.bezkoder.spring.hibernate.onetomany.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vehicles")
public class Vehicles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vehicleid")
	private int vehicleid ;
	
	@Column(name="vehiclenumber")
	private String vehiclenumber ;
	
	@Column(name="vehicletype")
	private String vehicletype ;
	
	@Column(name="fromlocation")
	private String fromlocation ;
	
	@Column(name="tolocation")
	private String tolocation ;
	
	@Column(name="status")
	private String status ;
	
	@Column(name="driver1name")
	private String driver1name ;
	
	@Column(name="driver2name")
	private String driver2name ;
	
	@Column(name="driver1phone")
	private String driver1phone ;
	
	@Column(name="driver2phone")
	private String driver2phone ;
	
	@Column(name="fare")
	private int fare ;
	
	@Column(name="sceduleddate")
	private String sceduleddate ;
	
	
	@Column(name="transporterid")
	private int transporterid ;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "vehicleid")
	private Set<Requests> requests = new HashSet<>();
	
	
	
	
	public Vehicles() {}


	

	public Vehicles(String vehiclenumber, String vehicletype, String fromlocation, String tolocation, String status,
			String driver1name, String driver2name, String driver1phone, String driver2phone, int fare,
			String sceduleddate, int transporterid) {
		super();
		this.vehiclenumber = vehiclenumber;
		this.vehicletype = vehicletype;
		this.fromlocation = fromlocation;
		this.tolocation = tolocation;
		this.status = status;
		this.driver1name = driver1name;
		this.driver2name = driver2name;
		this.driver1phone = driver1phone;
		this.driver2phone = driver2phone;
		this.fare = fare;
		this.sceduleddate = sceduleddate;
		this.transporterid = transporterid;
	}




	public int getVehicleid() {
		return vehicleid;
	}


	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}


	public String getVehiclenumber() {
		return vehiclenumber;
	}


	public void setVehiclenumber(String vehiclenumber) {
		this.vehiclenumber = vehiclenumber;
	}


	public String getVehicletype() {
		return vehicletype;
	}


	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}


	public String getFromlocation() {
		return fromlocation;
	}


	public void setFromlocation(String fromlocation) {
		this.fromlocation = fromlocation;
	}


	public String getTolocation() {
		return tolocation;
	}


	public void setTolocation(String tolocation) {
		this.tolocation = tolocation;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDriver1name() {
		return driver1name;
	}


	public void setDriver1name(String driver1name) {
		this.driver1name = driver1name;
	}


	public String getDriver2name() {
		return driver2name;
	}


	public void setDriver2name(String driver2name) {
		this.driver2name = driver2name;
	}


	public String getDriver1phone() {
		return driver1phone;
	}


	public void setDriver1phone(String driver1phone) {
		this.driver1phone = driver1phone;
	}


	public String getDriver2phone() {
		return driver2phone;
	}


	public void setDriver2phone(String driver2phone) {
		this.driver2phone = driver2phone;
	}


	public int getFare() {
		return fare;
	}
   

	public int getTransporterid() {
		return transporterid;
	}




	public void setTransporterid(int transporterid) {
		this.transporterid = transporterid;
	}




	public void setFare(int fare) {
		this.fare = fare;
	}
	
	public String getSceduleddate() {
		return sceduleddate;
	}


	public void setSceduleddate(String sceduleddate) {
		this.sceduleddate = sceduleddate;
		
	}
	
	public Set<Requests> getRequests() {
		return requests;
	}

	public void setRequests(Set<Requests> requests) {
		this.requests = requests;
	}
	
	public void removeRequests() {
	    this.requests.clear();
	  }




	@Override
	public String toString() {
		return "Vehicles [vehicleid=" + vehicleid + ", vehiclenumber=" + vehiclenumber + ", vehicletype=" + vehicletype
				+ ", fromlocation=" + fromlocation + ", tolocation=" + tolocation + ", status=" + status
				+ ", driver1name=" + driver1name + ", driver2name=" + driver2name + ", driver1phone=" + driver1phone
				+ ", driver2phone=" + driver2phone + ", fare=" + fare + ", sceduleddate=" + sceduleddate
				+ ", transporters=" + transporterid + "]";
	}


	

	
	
	


}
