package br.com.teujogo.controller;

import com.jayfella.jfx.embedded.jfx.EditorFxImageView;
import com.jme3.math.Vector2f;

import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
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
		initCanvas();
	}

	private void initCanvas() {
		JmePrincipal app = (JmePrincipal) JfxPrincipal.jfxApp.get();
		EditorFxImageView canv = app.getImageView();
		canv.requestFocus();
		pnlJme.getChildren().add(canv);
		
		
		pnlJme.setOnMouseDragged(mouseEvent -> {
			app.enqueue(() -> {
				app.gizMove(new Vector2f(Float.valueOf(String.valueOf(mouseEvent.getSceneX())).floatValue(), Float.valueOf(String.valueOf(mouseEvent.getSceneY())).floatValue()));
			});
		});
	 

		pnlJme.setOnMouseReleased(mouseEvent -> {
			
			ptDrop = new Vector2f(Float.valueOf(String.valueOf(mouseEvent.getSceneX())).floatValue(), Float.valueOf(String.valueOf(mouseEvent.getSceneY())).floatValue());
			
			menuElementosController.edtPersonagem(app.selectiona());
			
			app.enqueue(() -> {
				app.gizRemove();
			});
			
		});
		
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
