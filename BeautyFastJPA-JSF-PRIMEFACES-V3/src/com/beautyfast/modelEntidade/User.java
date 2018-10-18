package com.beautyfast.modelEntidade;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private int userId;

	@Column(name = "user_name", unique = true, nullable = false)
	private String userName;

	@Column(name = "user_password", nullable = false)
	private String userPassword;

	@Column(name = "last_access", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date userLastAccess;

	@Column(name = "user_active", nullable = false)
	private boolean userActive;

	@Column(name = "user_Type")
	private int userType;

	@OneToOne(mappedBy = "adminUser", fetch = FetchType.LAZY)
	private Administrator userAdmin;

	@OneToOne(mappedBy = "clientUser", fetch = FetchType.LAZY)
	private Client userClient;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "profUser", fetch = FetchType.LAZY)
	private Professional userProf;

	public User() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getUserLastAccess() {
		return userLastAccess;
	}

	public void setUserLastAccess(Date lastAccess) {
		this.userLastAccess = lastAccess;
	}

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userAdmin
	 */
	public Administrator getUserAdmin() {
		return userAdmin;
	}

	/**
	 * @param userAdmin the userAdmin to set
	 */
	public void setUserAdmin(Administrator userAdmin) {
		this.userAdmin = userAdmin;
	}

	/**
	 * @return the userClient
	 */
	public Client getUserClient() {
		return userClient;
	}

	/**
	 * @param userClient the userClient to set
	 */
	public void setUserClient(Client userClient) {
		this.userClient = userClient;
	}

	/**
	 * @return the userProf
	 */
	public Professional getUserProf() {
		return userProf;
	}

	/**
	 * @param userProf the userProf to set
	 */
	public void setUserProf(Professional userProf) {
		this.userProf = userProf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", lastAccess="
				+ userLastAccess + ", userActive=" + userActive + ", userType=" + userType + ", userAdmin=" + userAdmin
				+ ", userClient=" + userClient + ", userProf=" + userProf + "]";
	}

}