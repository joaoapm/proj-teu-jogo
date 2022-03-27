package br.com.teujogo.componentes;

import java.io.IOException;

import br.com.teujogo.controller.MenuElementosController;
import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;

public class Obj extends FlowPane {

	private ObjectProperty<EventHandler<DragEvent>> propertyOnXAction = new SimpleObjectProperty<EventHandler<DragEvent>>();

	@FXML
	private ImageView img;

	private String imgC;

	public Obj(@NamedArg("img") String imgC) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("componentes/Obj.fxml"));

		fxmlloader.setRoot(this);
		fxmlloader.setController(this);
		this.imgC = imgC;

		try {
			fxmlloader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void initialize() {
		setDragDrop();

		Image imProfile = new Image(getClass().getResourceAsStream("/img/btnAssets/"+imgC+".png"));
		
		img.setImage(imProfile);
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