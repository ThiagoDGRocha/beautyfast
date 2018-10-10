package com.beautyfast.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "login")
public class LoginMB {

	private String user;
	private String psw;

	public String autenticar() {
		if (this.user.equals("admin")) {
			return "homeAdmin";
		} else if (this.user.equals("prof")) {
			return "homeProf";
		} else if (this.user.equals("client")) {
			return "homeCliente";
		} else {
			FacesMessage fm = new FacesMessage("Login inválido!");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
			return null;
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
}
