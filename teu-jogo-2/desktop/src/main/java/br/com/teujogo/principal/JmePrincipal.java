package br.com.teujogo.principal;

import com.jayfella.jfx.embedded.SimpleJfxApplication;
import com.jme3.app.state.AppState;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

import br.com.teujogo.Carregador;
import br.com.teujogo.controller.PrincipalController;

public class JmePrincipal extends SimpleJfxApplication {

	public JmePrincipal(AppState... initialStates) {
		super(initialStates);
	}

	public Carregador c;
	public Node shootables;

	@Override
	public void initApp() {

		c = new Carregador();

		c.inicia(this);
		c.iniciaPlano(this);
		c.iniciaViewPort(this);
		c.inciaLuz(this);

		shootables = new Node("Shootables");
		rootNode.attachChild(shootables);
		shootables.attachChild(c.plano);

	}

	public void addPersonagem() {

		CollisionResults results = new CollisionResults();

		Vector2f mouseCoords = PrincipalController.ptDrop;
		mouseCoords.setY(720 - mouseCoords.y);

		Ray ray = new Ray(c.cam.getWorldCoordinates(mouseCoords, 0),
				c.cam.getWorldCoordinates(mouseCoords, 1).subtractLocal(c.cam.getWorldCoordinates(mouseCoords, 0)));

		shootables.collideWith(ray, results);

		if (results.size() > 0) {
			CollisionResult closest = results.getClosestCollision();

			float dist = closest.getDistance();
			Vector3f pt = closest.getContactPoint();
			String hit = closest.getGeometry().getName();

			//System.out.println("Colisao[" + hit + " at " + pt + ", " + dist + "]");

			Box b = new Box(2, 2, 2);
			Geometry geom = new Geometry("Box", b);
			Material mat = new Material(getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
			mat.setBoolean("UseMaterialColors", true);
			mat.setColor("Diffuse", ColorRGBA.Magenta);
			mat.setColor("Ambient", ColorRGBA.Magenta);
			geom.setMaterial(mat);
			getRootNode().attachChild(geom);
			geom.setLocalTranslation(pt);
			shootables.attachChild(geom);

		}
	}
}