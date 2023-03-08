package br.com.teujogo.componentes;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import br.com.teujogo.ed.Elemento;
import br.com.teujogo.ed.Regra;
import br.com.teujogo.enumeration.TipoSnippet;
import br.com.teujogo.testes.Interpretador;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class Editor extends HBox {

	private ObjectProperty<EventHandler<MouseEvent>> propertyOnFechar = new SimpleObjectProperty<EventHandler<MouseEvent>>();
	private ObjectProperty<EventHandler<MouseEvent>> propertyOnRemover = new SimpleObjectProperty<EventHandler<MouseEvent>>();
	private ObjectProperty<EventHandler<MouseEvent>> propertyOnAlterar = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	private static final String COMMENT_PATTERN = "//[^\n]*|/\\*(.|\\R)*?\\*/|/\\\\*[^\\\\v]*|^\\\\h*\\\\*([^\\\\v]*|/)";
	private static final String[] KEYWORDS = new String[] { "F", "m", "g", "G", "a" };
	private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
	private static final String SEMICOLON_PATTERN = "\\*";

	private static final Pattern PATTERN = Pattern.compile("(?<KEYWORD>" 
											+ KEYWORD_PATTERN + ")" + "|(?<SEMICOLON>"
											+ SEMICOLON_PATTERN + ")" + "|(?<COMMENT>" 
											+ COMMENT_PATTERN + ")");

	@FXML
	private FlowPane snippets;

	@FXML
	private CodeArea areaTexto;

	@FXML
	private Button btnRemover;
	
	private Regra regra;
	
	private Elemento elemento;

	public void setRegra(Regra regra, Elemento elemento) {
		this.elemento = elemento;
		this.regra = regra;
		if (regra.getRegra() != null)
			areaTexto.replaceText(regra.getRegra());
	}

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
		areaTexto.setStyle("-fx-font-family: consolas; -fx-font-size: 14pt;");

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

		areaTexto.textProperty().addListener((observable, oldValue, newValue) -> {
			areaTexto.setStyleSpans(0, computeHighlighting(areaTexto.getText()));
			onAlterarProperty().get().handle(null);
		});

	}

	private StyleSpans<Collection<String>> computeHighlighting(String text) {
		Matcher matcher = PATTERN.matcher(text);
		int lastKwEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find()) {
			String styleClass = matcher.group("KEYWORD") != null ? "keyword" :
						        matcher.group("SEMICOLON") != null ? "semicolon": 
						        matcher.group("COMMENT") != null ? "comment" : null;
			assert styleClass != null;
			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
			lastKwEnd = matcher.end();
		}
		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}

	public String getValue() {
		return areaTexto.getText();
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
	
	@FXML
	private void aoSalvar(MouseEvent event) {
		regra.setRegra(areaTexto.getText());
		Interpretador.interpretarRegra(regra, elemento);
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

	public final ObjectProperty<EventHandler<MouseEvent>> onAlterarProperty() {
		return propertyOnAlterar;
	}

	public final void setOnAlterar(EventHandler<MouseEvent> handler) {
		propertyOnAlterar.set(handler);
	}

	public final EventHandler<MouseEvent> getOnAlterar() {
		return propertyOnAlterar.get();
	}

	public void setRegra(ElementoJogo elemento) {
		// TODO Auto-generated method stub
		
	}
}