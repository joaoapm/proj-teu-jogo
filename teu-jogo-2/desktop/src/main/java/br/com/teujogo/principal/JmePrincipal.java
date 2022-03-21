package br.com.teujogo.principal;

import com.jayfella.jfx.embedded.SimpleJfxApplication;
import com.jme3.app.state.AppState;

import br.com.teujogo.Carregador;

public class JmePrincipal extends SimpleJfxApplication {

	public static JmePrincipal simpleApplication;

	public JmePrincipal(AppState... initialStates) {
		super(initialStates);
	}

	@Override
	public void initApp() {
		Carregador c = new Carregador();

		c.inicia(this);
		c.iniciaPlano(this);
		c.iniciaViewPort(this);
		c.inciaLuz(this);

	}

}