package com.beautyfast.controller;

import java.util.List;

import com.beautyfast.modelDAO.SpecialtyJpaDAO;
import com.beautyfast.modelEntidade.Specialty;

public class SpecialtyController {
	private static SpecialtyJpaDAO instance;

	public SpecialtyController() {
		instance = getInstanceSpecialtyJPA();
	}

	private static SpecialtyJpaDAO getInstanceSpecialtyJPA() {
		if (instance == null) {
			return new SpecialtyJpaDAO();
		} else {
			return instance;
		}
	}

	public String saveSpecialty(Specialty specialty) {
		try {
			instance.persist(specialty);
			return "Especialidade salva com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao salvar especialidade!\n" + e.getMessage();
		}
	}

	public String updateSpecialty(Specialty specialty) {
		try {
			instance.merge(specialty);
			return "Administrador atualizado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao atualizar administrador!\n" + e.getMessage();
		}
	}

	public String deleteByIdSpecialty(int idSpecialty) {
		try {
			instance.removeById(idSpecialty);
			return "Administrador removido com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao remover administrador\n" + e.getMessage();
		}
	}

	public List<Specialty> listAll() {
		try {
			return instance.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Specialty> listAllByTxt(String txt) {
		try {
			return instance.findByTxt(txt);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
