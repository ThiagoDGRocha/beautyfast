package br.com.beautyfast.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Administrator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;

	@Column(nullable = false, length = 30)
	@Size(min = 5, max = 30)
	private String adminName;

	@Column(nullable = false, unique = true, length = 14)
	@CPF
	private String adminCPF;

	@Column(nullable = false)
	@Min(value = 18, message = "O Administrador deve ser maior de idade!")
	@Max(value = 45, message = "O Administrador não pode ter mais de {max} anos de idade!")
	private int adminAge;

	@OneToOne
	private User adminUser;

	@OneToOne
	private Adress adminAdress;

	@OneToOne
	private Contact adminContact;

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

	/**
	 * @return the adminAge
	 */
	public int getAdminAge() {
		return adminAge;
	}

	/**
	 * @param adminAge the adminAge to set
	 */
	public void setAdminAge(int adminAge) {
		this.adminAge = adminAge;
	}

	@Override
	public String toString() {
		return "Administrator [adminId=" + adminId + ", adminName=" + adminName + ", adminCPF=" + adminCPF
				+ ", adminUser=" + adminUser.getUserName() + ", adminAdress=" + adminAdress.getAdrCEP()
				+ ", adminContact=" + adminContact.getContEmail() + "]";
	}

}