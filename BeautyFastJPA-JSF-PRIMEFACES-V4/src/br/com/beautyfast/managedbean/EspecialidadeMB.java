package br.com.beautyfast.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.beautyfast.dao.SpecialtyJpaDAO;
import br.com.beautyfast.entidade.Specialty;

@ManagedBean
@ViewScoped
public class EspecialidadeMB {

	private Specialty specialty;
	private SpecialtyJpaDAO specialtyDAO;

	@PostConstruct
	public void initObjects() {
		specialtyDAO = SpecialtyJpaDAO.getInstance();
		String idEspecialidade = (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");
		if (idEspecialidade != null) {
			this.specialty = specialtyDAO.getById(Integer.parseInt(idEspecialidade));
		} else {
			this.specialty = new Specialty();
		}
	}

	public List<Specialty> getListAll() {
		return specialtyDAO.findAll();
	}

	public String save() {
		try {
			specialtyDAO.persist(specialty);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Especialidade salva com sucesso!", "Sucesso"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "/restrict/especialidade/listaespecialidades.xhtml?faces-redirect-true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao salvar especialidade!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public String delete(Specialty specialty) {
		try {
			specialtyDAO.remove(specialty);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Especialidade -" + specialty.getSpecialtyName() + "- removida com sucesso!", "Sucesso"));
			return null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Falha ao remover especialidade!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	// Botão atualizar da dataTable
	public String goToEditaEspecialidade(Specialty specialty) {
		return "/restrict/especialidade/editaespecialidade.xhtml?faces-redirect=true&id=" + specialty.getSpecialtyId();
	}

	// Botão salvar atualização da página editaespecialidade.xhtml
	public String update() {
		try {
			specialtyDAO.merge(specialty);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Especialidade atualizada com sucesso!", "Sucesso"));
			return "/restrict/especialidade/listaespecialidades.xhtml?faces-redirect-true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Falha ao atualizar especialidade!\n" + e.getMessage(), "Falha"));
			return null;
		}
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
}
