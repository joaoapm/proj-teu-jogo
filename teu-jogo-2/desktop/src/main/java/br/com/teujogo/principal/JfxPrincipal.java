package br.com.teujogo.principal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import com.jayfella.jfx.embedded.SimpleJfxApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JfxPrincipal {

	public static Stage primaryStage;
	public static AtomicReference<SimpleJfxApplication> jfxApp;

	public JfxPrincipal(Stage primaryStage, AtomicReference<SimpleJfxApplication> jfxApp) {
		JfxPrincipal.primaryStage = primaryStage;
		JfxPrincipal.jfxApp = jfxApp;
	}

	public void inicia() {

		Parent roo2t = null;
		try {
			roo2t = FXMLLoader.load(getClass().getClassLoader().getResource("telas/Principal.fxml"));

			Scene scene = new Scene(roo2t, 1080, 720);

			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setTitle("Teu Jogo");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
