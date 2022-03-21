package br.com.teujogo.componentes;

import java.io.IOException;

import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DragCard extends Pane {

	private ObjectProperty<EventHandler<MouseEvent>> propertyOnXAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	private double mouseAnchorX;
	private double mouseAnchorY;
	private double xIni;
	private double yIni;

	@FXML
	private Label testea;

	@FXML
	private Pane asd;

	private String label;

	public DragCard(@NamedArg("label") String label) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("componentes/DragCard.fxml"));

		fxmlloader.setRoot(this);
		fxmlloader.setController(this);
		this.label = label;

		try {
			fxmlloader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void initialize() {

		xIni = getTranslateX();
		yIni = getTranslateY();

		this.setOnMousePressed(mouseEvent -> {
			mouseAnchorX = mouseEvent.getSceneX() - getTranslateX();
			mouseAnchorY = mouseEvent.getSceneY() - getTranslateY();
		});
		this.setOnMouseDragged(mouseEvent -> {
			setTranslateX(mouseEvent.getSceneX() - mouseAnchorX);
			setTranslateY(mouseEvent.getSceneY() - mouseAnchorY);
		});
		this.setOnMouseReleased(mouseEvent -> {
			setTranslateX(xIni);
			setTranslateY(yIni);

			onActionProperty().get().handle(mouseEvent);
		});

		testea.setText(label);
	}

	public final ObjectProperty<EventHandler<MouseEvent>> onActionProperty() {
		return propertyOnXAction;
	}

	public final void setOnLargar(EventHandler<MouseEvent> handler) {
		propertyOnXAction.set(handler);
	}

	public final EventHandler<MouseEvent> getOnLargar() {
		return propertyOnXAction.get();
	}

}