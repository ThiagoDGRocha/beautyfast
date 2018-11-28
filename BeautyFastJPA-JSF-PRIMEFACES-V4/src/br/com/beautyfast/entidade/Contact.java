package br.com.beautyfast.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contId;

	@Column(nullable = false)
	private String contNumber;

	@Column(nullable = false)
	@Email(message = "Endereço de email inválido!")
	private String contEmail;

	@OneToOne(mappedBy = "adminContact", fetch = FetchType.LAZY)
	private Administrator contactAdmin;

	@OneToOne(mappedBy = "clientContact", fetch = FetchType.LAZY)
	private Client contactClient;

	@OneToOne(mappedBy = "profContact", fetch = FetchType.LAZY)
	private Professional contactProf;

	/**
	 * @return the contId
	 */
	public int getContId() {
		return contId;
	}

	/**
	 * @param contId the contId to set
	 */
	public void setContId(int contId) {
		this.contId = contId;
	}

	/**
	 * @return the contNumber
	 */
	public String getContNumber() {
		return contNumber;
	}

	/**
	 * @param contNumber the contNumber to set
	 */
	public void setContNumber(String contNumber) {
		this.contNumber = contNumber;
	}

	/**
	 * @return the contEmail
	 */
	public String getContEmail() {
		return contEmail;
	}

	/**
	 * @param contEmail the contEmail to set
	 */
	public void setContEmail(String contEmail) {
		this.contEmail = contEmail;
	}

	public Administrator getContactAdmin() {
		return contactAdmin;
	}

	public void setContactAdmin(Administrator contactAdmin) {
		this.contactAdmin = contactAdmin;
	}

	public Client getContactClient() {
		return contactClient;
	}

	public void setContactClient(Client contactClient) {
		this.contactClient = contactClient;
	}

	public Professional getContactProf() {
		return contactProf;
	}

	public void setContactProf(Professional contactProf) {
		this.contactProf = contactProf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [contId=" + contId + ", contNumber=" + contNumber + ", contEmail=" + contEmail
				+ ", contactAdmin=" + contactAdmin + ", contactClient=" + contactClient + ", contactProf=" + contactProf
				+ "]";
	}
}