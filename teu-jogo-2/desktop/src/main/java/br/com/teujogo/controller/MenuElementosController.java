package br.com.teujogo.controller;

import java.io.IOException;

import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import br.com.teujogo.principal.JfxPrincipal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.DragEvent;
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
	private void novoPersonagem(DragEvent event) {

		JfxPrincipal.jfxApp.get().enqueue(() -> {

			Box b = new Box(1, 1, 1);
			Geometry geom = new Geometry("Box", b);
			Material mat = new Material(JfxPrincipal.jfxApp.get().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Blue);
			geom.setMaterial(mat);
			JfxPrincipal.jfxApp.get().getRootNode().attachChild(geom);

		});

	}

}
