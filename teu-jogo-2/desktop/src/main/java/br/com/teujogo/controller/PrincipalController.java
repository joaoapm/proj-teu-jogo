package br.com.teujogo.controller;

import java.io.File;
import java.io.IOException;

import com.jayfella.jfx.embedded.jfx.EditorFxImageView;
import com.jme3.math.Vector2f;

import br.com.teujogo.componentes.DragCard;
import br.com.teujogo.componentes.Obj;
import br.com.teujogo.ed.TelaED;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import br.com.teujogo.util.GerenciadorArquivos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class PrincipalController {

	public static final String MENU_EDT_ELEMENTO_REGRA = "/areas/Telas.fxml";
	
	@FXML
	public FlowPane pnlDireita;
	
	@FXML
	public FlowPane pnlDireitaAdd;
	
	@FXML
	public StackPane pnlJme;

	@FXML
	public HBox pnlElementosAdd;
	public static HBox pnlElementosAddRef;

	@FXML
	private MenuElementosController menuElementosController;
	public static MenuElementosController menuElementosControllerRef;

	public static Vector2f ptDrop;

	public PrincipalController() {
		super();
	}

	@FXML
	private void initialize() {
		initCanvas();
		pnlElementosAddRef = pnlElementosAdd;
		menuElementosControllerRef = menuElementosController;
	}

	private void initCanvas() {
		JmePrincipal app = (JmePrincipal) JfxPrincipal.jfxApp.get();
		EditorFxImageView canv = app.getImageView();
		canv.requestFocus();
		pnlJme.getChildren().add(canv);

		pnlJme.setOnMouseDragged(mouseEvent -> {
			app.enqueue(() -> {
				app.gizMove(new Vector2f(Float.valueOf(String.valueOf(mouseEvent.getSceneX())).floatValue(),
						Float.valueOf(String.valueOf(mouseEvent.getSceneY())).floatValue()));
			});
		});

		pnlJme.setOnMouseReleased(mouseEvent -> {

			ptDrop = new Vector2f(Float.valueOf(String.valueOf(mouseEvent.getSceneX())).floatValue(),
					Float.valueOf(String.valueOf(mouseEvent.getSceneY())).floatValue());
			
			menuElementosController.edtPersonagem(app.selectiona());

			app.enqueue(() -> {
				app.gizRemove();
			});
		});

		pnlJme.setOnDragOver(mouseEvent -> {
			if (mouseEvent.getGestureSource().getClass() == DragCard.class) {
				mouseEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			} else if (mouseEvent.getGestureSource().getClass() == Obj.class) {
				mouseEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
		});

		pnlJme.setOnDragDropped(mouseEvent -> {
			if (mouseEvent.getGestureSource().getClass() == DragCard.class
					|| mouseEvent.getGestureSource().getClass() == Obj.class) {
				mouseEvent.setDropCompleted(true);
				mouseEvent.consume();
				ptDrop = new Vector2f(Float.valueOf(String.valueOf(mouseEvent.getSceneX())).floatValue(),
						Float.valueOf(String.valueOf(mouseEvent.getSceneY())).floatValue());
			}
		});

	}

	@FXML
	private void addPersonagem(MouseEvent event) {
		event.consume();
		menuElementosController.showMenu(MenuElementosController.MENU_PERSONAGENS);
	}

	@FXML
	private void addElemento(MouseEvent event) {
		event.consume();
		menuElementosController.showMenu(MenuElementosController.MENU_ELEMENTOS);
	}

	@FXML
	public void salvar(MouseEvent event) {
		JmePrincipal app = (JmePrincipal) JfxPrincipal.jfxApp.get();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Image");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("mjg", "*.mjg"));
		File file = fileChooser.showSaveDialog(JfxPrincipal.primaryStage);
		if (file != null) {
			app.enqueue(() -> {
				new GerenciadorArquivos().gerarArquivo(new TelaED(app, file));
			});

		}
	}

	@FXML
	public void export(MouseEvent event) throws IOException {
		JmePrincipal app = (JmePrincipal) JfxPrincipal.jfxApp.get();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Image");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("jar", "*.jar"));
		File file = fileChooser.showSaveDialog(JfxPrincipal.primaryStage);
		if (file != null) {
			app.enqueue(() -> {
				new GerenciadorArquivos().exporta(new TelaED(app, file));
			});
		}
	}

	@FXML
	private void montarFase(MouseEvent event) {
		event.consume();
		menuElementosController.showMenu(MenuElementosController.MENU_MONTAR_FASE);
	}
	
	@FXML
	public void telas(MouseEvent event) {
		pnlDireita.setVisible(true);
		showMenu(this.MENU_EDT_ELEMENTO_REGRA);
	}
	
	@FXML
	public void salvarTelas(MouseEvent event) {
		pnlDireitaAdd.getChildren().removeAll(pnlDireita.getChildren());
		pnlDireita.setVisible(false);
	}
	
	public void showMenu(String menu) {

		try {
			pnlDireitaAdd.getChildren().removeAll(pnlDireitaAdd.getChildren());
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(menu));
			Parent root = (Parent) fxmlLoader.load();
			pnlDireitaAdd.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
