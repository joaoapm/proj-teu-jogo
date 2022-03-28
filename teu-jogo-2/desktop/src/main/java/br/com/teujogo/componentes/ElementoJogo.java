package br.com.teujogo.componentes;

import br.com.teujogo.enumeration.TipoElemento;
import br.com.teujogo.enumeration.TipoTempo;
import javafx.scene.control.Button;

public class ElementoJogo extends Button {

	private TipoElemento tipoElemento;

	private TipoTempo tipoTempo;
	private Integer tempo;
	private String aoTerminar;
	private Integer aCada;
	private String aoACada;
	private String txtRegra;

	public TipoElemento getTipoElemento() {
		return tipoElemento;
	}

	public void setTipoElemento(TipoElemento tipoElemento) {
		this.tipoElemento = tipoElemento;
	}

	public TipoTempo getTipoTempo() {
		return tipoTempo;
	}

	public void setTipoTempo(TipoTempo tipoTempo) {
		this.tipoTempo = tipoTempo;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public String getAoTerminar() {
		return aoTerminar;
	}

	public void setAoTerminar(String aoTerminar) {
		this.aoTerminar = aoTerminar;
	}

	public Integer getaCada() {
		return aCada;
	}

	public void setaCada(Integer aCada) {
		this.aCada = aCada;
	}

	public String getAoACada() {
		return aoACada;
	}

	public void setAoACada(String aoACada) {
		this.aoACada = aoACada;
	}

	public String getTxtRegra() {
		return txtRegra;
	}

	public void setTxtRegra(String txtRegra) {
		this.txtRegra = txtRegra;
	}

}
