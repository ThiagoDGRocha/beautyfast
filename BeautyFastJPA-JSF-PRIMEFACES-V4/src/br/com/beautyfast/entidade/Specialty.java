package br.com.beautyfast.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Specialty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int specialtyId;

	@Column(nullable = false, unique = true, length = 30)
	@Size(min = 5, max = 30)
	private String specialtyName;

	@Column(nullable = false, length= 100)
	@Size(min = 10, max = 100)
	private String specialtyDescription;

	@Column(nullable = false)
	@Min(5)
	private int specialtyDuration;

	@Column(nullable = false)
	@DecimalMin("00.50")
	private double specialtyPrice;

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

	public double getSpecialtyPrice() {
		return specialtyPrice;
	}

	public void setSpecialtyPrice(double specialtyPrice) {
		this.specialtyPrice = specialtyPrice;
	}

}