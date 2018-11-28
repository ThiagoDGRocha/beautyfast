package br.com.beautyfast.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.beautyfast.dao.LoginJpaDAO;
import br.com.beautyfast.entidade.User;

@ManagedBean(name = "login")
@SessionScoped
public class LoginMB {

	private String email;
	private String psw;
	private LoginJpaDAO loginDAO;
	private User user;

	public void autentica() {
		loginDAO = LoginJpaDAO.getInstance();
		try {
			user = loginDAO.userLogin(email);

			switch (user.getUserType()) {
			case 1:
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Administrador", user);
				this.redireciona(1);
			case 2:
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Profissional", user);
				this.redireciona(2);
			case 3:
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Cliente", user);
				this.redireciona(3);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro de Login - " + e.getMessage(), "Login inválido!"));
		}
	}

	private String redireciona(int typeUser) {
		try {
			String nomeUsuario = "";
			if (typeUser == 1) {
				nomeUsuario = user.getUserAdmin().getAdminName();
			} else if (typeUser == 2) {
				nomeUsuario = user.getUserProf().getProfName();
			} else {
				nomeUsuario = user.getUserClient().getClientName();
			}

			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo(a) " + nomeUsuario, ""));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Último acesso em: " + user.getUserLastAccess().toString(), ""));
			PrimeFaces.current().ajax().addCallbackParam("loggedIn", true);

			if (typeUser == 1) {
				return "/restric/administrador/principaladmin.xhtml";
			} else if (typeUser == 2) {
				return "/restrict/profissional/principalprof.xhtml";
			} else {
				return "/restrict/cliente/principalcliente.xhtml";
			}
		} catch (Exception e) {
			return "Erro no redirecionamento!\n" + e.getMessage();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
}
