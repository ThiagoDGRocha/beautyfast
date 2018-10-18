package com.beautyfast.modelManagedBean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.beautyfast.controller.LoginController;
import com.beautyfast.modelEntidade.User;

@ManagedBean(name = "login")
public class LoginMB {

	private String email;
	private String psw;

	public void autentica() {
		LoginController controller = new LoginController();
		try {
			switch (Integer.parseInt(controller.login(email, psw))) {
			case 1:
				this.createMessage(1);
			case 2:
				this.createMessage(2);
			case 3:
				this.createMessage(3);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro de Login - " + e.getMessage(), "Login inválido!"));
		}
	}

	private void createMessage(int typeUser) {
		try {
			String nomeUsuario = "";
			User user = LoginController.getInstanceUser();
			if (typeUser == 1) {
				nomeUsuario = user.getUserAdmin().getAdminName();
			} else if (typeUser == 2) {
				nomeUsuario = user.getUserProf().getProfName();
			} else {
				nomeUsuario = user.getUserClient().getClientName();
			}
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo(a) " + nomeUsuario, "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			message = new FacesMessage("",
					"Último acesso em: " + LoginController.getInstanceUser().getUserLastAccess().toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
			PrimeFaces.current().ajax().addCallbackParam("loggedIn", true);

			try {
				if (typeUser == 1) {
					ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
					ec.redirect(ec.getRequestContextPath() + "/homeAdmin.xhtml");
				} else if (typeUser == 2) {
					ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
					ec.redirect(ec.getRequestContextPath() + "/homeProf.xhtml");
				} else {
					ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
					ec.redirect(ec.getRequestContextPath() + "/homeClient.xhtml");
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			System.out.println(e.toString() + "\nErro na criação da mensagem!");
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
