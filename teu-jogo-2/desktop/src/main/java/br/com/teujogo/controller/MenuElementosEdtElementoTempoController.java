package br.com.teujogo.controller;

import br.com.teujogo.componentes.ElementoJogo;
import br.com.teujogo.util.EdtElemento;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MenuElementosEdtElementoTempoController extends EdtElemento {

	public void atualiza(ElementoJogo elemento) {
		setElemento(elemento);
	}

	@FXML
	private void removeEleTmp(MouseEvent e) {
		PrincipalController.menuElementosControllerRef.removeElemento(getElemento());
		PrincipalController.menuElementosControllerRef.reset();
	}

}
