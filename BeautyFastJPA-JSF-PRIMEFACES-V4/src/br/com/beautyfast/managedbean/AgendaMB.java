package br.com.beautyfast.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.beautyfast.dao.SchedruleJpaDAO;
import br.com.beautyfast.entidade.Client;
import br.com.beautyfast.entidade.Professional;
import br.com.beautyfast.entidade.Schedrule;
import br.com.beautyfast.entidade.Specialty;
import br.com.beautyfast.utils.Utils;

@ManagedBean
@ViewScoped
public class AgendaMB {

	private SchedruleJpaDAO schedruleDAO;
	private Schedrule schedrule;
	private Client client;
	private Professional prof;
	private List<Specialty> listSpecialty;

	@PostConstruct
	public void initObjects() {
		schedruleDAO = SchedruleJpaDAO.getInstance();
		client = (Client) Utils.getObjectSession("Cliente");
		prof = (Professional) Utils.getObjectSession("Professional");
		String idSchedrule = (String) Utils.getObjectSession("id");
		if (idSchedrule != null) {
			this.schedrule = schedruleDAO.getById(Integer.parseInt(idSchedrule));
		} else {
			this.schedrule = new Schedrule();
		}
	}

	public List<Schedrule> getListAll() {
		return schedruleDAO.findAll();
	}

	public String getClientName() {
		// client.getClientName()
		return "Thiago";
	}

	public List<Schedrule> getListByClient() {
		return client.getClientSchedrules();
	}

	public List<Schedrule> getListByProfessional() {
		return prof.getProfListSchedrules();
	}

	public String save() {
		try {
			schedruleDAO.persist(schedrule);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento realizado com sucesso!", "Sucesso"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "/restrict/agenda/novoagendamento.xhtml?faces-redirect-true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao realizar agendamento!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public String delete(Schedrule schedrule) {
		try {
			schedruleDAO.remove(schedrule);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Agendamento -" + schedrule.getSchedrId() + "- removido com sucesso!", "Sucesso"));
			return null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Falha ao remover agendamento!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	// Botão atualizar da dataTable
	public String goToEditaSchedrule(Schedrule schedrule) {
		return "/restrict/schedrule/editaagenda.xhtml?faces-redirect=true&id=" + schedrule.getSchedrId();
	}

	// Botão salvar atualização da página editaagenda.xhtml
	public String update() {
		try {
			schedruleDAO.merge(schedrule);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Agendamento -" + schedrule.getSchedrId() + "- atualizado com sucesso!", "Sucesso"));
			return "/restrict/agenda/listaagendas.xhtml?faces-redirect-true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao atualizar schedrulee!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public void onProfessionalChange() {
		setListSpecialty(schedrule.getSchedrProf().getProfListSpecialty());
	}

	public Schedrule getSchedrule() {
		return schedrule;
	}

	public void setSchedrule(Schedrule schedrule) {
		this.schedrule = schedrule;
	}

	/**
	 * @return the listSpecialty
	 */
	public List<Specialty> getListSpecialty() {
		return listSpecialty;
	}

	/**
	 * @param listSpecialty the listSpecialty to set
	 */
	public void setListSpecialty(List<Specialty> listSpecialty) {
		this.listSpecialty = listSpecialty;
	}
}
