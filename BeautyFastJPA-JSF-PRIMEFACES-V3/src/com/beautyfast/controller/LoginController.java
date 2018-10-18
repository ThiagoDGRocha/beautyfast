package com.beautyfast.controller;

import com.beautyfast.modelDAO.LoginJpaDAO;
import com.beautyfast.modelEntidade.User;

public class LoginController {
	private static User userLogged;
	private static LoginJpaDAO instance;

	public LoginController() {
		instance = getInstanceUserJPA();
	}

	private LoginJpaDAO getInstanceUserJPA() {
		if (instance == null) {
			return new LoginJpaDAO();
		} else {
			return instance;
		}
	}

	public static User getInstanceUser() {
		if (userLogged == null) {
			return null;
		} else {
			return userLogged;
		}
	}

	public String login(String email, String psw) {
		try {
			userLogged = instance.userLogin(email);
			if(userLogged.getUserPassword().equals(psw)) {
				return String.valueOf((userLogged.getUserType()));
			}else {
				return "Usuário ou senha inválidos!";
			}
		}catch(Exception e) {
			return "Usuário não encontrado!";
		}
	}
}
