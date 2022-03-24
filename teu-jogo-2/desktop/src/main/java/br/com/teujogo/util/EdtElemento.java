package br.com.teujogo.util;

import br.com.teujogo.componentes.ElementoJogo;
import br.com.teujogo.ed.Elemento;
import br.com.teujogo.ed.Regra;

public class EdtElemento {
	private ElementoJogo elemento;

	private Elemento element;

	private Regra regra;

	public void atualiza(ElementoJogo ele) {

	}

	public void atualiza(Elemento element) {

	}

	public ElementoJogo getElemento() {
		return elemento;
	}

	public void setElemento(ElementoJogo elemento) {
		this.elemento = elemento;
	}

	public Elemento getElement() {
		return element;
	}

	public void setElement(Elemento element) {
		this.element = element;
	}

	public Regra getRegra() {
		return regra;
	}

	public void setRegra(Regra regra) {
		this.regra = regra;
	}

}
