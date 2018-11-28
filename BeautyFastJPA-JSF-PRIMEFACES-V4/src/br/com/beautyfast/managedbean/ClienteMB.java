package br.com.beautyfast.managedbean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.beautyfast.dao.ClientJpaDAO;
import br.com.beautyfast.entidade.Client;
import br.com.beautyfast.entidade.Adress;
import br.com.beautyfast.entidade.Contact;
import br.com.beautyfast.entidade.User;
import br.com.beautyfast.utils.TypeUser;

@ManagedBean
@ViewScoped
public class ClienteMB {

	private Client client;
	private ClientJpaDAO clientDAO;
	private Adress adress;
	private Contact contact;
	private User user;
	private String confirmSenha;

	@PostConstruct
	public void initObjects() {
		clientDAO = ClientJpaDAO.getInstance();
		adress = new Adress();
		contact = new Contact();
		user = new User();
		user.setUserActive(true);
		user.setUserLastAccess(new Date());
		user.setUserType(TypeUser.CLIENT.getDescricao());
		String idClient = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("id");
		if (idClient != null) {
			this.client = clientDAO.getById(Integer.parseInt(idClient));
		} else {
			this.client = new Client();
		}
	}

	public List<Client> getListAll() {
		return clientDAO.findAll();
	}

	public String save() {
		try {
			if (user.getUserPassword().equals(confirmSenha)) {
				adress.setAdrClient(client);
				user.setUserClient(client);
				contact.setContEmail(user.getUserName());
				contact.setContactClient(client);
				client.setClientAdress(adress);
				client.setClientContact(contact);
				client.setClientUser(user);
				clientDAO.persist(client);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Cliente -" + client.getClientName() + "- salvo com sucesso!", "Sucesso"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "/restrict/client/listaclientes.xhtml?faces-redirect-true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Falha ao salvar cliente!\nAs senhas não conférem!", "Falha"));
				return null;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao salvar cliente!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public String delete(Client client) {
		try {
			clientDAO.remove(client);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cliente -" + client.getClientName() + "- removido com sucesso!", "Sucesso"));
			return null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Falha ao remover cliente!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	// Botão atualizar da dataTable
	public String goToEditaClient(Client client) {
		return "/restrict/client/editacliente.xhtml?faces-redirect=true&id=" + client.getClientId();
	}

	// Botão salvar atualização da página editaespecialidade.xhtml
	public String update() {
		try {
			if (user.getUserPassword().equals(confirmSenha)) {
				adress.setAdrClient(client);
				user.setUserClient(client);
				contact.setContEmail(user.getUserName());
				contact.setContactClient(client);
				client.setClientAdress(adress);
				client.setClientContact(contact);
				client.setClientUser(user);
				clientDAO.merge(client);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Cliente -" + client.getClientName() + "- atualizado com sucesso!", "Sucesso"));
				return "/restrict/client/listaclientes.xhtml?faces-redirect-true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Falha ao salvar cliente!\nAs senhas são diferentes", "Falha"));
				return null;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao atualizar cliente!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * @return the adress
	 */
	public Adress getAdress() {
		return adress;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
}
