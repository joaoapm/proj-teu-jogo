package br.com.teujogo.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jme3.scene.Spatial;

import br.com.teujogo.ed.TelaED;


public class GerenciadorArquivos {

	private String propriedade = "%s = %s";
	private String propriedadeTipo = "%s%s";

	private ByteArrayOutputStream myWriter;
	private FileOutputStream fos;

	public ByteArrayOutputStream gerarArquivo(TelaED telaED) {

		try {
			myWriter = new ByteArrayOutputStream();

			JSONObject obRet = new JSONObject();
			JSONArray ob = new JSONArray();

			for (Spatial obj : telaED.getSimpleApplication().getRootNode().getChildren())
				ob.put(geraObj(obj));

			obRet.put("parametros", ob);

			myWriter.write(obRet.toString().getBytes());

			if (telaED.getFileToSave() != null) {
				fos = new FileOutputStream(new File(telaED.getFileToSave().getAbsolutePath() + ".mjg"));
				
				myWriter.writeTo(fos);
			}

			myWriter.close();
			
			if(fos != null)
				fos.close();

			return myWriter;

		} catch (IOException | JSONException e) {
			System.out.println("[Erro ao salvar arquivo]");
			e.printStackTrace();
		}
		return myWriter;

	}

	public void exporta(TelaED telaED) {
		ByteArrayOutputStream arq = new GerenciadorArquivos().gerarArquivo(new TelaED(telaED.getSimpleApplication(), null));
		try {
			updateJarFile(telaED.getFileToSave(),arq);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 

	private void updateJarFile(File file2, ByteArrayOutputStream arq) throws IOException {

		InputStream is = getClass().getResourceAsStream("/carregador-1.0.jar");

		File tmpJarFile = File.createTempFile("tempJar", ".jar");

		File srcJarFile = stream2file(is);

		File filesToAdd = File.createTempFile("tempMjg", ".mjg");

		try (FileOutputStream outputStream = new FileOutputStream(filesToAdd)) {
		    outputStream.write(arq.toByteArray());
		}
		
		JarFile jarFile = new JarFile(srcJarFile);

		boolean jarUpdated = false;

		List<String> fileNames = new ArrayList<String>();

		try {
			JarOutputStream tempJarOutputStream = new JarOutputStream(new FileOutputStream(tmpJarFile));
			try {
				File file = filesToAdd;
				FileInputStream fis = new FileInputStream(file);
				try {
					byte[] buffer = new byte[1024];
					int bytesRead = 0;
					JarEntry entry = new JarEntry("jogo.mjg");
					fileNames.add(entry.getName());
					tempJarOutputStream.putNextEntry(entry);
					while ((bytesRead = fis.read(buffer)) != -1) {
						tempJarOutputStream.write(buffer, 0, bytesRead);
					}
				} finally {
					fis.close();
				}

				Enumeration<?> jarEntries = jarFile.entries();
				while (jarEntries.hasMoreElements()) {
					JarEntry entry = (JarEntry) jarEntries.nextElement();
					String[] fileNameArray = (String[]) fileNames.toArray(new String[0]);
					Arrays.sort(fileNameArray);
					boolean update = true;
					if (Arrays.binarySearch(fileNameArray, entry.getName()) < 0) {
						InputStream entryInputStream = jarFile.getInputStream(entry);
						tempJarOutputStream.putNextEntry(entry);
						byte[] buffer = new byte[1024];
						int bytesRead = 0;
						while ((bytesRead = entryInputStream.read(buffer)) != -1) {
							tempJarOutputStream.write(buffer, 0, bytesRead);
						}
					} else if (!update) {
						throw new IOException("[Erro ao exportar]");
					}
				}

				jarUpdated = true;
			} catch (Exception ex) {
				System.err.println("[Erro ao exportar]");
				tempJarOutputStream.putNextEntry(new JarEntry("stub"));
			} finally {
				tempJarOutputStream.close();
			}

		} finally {
			jarFile.close();
			if (!jarUpdated) {
				tmpJarFile.delete();
			}
		}

		if (jarUpdated) {
			srcJarFile.delete();
			File f = new File(file2.getAbsolutePath() + ".jar");
			tmpJarFile.renameTo(f);
		}
	}
	
	public static File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile("tempJar", ".jar");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }
	
	private JSONObject geraObj(Spatial obj) throws IOException, JSONException {
		JSONObject ob = new JSONObject();
		ob.put("obj", "node");
		ob.put("id", obj.getName());
		ob.put("tipo", obj.getClass().getSimpleName());

		ob.put("translation", String.format(propriedadeTipo, "Vector3", obj.getLocalTransform().getTranslation()));
		ob.put("rotation", String.format(propriedadeTipo, "Quartenion", obj.getLocalTransform().getRotation()));
		ob.put("scale", String.format(propriedadeTipo, "Vector3", obj.getLocalTransform().getScale()));

		return ob;

	}
	
}
