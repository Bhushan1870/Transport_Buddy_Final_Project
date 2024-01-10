package com.bezkoder.spring.hibernate.onetomany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderstatus")
public class OrderStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="orderid")
	private int orderid ;
	
	@Column(name="pickedup")
	private String pickedup ;
	
	@Column(name="ontransit")
	private String ontransit ;
	
	@Column(name="delivered")
	private String delivered ;
	
	@Column(name="pickupdate")
	private String pickupdate ;
	
	@Column(name="deliverydate")
	private String deliverydate ;
	
//	@Column(name="status")
//	private boolean status ;
	
	public OrderStatus() {}

	public OrderStatus(int orderid, String pickedup, String ontransit, String delivered, String pickupdate,
			String deliverydate) {
		super();
		this.orderid = orderid;
		this.pickedup = pickedup;
		this.ontransit = ontransit;
		this.delivered = delivered;
		this.pickupdate = pickupdate;
		this.deliverydate = deliverydate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getPickedup() {
		return pickedup;
	}

	public void setPickedup(String pickedup) {
		this.pickedup = pickedup;
	}

	public String getOntransit() {
		return ontransit;
	}

	public void setOntransit(String ontransit) {
		this.ontransit = ontransit;
	}

	public String getDelivered() {
		return delivered;
	}

	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	public String getPickupdate() {
		return pickupdate;
	}

	public void setPickupdate(String pickupdate) {
		this.pickupdate = pickupdate;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}

	

	@Override
	public String toString() {
		return "OrderStatus [id=" + id + ", orderid=" + orderid + ", pickedup=" + pickedup + ", ontransit=" + ontransit
				+ ", delivered=" + delivered + ", pickupdate=" + pickupdate + ", deliverydate=" + deliverydate
				+  "]";
	}
	

}
