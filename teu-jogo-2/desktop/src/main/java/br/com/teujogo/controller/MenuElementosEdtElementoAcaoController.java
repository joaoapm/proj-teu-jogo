package br.com.teujogo.controller;

import br.com.teujogo.componentes.ElementoJogo;
import br.com.teujogo.util.EdtElemento;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MenuElementosEdtElementoAcaoController extends EdtElemento {

	public void atualiza(ElementoJogo elemento) {
		setElemento(elemento);
	}

	@FXML
	private void fechared(MouseEvent e) {
		PrincipalController.menuElementosControllerRef.reset();
	}

	@FXML
	private void removerReg(MouseEvent e) {
		PrincipalController.menuElementosControllerRef.removeElemento(getElemento());
		PrincipalController.menuElementosControllerRef.reset();
	}

}
