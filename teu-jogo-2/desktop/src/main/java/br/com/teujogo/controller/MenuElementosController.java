package br.com.teujogo.controller;

import java.io.IOException;

import br.com.teujogo.componentes.ElementoJogo;
import br.com.teujogo.componentes.DragCard;
import br.com.teujogo.ed.Elemento;
import br.com.teujogo.enumeration.TipoElemento;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;

public class MenuElementosController {

	public static final DataFormat CLIPBOARD_DATAFORMAT = new DataFormat("nbt-editor-item-snip");
	
	public static final String MENU_PERSONAGENS = "/telas/MenuElementos/MenuElementosAddPersonagens.fxml";
	public static final String MENU_ELEMENTOS = "/telas/MenuElementos/MenuElementosAddElementos.fxml";
	public static final String MENU_EDT_PERSONAGEM = "/telas/MenuElementos/MenuElementosEdtPersonagens.fxml";
	public static final String MENU_MONTAR_FASE = "/telas/MenuElementos/MenuElementosMontarFase.fxml";
	public static final String MENU_EDT_ELEMENTO_TEMPO = "/telas/MenuElementos/MenuElementosEdtElementoTempo.fxml";
	public static final String MENU_EDT_ELEMENTO_ACAO = "/telas/MenuElementos/MenuElementosEdtElementoAcao.fxml";

	@FXML
	public Pane pnlElementos;

	public MenuElementosController() {
		super();
	}

	@FXML
	private void novoPersonagem(DragEvent event) {
		JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();
		p.enqueue(() -> {
			DragCard dc = (DragCard) event.getSource();
			p.addPersonagem(dc.getTipo());
		});
	}

	@FXML
	private void novoElemento(DragEvent event) {
		ElementoJogo b = new ElementoJogo();
		DragCard dc = (DragCard) event.getSource();
		b.setText(dc.getTipo().getTxtBtn());
		b.setTipoElemento(dc.getTipo());
		b.setOnMouseReleased(mouseEvent -> {
			PrincipalController.menuElementosControllerRef.edtElemento(b);
		});
		PrincipalController.pnlElementosAddRef.getChildren().add(b);
	}

	@FXML
	public void edtElemento(ElementoJogo elemento) {
		if (elemento != null) {
			try {
				if (elemento.getTipoElemento().equals(TipoElemento.TEMPO)) {
					carregaEdtElementoObj(MENU_EDT_ELEMENTO_TEMPO, elemento);
				} else if (elemento.getTipoElemento().equals(TipoElemento.ACAO)) {
					carregaEdtElementoObj(MENU_EDT_ELEMENTO_ACAO, elemento);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@FXML
	public void edtPersonagem(Elemento elemento) {
		if (elemento != null) {
			try {
				pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MENU_EDT_PERSONAGEM));
				Parent root = (Parent) fxmlLoader.load();
				MenuElementosEdtPersonagemController controller = fxmlLoader
						.<MenuElementosEdtPersonagemController>getController();
				pnlElementos.getChildren().add(root);
				controller.atualiza(elemento);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void carregaEdtElementoObj(String menu, ElementoJogo ele) throws IOException {
		pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(menu));
		Parent root = (Parent) fxmlLoader.load();
		EdtElemento controller = fxmlLoader.<EdtElemento>getController();
		pnlElementos.getChildren().add(root);
		controller.atualiza(ele);
	}

	public void showMenu(String menu) {

		try {
			pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(menu));
			Parent root = (Parent) fxmlLoader.load();
			pnlElementos.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addRegra(ElementoJogo elemento) {
		 
	}
	
	@FXML
	public void remRegra(ElementoJogo elemento) {
		 
	}

}
