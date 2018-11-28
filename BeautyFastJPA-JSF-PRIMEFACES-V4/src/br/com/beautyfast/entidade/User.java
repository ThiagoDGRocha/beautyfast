package br.com.beautyfast.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(unique = true, nullable = false)
	@Email(message = "A classe User rejeitou o email informado!")
	private String userName;

	@Column(nullable = false)
	@Size(min = 5, message = "A senha deve conter pelo menos 5 caracteres.")
	private String userPassword;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date userLastAccess;

	@Column(nullable = false)
	@AssertTrue
	private boolean userActive;

	@Column(nullable = false)
	private int userType;

	@OneToOne(mappedBy = "adminUser", fetch = FetchType.LAZY)
	private Administrator userAdmin;

	@OneToOne(mappedBy = "clientUser", fetch = FetchType.LAZY)
	private Client userClient;

	@OneToOne(mappedBy = "profUser", fetch = FetchType.LAZY)
	private Professional userProf;

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