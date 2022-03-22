package br.com.teujogo.util;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Dome;

import br.com.teujogo.ed.Elemento;
import br.com.teujogo.principal.JfxPrincipal;
import br.com.teujogo.principal.JmePrincipal;

public class Gizmo {

	private Elemento elemeSel;
	private Vector2f ptIni;
	private Vector2f ptAtual;
	private Vector3f ptInicioEle;
	private Geometry geoGiz = null;

	public boolean isGizming() {
		return geoGiz != null;
	}

	private void criaArestas(Vector3f pos, float ang, Vector3f dir, ColorRGBA cor, String nome) {
		Cylinder b = new Cylinder(5, 50, 0.1f, 5, true);
		Geometry geom = new Geometry(nome, b);
		Material mat = new Material(JfxPrincipal.jfxApp.get().getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
		mat.setBoolean("UseMaterialColors", true);
		mat.setColor("Diffuse", cor);
		mat.setColor("Ambient", cor);
		geom.setMaterial(mat);
		geom.setLocalTranslation(pos);

		Quaternion roll180 = new Quaternion();
		roll180.fromAngleAxis(ang, dir);
		geom.setLocalRotation(roll180);

		JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();
		p.enqueue(() -> {
			p.elementosAltAdd(geom);
		});
	}

	public void criaSeta(Vector3f pos, float ang, Vector3f dir, ColorRGBA cor, String nome) {
		Dome bsy = new Dome(new Vector3f(0, 0, 0), 2, 9, 0.9f, false);
		Geometry geomsz = new Geometry(nome, bsy);
		Material matsz = new Material(JfxPrincipal.jfxApp.get().getAssetManager(),
				"Common/MatDefs/Light/Lighting.j3md");
		matsz.setBoolean("UseMaterialColors", true);
		matsz.setColor("Diffuse", cor);
		matsz.setColor("Ambient", cor);
		geomsz.setMaterial(matsz);

		geomsz.setLocalTranslation(pos);

		Quaternion roll180dz = new Quaternion();
		roll180dz.fromAngleAxis(ang, dir);
		geomsz.setLocalRotation(roll180dz);

		JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();
		p.enqueue(() -> {
			p.elementosAltAdd(geomsz);
		});
	}

	public void criaGizmo(Elemento elemento) {

		removeGizmo();

		criaArestas(getPos("ZA", elemento.getGeometry().getLocalTranslation()), 0, new Vector3f(), ColorRGBA.Green,
				"ZA");
		criaSeta(getPos("ZS", elemento.getGeometry().getLocalTranslation()), 1.5708f, new Vector3f(1, 0, 0),
				ColorRGBA.Green, "ZS");

		criaArestas(getPos("XA", elemento.getGeometry().getLocalTranslation()), 1.5708f, new Vector3f(0, 1, 0),
				ColorRGBA.Blue, "XA");
		criaSeta(getPos("XS", elemento.getGeometry().getLocalTranslation()), -1.5708f, new Vector3f(0, 0, 1),
				ColorRGBA.Blue, "XS");

		criaArestas(getPos("YA", elemento.getGeometry().getLocalTranslation()), 1.5708f, new Vector3f(1, 0, 0),
				ColorRGBA.Red, "YA");
		criaSeta(getPos("YS", elemento.getGeometry().getLocalTranslation()), 0, new Vector3f(), ColorRGBA.Red, "YS");

		elemeSel = elemento;

	}

	public Vector3f getPos(String tp, Vector3f posE) {
		Vector3f v3 = new Vector3f();

		if (tp == "ZA") {
			return posE.add(new Vector3f(0, 0, 0));
		} else if (tp == "ZS") {
			return posE.add(new Vector3f(0, 0, 3.0f));

		} else if (tp == "XA") {
			return posE.add(new Vector3f(2.5f, 0, 0));
		} else if (tp == "XS") {
			return posE.add(new Vector3f(4.8f, 0, 0));

		} else if (tp == "YA") {
			return posE.add(new Vector3f(0, 2.5f, 0));
		} else if (tp == "YS") {
			return posE.add(new Vector3f(0, 4.8f, 0));
		}

		return v3;
	}

	public  void removeGizmo() {
		JmePrincipal p = (JmePrincipal) JfxPrincipal.jfxApp.get();
		p.enqueue(() -> {
			for (Spatial ele : p.elementosAlt.getChildren()) {
				ele.removeFromParent();
			}
		});
	}

	public void resetGizmo() {
		ptIni = null;
		ptAtual = null;
		ptInicioEle = null;
		this.geoGiz = null;
	}

	public void moveGizmo(JmePrincipal jme, Geometry geometry, Vector2f mouseCoords) {
		
		if (this.geoGiz == null)
			this.geoGiz = geometry;
		
		if (ptIni == null) {
			ptInicioEle = new Vector3f(geoGiz.getLocalTranslation().x, geoGiz.getLocalTranslation().y,geoGiz.getLocalTranslation().z);
			ptIni = new Vector2f(mouseCoords.x, mouseCoords.y);
			ptAtual = new Vector2f(mouseCoords.x,720- mouseCoords.y);
		}

		Vector2f rel = ptAtual.subtractLocal(mouseCoords);
		ptAtual = new Vector2f(mouseCoords.x, mouseCoords.y);

		Vector3f a = jme.getViewPort().getCamera().getRotation().getRotationColumn(1).mult(rel.y / 30);
		Vector3f a1 = jme.getViewPort().getCamera().getRotation().getRotationColumn(1).mult(rel.x / 30);

		Vector3f b = jme.getViewPort().getCamera().getRotation().getRotationColumn(0).mult(rel.y / 30);
		Vector3f b1 = jme.getViewPort().getCamera().getRotation().getRotationColumn(0).mult(rel.x / 30);

		Vector3f c = jme.getViewPort().getCamera().getRotation().getRotationColumn(2).mult(rel.y / 15);

		Vector3f posAt = elemeSel.getGeometry().getLocalTranslation();
		posAt.addLocal(a);
		posAt.addLocal(a1);
		posAt.addLocal(b);
		posAt.addLocal(b1);
		posAt.addLocal(c);

		if (geoGiz.getName().contains("X")) {
			posAt.y = ptInicioEle.y;
			posAt.z = ptInicioEle.z;
		} else if (geoGiz.getName().contains("Y")) {
			posAt.x = ptInicioEle.x;
			posAt.z = ptInicioEle.z;
		} else if (geoGiz.getName().contains("Z")) {
			posAt.x = ptInicioEle.x;
			posAt.y = ptInicioEle.y;
		}

		elemeSel.getGeometry().setLocalTranslation(posAt);
		criaGizmo(elemeSel);

	}

}
