package br.com.teujogo.controller;

import br.com.teujogo.componentes.Editor;
import br.com.teujogo.componentes.ElementoJogo;
import br.com.teujogo.ed.Elemento;
import br.com.teujogo.ed.Regra;
import br.com.teujogo.util.EdtElemento;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MenuElementosEdtPersonRegraController extends EdtElemento {
	@FXML
	private Editor editor;
	
	@FXML
	private void fechared(MouseEvent e) {
		PrincipalController.menuElementosControllerRef.edtPersonagem(null);
	}

	@FXML
	private void removerReg(MouseEvent e) {
		PrincipalController.menuElementosControllerRef.remRegra(getRegra());
	}

	public void atualiza(ElementoJogo elemento) {
		this.setElemento(elemento);
	}

	public void atualiza(Regra r) {
		this.setRegra(r);
		editor.setRegra(r.getRegra());
	}
	
	public void atualiza(Regra r,Elemento e) {
		this.setRegra(r);
		this.setElement(e);
	}
	
	@FXML
	private void altEditor(MouseEvent e) {
		this.getRegra().setRegra(editor.getValue());
	}
}
