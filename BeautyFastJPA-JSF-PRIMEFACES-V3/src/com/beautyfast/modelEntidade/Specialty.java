package com.beautyfast.modelEntidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "specialty")
public class Specialty {

	@Id
	@Column(name = "specialty_id")
	private int specialtyId;

	@Column(name = "specialty_name")
	private String specialtyName;

	@Column(name = "specialty_description")
	private String specialtyDescription;

	@Column(name = "specialty_duration")
	private int specialtyDuration;

	@Column(name = "specialty_price")
	private Double specialtyPrice;

	@ManyToOne
	private Professional specialtyProf;

	public int getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	public String getSpecialtyDescription() {
		return specialtyDescription;
	}

	public void setSpecialtyDescription(String specialtyDescription) {
		this.specialtyDescription = specialtyDescription;
	}

	public int getSpecialtyDuration() {
		return specialtyDuration;
	}

	public void setSpecialtyDuration(int specialtyDuration) {
		this.specialtyDuration = specialtyDuration;
	}

	public Double getSpecialtyPrice() {
		return specialtyPrice;
	}

	public void setSpecialtyPrice(Double specialtyPrice) {
		this.specialtyPrice = specialtyPrice;
	}

	/**
	 * @return the professional
	 */
	public Professional getProfessional() {
		return specialtyProf;
	}

	/**
	 * @param professional the professional to set
	 */
	public void setProfessional(Professional specialtyProfessional) {
		this.specialtyProf = specialtyProfessional;
	}

}