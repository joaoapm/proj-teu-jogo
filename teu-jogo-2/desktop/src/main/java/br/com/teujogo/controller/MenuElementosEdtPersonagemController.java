package br.com.teujogo.controller;

import br.com.teujogo.ed.Elemento;
import br.com.teujogo.ed.Regra;
import br.com.teujogo.util.EdtElemento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class MenuElementosEdtPersonagemController extends EdtElemento {

	@FXML
	private Label lblPerso;

	@FXML
	private FlowPane regras;

	public void atualiza(Elemento elemento) {
		lblPerso.setText(elemento.getTipoElemento().getDescricao());

		for (Regra r : elemento.getRegras()) {
			Button b = new Button("Regra " + r.getId());

			b.setOnMouseReleased(mouseEvent -> {
				PrincipalController.menuElementosControllerRef.edtRegra(r.getId());
			});
			
			regras.getChildren().add(b);
		}
	}

	@FXML
	private void addRegra(MouseEvent event) {
		PrincipalController.menuElementosControllerRef.edtRegra(null);
	}

}
