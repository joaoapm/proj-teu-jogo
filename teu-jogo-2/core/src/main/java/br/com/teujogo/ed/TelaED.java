package br.com.teujogo.ed;

import java.io.File;

import com.jme3.app.SimpleApplication;

public class TelaED {
	private SimpleApplication simpleApplication;
	private File fileToSave;

	public File getFileToSave() {
		return fileToSave;
	}

	public void setFileToSave(File fileToSave) {
		this.fileToSave = fileToSave;
	}

	public TelaED(SimpleApplication simpleApplication) {
		this.simpleApplication = simpleApplication;
	}

	public SimpleApplication getSimpleApplication() {
		return simpleApplication;
	}

	public void setSimpleApplication(SimpleApplication simpleApplication) {
		this.simpleApplication = simpleApplication;
	}

	public TelaED(SimpleApplication simpleApplication, File fileToSave) {
		super();
		this.simpleApplication = simpleApplication;
		this.fileToSave = fileToSave;
	}

}
