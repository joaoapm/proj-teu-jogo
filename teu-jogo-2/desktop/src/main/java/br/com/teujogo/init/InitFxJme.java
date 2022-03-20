package br.com.teujogo.init;

import java.util.concurrent.atomic.AtomicReference;

import com.jayfella.jfx.embedded.SimpleJfxApplication;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.StatsAppState;
import com.jme3.audio.AudioListenerState;
import com.jme3.system.AppSettings;

import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class InitFxJme extends Application {

	AtomicReference<SimpleJfxApplication> jfxApp;

	@Override
	public void start(Stage primaryStage) throws Exception {

		jfxApp = new AtomicReference<>();

		new Thread(new ThreadGroup("LWJGL"), () -> {

			SimpleJfxApplication myJmeGame = new JmePrincipal(new StatsAppState(), new AudioListenerState(), new FlyCamAppState());

			AppSettings appSettings = myJmeGame.getSettings();
			appSettings.setUseJoysticks(true);
			appSettings.setGammaCorrection(true);
			appSettings.setSamples(16);
			myJmeGame.setShowSettings(false);
			myJmeGame.setDisplayFps(false);
			myJmeGame.setDisplayStatView(false);

			jfxApp.set(myJmeGame);

			jfxApp.get().start();

		}, "LWJGL Render").start();

		while (jfxApp.get() == null || !jfxApp.get().isInitialized()) {
			Thread.sleep(10);
		}

		new JfxPrincipal(primaryStage, jfxApp).inicia();

	}

}