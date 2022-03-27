package br.com.teujogo.init;

import org.lwjgl.system.Configuration;

import com.jme3.util.LWJGLBufferAllocator;

import javafx.application.Application;

public class Main {

	public static void main(String... args) {

		Configuration.GLFW_CHECK_THREAD0.set(false);
		Configuration.MEMORY_ALLOCATOR.set("jemalloc");
		System.setProperty("prism.lcdtext", "false");

		System.setProperty(LWJGLBufferAllocator.PROPERTY_CONCURRENT_BUFFER_ALLOCATOR, "true");

		Application.launch(InitFxJme.class, args);

	}

}