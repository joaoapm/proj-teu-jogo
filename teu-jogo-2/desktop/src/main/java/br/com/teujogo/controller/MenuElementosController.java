package br.com.teujogo.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MenuElementosController {

	public static final String MENU_PERSONAGENS = "telas/MenuElementosAddPersonagens.fxml";
	public static final String MENU_ELEMENTOS = "telas/MenuElementosAddElementos.fxml";

	@FXML
	public Pane pnlElementos;

	public MenuElementosController() {
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
	
	@FXML
	private void teste(MouseEvent event) {
		event.consume();
		System.out.println(9);
	}

}
