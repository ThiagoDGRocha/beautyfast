package modeloEntidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.ToString;

@Entity
@Table(name = "professional")
@ToString
public class Professional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prof_id", unique = true, nullable = false)
	private int profId;
	@Size(min = 6, max = 30, message = "O nome deve conter de {min} a {max} digitos!")
	@Column(name = "prof_name", unique = true, nullable = false)
	private String profName;
	@CPF(message = "CPF inv�lido!")
	@Column(name = "prof_CPF")
	private String profCPF;
	@Min(18)
	@Size(min = 1, max = 2, message = "A idade inv�lida ou n�o informada!")
	@Column(name = "prof_age")
	private int profAge;
	@NotBlank(message = "User n�o informado!")
	@JoinColumn(name = "prof_user", nullable = false)
	@OneToOne(mappedBy = "prof")
	private User profuser;
	
	
	@NotBlank(message = "Adress n�o informado!")
	@JoinColumn(name = "prof_adress")
	@OneToOne(mappedBy = "prof")
	private Adress profAdress;
	
	@NotBlank(message = "Contact n�o informado!")
	@JoinColumn(name = "prof_contact", nullable = false)
	@OneToOne(mappedBy = "prof")
	private Contact profContact;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "specialtyProfessional")
	@JoinColumn(name = "specialty_id")
	private List<Specialty> profListSpecialty;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schedrProf")
	@JoinColumn(name = "schedr_id")
	private List<Schedrule> profSchedrules;

	public int getProfId() {
		return profId;
	}

	public void setProfId(int profId) {
		this.profId = profId;
	}

	public User getProfUser() {
		return profuser;
	}

	public void setProfUser(User user) {
		this.profuser = user;
	}

	public Adress getProfAdress() {
		return profAdress;
	}

	public void setProfAdress(Adress adress) {
		this.profAdress = adress;
	}

	public Contact getProfContact() {
		return profContact;
	}

	public void setProfContact(Contact contact) {
		this.profContact = contact;
	}

	public List<Specialty> getProfListSpecialty() {
		return profListSpecialty;
	}

	public void setProfListSpecialty(List<Specialty> profListSpecialty) {
		this.profListSpecialty = profListSpecialty;
	}

	public List<Schedrule> getProfSchedrules() {
		return profSchedrules;
	}

	public void setProfSchedrules(List<Schedrule> profSchedrules) {
		this.profSchedrules = profSchedrules;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public String getProfCPF() {
		return profCPF;
	}

	public void setProfCPF(String profCPF) {
		this.profCPF = profCPF;
	}

	public int getProfAge() {
		return profAge;
	}

	public void setProfAge(int age) {
		this.profAge = age;
	}
}