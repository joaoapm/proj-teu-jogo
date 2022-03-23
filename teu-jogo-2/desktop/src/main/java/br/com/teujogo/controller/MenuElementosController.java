package br.com.teujogo.controller;

import java.io.IOException;

import br.com.teujogo.componentes.BotaoElemento;
import br.com.teujogo.componentes.DragCard;
import br.com.teujogo.ed.Elemento;
import br.com.teujogo.enumeration.TipoElemento;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;

public class MenuElementosController {

	public static final String MENU_PERSONAGENS = "/telas/MenuElementosAddPersonagens.fxml";
	public static final String MENU_ELEMENTOS = "/telas/MenuElementosAddElementos.fxml";
	public static final String MENU_EDT_PERSONAGEM = "/telas/MenuElementosEdtPersonagens.fxml";
	public static final String MENU_MONTAR_FASE = "/telas/MenuElementosMontarFase.fxml";
	public static final String MENU_EDT_ELEMENTO = "/telas/MenuElementosEdtElemento.fxml";

	@FXML
	public Pane pnlElementos;

	public MenuElementosController() {
		super();
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
	private void novoPersonagem(DragEvent event) {
		JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();
		p.enqueue(() -> {
			DragCard dc = (DragCard) event.getSource();
			p.addPersonagem(dc.getTipo());
		});
	}

	@FXML
	private void novoElemento(DragEvent event) {
		BotaoElemento b = new BotaoElemento();
		b.setText(TipoElemento.TEMPO.getTxtBtn());
		b.setTipoElemento(TipoElemento.TEMPO);
		b.setOnMouseReleased(mouseEvent -> {
			PrincipalController.menuElementosControllerRef.showMenu(MENU_EDT_ELEMENTO);
		});
		PrincipalController.pnlElementosAddRef.getChildren().add(b);
	}

	@FXML
	public void edtPersonagem(Elemento elemento) {
		if (elemento != null) {
			try {
				pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MENU_EDT_PERSONAGEM));
				Parent root = (Parent) fxmlLoader.load();
				MenuElementosEdtPersonagemController controller = fxmlLoader.<MenuElementosEdtPersonagemController>getController();
				pnlElementos.getChildren().add(root);
				controller.atualiza(elemento);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
