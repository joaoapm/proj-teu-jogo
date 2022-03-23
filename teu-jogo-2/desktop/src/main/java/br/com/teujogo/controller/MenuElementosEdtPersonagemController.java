package br.com.teujogo.controller;

import br.com.teujogo.ed.Elemento;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuElementosEdtPersonagemController {

	@FXML
	private Label lblPerso;

	public void atualiza(Elemento elemento) {
		lblPerso.setText(elemento.getTipoElemento().getDescricao());
	}

}
