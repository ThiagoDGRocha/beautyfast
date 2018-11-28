package br.com.beautyfast.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;

	@Column(nullable = false)
	@Min(value = 16, message = "O cliente deve ter ao menos 16 anos!")
	private int clientAge;

	@Column(nullable = false, unique = true, length = 14)
	@CPF
	private String clientCPF;

	@Column(nullable = false, length = 30)
	@Size(min = 5, max = 30)
	private String clientName;
	
	@Column(nullable = false)
	@CreditCardNumber
	private String clientCreditCard;

	@OneToOne(cascade = CascadeType.ALL)
	private User clientUser;

	@OneToOne(cascade = CascadeType.ALL)
	private Adress clientAdress;

	@OneToOne(cascade = CascadeType.ALL)
	private Contact clientContact;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Schedrule> clientSchedrules;

	public Client() {
	}

	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientAge
	 */
	public int getClientAge() {
		return clientAge;
	}

	/**
	 * @param clientAge the clientAge to set
	 */
	public void setClientAge(int clientAge) {
		this.clientAge = clientAge;
	}

	/**
	 * @return the clientCPF
	 */
	public String getClientCPF() {
		return clientCPF;
	}

	/**
	 * @param clientCPF the clientCPF to set
	 */
	public void setClientCPF(String clientCPF) {
		this.clientCPF = clientCPF;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the clientUser
	 */
	public User getClientUser() {
		return clientUser;
	}

	/**
	 * @param clientUser the clientUser to set
	 */
	public void setClientUser(User clientUser) {
		this.clientUser = clientUser;
	}

	/**
	 * @return the clientAdress
	 */
	public Adress getClientAdress() {
		return clientAdress;
	}

	/**
	 * @param clientAdress the clientAdress to set
	 */
	public void setClientAdress(Adress clientAdress) {
		this.clientAdress = clientAdress;
	}

	/**
	 * @return the clientContact
	 */
	public Contact getClientContact() {
		return clientContact;
	}

	/**
	 * @param clientContact the clientContact to set
	 */
	public void setClientContact(Contact clientContact) {
		this.clientContact = clientContact;
	}

	/**
	 * @return the clientSchedrules
	 */
	public List<Schedrule> getClientSchedrules() {
		return clientSchedrules;
	}

	/**
	 * @param clientSchedrules the clientSchedrules to set
	 */
	public void setClientSchedrules(List<Schedrule> clientSchedrules) {
		this.clientSchedrules = clientSchedrules;
	}

	/**
	 * @return the clientCreditCard
	 */
	public String getClientCreditCard() {
		return clientCreditCard;
	}

	/**
	 * @param clientCreditCard the clientCreditCard to set
	 */
	public void setClientCreditCard(String clientCreditCard) {
		this.clientCreditCard = clientCreditCard;
	}

}