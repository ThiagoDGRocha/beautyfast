package com.exerc01;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "pessoa")
public class Pessoa {

	private String nomeCompleto;
	private int idade;
	private String sexo;
	private List<Pessoa> lista;

	@PostConstruct
	public void init() {
		this.lista = new ArrayList<Pessoa>();
		Pessoa p = new Pessoa("Thiago Guimarães", 28, "MASCULINO");
		this.lista.add(p);
		p = new Pessoa("Carol Oliveira", 20, "FEMININO");
		this.lista.add(p);
		p = new Pessoa("Marcelena Rodrigues", 22, "FEMININO");
		this.lista.add(p);
		p = new Pessoa("Marcus Vinicius", 29, "MASCULINO");
		this.lista.add(p);

	}

	public Pessoa() {

	}

	public Pessoa(String nome, int idade, String sexo) {
		this.nomeCompleto = nome;
		this.idade = idade;
		this.sexo = sexo;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<Pessoa> getLista() {
		return this.lista;
	}

	@Override
	public String toString() {
		return "Exerc01MB [nomeCompleto=" + nomeCompleto + ", idade=" + idade + ", sexo=" + sexo + "]";
	}
}
