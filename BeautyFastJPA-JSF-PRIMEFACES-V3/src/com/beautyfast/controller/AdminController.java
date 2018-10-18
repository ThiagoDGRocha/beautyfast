package com.beautyfast.controller;

import java.util.List;

import com.beautyfast.modelDAO.AdministratorJpaDAO;
import com.beautyfast.modelEntidade.Administrator;

public class AdminController {
	private static AdministratorJpaDAO instance;

	public AdminController() {
		instance = getInstanceAdministratorJPA();
	}

	private static AdministratorJpaDAO getInstanceAdministratorJPA() {
		if (instance == null) {
			return new AdministratorJpaDAO();
		} else {
			return instance;
		}
	}

	public String saveAdministrator(Administrator administrator) {
		try {
			instance.persist(administrator);
			return "Administrador salvo com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao salvar administrador!\n" + e.getMessage();
		}
	}

	public String updateAdministrator(Administrator administrator) {
		try {
			instance.merge(administrator);
			return "Administrador atualizado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao atualizar administrador!\n" + e.getMessage();
		}
	}

	public String deleteByIdAdministrator(int idAdministrator) {
		try {
			instance.removeById(idAdministrator);
			return "Administrador removido com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao remover administrador\n" + e.getMessage();
		}
	}

	public List<Administrator> listAll() {
		try {
			return instance.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Administrator> listAllByTxt(String txt) {
		try {
			return instance.findByTxt(txt);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
