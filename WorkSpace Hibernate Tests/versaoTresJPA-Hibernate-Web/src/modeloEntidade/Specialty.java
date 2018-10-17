package modeloEntidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "specialty")
public class Specialty {

	@Id
	@Column(name = "specialty_id")
	private int specialtyId;

	@Column(name = "specialty_name")
	@Size(min = 8, max = 15, message = "O nome deve conter de {min} a {max} digitos!")
	private String specialtyName;

	@Column(name = "specialty_description")
	@Size(min = 8, max = 50, message = "A descrição deve conter de {min} a {max} digitos!")
	private String specialtyDescription;

	@Column(name = "specialty_duration")
	@Min(10)
	@NotBlank(message = "Duração não informada!")
	private int specialtyDuration;

	@Min(10)
	@Column(name = "specialty_price")
	@NotBlank(message = "Preço não informada!")
	private Double specialtyPrice;

	@ManyToOne
	@JoinTable(name = "prof_id")
	private Professional specialtyProfessional;

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
		return specialtyProfessional;
	}

	/**
	 * @param professional the professional to set
	 */
	public void setProfessional(Professional specialtyProfessional) {
		this.specialtyProfessional = specialtyProfessional;
	}

}