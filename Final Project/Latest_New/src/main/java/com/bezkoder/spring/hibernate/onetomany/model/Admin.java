package com.bezkoder.spring.hibernate.onetomany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="adminid")
	private int userid ;
	
	@Column(name="username")
	private String username ;
	
	@Column(name="password")
	private String password ;
	
	@Column(name="rol")
	private String rol ;
	
	public Admin() {} 

	public Admin(String username, String password,String rol) {
		super();
		this.username = username;
		this.password = password;
		this.rol = rol;
	}

	public int getUserId() {
		return userid;
	}

	public void setAdminId(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

	@Override
	public String toString() {
		return "Admin [userid=" + userid + ", username=" + username + ", password=" + password + ", rol=" + rol + "]";
	}
    
    

}
