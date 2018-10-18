package com.beautyfast.modelEntidade;

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
import javax.persistence.Table;

@Entity
@Table(name = "professional")
public class Professional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prof_id", unique = true, nullable = false)
	private int profId;

	@Column(name = "prof_name", nullable = false)
	private String profName;

	@Column(name = "prof_CPF", nullable = false)
	private String profCPF;

	@Column(name = "prof_age", nullable = false)
	private int profAge;

	@OneToOne
	private User profUser;

	@OneToOne
	private Adress profAdress;

	@OneToOne
	private Contact profContact;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Specialty> profListSpecialty;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Schedrule> profSListchedrules;

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
	 * @return the profSListchedrules
	 */
	public List<Schedrule> getProfSListchedrules() {
		return profSListchedrules;
	}

	/**
	 * @param profSListchedrules the profSListchedrules to set
	 */
	public void setProfSListchedrules(List<Schedrule> profSListchedrules) {
		this.profSListchedrules = profSListchedrules;
	}

}