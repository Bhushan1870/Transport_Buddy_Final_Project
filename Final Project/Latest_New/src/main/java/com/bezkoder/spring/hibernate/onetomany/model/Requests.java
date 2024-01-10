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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="requests")
public class Requests {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="requestid")
	private int requestid=0 ;
	
	@Column(name="userid")
	private int userid ;
	

	@Column(name="vehicleid")
	private int vehicleid ;
	
	@Column(name="status")
	private String status="1";
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "requestid")
	private Set<FinalOrder> finalorder = new HashSet<>();
	
	public Requests() {}

	public Requests(int userid,int vehicleid,String status) {
		super();
		this.userid = userid ;
		this.vehicleid = vehicleid ;
		this.status = status ;
		
	}

	

	public int getRequestId() {
		return requestid;
	}

	public void setRequestId(int requestid) {
		this.requestid = requestid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	
	

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}

	

	public Set<FinalOrder> getFinalorder() {
		return finalorder;
	}

	public void setFinalorder(Set<FinalOrder> finalorder) {
		this.finalorder = finalorder;
	}
	
	public void removeFinalorder() {
	    this.finalorder.clear();
	  }

	@Override
	public String toString() {
		return "Requests [requestid=" + requestid + ", userid=" + userid + ", vehicleid=" + vehicleid + ", status="
				+ status + ", finalorder=" + finalorder + "]";
	}

	

	
	
	

}
