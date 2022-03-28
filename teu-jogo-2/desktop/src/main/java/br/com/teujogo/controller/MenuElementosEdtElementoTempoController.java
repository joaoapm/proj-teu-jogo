package br.com.teujogo.controller;

import java.util.ArrayList;

import br.com.teujogo.componentes.ElementoJogo;
import br.com.teujogo.enumeration.TipoTempo;
import br.com.teujogo.util.EdtElemento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MenuElementosEdtElementoTempoController extends EdtElemento {

	@FXML
	private TextField tempo;

	@FXML
	private ComboBox<TipoTempo> tipo;

	@FXML
	private ComboBox<String> aoTerminar;

	@FXML
	private TextField aCada;

	@FXML
	private ComboBox<String> aCadaAcao;

	public void atualiza(ElementoJogo elemento) {
		setElemento(elemento);
		
		if(getElemento().getTempo() != null)
			tempo.setText(getElemento().getTempo().toString());
		if(getElemento().getaCada() != null)
			aCada.setText(getElemento().getaCada().toString());
		if(getElemento().getTipoTempo() != null)
			tipo.setValue(getElemento().getTipoTempo());
		if(getElemento().getAoTerminar() != null)
			aoTerminar.setValue(getElemento().getAoTerminar());
		if(getElemento().getAoTerminar() != null)
			aCadaAcao.setValue(getElemento().getAoACada()); 
	}

	@FXML
	private void removeEleTmp(MouseEvent e) {
		PrincipalController.menuElementosControllerRef.removeElemento(getElemento());
		PrincipalController.menuElementosControllerRef.reset();

	}

	@FXML
	public void initialize() {
		
		ObservableList<TipoTempo> a = FXCollections.observableArrayList(); 
		a.add(TipoTempo.CRESCENTE);
		a.add(TipoTempo.DECRESCENTE);
		tipo.setItems(a);
		tipo.setValue(TipoTempo.CRESCENTE);
		
		ObservableList<String> b = FXCollections.observableArrayList(); 
		b.add("ACAO 1");
		b.add("ACAO 2");
		aCadaAcao.setItems(b);
		aoTerminar.setItems(b);
		aoTerminar.setValue(b.get(0));
		aCadaAcao.setValue(b.get(0));
		
		tempo.textProperty().addListener((observable, oldValue, newValue) -> {
			getElemento().setTempo(Integer.valueOf(newValue));
		});
		aCada.textProperty().addListener((observable, oldValue, newValue) -> {
			getElemento().setaCada(Integer.valueOf(newValue));
		});

		tipo.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				getElemento().setTipoTempo(tipo.getValue());
			}
		});
		aCadaAcao.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				getElemento().setAoACada(aCadaAcao.getValue());
			}
		});
		aoTerminar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				getElemento().setAoTerminar(aoTerminar.getValue());
			}
		});

	}

}
