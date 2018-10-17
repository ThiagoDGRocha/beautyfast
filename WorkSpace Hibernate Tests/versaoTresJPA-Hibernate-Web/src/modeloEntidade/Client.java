package modeloEntidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.ToString;

@Entity
@Table(name = "client")
@ToString
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id", unique = true, nullable = false)
	private int clientId;
	@Min(16)
	@Column(name = "client_age", nullable = false)
	@Size(min = 1, max = 2, message = "A idade inválida ou não informada!")
	private int clientAge;
	@CPF(message = "CPF inválido!")
	@Column(name = "client_CPF", nullable = false)
	private String clientCPF;
	@Column(name = "client_name", nullable = false)
	@Size(min = 8, max = 30, message = "O nome deve conter de {min} a {max} digitos!")
	private String clientName;
	@NotBlank(message = "User não informado!")
	@Column(name = "client_user", unique = true, nullable = false)
	@OneToOne
	private User clientUser;
	@NotBlank(message = "Adress não informado!")
	@Column(name = "client_adress", nullable = false)
	@OneToOne
	private Adress clientAdress;
	
	@NotBlank(message = "Contact não informado!")
	@Column(name = "client_contact", unique = true, nullable = false)
	@OneToOne
	private Contact clientContact;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schedrClient")
	@JoinColumn(name="schedr_id")
	private List<Schedrule> clientSchedrules;

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

}