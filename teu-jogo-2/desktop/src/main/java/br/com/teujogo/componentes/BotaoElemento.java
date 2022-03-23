package br.com.teujogo.componentes;

import br.com.teujogo.enumeration.TipoElemento;
import javafx.scene.control.Button;

public class BotaoElemento extends Button{

	private TipoElemento tipoElemento;

	public TipoElemento getTipoElemento() {
		return tipoElemento;
	}

	public void setTipoElemento(TipoElemento tipoElemento) {
		this.tipoElemento = tipoElemento;
	}
	
}
