package br.com.teujogo.componentes;

import java.io.IOException;

import br.com.teujogo.enumeration.TipoSnippet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class Editor extends HBox {

	private ObjectProperty<EventHandler<MouseEvent>> propertyOnFechar = new SimpleObjectProperty<EventHandler<MouseEvent>>();
	private ObjectProperty<EventHandler<MouseEvent>> propertyOnRemover = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	@FXML
	private FlowPane snippets;

	@FXML
	private TextArea areaTexto;

	@FXML
	private Button btnRemover;

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

	public final ObjectProperty<EventHandler<MouseEvent>> onFecharProperty() {
		return propertyOnFechar;
	}

	public final void setOnFechar(EventHandler<MouseEvent> handler) {
		propertyOnFechar.set(handler);
	}

	public final EventHandler<MouseEvent> getOnFechar() {
		return propertyOnFechar.get();
	}

	@FXML
	private void aoFechar(MouseEvent event) {
		propertyOnFechar.get().handle(event);
	}

	public final ObjectProperty<EventHandler<MouseEvent>> onRemoverProperty() {
		return propertyOnRemover;
	}

	public final void setOnRemover(EventHandler<MouseEvent> handler) {
		propertyOnRemover.set(handler);
	}

	public final EventHandler<MouseEvent> getOnRemover() {
		return propertyOnRemover.get();
	}

	@FXML
	private void aoRemover(MouseEvent event) {
		propertyOnRemover.get().handle(event);
	}

}