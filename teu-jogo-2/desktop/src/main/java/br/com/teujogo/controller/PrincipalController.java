package br.com.teujogo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jayfella.jfx.embedded.jfx.EditorFxImageView;
import com.jme3.math.Vector2f;

import br.com.teujogo.componentes.DragCard;
import br.com.teujogo.componentes.Obj;
import br.com.teujogo.ed.Elemento;
import br.com.teujogo.ed.TelaED;
import br.com.teujogo.ed.Telas;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import br.com.teujogo.util.GerenciadorArquivos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class PrincipalController {

	public static final String TELAS_INTRODUCAO = "/areas-sub/Telas/TelasIntroducao.fxml";
	public static final String TELAS_FIM = "/areas-sub/Telas/TelaFim.fxml";
	public static final String TELAS_TITULO = "/areas-sub/Telas/TelasTitulo.fxml";

	@FXML
	private ComboBox<Telas> opcoesTela;

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

	private List<Telas> infTelas = new ArrayList<Telas>();

	public PrincipalController() {
		super();
	}

	@FXML
	private void initialize() {

		initCanvas();
		initComboEdtTelas();

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

			Elemento e = app.selectiona();
			if(e != null)
				menuElementosController.edtPersonagem(e);

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
		fechar(null);
		event.consume();
		menuElementosController.showMenu(MenuElementosController.MENU_PERSONAGENS);
	}

	@FXML
	private void addElemento(MouseEvent event) {
		fechar(null);
		event.consume();
		menuElementosController.showMenu(MenuElementosController.MENU_ELEMENTOS);
	}

	@FXML
	private void montarFase(MouseEvent event) {
		fechar(null);
		event.consume();
		menuElementosController.showMenu(MenuElementosController.MENU_MONTAR_FASE);
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

	private void initComboEdtTelas() {

		infTelas.add(new Telas(0, "Tela de Introdução"));
		infTelas.add(new Telas(1, "Tela de Título"));
		infTelas.add(new Telas(2, "Tela Final"));

		ObservableList<Telas> telas = FXCollections.observableArrayList();
		telas.add(getTelas(0));
		telas.add(getTelas(1));
		telas.add(getTelas(2));
		opcoesTela.setItems(telas);
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				edtTelas(opcoesTela.getValue());
			}
		};
		opcoesTela.setOnAction(event);
		showMenu(PrincipalController.TELAS_INTRODUCAO);

		opcoesTela.setValue(getTelas(0));

	}

	private void edtTelas(Telas value) {
		if (value.getId() == 0)
			showMenu(PrincipalController.TELAS_INTRODUCAO);
		else if (value.getId() == 1)
			showMenu(PrincipalController.TELAS_TITULO);
		else if (value.getId() == 2)
			showMenu(PrincipalController.TELAS_FIM);
		setTelas();
	}

	@FXML
	public void telas(MouseEvent event) {
		fechar(event);
		pnlDireita.setVisible(true);
		opcoesTela.setValue(getTelas(0));
		showMenu(PrincipalController.TELAS_INTRODUCAO);

	}

	@FXML
	public void salvarTelas(MouseEvent event) {
		if (opcoesTela.getValue().getId() == 0) {
			Telas t = getTelas(0);
			TextArea intro1 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#intro1");
			t.setTxt1(intro1.getText());
		} else if (opcoesTela.getValue().getId() == 1) {
			Telas t = getTelas(1);
			TextArea intro1 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#titulo1");
			TextArea intro2 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#titulo2");
			t.setTxt1(intro1.getText());
			t.setTxt2(intro2.getText());
		} else if (opcoesTela.getValue().getId() == 2) {
			Telas t = getTelas(2);
			TextArea fim1 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#fim1");
			TextArea fim2 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#fim2");
			t.setTxt1(fim1.getText());
			t.setTxt2(fim2.getText());
		}
	}

	public void setTelas() {
		if (opcoesTela.getValue().getId() == 0) {
			Telas t = getTelas(0);
			TextArea intro1 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#intro1");
			intro1.setText(t.getTxt1());
		} else if (opcoesTela.getValue().getId() == 1) {
			Telas t = getTelas(1);
			TextArea titulo1 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#titulo1");
			TextArea titulo2 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#titulo2");
			titulo1.setText(t.getTxt1());
			titulo2.setText(t.getTxt2());
		} else if (opcoesTela.getValue().getId() == 2) {
			Telas t = getTelas(2);
			TextArea fim1 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#fim1");
			TextArea fim2 = (TextArea) JfxPrincipal.primaryStage.getScene().lookup("#fim2");
			fim1.setText(t.getTxt1());
			fim2.setText(t.getTxt2());
		}
	}

	private Telas getTelas(int id) {
		for (Telas t : infTelas)
			if (t.getId() == id)
				return t;
		return null;
	}

	@FXML
	public void fechar(MouseEvent event) {
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
