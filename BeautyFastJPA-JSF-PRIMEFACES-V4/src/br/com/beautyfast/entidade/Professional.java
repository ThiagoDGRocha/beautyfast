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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Professional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profId;

	@Column(nullable = false, length = 30)
	@Size(min = 5, max = 30)
	private String profName;

	@Column(nullable = false, unique = true, length = 14)
	@CPF(message = "CPF inválido!")
	private String profCPF;

	@Column(nullable = false)
	@Min(value = 18, message = "O Profissional deve ser maior de idade!")
	@Max(value = 45, message = "O Profissional não pode ter mais de 45 anos de idade!")
	private int profAge;

	@OneToOne(cascade = CascadeType.ALL)
	private User profUser;

	@OneToOne(cascade = CascadeType.ALL)
	private Adress profAdress;

	@OneToOne(cascade = CascadeType.ALL)
	private Contact profContact;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Specialty> profListSpecialty;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Schedrule> profListSchedrules;

	/**
	 * @return the profId
	 */
	public int getProfId() {
		return profId;
	}

	/**
	 * @param profId the profId to set
	 */
	public void setProfId(int profId) {
		this.profId = profId;
	}

	/**
	 * @return the profName
	 */
	public String getProfName() {
		return profName;
	}

	/**
	 * @param profName the profName to set
	 */
	public void setProfName(String profName) {
		this.profName = profName;
	}

	/**
	 * @return the profCPF
	 */
	public String getProfCPF() {
		return profCPF;
	}

	/**
	 * @param profCPF the profCPF to set
	 */
	public void setProfCPF(String profCPF) {
		this.profCPF = profCPF;
	}

	/**
	 * @return the profAge
	 */
	public int getProfAge() {
		return profAge;
	}

	/**
	 * @param profAge the profAge to set
	 */
	public void setProfAge(int profAge) {
		this.profAge = profAge;
	}

	/**
	 * @return the profUser
	 */
	public User getProfUser() {
		return profUser;
	}

	/**
	 * @param profUser the profUser to set
	 */
	public void setProfUser(User profUser) {
		this.profUser = profUser;
	}

	/**
	 * @return the profAdress
	 */
	public Adress getProfAdress() {
		return profAdress;
	}

	/**
	 * @param profAdress the profAdress to set
	 */
	public void setProfAdress(Adress profAdress) {
		this.profAdress = profAdress;
	}

	/**
	 * @return the profContact
	 */
	public Contact getProfContact() {
		return profContact;
	}

	/**
	 * @param profContact the profContact to set
	 */
	public void setProfContact(Contact profContact) {
		this.profContact = profContact;
	}

	/**
	 * @return the profListSpecialty
	 */
	public List<Specialty> getProfListSpecialty() {
		return profListSpecialty;
	}

	/**
	 * @param profListSpecialty the profListSpecialty to set
	 */
	public void setProfListSpecialty(List<Specialty> profListSpecialty) {
		this.profListSpecialty = profListSpecialty;
	}

	/**
	 * @return the profListSchedrules
	 */
	public List<Schedrule> getProfListSchedrules() {
		return profListSchedrules;
	}

	/**
	 * @param profListSchedrules the profListSchedrules to set
	 */
	public void setProfListSchedrules(List<Schedrule> profListSchedrules) {
		this.profListSchedrules = profListSchedrules;
	}

}