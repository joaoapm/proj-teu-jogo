package br.com.teujogo.controller;

import com.jayfella.jfx.embedded.SimpleJfxApplication;
import com.jayfella.jfx.embedded.jfx.EditorFxImageView;
import com.jme3.math.Vector2f;

import br.com.teujogo.principal.JfxPrincipal;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

public class PrincipalController {

	@FXML
	public StackPane pnlJme;

	@FXML
	private MenuElementosController menuElementosController;

	public static Vector2f ptDrop;

	public PrincipalController() {
		super();
	}

	@FXML
	private void initialize() {
		SimpleJfxApplication app = JfxPrincipal.jfxApp.get();
		EditorFxImageView asd = app.getImageView();
		pnlJme.getChildren().add(asd);

		asd.requestFocus();
		pnlJme.setOnDragOver(mouseEvent -> {
			mouseEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		});

		pnlJme.setOnDragDropped(mouseEvent -> {
			mouseEvent.setDropCompleted(true);
			mouseEvent.consume();
			ptDrop = new Vector2f(Float.valueOf(String.valueOf(mouseEvent.getSceneX())).floatValue(), Float.valueOf(String.valueOf(mouseEvent.getSceneY())).floatValue());
		});

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
