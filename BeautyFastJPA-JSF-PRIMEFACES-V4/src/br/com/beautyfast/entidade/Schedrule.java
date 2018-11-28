package br.com.beautyfast.entidade;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;

@Entity
public class Schedrule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schedrId;

	@Column(nullable = false)
	@Future
	private Calendar schedrData; // data.set(year, month, date, hourOfDay, minute);

	@Column(nullable = false, length = 200)
	@Min(10)
	private String schedrPlaceOfCare;

	@ManyToOne
	private Specialty schedrProfSpecialty;

	@ManyToOne
	private Client schedrClient;

	@ManyToOne
	private Professional schedrProf;

	public int getSchedrId() {
		return schedrId;
	}

	public void setSchedrId(int schedrId) {
		this.schedrId = schedrId;
	}

	/**
	 * @return the schedrPlaceOfCare
	 */
	public String getSchedrPlaceOfCare() {
		return schedrPlaceOfCare;
	}

	/**
	 * @param schedrPlaceOfCare the schedrPlaceOfCare to set
	 */
	public void setSchedrPlaceOfCare(String schedrPlaceOfCare) {
		this.schedrPlaceOfCare = schedrPlaceOfCare;
	}

	public Specialty getSchedrProfSpecialty() {
		return schedrProfSpecialty;
	}

	public void setSchedrProfSpecialty(Specialty schedrProfSpecialty) {
		this.schedrProfSpecialty = schedrProfSpecialty;
	}

	public Client getSchedrClient() {
		return schedrClient;
	}

	public void setSchedrClient(Client schedrClient) {
		this.schedrClient = schedrClient;
	}

	public Professional getSchedrProf() {
		return schedrProf;
	}

	public void setSchedrProf(Professional schedrProf) {
		this.schedrProf = schedrProf;
	}

	public Calendar getSchedrData() {
		return schedrData;
	}

	public void setSchedrData(Calendar data) {
		this.schedrData = data;
	}
}