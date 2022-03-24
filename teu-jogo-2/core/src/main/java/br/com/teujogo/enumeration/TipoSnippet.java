package br.com.teujogo.enumeration;

public enum TipoSnippet {

	GRAVIDADE(1, "Gravidade", "G = m * g", "G"), FORCA(2, "Força", "F = m * a", "F");

	private int valor;
	private String descricao;
	private String eq;
	private String label;

	private TipoSnippet(int valor, String descricao, String eq, String label) {
		this.valor = valor;
		this.descricao = descricao;
		this.eq = eq;
		this.label = label;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getEq() {
		return eq;
	}

	public String getLabel() {
		return label;
	}

	public int getValor() {
		return valor;
	}

	public static TipoSnippet getElemento(int valor) {
		if (GRAVIDADE.valor == valor) {
			return GRAVIDADE;
		} else if (FORCA.valor == valor) {
			return FORCA;
		}
		return null;
	}

	public static TipoSnippet getElementoByName(String valor) {
		if (GRAVIDADE.name().equals(valor)) {
			return GRAVIDADE;
		} else if (FORCA.name().equals(valor)) {
			return FORCA;
		}
		return null;
	}

}