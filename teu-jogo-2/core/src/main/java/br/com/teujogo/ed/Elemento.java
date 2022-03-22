package br.com.teujogo.ed;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

import br.com.teujogo.enumeration.TipoElemento;

public class Elemento extends Node {

	private TipoElemento tipoElemento;
	private Geometry geometry;

	public Elemento(Geometry geom, TipoElemento tipoElemento2) {
		this.geometry = geom;
		this.tipoElemento = tipoElemento2;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public TipoElemento getTipoElemento() {
		return tipoElemento;
	}

	public void setTipoElemento(TipoElemento tipoElemento) {
		this.tipoElemento = tipoElemento;
	}

}
