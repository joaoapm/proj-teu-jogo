package br.com.teujogo.componentes;

import java.io.IOException;

import javafx.beans.NamedArg;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class Propriedade extends FlowPane {

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
				});
	}

}