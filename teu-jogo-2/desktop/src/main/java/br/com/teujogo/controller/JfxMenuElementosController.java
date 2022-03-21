package br.com.teujogo.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class JfxMenuElementosController {

	public static final String MENU_PERSONAGENS = "telas/MenuAddPersonagens.fxml";
	public static final String MENU_ELEMENTOS = "telas/MenuAddElementos.fxml";

	@FXML
	public Pane pnlElementos;

	public JfxMenuElementosController() {
		super();
	}

	public void showMenu(String menu) {

		try {
			pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
			HBox newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource(menu));
			pnlElementos.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
