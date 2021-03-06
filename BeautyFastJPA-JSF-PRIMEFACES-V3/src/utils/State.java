/**
 * 
 */
package utils;

public enum State {
	AC("AC", "Acre"), AL("AL", "Alagoas"), AP("AP", "Amap�"), AM("AM", "Amazonas"), BA("BA", "Bahia"),
	CE("CE", "Cear�"), DF("DF", "Distrito Federal"), ES("ES", "Esp�rito Santo"), GO("GO", "Goi�s"),
	MA("MA", "Maranh�o"), MT("MT", "Mato Grosso"), MS("MS", "Mato Grosso do Sul"), MG("MG", "Minas Gerais"),
	PA("PA", "Par�"), PB("PB", "Para�ba"), PR("PR", "Paran�"), PE("PE", "Pernambuco"), PI("PI", "Piau�"),
	RJ("RJ", "Rio de Janeiro"), RN("RN", "Rio Grande do Norte"), RS("RS", "Rio Grande do Sul"), RO("RO", "Rond�nia"),
	RR("RR", "Roraima"), SC("SC", "Santa Catarina"), SP("SP", "S�o Paulo"), SE("SE", "Sergipe"), TO("TO", "Tocantins");

	private String uf;
	private String nome;

	State(String sigla, String nome) {
		this.uf = sigla;
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public String getNome() {
		return nome;
	}
}
