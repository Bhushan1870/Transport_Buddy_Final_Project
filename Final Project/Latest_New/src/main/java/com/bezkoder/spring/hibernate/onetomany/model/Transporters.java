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
@Table(name="transporters")
public class Transporters {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transporterid")
	private int userid ;
	
	@Column(name="transporter_name")
	private String transportername ;
	
	@Column(name="username")
	private String username ;
	
	@Column(name="email")
	private String email ;
	
	@Column(name="phone")
	private String phone ;
	
	@Column(name="password")
	private String password ;
	
	@Column(name="rol")
	private String rol;
	
	@Column(name="isactive")
	private Boolean isactive = false ;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "transporterid")
	private Set<FinalOrder> finalorder = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "transporterid")
	private Set<Vehicles> vehicles = new HashSet<>();
	
	public Transporters() {}

	public Transporters(String transportername, String email, String phone, String password, String username,String rol,Boolean isactive) {
		super();
		this.transportername = transportername;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.username = username;
		this.rol = rol;
		this.isactive = isactive ;
	}

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userid) {
		this.userid = userid;
	}

	public String getTransportername() {
		return transportername;
	}

	public void setTransportername(String transportername) {
		this.transportername = transportername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Set<Vehicles> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicles> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void removeVehicles() {
	    this.vehicles.clear();
	  }
	
	public Set<FinalOrder> getFinalorder() {
		return finalorder;
	}

	public void setFinalorder(Set<FinalOrder> finalorder) {
		this.finalorder = finalorder;
	}
	
	public void removeFinalorder() {
	    this.vehicles.clear();
	  }

	@Override
	public String toString() {
		return "Transporters [userid=" + userid + ", transportername=" + transportername + ", username=" + username
				+ ", email=" + email + ", phone=" + phone + ", password=" + password + ", rol=" + rol + ", isactive="
				+ isactive + ", vehicles=" + vehicles + "]";
	}

	

}
