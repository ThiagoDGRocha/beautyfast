package com.beautyfast.controller;

import java.util.List;

import com.beautyfast.modelDAO.ClientJpaDAO;
import com.beautyfast.modelEntidade.Client;

public class ClientController {
	private static ClientJpaDAO instance;

	public ClientController() {
		instance = getInstanceClientJPA();
	}

	public static ClientJpaDAO getInstanceClientJPA() {
		if (instance == null) {
			return new ClientJpaDAO();
		} else {
			return instance;
		}
	}

	public String saveClient(Client client) {
		try {
			instance.persist(client);
			return "Cliente salvo com sucesso!\nFaça login para entrar!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao salvar cliente!\n" + e.getMessage();
		}
	}

	public String updateCient(Client client) {
		try {
			instance.merge(client);
			return "Cliente atualizado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao atualizar cliente!\n" + e.getMessage();
		}
	}
	
	public String deleteIdByClient(int idClient) {
		try {
			instance.removeById(idClient);
			return "Cliente removido com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao remover cliente\n" + e.getMessage();
		}
	}
	
	public List<Client> listAll(){
		try {
			return instance.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Client> listAllByTxt(String txt){
		try {
			return instance.findByTxt(txt);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
