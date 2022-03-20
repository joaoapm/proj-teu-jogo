package br.com.teujogo.enumeration;

public enum TipoElemento {

	HUMANOIDE(1, "Humanóide"), 
	VEICULO(2, "Veículo"), 
	TEMPO(3, "Tempo");

	private int valor;
	private String descricao;

	private TipoElemento(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getValor() {
		return valor;
	}

	public static TipoElemento getElemento(int valor) {
		if (HUMANOIDE.valor == valor) {
			return HUMANOIDE;
		} else if (VEICULO.valor == valor) {
			return VEICULO;
		} else if (TEMPO.valor == valor) {
			return TEMPO;
		}
		return null;
	}

}
