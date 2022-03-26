package br.com.teujogo.controller;

import br.com.teujogo.componentes.Obj;
import br.com.teujogo.ed.Asset;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import br.com.teujogo.util.GerenciadorAssets;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class MenuElementosMontarFaseController {

	@FXML
	private FlowPane addTerreno;

	@FXML
	private FlowPane addObj;

	@FXML
	private Slider sldLuz;

	JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();

	@FXML
	private void initialize() {
		for (Asset as : GerenciadorAssets.lerAssets()) {
			Obj o = new Obj(as.getImgBtn());

			if (as.getTipo().equals("terreno"))
				addTerreno.getChildren().add(o);
			else if (as.getTipo().equals("obj"))
				addObj.getChildren().add(o);

			o.setOnLargar(mouseEvent -> {
				p.enqueue(() -> {
					p.addObj(as);
				});
			});
		}

		sldLuz.setValue(p.getLuzI());
		sldLuz.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				p.enqueue(() -> {
					p.setLuz((double) newValue);
				});
			}
		});
		
	}

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
