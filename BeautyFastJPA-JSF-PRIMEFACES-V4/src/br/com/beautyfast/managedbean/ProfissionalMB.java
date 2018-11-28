package br.com.beautyfast.managedbean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;

import br.com.beautyfast.dao.ProfessionalJpaDAO;
import br.com.beautyfast.entidade.Adress;
import br.com.beautyfast.entidade.Contact;
import br.com.beautyfast.entidade.Professional;
import br.com.beautyfast.entidade.Specialty;
import br.com.beautyfast.entidade.User;
import br.com.beautyfast.utils.TypeUser;

@ManagedBean
@ViewScoped
public class ProfissionalMB {

	private Professional prof;
	private ProfessionalJpaDAO profDAO;
	private Adress adress;
	private Contact contact;
	private User user;
	private String confirmSenha;
	private List<Specialty> listSpecialties;

	@PostConstruct
	public void initObjects() {
		profDAO = ProfessionalJpaDAO.getInstance();

		String idProf = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("id");
		if (idProf != null) {
			prof = profDAO.getById(Integer.parseInt(idProf));
		} else {
			prof = new Professional();
			prof.setProfAdress(new Adress());
			prof.setProfContact(new Contact());
			user = new User();
			user.setUserActive(true);
			user.setUserLastAccess(new Date());
			user.setUserType(TypeUser.PROFESSIONAL.getDescricao());
			prof.setProfUser(user);
		}
	}

	public List<Professional> getListAll() {
		return profDAO.findAll();
	}

	public String save() {
		try {
			if (user.getUserPassword().equals(getConfirmSenha())) {
				prof.getProfContact().setContEmail(prof.getProfUser().getUserName());
				profDAO.persist(prof);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Profissional -" + prof.getProfName() + "- salvo com sucesso!", "Sucesso"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				return "/restrict/profissional/listaprofissionais.xhtml?faces-redirect-true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Falha ao salvar profissional!\nAs senhas não diferentes!", "Falha"));
				return null;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao salvar profissional!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public String delete(Professional prof) {
		try {
			profDAO.remove(prof);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Profe -" + prof.getProfName() + "- removido com sucesso!", "Sucesso"));
			return null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Falha ao remover profe!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	// Botão atualizar da dataTable
	public String goToEditaProf(Professional prof) {
		return "/restrict/prof/editaprofe.xhtml?faces-redirect=true&id=" + prof.getProfId();
	}

	// Botão salvar atualização da página editaespecialidade.xhtml
	public String update() {
		try {
			if (user.getUserPassword().equals(getConfirmSenha())) {
				adress.setAdrProf(prof);
				user.setUserProf(prof);
				contact.setContEmail(user.getUserName());
				contact.setContactProf(prof);
				prof.setProfAdress(adress);
				prof.setProfContact(contact);
				prof.setProfUser(user);
				profDAO.merge(prof);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Profe -" + prof.getProfName() + "- atualizado com sucesso!", "Sucesso"));
				return "/restrict/prof/listaprofes.xhtml?faces-redirect-true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Falha ao salvar profe!\nAs senhas são diferentes", "Falha"));
				return null;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao atualizar profe!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}
	
	public Professional getProf() {
		return prof;
	}

	public void setProf(Professional prof) {
		this.prof = prof;
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

	/**
	 * @return the confirmSenha
	 */
	public String getConfirmSenha() {
		return confirmSenha;
	}

	/**
	 * @param confirmSenha the confirmSenha to set
	 */
	public void setConfirmSenha(String confirmSenha) {
		this.confirmSenha = confirmSenha;
	}
}
