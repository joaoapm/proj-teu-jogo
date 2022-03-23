package br.com.teujogo.componentes;

import java.io.IOException;

import br.com.teujogo.enumeration.TipoElemento;
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
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

public class DragCard extends Pane {

	private ObjectProperty<EventHandler<DragEvent>> propertyOnXAction = new SimpleObjectProperty<EventHandler<DragEvent>>();
	private static final DataFormat CLIPBOARD_DATAFORMAT = new DataFormat("nbt-editor-item");

	@FXML
	private Label testea;

	@FXML
	private Pane asd;

	private String label;

	private TipoElemento tipo;

	public DragCard(@NamedArg("label") String label, @NamedArg("tipo") String tipoS) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("componentes/DragCard.fxml"));
		
		fxmlloader.setRoot(this);
		fxmlloader.setController(this);
		this.label = label;
		tipo =  TipoElemento.getElementoByName(tipoS);
		
		try {
			fxmlloader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void initialize() {
		setDragDrop();
		testea.setText(label);
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
			cbc.put(CLIPBOARD_DATAFORMAT, true);
			db.setContent(cbc);
			mouseEvent.consume();
		});

		this.setOnDragDone(mouseEvent -> {
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

	public TipoElemento getTipo() {
		return tipo;
	}

	public void setTipo(TipoElemento tipo) {
		this.tipo = tipo;
	}
}