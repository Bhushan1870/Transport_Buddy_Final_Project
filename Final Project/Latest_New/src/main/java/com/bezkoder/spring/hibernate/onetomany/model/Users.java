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
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid")
	private int userid ;
	
	@Column(name="username",unique = true )
	private String username ;
	
	@Column(name="password")
	private String password ;
	
	@Column(name="useremail")
	private String useremail ;
	
	@Column(name="phone")
	private String phone ;
	
	@Column(name="fulladdress")
	private String fulladdress ;
	
	@Column(name="rol")
	private String rol;
	
	@Column(name="userstatus")
	private boolean userstatus = false;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "userid")
	private Set<Requests> requests = new HashSet<>();
	
	public Users() {}

	public Users(String username, String password, String useremail, String phone, String fulladdress,String rol,boolean userstatus) {
		super();
		this.username = username;
		this.password = password;
		this.useremail = useremail;
		this.phone = phone;
		this.fulladdress = fulladdress;
		this.rol = rol;
		this.userstatus = userstatus ;
	}

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userid) {
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

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFulladdress() {
		return fulladdress;
	}

	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}

	public Set<Requests> getRequests() {
		return requests;
	}

	public void setRequests(Set<Requests> requests) {
		this.requests = requests;
	}
	
	public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

	public void removeRequests() {
	    this.requests.clear();
	  }
	
	

	

	public boolean isUserstatus() {
		return userstatus;
	}

	public void setUserstatus(boolean userstatus) {
		this.userstatus = userstatus;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", password=" + password + ", useremail="
				+ useremail + ", phone=" + phone + ", fulladdress=" + fulladdress + ", rol=" + rol + ", userstatus="
				+ userstatus + ", requests=" + requests + "]";
	}

	
	
	
}
