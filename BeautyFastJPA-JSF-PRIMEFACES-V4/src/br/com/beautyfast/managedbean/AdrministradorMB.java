package br.com.beautyfast.managedbean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.beautyfast.dao.AdministratorJpaDAO;
import br.com.beautyfast.entidade.Administrator;
import br.com.beautyfast.entidade.Adress;
import br.com.beautyfast.entidade.Contact;
import br.com.beautyfast.entidade.User;
import br.com.beautyfast.utils.TypeUser;

@ManagedBean
@ViewScoped
public class AdrministradorMB {

	private Administrator admin;
	private AdministratorJpaDAO adminDAO;
	private Adress adress;
	private Contact contact;
	private User user;
	private String confirmSenha;

	@PostConstruct
	public void initObjects() {
		adminDAO = AdministratorJpaDAO.getInstance();
		adress = new Adress();
		contact = new Contact();
		user = new User();
		user.setUserActive(true);
		user.setUserLastAccess(new Date());
		user.setUserType(TypeUser.ADMINISTRATOR.getDescricao());
		String idAdmin = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("id");
		if (idAdmin != null) {
			this.admin = adminDAO.getById(Integer.parseInt(idAdmin));
		} else {
			this.admin = new Administrator();
		}
	}

	public List<Administrator> getListAll() {
		return adminDAO.findAll();
	}

	public String save() {
		try {
			if (user.getUserPassword().equals(confirmSenha)) {
				adress.setAdrAdmin(admin);
				user.setUserAdmin(admin);
				contact.setContEmail(user.getUserName());
				contact.setContactAdmin(admin);
				admin.setAdminAdress(adress);
				admin.setAdminContact(contact);
				admin.setAdminUser(user);
				adminDAO.persist(admin);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Administrador -" + admin.getAdminName() + "- salvo com sucesso!", "Sucesso"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "/restrict/administrador/listaadministradores.xhtml?faces-redirect-true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Falha ao salvar administrador!\nAs senhas não conférem!!", "Falha"));
				return null;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao salvar administrador!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public String delete(Administrator admin) {
		try {
			adminDAO.remove(admin);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Administrador -" + admin.getAdminName() + "- removido com sucesso!", "Sucesso"));
			return null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Falha ao remover administrador!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	// Botão atualizar da dataTable
	public String goToEditaAdmin(Administrator admin) {
		return "/restrict/administrador/editaadministrador.xhtml?faces-redirect=true&id=" + admin.getAdminId();
	}

	// Botão salvar atualização da página editaespecialidade.xhtml
	public String update() {
		try {
			if (user.getUserPassword().equals(confirmSenha)) {
				adress.setAdrAdmin(admin);
				user.setUserAdmin(admin);
				contact.setContEmail(user.getUserName());
				contact.setContactAdmin(admin);
				admin.setAdminAdress(adress);
				admin.setAdminContact(contact);
				admin.setAdminUser(user);
				adminDAO.merge(admin);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Administrador atualizado com sucesso!", "Sucesso"));
				return "/restrict/administrador/listaadministradores.xhtml?faces-redirect-true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Falha ao salvar administrador!\nAs senhas não conférem", "Falha"));
				return null;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao atualizar administrador!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public Administrator getAdministrator() {
		return admin;
	}

	public void setAdministrator(Administrator admin) {
		this.admin = admin;
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
