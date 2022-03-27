package br.com.teujogo.controller;

import br.com.teujogo.ed.Elemento;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import br.com.teujogo.util.EdtElemento;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MenuElementosEdtObjController extends EdtElemento {

	private JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();

	public void atualiza(Elemento elemento) {
		this.setElement(elemento);
	}

	@FXML
	public void removerObj(MouseEvent e) {
		p.enqueue(() -> {
			p.removeObj(getElement());
		});
		PrincipalController.menuElementosControllerRef.reset();
	}
}
