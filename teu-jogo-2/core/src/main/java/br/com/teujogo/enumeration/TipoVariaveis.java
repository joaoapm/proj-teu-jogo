package br.com.teujogo.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum TipoVariaveis {

	GRAVIDADE(1, "Gravidade", "G"), FORCA(2, "Força", "F"), MASSA(2, "Massa", "m"), ACELERACAO(2, "Aceleração", "a"),
	CONST_GRAV(2, "Constante Gravitacional", "g");

	private int valor;
	private String descricao;
	private String label;

	private TipoVariaveis(int valor, String descricao, String label) {
		this.valor = valor;
		this.descricao = descricao;
		this.label = label;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getLabel() {
		return label;
	}

	public int getValor() {
		return valor;
	}

	public static TipoVariaveis getElemento(int valor) {
		if (GRAVIDADE.valor == valor) {
			return GRAVIDADE;
		} else if (FORCA.valor == valor) {
			return FORCA;
		} else if (MASSA.valor == valor) {
			return MASSA;
		} else if (ACELERACAO.valor == valor) {
			return ACELERACAO;
		} else if (CONST_GRAV.valor == valor) {
			return CONST_GRAV;
		}
		return null;
	}

	public static TipoVariaveis getElementoByName(String valor) {
		if (GRAVIDADE.name().equals(valor)) {
			return GRAVIDADE;
		} else if (FORCA.name().equals(valor)) {
			return FORCA;
		} else if (MASSA.name().equals(valor)) {
			return MASSA;
		} else if (ACELERACAO.name().equals(valor)) {
			return ACELERACAO;
		} else if (CONST_GRAV.name().equals(valor)) {
			return CONST_GRAV;
		}
		return null;
	}
	
	public static TipoVariaveis getElementoByLabel(String valor) {
		if (GRAVIDADE.getLabel().equals(valor)) {
			return GRAVIDADE;
		} else if (FORCA.getLabel().equals(valor)) {
			return FORCA;
		} else if (MASSA.getLabel().equals(valor)) {
			return MASSA;
		} else if (ACELERACAO.getLabel().equals(valor)) {
			return ACELERACAO;
		} else if (CONST_GRAV.getLabel().equals(valor)) {
			return CONST_GRAV;
		}
		return null;
	}

	public static List<String> getLabelList() {
		List<String> lbl = new ArrayList<String>();
		lbl.add(GRAVIDADE.getLabel());
		lbl.add(FORCA.getLabel());
		lbl.add(MASSA.getLabel());
		lbl.add(ACELERACAO.getLabel());
		lbl.add(CONST_GRAV.getLabel());
		return lbl;
	}
}