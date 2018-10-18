package com.beautyfast.controller;

import java.util.List;

import com.beautyfast.modelDAO.ProfessionalJpaDAO;
import com.beautyfast.modelEntidade.Professional;

public class ProfController {
	private static ProfessionalJpaDAO instance;

	public ProfController() {
		instance = getInstanceProfessionalJPA();
	}

	private static ProfessionalJpaDAO getInstanceProfessionalJPA() {
		if (instance == null) {
			return new ProfessionalJpaDAO();
		} else {
			return instance;
		}
	}

	public String saveProfessional(Professional professional) {
		try {
			instance.persist(professional);
			return "Profissional salvo com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao salvar profissional!\n" + e.getMessage();
		}
	}

	public String updateProfessional(Professional professional) {
		try {
			instance.merge(professional);
			return "Profissional atualizado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao atualizar profissional!\n" + e.getMessage();
		}
	}

	public String deleteIdByProfessional(int idProfessional) {
		try {
			instance.removeById(idProfessional);
			return "Profissional removido com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao remover Profissional\n" + e.getMessage();
		}
	}

	public List<Professional> listAll() {
		try {
			return instance.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Professional> listAllByTxt(String txt) {
		try {
			return instance.findByTxt(txt);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
