package br.com.teujogo.enumeration;

public enum TipoElemento {

	HUMANOIDE(1, "Humanóide", "H"), VEICULO(2, "Veículo", "V"), ACAO(3, "Ação", "A"), TEMPO(4, "Tempo", "T"),
	ASSET(4, "Asset", "AT");

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
		} else if (ACAO.valor == valor) {
			return ACAO;
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
		} else if (ACAO.name().equals(valor)) {
			return ACAO;
		}
		return null;
	}

}
