package br.com.teujogo.principal;

import com.jayfella.jfx.embedded.SimpleJfxApplication;
import com.jme3.app.state.AppState;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

import br.com.teujogo.Carregador;
import br.com.teujogo.controller.PrincipalController;
import br.com.teujogo.ed.Asset;
import br.com.teujogo.ed.Elemento;
import br.com.teujogo.enumeration.TipoElemento;
import br.com.teujogo.util.Gizmo;

public class JmePrincipal extends SimpleJfxApplication {

	public Carregador c;
	public Node shootables;
	public Node elementos;
	public Node elementosAlt;
	private Gizmo gizmo = new Gizmo();
	private DirectionalLight luz;
	private float luzI = 1f;

	public void elementosAltAdd(Geometry e) {
		elementosAlt.attachChild(e);
	}

	public JmePrincipal(AppState... initialStates) {
		super(initialStates);
	}

	public void setLuz(double d) {
		luzI = Float.valueOf(String.valueOf(d));
		luz.setColor(new ColorRGBA(ColorRGBA.White.mult(luzI)));
	}

	@Override
	public void initApp() {

		c = new Carregador();

		c.inicia(this);
		c.iniciaPlano(this);
		c.iniciaViewPort(this);
		luz = c.inciaLuz(this);

		shootables = new Node("Shootables");
		elementos = new Node("Elementos");
		elementosAlt = new Node("ElementosAlt");

		rootNode.attachChild(shootables);
		rootNode.attachChild(elementos);
		rootNode.attachChild(elementosAlt);

		shootables.attachChild(c.plano);

	}

	public void removeObj(Elemento e) {
		
		rootNode.detachChild(e.getGeometry());
		elementos.detachChild(e.getGeometry());
		e.removeFromParent();
		gizmo.removeGizmo();
	}
	
	public void addObj(Asset asset) {

		CollisionResults results = new CollisionResults();

		Vector2f mouseCoords = PrincipalController.ptDrop;
		mouseCoords.setY(720 - mouseCoords.y);

		Ray ray = new Ray(c.cam.getWorldCoordinates(mouseCoords, 0),
				c.cam.getWorldCoordinates(mouseCoords, 1).subtractLocal(c.cam.getWorldCoordinates(mouseCoords, 0)));

		shootables.collideWith(ray, results);

		if (results.size() > 0) {
			CollisionResult closest = results.getClosestCollision();
			Vector3f pt = closest.getContactPoint();
			Geometry geom = (Geometry) assetManager.loadModel("modelos/" + asset.getNome() + ".obj");
			getRootNode().attachChild(geom);
			geom.setLocalTranslation(pt);
			elementos.attachChild(new Elemento(geom, TipoElemento.ASSET));
		}
	}

	public long getid() {
		Thread currentThread = Thread.currentThread();
		return currentThread.getId();
	}

	public void addPersonagem(TipoElemento tipo) {

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

			System.out.println("Colisao[" + hit + " at " + pt + ", " + dist + "]");

			if (tipo.equals(TipoElemento.HUMANOIDE)) {

				Box b = new Box(2, 2, 2);
				Geometry geom = new Geometry("Box", b);
				Material mat = new Material(getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
				mat.setBoolean("UseMaterialColors", true);
				mat.setColor("Diffuse", ColorRGBA.Magenta);
				mat.setColor("Ambient", ColorRGBA.Magenta);
				geom.setMaterial(mat);
				getRootNode().attachChild(geom);
				geom.setLocalTranslation(pt);
				elementos.attachChild(new Elemento(geom, tipo));

			} else if (tipo.equals(TipoElemento.VEICULO)) {

				Box b = new Box(2, 1, 1);
				Geometry geom = new Geometry("Box", b);
				Material mat = new Material(getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
				mat.setBoolean("UseMaterialColors", true);
				mat.setColor("Diffuse", ColorRGBA.Cyan);
				mat.setColor("Ambient", ColorRGBA.Cyan);
				geom.setMaterial(mat);
				getRootNode().attachChild(geom);
				geom.setLocalTranslation(pt);
				elementos.attachChild(new Elemento(geom, tipo));
			}
		}
	}

	public Elemento selectiona() {
		CollisionResults results = new CollisionResults();

		Vector2f mouseCoords = PrincipalController.ptDrop;
		mouseCoords.setY(720 - mouseCoords.y);

		Ray ray = new Ray(c.cam.getWorldCoordinates(mouseCoords, 0),
				c.cam.getWorldCoordinates(mouseCoords, 1).subtractLocal(c.cam.getWorldCoordinates(mouseCoords, 0)));

		elementos.collideWith(ray, results);

		if (results.size() > 0) {
			Elemento e = getElemento(results);
			gizmo.criaGizmo(e);
			return e;
		}
		return null;
	}

	private Elemento getElemento(CollisionResults results) {
		CollisionResult closest = results.getClosestCollision();
		for (Spatial ele : elementos.getChildren()) {
			Elemento e = (Elemento) ele;
			if (e.getGeometry() != null && e.getGeometry().equals(closest.getGeometry())) {
				gizmo.criaGizmo(e);
				return e;
			}
		}
		return null;
	}

	public void gizMove(Vector2f pt) {
		if (gizmo.isGizming()) {
			gizmo.moveGizmo(this, null, pt);
		} else {
			CollisionResults results = new CollisionResults();

			Vector2f mouseCoords = pt;
			mouseCoords.setY(720 - mouseCoords.y);

			Ray ray = new Ray(c.cam.getWorldCoordinates(mouseCoords, 0),
					c.cam.getWorldCoordinates(mouseCoords, 1).subtractLocal(c.cam.getWorldCoordinates(mouseCoords, 0)));

			elementosAlt.collideWith(ray, results);

			if (results.size() > 0) {
				CollisionResult closest = results.getClosestCollision();
				gizmo.moveGizmo(this, closest.getGeometry(), mouseCoords);
			}
		}
	}

	public void gizRemove() {
		gizmo.resetGizmo();
	}

	public void moveCamE() {
		Camera cam = c.cam;
		Vector3f pos = cam.getLocation();
		pos.x -= 1;
		cam.setLocation(pos);
		cam.updateViewProjection();
	}

	public void moveCamD() {
		Camera cam = c.cam;
		Vector3f pos = cam.getLocation();
		pos.x += 1;
		cam.setLocation(pos);
		cam.updateViewProjection();
	}

	public void moveCamC() {
		Camera cam = c.cam;
		Vector3f pos = cam.getLocation();
		pos.y += 1;
		cam.setLocation(pos);
		cam.updateViewProjection();
	}

	public void moveCamB() {
		Camera cam = c.cam;
		Vector3f pos = cam.getLocation();
		pos.y -= 1;
		cam.setLocation(pos);
		cam.updateViewProjection();
	}

	public void roteCamC() {
		Camera cam = c.cam;

		Quaternion rotate = new Quaternion();

		rotate.fromAngleNormalAxis(0.03f, new Vector3f(1, 0, 0));
		Quaternion q = rotate.mult(cam.getRotation());
		cam.setRotation(q);
	}

	public void roteCamB() {
		Camera cam = c.cam;

		Quaternion rotate = new Quaternion();

		rotate.fromAngleNormalAxis(-0.03f, new Vector3f(1, 0, 0));
		Quaternion q = rotate.mult(cam.getRotation());
		cam.setRotation(q);
	}

	public void moveCamF() {
		Camera cam = c.cam;
		Vector3f pos = cam.getLocation();
		pos.z -= 1;
		cam.setLocation(pos);
		cam.updateViewProjection();
	}

	public void moveCamT() {
		Camera cam = c.cam;
		Vector3f pos = cam.getLocation();
		pos.z += 1;
		cam.setLocation(pos);
		cam.updateViewProjection();
	}

	public float getLuzI() {
		return luzI;
	}

	public void setLuzI(float luzI) {
		this.luzI = luzI;
	}

}