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
@Table(name="finalorder")
public class FinalOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderid")
	private int orderid ;
	
	@Column(name="status")
	private boolean status ;
	
	@Column(name="adminid")
	private int adminid ;
	
	@Column(name="transporterid")
	private int transporterid ;
	
	@Column(name="requestid")
	private int requestid ;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "orderid")
	private Set<OrderStatus> orderstatus = new HashSet<>();
	
	public FinalOrder() {}

	
	public FinalOrder(boolean status, int adminid, int transporterid, int requestid) {
		super();
		this.status = status;
		this.adminid = adminid;
		this.transporterid = transporterid;
		this.requestid = requestid;
	}


	

	public int getOrderid() {
		return orderid;
	}


	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public int getAdminid() {
		return adminid;
	}


	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}


	public int getTransporterid() {
		return transporterid;
	}


	public void setTransporterid(int transporterid) {
		this.transporterid = transporterid;
	}


	public int getRequestid() {
		return requestid;
	}


	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}


	public Set<OrderStatus> getOrderstatus() {
		return orderstatus;
	}


	public void setOrderstatus(Set<OrderStatus> orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	public void removeOrderstatus() {
	    this.orderstatus.clear();
	  }


	@Override
	public String toString() {
		return "FinalOrder [orderid=" + orderid + ", status=" + status + ", adminid=" + adminid + ", transporterid="
				+ transporterid + ", requestid=" + requestid + "]";
	}


}
