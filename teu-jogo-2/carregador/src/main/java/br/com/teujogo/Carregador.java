package br.com.teujogo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

import br.com.teujogo.ed.TelaED;

public class Carregador extends SimpleApplication {

	private boolean isStandalone;
	public Camera cam;
	public Geometry plano;
	private Vector3f camIni = new Vector3f(0, 5.1f, 40);

	public Carregador(boolean isStandalone) {
		this.isStandalone = isStandalone;
	}

	@Override
	public void simpleInitApp() {
		getFlyByCamera().setEnabled(false);

		cam = this.getCamera().clone();
		cam.setLocation(camIni);

		iniciaViewPort(this);

		inciaLuz(this);
		// iniciaPlano(this);

		try {
			processArquivo();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inicia(TelaED telaED) {

		cam = telaED.getSimpleApplication().getCamera().clone();
		cam.setViewPort(0.250f, 1f, 0.250f, 1f);
		cam.setLocation(camIni);

		telaED.getSimpleApplication().getRenderManager().getMainView("Default").setEnabled(false);

		iniciaViewPort(telaED.getSimpleApplication());
		inciaLuz(telaED.getSimpleApplication());
		iniciaPlano(telaED.getSimpleApplication());

	}

	public ViewPort view2;

	private void iniciaViewPort(SimpleApplication simpleApplication) {
		view2 = simpleApplication.getRenderManager().createMainView("Bottom Left", cam);
		view2.setClearFlags(true, true, true);
		view2.attachScene(simpleApplication.getRootNode());
		view2.setBackgroundColor(new ColorRGBA(127f / 255f, 168f / 255f, 235f / 255f, 100f));
	}

	public static void main(String[] args) throws Exception {
		Carregador app = new Carregador(true);
		app.setSettings(new AppSettings(true));
		app.setShowSettings(false);
		app.settings.setResolution(1080, 720);
		app.setDisplayFps(false);
		app.setDisplayStatView(!false);
		app.start();
	}

	private void processArquivo() throws FileNotFoundException, Exception {
		InputStream is = getClass().getResourceAsStream("/jogo.mjg");
		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			String arquivo = "";
			while ((line = br.readLine()) != null) {
				String data = line;
				arquivo += data;
			}

			JSONObject jsonObj = new JSONObject(arquivo);
			final JSONArray parametros = jsonObj.getJSONArray("parametros");

			for (int i = 0; i < parametros.length(); ++i) {
				final JSONObject person = parametros.getJSONObject(i);
				instancia(person);
			}
		}
	}

	private void instancia(JSONObject person) {
		Box b = new Box(1, 1, 1);
		Geometry geom = new Geometry("Box", b);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geom.setMaterial(mat);
		rootNode.attachChild(geom);
		geom.setLocalTranslation(new Vector3f(1.1f, 0, -1.7f));
	}

	private void iniciaPlano(SimpleApplication ap) {
		Box b = new Box(50, 0.1f, 15);
		Geometry geom = new Geometry("Box", b);
		Material mat = new Material(ap.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
		mat.setBoolean("UseMaterialColors", true);
		mat.setColor("Diffuse", ColorRGBA.LightGray);
		mat.setColor("Ambient", ColorRGBA.LightGray);
		geom.setMaterial(mat);
		// ap.getRootNode().attachChild(geom);
		geom.setLocalTranslation(new Vector3f(0, -0.2f, 0));
		plano = geom;
	}

	private void inciaLuz(SimpleApplication simpleApplication) {
		DirectionalLight sun = new DirectionalLight();
		sun.setDirection(new Vector3f(0.5f, -1, -0.5f).normalizeLocal());
		sun.setColor(new ColorRGBA(ColorRGBA.White));
		simpleApplication.getRootNode().addLight(sun);

	}

	public boolean isStandalone() {
		return isStandalone;
	}

	public void setStandalone(boolean isStandalone) {
		this.isStandalone = isStandalone;
	}

}
