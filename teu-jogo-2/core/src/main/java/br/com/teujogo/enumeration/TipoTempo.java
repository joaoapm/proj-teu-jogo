package br.com.teujogo.enumeration;

public enum TipoTempo {

	DECRESCENTE(0, "Decrescente"), CRESCENTE(1, "Crescente");

	private int valor;
	private String descricao;

	private TipoTempo(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getValor() {
		return valor;
	}

	public static TipoTempo getElemento(int valor) {
		if (DECRESCENTE.valor == valor) {
			return DECRESCENTE;
		} else if (CRESCENTE.valor == valor) {
			return CRESCENTE;
		}
		return null;
	}

	public static TipoTempo getElementoByName(String valor) {
		if (DECRESCENTE.name().equals(valor)) {
			return DECRESCENTE;
		} else if (CRESCENTE.name().equals(valor)) {
			return CRESCENTE;
		}
		return null;
	}

}
