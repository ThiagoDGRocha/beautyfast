package br.com.beautyfast.utils;

public enum TypeUser {
	ADMINISTRATOR(1), PROFESSIONAL(2), CLIENT(3);

	private int descricao;

	TypeUser(int descricao) {
		this.descricao = descricao;
	}

	public int getDescricao() {
		return this.descricao;
	}
}
