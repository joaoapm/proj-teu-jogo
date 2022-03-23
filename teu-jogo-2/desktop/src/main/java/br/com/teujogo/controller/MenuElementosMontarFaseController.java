package br.com.teujogo.controller;

import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MenuElementosMontarFaseController {

	JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();

	@FXML
	private void cameraCima(MouseEvent event) {
		p.enqueue(() -> {
			p.moveCamC();
		});
	}
	
	@FXML
	private void cameraBaixo(MouseEvent event) {
		p.enqueue(() -> {
			p.moveCamB();
		});
	}
	
	@FXML
	private void cameraDireita(MouseEvent event) {
		p.enqueue(() -> {
			p.moveCamD();
		});
	}
	
	@FXML
	private void cameraEsquerda(MouseEvent event) {
		p.enqueue(() -> {
			p.moveCamE();
		});
	}
	
	@FXML
	private void cameraFrente(MouseEvent event) {
		p.enqueue(() -> {
			p.moveCamF();
		});
	}
	
	@FXML
	private void cameraTras(MouseEvent event) {
		p.enqueue(() -> {
			p.moveCamT();
		});
	}
	
	@FXML
	private void cameraAngBaixo(MouseEvent event) {
		p.enqueue(() -> {
			p.roteCamB();
		});
	}
	
	@FXML
	private void cameraAngCima(MouseEvent event) {
		p.enqueue(() -> {
			p.roteCamC();
		});
	}

}
