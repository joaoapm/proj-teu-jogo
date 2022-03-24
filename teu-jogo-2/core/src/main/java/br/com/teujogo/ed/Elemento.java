package br.com.teujogo.ed;

import java.util.Queue;

import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResults;
import com.jme3.collision.UnsupportedCollisionException;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;

import br.com.teujogo.enumeration.TipoElemento;

public class Elemento extends Spatial {

	private TipoElemento tipoElemento;
	private Geometry geometry;

	public Elemento(Geometry geom, TipoElemento tipoElemento2) {
		this.geometry = geom;
		this.tipoElemento = tipoElemento2;
	}

	public Elemento() {
		super();
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

	@Override
	public int collideWith(Collidable other, CollisionResults results) throws UnsupportedCollisionException {
		return geometry.collideWith(other, results);
	}

	@Override
	public void updateModelBound() {
		geometry.updateModelBound();

	}

	@Override
	public void setModelBound(BoundingVolume modelBound) {
		geometry.setModelBound(modelBound);

	}

	@Override
	public int getVertexCount() {
		return geometry.getVertexCount();
	}

	@Override
	public int getTriangleCount() {
		return geometry.getTriangleCount();
	}

	@Override
	public void depthFirstTraversal(SceneGraphVisitor visitor, DFSMode mode) {
		geometry.depthFirstTraversal(visitor, mode);

	}

	@Override
	protected void breadthFirstTraversal(SceneGraphVisitor visitor, Queue<Spatial> queue) {
		geometry.breadthFirstTraversal(visitor);

	}

}
