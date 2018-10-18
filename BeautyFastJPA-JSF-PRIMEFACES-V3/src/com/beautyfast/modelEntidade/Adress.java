package com.beautyfast.modelEntidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adress")
public class Adress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adr_id", unique = true, nullable = false)
	private int adrId;

	@Column(name = "adr_street", nullable = false, length = 30)
	private String adrStreet;

	@Column(name = "adr_complement", nullable = false, length = 30)
	private String adrComplement;

	@Column(name = "adr_CEP", nullable = false, length = 9)
	private String adrCEP;

	@Column(name = "adr_city", nullable = false, length = 30)
	private String adrCity;

	@Column(name = "adr_state", nullable = false, length = 30)
	private String adrState;

	@OneToOne(mappedBy = "adminAdress", fetch = FetchType.LAZY)
	private Administrator adrAdmin;

	@OneToOne(mappedBy = "clientAdress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Client adrClient;

	@OneToOne(mappedBy = "profAdress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Professional adrProf;

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

	public Administrator getAdrAdmin() {
		return adrAdmin;
	}

	public void setAdrAdmin(Administrator adrAdmin) {
		this.adrAdmin = adrAdmin;
	}

	public Client getAdrClient() {
		return adrClient;
	}

	public void setAdrClient(Client adrClient) {
		this.adrClient = adrClient;
	}

	public Professional getAdrProf() {
		return adrProf;
	}

	public void setAdrProf(Professional adrProf) {
		this.adrProf = adrProf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Adress [adrId=" + adrId + ", adrStreet=" + adrStreet + ", adrComplement=" + adrComplement + ", adrCEP="
				+ adrCEP + ", adrCity=" + adrCity + ", adrState=" + adrState + ", adrAdmin=" + adrAdmin + ", adrClient="
				+ adrClient + ", adrProf=" + adrProf + "]";
	}
}