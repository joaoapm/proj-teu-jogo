package br.com.teujogo.componentes;

import java.io.IOException;

import br.com.teujogo.enumeration.TipoSnippet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class Editor extends HBox {

	@FXML
	private FlowPane snippets;

	@FXML
	private TextArea areaTexto;

	public Editor() {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("componentes/Editor.fxml"));

		fxmlloader.setRoot(this);
		fxmlloader.setController(this);
		try {
			fxmlloader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void initialize() {

		areaTexto.setOnDragOver(mouseEvent -> {
			if (mouseEvent.getGestureSource().getClass() == EditorSnippet.class) {
				mouseEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
		});

		areaTexto.setOnDragDropped(mouseEvent -> {
			if (mouseEvent.getGestureSource().getClass() == EditorSnippet.class) {
				mouseEvent.setDropCompleted(true);
				mouseEvent.consume();
			}
		});

		for (TipoSnippet ts : TipoSnippet.values()) {
			EditorSnippet sp = new EditorSnippet(ts.getLabel());
			sp.setTipoSnippet(ts);
			sp.setOnLargar(mouseEvent -> {
				aoAdicionarSnippet((EditorSnippet) mouseEvent.getSource());
			});
			snippets.getChildren().add(sp);
		}
	}

	private void aoAdicionarSnippet(EditorSnippet es) {
		areaTexto.appendText(es.getTipoSnippet().getEq());
	}

}