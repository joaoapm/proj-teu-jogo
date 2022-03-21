package br.com.teujogo.controller;

import com.jayfella.jfx.embedded.SimpleJfxApplication;

import br.com.teujogo.principal.JfxPrincipal;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class PrincipalController {

	@FXML
	public StackPane pnlJme;

	@FXML
	private MenuElementosController menuElementosController;

	public PrincipalController() {
		super();
	}

	@FXML
	private void initialize() {
		SimpleJfxApplication app = JfxPrincipal.jfxApp.get();
		pnlJme.getChildren().add(app.getImageView());
	}

	@FXML
	private void addPersonagem(MouseEvent event) {
		event.consume();
		menuElementosController.showMenu(MenuElementosController.MENU_PERSONAGENS);
	}

	@FXML
	private void addElemento(MouseEvent event) {
		event.consume();
		menuElementosController.showMenu(MenuElementosController.MENU_ELEMENTOS);
	}

}
