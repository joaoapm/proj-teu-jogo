package br.com.teujogo.controller;

import br.com.teujogo.componentes.Propriedade;
import br.com.teujogo.ed.Elemento;
import br.com.teujogo.ed.Regra;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;
import br.com.teujogo.util.EdtElemento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class MenuElementosEdtPersonagemController extends EdtElemento {

	private JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();

	@FXML
	private Label lblPerso;

	@FXML
	private FlowPane regras;

	@FXML
	private Propriedade ataque;

	@FXML
	private Propriedade inimigo;

	@FXML
	private Propriedade pulo;

	@FXML
	private Propriedade vidas;

	@FXML
	private void altAtaque(MouseEvent e) {
		getElement().setPodeAtacar(ataque.getVlPrimario());
		if (ataque.getVlSec() != null && !ataque.getVlSec().isEmpty())
			getElement().setDano(Integer.valueOf(ataque.getVlSec()));
	}

	@FXML
	private void altInimigo(MouseEvent e) {
		getElement().setInimigo(inimigo.getVlPrimario());
	}

	@FXML
	private void altPulo(MouseEvent e) {
		getElement().setPodePular(pulo.getVlPrimario());
	}

	@FXML
	private void altVida(MouseEvent e) {
		getElement().setPossuiVidas(vidas.getVlPrimario());
		if (vidas.getVlSec() != null && !vidas.getVlSec().isEmpty())
			getElement().setQntVidas(Integer.valueOf(vidas.getVlSec()));
	}

	public void atualiza(Elemento elemento) {
		setElement(elemento);
		lblPerso.setText(elemento.getTipoElemento().getDescricao());

		vidas.setPropriedade(getElement().isPossuiVidas(), getElement().getQntVidas() != null ? String.valueOf(getElement().getQntVidas()) : null);
		ataque.setPropriedade(getElement().isPodeAtacar(), getElement().getDano() != null ? String.valueOf(getElement().getDano())  : null);
		inimigo.setPropriedade(getElement().isInimigo(), null);
		pulo.setPropriedade(getElement().isPodePular(), null);

		for (Regra r : elemento.getRegras()) {
			Button b = new Button("Regra " + r.getId());

			b.setOnMouseReleased(mouseEvent -> {
				PrincipalController.menuElementosControllerRef.edtRegra(r.getId());
			});

			regras.getChildren().add(b);
		}
	}

	@FXML
	private void addRegra(MouseEvent event) {
		PrincipalController.menuElementosControllerRef.edtRegra(null);
	}

	@FXML
	private void removerPer(MouseEvent e) {
		p.enqueue(() -> {
			p.removeObj(getElement());
		});
		PrincipalController.menuElementosControllerRef.reset();
	}

}
