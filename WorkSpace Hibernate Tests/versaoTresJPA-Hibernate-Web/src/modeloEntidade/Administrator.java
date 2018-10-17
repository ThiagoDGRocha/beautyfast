package modeloEntidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "administrator")
public class Administrator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id", unique = true, nullable = false)
	private int adminId;
	@Column(name = "admin_name")
	@Size(min = 6, max = 30, message = "O nome deve conter de {min} a {max} digitos!")
	private String adminName;
	@CPF(message = "CPF inválido!")
	@Column(name = "admin_CPF")
	private String adminCPF;
	@NotBlank(message = "User não informado!")
	@Column(name = "admin_user", unique = true, nullable = false)
	@OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
	private User adminUser;

	@NotBlank(message = "Adress não informado!")
	@Column(name = "admin_adress", nullable = false)
	@OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
	private Adress adminAdress;

	@NotBlank(message = "Contact não informado!")
	@Column(name = "admin_contact", unique = true, nullable = false)
	@OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
	private Contact adminContact;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminCPF() {
		return adminCPF;
	}

	public void setAdminCPF(String adminCPF) {
		this.adminCPF = adminCPF;
	}

	public User getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(User adminUser) {
		this.adminUser = adminUser;
	}

	public Adress getAdminAdress() {
		return adminAdress;
	}

	public void setAdminAdress(Adress adminAdress) {
		this.adminAdress = adminAdress;
	}

	public Contact getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(Contact adminContact) {
		this.adminContact = adminContact;
	}

}