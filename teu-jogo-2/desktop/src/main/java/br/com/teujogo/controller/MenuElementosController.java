package br.com.teujogo.controller;

import java.io.IOException;

import br.com.teujogo.componentes.DragCard;
import br.com.teujogo.componentes.ElementoJogo;
import br.com.teujogo.ed.Elemento;
import br.com.teujogo.ed.Regra;
import br.com.teujogo.enumeration.TipoElemento;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import br.com.teujogo.util.EdtElemento;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;

public class MenuElementosController {

	public static final DataFormat CLIPBOARD_DATAFORMAT = new DataFormat("nbt-editor-item-snip");

	public static final String MENU_PERSONAGENS = "/areas-sub/MenuElementos/MenuElementosAddPersonagens.fxml";
	public static final String MENU_ELEMENTOS = "/areas-sub/MenuElementos/MenuElementosAddElementos.fxml";
	public static final String MENU_EDT_PERSONAGEM = "/areas-sub/MenuElementos/MenuElementosEdtPersonagens.fxml";
	public static final String MENU_MONTAR_FASE = "/areas-sub/MenuElementos/MenuElementosMontarFase.fxml";
	public static final String MENU_EDT_ELEMENTO_TEMPO = "/areas-sub/MenuElementos/MenuElementosEdtElementoTempo.fxml";
	public static final String MENU_EDT_ELEMENTO_ACAO = "/areas-sub/MenuElementos/MenuElementosEdtElementoAcao.fxml";
	public static final String MENU_EDT_ELEMENTO_REGRA = "/areas-sub/MenuElementos/MenuElementosEdtRegra.fxml";
	public static final String MENU_EDT_OBJ = "/areas-sub/MenuElementos/MenuElementosEdtObjetos.fxml";
	public static final String MENU_EDT_PERSONAGEM_REGRA = "/areas-sub/MenuElementos/MenuElementosEdtPersonRegr.fxml";

	@FXML
	public Pane pnlElementos;

	private Elemento elemento;

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

	public void removeElemento(ElementoJogo e) {
		PrincipalController.pnlElementosAddRef.getChildren().remove(e);
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
		try {
			if (elemento != null)
				this.elemento = elemento;
			if (this.elemento != null && this.elemento.getTipoElemento() != null) {
				if (this.elemento.getTipoElemento() != TipoElemento.ASSET) {
					pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MENU_EDT_PERSONAGEM));
					Parent root = (Parent) fxmlLoader.load();
					MenuElementosEdtPersonagemController controller = fxmlLoader
							.<MenuElementosEdtPersonagemController>getController();
					pnlElementos.getChildren().add(root);
					controller.atualiza(this.elemento);
				} else {
					pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MENU_EDT_OBJ));
					Parent root = (Parent) fxmlLoader.load();
					MenuElementosEdtObjController controller = fxmlLoader
							.<MenuElementosEdtObjController>getController();
					pnlElementos.getChildren().add(root);
					controller.atualiza(this.elemento);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void edtRegra(Integer regra) {
		try {
			Regra r = null;
			if (regra == null) {
				r = new Regra(this.elemento.getRegras().size() + 1);
				this.elemento.getRegras().add(r);
				regra = r.getId();
			} else {
				for (Regra rp : elemento.getRegras())
					if (rp.getId().equals(regra))
						r = rp;
			}

			pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MENU_EDT_PERSONAGEM_REGRA));
			Parent root = (Parent) fxmlLoader.load();
			MenuElementosEdtPersonRegraController controller = fxmlLoader
					.<MenuElementosEdtPersonRegraController>getController();
			pnlElementos.getChildren().add(root);
			controller.atualiza(r);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void remRegra(Regra regra) {
		this.elemento.getRegras().remove(regra);
		edtPersonagem(null);
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

	public void reset() {
		pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
	}

	private void carregaEdtElementoObj(String menu, ElementoJogo ele) throws IOException {
		pnlElementos.getChildren().removeAll(pnlElementos.getChildren());
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(menu));
		Parent root = (Parent) fxmlLoader.load();
		EdtElemento controller = fxmlLoader.<EdtElemento>getController();
		pnlElementos.getChildren().add(root);
		controller.atualiza(ele);
	}

}
