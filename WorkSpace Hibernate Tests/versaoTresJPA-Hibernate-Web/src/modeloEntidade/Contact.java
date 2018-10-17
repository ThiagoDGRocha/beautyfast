package modeloEntidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.ToString;

@Entity
@Table(name = "contact")
@ToString
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cont_id", unique = true, nullable = false)
	private int contId;
	@Column(name = "cont_Number", nullable = false)
	@Size(min = 8, max = 30, message = "O número de telefone deve conter de {min} a {max} digitos!")
	private String contNumber;
	@Email
	@Column(name = "cont_email", nullable = false)
	private String contEmail;

	@OneToOne
	@JoinColumn(name = "admin_id")
	private Administrator admin;
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@OneToOne
	@JoinColumn(name = "prof_id")
	private Professional prof;

	public Contact() {
		super();
	}

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

	/**
	 * @return the admin
	 */
	public Administrator getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the prof
	 */
	public Professional getProf() {
		return prof;
	}

	/**
	 * @param prof the prof to set
	 */
	public void setProf(Professional prof) {
		this.prof = prof;
	}
}