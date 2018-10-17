package modeloEntidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.ToString;

@Entity
@Table(name = "adress")
@ToString
public class Adress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adr_id", unique = true, nullable = false)
	private int adrId;
	@Size(min = 6, max = 30, message = "O nome da rua deve conter de {min} a {max} digitos!")
	@Column(name = "adr_street", unique = true, nullable = false, length = 30)
	private String adrStreet;
	@Size(min = 6, max = 30, message = "O complemento deve conter de {min} a {max} digitos!")
	@Column(name = "adr_complement", unique = true, nullable = false, length = 30)
	private String adrComplement;
	@Size(min = 9, max = 9, message = "O CEP deve conter de {min} digitos!")
	@Column(name = "adr_CEP", unique = true, length = 9)
	private String adrCEP;
	@Size(min = 6, max = 30, message = "A cidade deve conter de {min} digitos!")
	@Column(name = "adr_city", unique = true, nullable = false, length = 30)
	private String adrCity;
	@Size(min = 6, max = 30, message = "O estado deve conter de {min} digitos!")
	@Column(name = "adr_state", unique = true, nullable = false, length = 30)
	private String adrState;
	
	
	@OneToOne
	@JoinColumn(name = "admin_id")
	private Administrator admin;
	
	
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToOne
	@JoinColumn(name = "prof_id")
	private Professional prof;

	public int getAdrId() {
		return adrId;
	}

	public void setAdrId(int adrId) {
		this.adrId = adrId;
	}

	public String getAdrStreet() {
		return adrStreet;
	}

	public void setAdrStreet(String adrStreet) {
		this.adrStreet = adrStreet;
	}

	public String getAdrComplement() {
		return adrComplement;
	}

	public void setAdrComplement(String adrComplement) {
		this.adrComplement = adrComplement;
	}

	public String getAdrCEP() {
		return adrCEP;
	}

	public void setAdrCEP(String adrCEP) {
		this.adrCEP = adrCEP;
	}

	public String getAdrCity() {
		return adrCity;
	}

	public void setAdrCity(String adrCity) {
		this.adrCity = adrCity;
	}

	public String getAdrState() {
		return adrState;
	}

	public void setAdrState(String adrState) {
		this.adrState = adrState;
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