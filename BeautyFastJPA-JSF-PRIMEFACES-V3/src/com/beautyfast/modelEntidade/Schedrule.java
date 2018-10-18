package com.beautyfast.modelEntidade;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedrule")
public class Schedrule {

	@Id
	@Column(name = "schedr_id")
	private int schedrId;

	@Column(name = "schedr_date")
	private Calendar schedrData; //data.set(year, month, date, hourOfDay, minute);

	@Column(name = "schedr_duration")
	private int schedrProfSpecialtyDuration;

	@ManyToOne
	private Specialty schedrProfSpecialty;

	@ManyToOne
	private Client schedrClient;

	@ManyToOne
	private Professional schedrProf;

	public Schedrule() {

	}

	public int getSchedrId() {
		return schedrId;
	}

	public void setSchedrId(int schedrId) {
		this.schedrId = schedrId;
	}

	public int getSchedrProfSpecialtyDuration() {
		return schedrProfSpecialtyDuration;
	}

	public void setSchedrProfSpecialtyDuration(int schedrProfSpecialtyDuration) {
		this.schedrProfSpecialtyDuration = schedrProfSpecialtyDuration;
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

	public Calendar getData() {
		return schedrData;
	}

	public void setData(Calendar data) {
		this.schedrData = data;
	}
}