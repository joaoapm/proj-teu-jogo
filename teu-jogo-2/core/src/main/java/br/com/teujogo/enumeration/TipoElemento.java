package br.com.teujogo.enumeration;

public enum TipoElemento {

	HUMANOIDE(1, "Humanóide", "H"), VEICULO(2, "Veículo", "V"), TEMPO(3, "Tempo", "T");

	private int valor;
	private String descricao;
	private String txtBtn;

	private TipoElemento(int valor, String descricao, String txtBt) {
		this.valor = valor;
		this.descricao = descricao;
		this.txtBtn = txtBt;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getValor() {
		return valor;
	}

	public String getTxtBtn() {
		return txtBtn;
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

	public static TipoElemento getElementoByName(String valor) {
		if (HUMANOIDE.name().equals(valor)) {
			return HUMANOIDE;
		} else if (VEICULO.name().equals(valor)) {
			return VEICULO;
		} else if (TEMPO.name().equals(valor)) {
			return TEMPO;
		}
		return null;
	}

}
