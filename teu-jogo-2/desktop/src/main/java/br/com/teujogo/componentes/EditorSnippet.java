package br.com.teujogo.componentes;

import java.io.IOException;

import br.com.teujogo.controller.MenuElementosController;
import br.com.teujogo.enumeration.TipoSnippet;
import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;

public class EditorSnippet extends FlowPane {

	private ObjectProperty<EventHandler<DragEvent>> propertyOnXAction = new SimpleObjectProperty<EventHandler<DragEvent>>();

	@FXML
	private Label lblSnippet;

	private String lblSnippetTxt;
	
	private TipoSnippet tipoSnippet;

	public TipoSnippet getTipoSnippet() {
		return tipoSnippet;
	}

	public void setTipoSnippet(TipoSnippet tipoSnippet) {
		this.tipoSnippet = tipoSnippet;
	}

	public EditorSnippet(@NamedArg("label") String label) {
		FXMLLoader fxmlloader = new FXMLLoader(
				getClass().getClassLoader().getResource("componentes/EditorSnippet.fxml"));

		fxmlloader.setRoot(this);
		fxmlloader.setController(this);
		this.lblSnippetTxt = label;

		try {
			fxmlloader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void initialize() {
		setDragDrop();
		lblSnippet.setText(lblSnippetTxt);
	}

	private void setDragDrop() {

		this.setOnDragDetected(mouseEvent -> {
			Dragboard db = this.startDragAndDrop(TransferMode.ANY);
			WritableImage wi = new WritableImage((int) getWidth(), (int) getHeight());
			Image dbImg = snapshot(null, wi);
			db.setDragView(dbImg);
			db.setDragViewOffsetX(getWidth() / 2);
			db.setDragViewOffsetY(getHeight() / 2);
			ClipboardContent cbc = new ClipboardContent();
			cbc.put(MenuElementosController.CLIPBOARD_DATAFORMAT, true);
			db.setContent(cbc);
			mouseEvent.consume();
		});

		this.setOnDragDone(mouseEvent -> {
			if (mouseEvent.isAccepted())
				onActionProperty().get().handle(mouseEvent);
		});

	}

	public final ObjectProperty<EventHandler<DragEvent>> onActionProperty() {
		return propertyOnXAction;
	}

	public final void setOnLargar(EventHandler<DragEvent> handler) {
		propertyOnXAction.set(handler);
	}

	public final EventHandler<DragEvent> getOnLargar() {
		return propertyOnXAction.get();
	}
}