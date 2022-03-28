package br.com.teujogo.componentes;

import java.io.IOException;

import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class Propriedade extends FlowPane {

	private ObjectProperty<EventHandler<MouseEvent>> propertyOnAlterar = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	@FXML
	private Label lblPrinc;

	@FXML
	private Label lblSec;

	@FXML
	private TextField vlSec;

	@FXML
	private CheckBox vlPrinc;

	private String lblPrincTxt;
	private String labelSecTxt;
	private boolean possuiSec = false;

	public void setPropriedade(boolean vlP, String vlS) {
		vlPrinc.setSelected(vlP);
		if (vlP) {
			vlSec.setText(vlS);
		}
		if (vlP && vlS != null && !vlS.isEmpty()) {
			vlSec.setVisible(true);
			lblSec.setVisible(true);
		}
	}

	public Propriedade(@NamedArg("labelPrinc") String lblPrincTxt, @NamedArg("labelSec") String labelSecTxt,
			@NamedArg("possuiVlSecundario") boolean possuiSec) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("componentes/Propriedade.fxml"));

		fxmlloader.setRoot(this);
		fxmlloader.setController(this);

		this.lblPrincTxt = lblPrincTxt;
		this.labelSecTxt = labelSecTxt;
		this.possuiSec = possuiSec;

		try {
			fxmlloader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void initialize() {
		lblPrinc.setText(this.lblPrincTxt);
		lblSec.setText(this.labelSecTxt);

		vlPrinc.selectedProperty()
				.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
					if (possuiSec) {
						vlSec.setVisible(new_val);
						lblSec.setVisible(new_val);
					}
					onActionProperty().get().handle(null);
				});

		vlSec.textProperty().addListener((observable, oldValue, newValue) -> {
			onActionProperty().get().handle(null);
		});

	}

	public boolean getVlPrimario() {
		return vlPrinc.isSelected();
	}

	public String getVlSec() {
		return vlSec.getText();
	}

	public final ObjectProperty<EventHandler<MouseEvent>> onActionProperty() {
		return propertyOnAlterar;
	}

	public final void setOnAlterar(EventHandler<MouseEvent> handler) {
		propertyOnAlterar.set(handler);
	}

	public final EventHandler<MouseEvent> getOnAlterar() {
		return propertyOnAlterar.get();
	}

}