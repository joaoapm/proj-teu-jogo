package br.com.teujogo.ed;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResults;
import com.jme3.collision.UnsupportedCollisionException;
import com.jme3.scene.Geometry;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;

import br.com.teujogo.enumeration.TipoElemento;
import javafx.scene.paint.Color;

public class Elemento extends Spatial {

	private TipoElemento tipoElemento;
	private Geometry geometry;

	private List<Regra> regras = new ArrayList<Regra>();

	private boolean isInimigo;
	private boolean podeAtacar;
	private Integer dano;
	private boolean podePular;
	private boolean possuiVidas;
	private Integer qntVidas;
	private Color cor;
	private String asset;

	public Elemento(Geometry geom, TipoElemento tipoElemento2) {
		this.geometry = geom;
		this.tipoElemento = tipoElemento2;
	}

	public Elemento() {
		super();
	}

	public Integer getQntVidas() {
		return qntVidas;
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

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

	public boolean isInimigo() {
		return isInimigo;
	}

	public void setInimigo(boolean isInimigo) {
		this.isInimigo = isInimigo;
	}

	public boolean isPodeAtacar() {
		return podeAtacar;
	}

	public void setPodeAtacar(boolean podeAtacar) {
		this.podeAtacar = podeAtacar;
	}

	public Integer getDano() {
		return dano;
	}

	public void setDano(Integer dano) {
		this.dano = dano;
	}

	public void setQntVidas(Integer qntVidas) {
		this.qntVidas = qntVidas;
	}

	public boolean isPodePular() {
		return podePular;
	}

	public void setPodePular(boolean podePular) {
		this.podePular = podePular;
	}

	public boolean isPossuiVidas() {
		return possuiVidas;
	}

	public void setPossuiVidas(boolean possuiVidas) {
		this.possuiVidas = possuiVidas;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
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
