package br.com.teujogo.principal;

import java.util.concurrent.atomic.AtomicReference;

import com.jayfella.jfx.embedded.SimpleJfxApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JfxPrincipal {

	private Stage primaryStage;
	private AtomicReference<SimpleJfxApplication> jfxApp;

	public JfxPrincipal(Stage primaryStage, AtomicReference<SimpleJfxApplication> jfxApp) {
		this.primaryStage = primaryStage;
		this.jfxApp = jfxApp;
	}

	public void inicia() {

		SimpleJfxApplication app = jfxApp.get();
		StackPane root = new StackPane();

		root.getChildren().add(app.getImageView());

		primaryStage.setScene(new Scene(root, 1024, 720));
		primaryStage.show();

		primaryStage.setTitle("Test JME Embedded in JavaFx");

		Button button = new Button("Click Me");

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				app.enqueue(() -> {
					DirectionalLight directionalLight = new DirectionalLight(new Vector3f(-1, -1, -1).normalizeLocal(),
							ColorRGBA.White.clone());
					app.getRootNode().addLight(directionalLight);
					System.out.println("   button   selected    ");
				});

			}
		};
		button.setOnAction(event);
		root.getChildren().add(button);

	}

}
