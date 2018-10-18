package com.beautyfast.modelManagedBean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.beautyfast.controller.ClientController;
import com.beautyfast.modelEntidade.Adress;
import com.beautyfast.modelEntidade.Client;
import com.beautyfast.modelEntidade.Contact;
import com.beautyfast.modelEntidade.User;

import utils.TypeUser;

@ManagedBean(name = "cadastroCliente")
public class CadastroClienteMB {

	private Adress adress;
	private Contact contact;
	private User user;
	private Client client;

	private String name;
	private String cpf;
	private int age;
	private String street;
	private String complement;
	private String city;
	private String state;
	private String cep;
	private String numeroContato;
	private String emailContato;
	private String psw;
	private String pswConfirm;

	public String cadastraCliente() {
		ClientController controller = new ClientController();
		adress = new Adress();
		contact = new Contact();
		user = new User();
		client = new Client();
		try {
			client.setClientName(name);
			client.setClientCPF(cpf);
			client.setClientAge(age);
			
			adress.setAdrStreet(street);
			adress.setAdrComplement(complement);
			adress.setAdrCity(city);
			adress.setAdrState(state);
			adress.setAdrCEP(cep);
			
			contact.setContEmail(emailContato);
			contact.setContNumber(numeroContato);
			
			user.setUserName(emailContato);
			user.setUserLastAccess(new Date());
			user.setUserActive(true);
			user.setUserType(TypeUser.CLIENT.getDescricao());
			if(this.psw.equals(this.pswConfirm)) {
			user.setUserPassword(psw);
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "As senhas não conferem!", "Persistencia inválida!"));
				return null;
			}
			client.setClientAdress(adress);
			client.setClientContact(contact);
			client.setClientUser(user);
			adress.setAdrClient(client);
			contact.setContactClient(client);
			user.setUserClient(client);
			
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", controller.saveClient(client)));
			return "index";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao salvar cliente!", "Persistencia inválida!"));
		}
		return null;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the complement
	 */
	public String getComplement() {
		return complement;
	}

	/**
	 * @param complement the complement to set
	 */
	public void setComplement(String complement) {
		this.complement = complement;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the numeroContato
	 */
	public String getNumeroContato() {
		return numeroContato;
	}

	/**
	 * @param numeroContato the numeroContato to set
	 */
	public void setNumeroContato(String numeroContato) {
		this.numeroContato = numeroContato;
	}

	/**
	 * @return the emailContato
	 */
	public String getEmailContato() {
		return emailContato;
	}

	/**
	 * @param emailContato the emailContato to set
	 */
	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}

	/**
	 * @return the psw
	 */
	public String getPsw() {
		return psw;
	}

	/**
	 * @param psw the psw to set
	 */
	public void setPsw(String psw) {
		this.psw = psw;
	}

	/**
	 * @return the pswConfirm
	 */
	public String getPswConfirm() {
		return pswConfirm;
	}

	/**
	 * @param pswConfirm the pswConfirm to set
	 */
	public void setPswConfirm(String pswConfirm) {
		this.pswConfirm = pswConfirm;
	}

}
