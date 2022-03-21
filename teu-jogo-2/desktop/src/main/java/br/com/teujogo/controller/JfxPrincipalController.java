package br.com.teujogo.controller;

import com.jayfella.jfx.embedded.SimpleJfxApplication;

import br.com.teujogo.principal.JfxPrincipal;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class JfxPrincipalController {

	@FXML
	public StackPane pnlJme;

	public JfxPrincipalController() {
		super();
	}

	@FXML
	private void initialize() {
		SimpleJfxApplication app = JfxPrincipal.jfxApp.get();
		pnlJme.getChildren().add(app.getImageView());
	}

}
