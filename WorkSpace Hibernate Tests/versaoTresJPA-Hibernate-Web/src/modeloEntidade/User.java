package modeloEntidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "user")
@ToString
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private int userId;
	@Column(name = "user_name", unique = true, nullable = false)
	@Size(min = 6, max = 20, message = "O username deve conter de {min} a {max} digitos!")
	private String userName;
	@Column(name = "user_password", nullable = false)
	@Size(min = 6, max = 20, message = "O password deve conter de {min} a {max} digitos!")
	private String userPassword;
	@NotBlank(message = "último acesso não informado!")
	@Column(name = "last_access")
	@Temporal(TemporalType.DATE)
	private Date lastAccess;
	@NotBlank(message = "Status usuário não informada!")
	@Column(name = "user_active")
	private boolean userActive;
	@NotBlank(message = "Tipo usuário não inserido!")
	@Column(name = "user_Type")
	private String userType;
	@OneToOne
	@JoinColumn(name = "admin_id")
	private Administrator admin;
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@OneToOne
	@JoinColumn(name = "professional_id")
	private Professional prof;

	public User() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the admin
	 */
	public Administrator getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the prof
	 */
	public Professional getProf() {
		return prof;
	}

	/**
	 * @param prof the prof to set
	 */
	public void setProf(Professional prof) {
		this.prof = prof;
	}

}