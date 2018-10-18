package com.beautyfast.modelEntidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="administrador")
public class Administrator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id", unique = true, nullable = false)
	private int adminId;

	@Column(name = "admin_name", nullable = false)
	private String adminName;

	@Column(name = "admin_CPF", nullable = false)
	private String adminCPF;

	@OneToOne
	private User adminUser;

	@OneToOne
	private Adress adminAdress;

	@OneToOne
	private Contact adminContact;

	public Administrator() {
		
	}
	
	/**
	 * @return the adminId
	 */
	public int getAdminId() {
		return adminId;
	}


	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	/**
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}


	/**
	 * @param adminName the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}


	/**
	 * @return the adminCPF
	 */
	public String getAdminCPF() {
		return adminCPF;
	}


	/**
	 * @param adminCPF the adminCPF to set
	 */
	public void setAdminCPF(String adminCPF) {
		this.adminCPF = adminCPF;
	}


	/**
	 * @return the adminUser
	 */
	public User getAdminUser() {
		return adminUser;
	}


	/**
	 * @param adminUser the adminUser to set
	 */
	public void setAdminUser(User adminUser) {
		this.adminUser = adminUser;
	}


	/**
	 * @return the adminAdress
	 */
	public Adress getAdminAdress() {
		return adminAdress;
	}


	/**
	 * @param adminAdress the adminAdress to set
	 */
	public void setAdminAdress(Adress adminAdress) {
		this.adminAdress = adminAdress;
	}


	/**
	 * @return the adminContact
	 */
	public Contact getAdminContact() {
		return adminContact;
	}


	/**
	 * @param adminContact the adminContact to set
	 */
	public void setAdminContact(Contact adminContact) {
		this.adminContact = adminContact;
	}


	@Override
	public String toString() {
		return "Administrator [adminId=" + adminId + ", adminName=" + adminName + ", adminCPF=" + adminCPF
				+ ", adminUser=" + adminUser.getUserName() + ", adminAdress=" + adminAdress.getAdrCEP() + ", adminContact=" + adminContact.getContEmail() + "]";
	}

	
}