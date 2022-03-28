package br.com.teujogo.controller;

import br.com.teujogo.componentes.Editor;
import br.com.teujogo.componentes.ElementoJogo;
import br.com.teujogo.util.EdtElemento;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MenuElementosEdtElementoAcaoController extends EdtElemento {

	@FXML
	private Editor editor;

	public void atualiza(ElementoJogo elemento) {
		setElemento(elemento);
		editor.setRegra(this.getElemento().getTxtRegra());
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

	@FXML
	private void altEditor(MouseEvent e) {
		this.getElemento().setTxtRegra(editor.getValue());
	}

}
