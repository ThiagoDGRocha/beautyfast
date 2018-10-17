package modeloEntidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "schedrule")
public class Schedrule {
	
	@Id
	@Column(name = "schedr_id")
	private int schedrId;
	@Column(name = "schedr_time")
	@NotBlank(message = "Hora não informada!")
	private String schedrTime;
	@Column(name = "schedr_day")
	@NotBlank(message = "Dia não informada!")
	private int schedrDay;
	@Column(name = "schedr_month")
	@NotBlank(message = "Mês não informada!")
	private int schedrMonth;
	@Column(name = "schedr_year")
	@NotBlank(message = "Ano não informada!")
	private int schedrYear;
	@Column(name = "schedr_duration")
	@NotBlank(message = "Duração não informada!")
	private int schedrProfSpecialtyDuration;
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
	public String getSchedrTime() {
		return schedrTime;
	}
	public void setSchedrTime(String schedrTime) {
		this.schedrTime = schedrTime;
	}
	public int getSchedrDay() {
		return schedrDay;
	}
	public void setSchedrDay(int schedrDay) {
		this.schedrDay = schedrDay;
	}
	public int getSchedrMonth() {
		return schedrMonth;
	}
	public void setSchedrMonth(int schedrMonth) {
		this.schedrMonth = schedrMonth;
	}
	public int getSchedrYear() {
		return schedrYear;
	}
	public void setSchedrYear(int schedrYear) {
		this.schedrYear = schedrYear;
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
}