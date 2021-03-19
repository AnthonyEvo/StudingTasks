package lwjgl3Studing;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Main {
	
	public static void main(String args[]) {
		new Game();
	}
}

class Game implements Runnable{
	
	Thread game = new Thread(this, "game");
	public Window window;
	
	Game() {
		game.start();
	}
	
	public void run() {
		init();
		while(!window.shouldClose()) {
			update();
			render();
		}
	}
	
	public void init() {
		window = new Window(800, 600, "Game");
		window.create();
	}
	
	private void update() {
		window.update();
	}
	
	private void render() {
		window.swapBuffers();
	}
}

class Window {
	
	private int width, height;
	private String title;
	private long window;
	
	Window(int width, int heigth, String title) {
		this.width = width;
		this.height = heigth;
		this.title = title;
	}
	
	public void create() {
		if(!GLFW.glfwInit()) {
			System.err.println("GLFW failed");
			return;
		}
		
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		
		if(window == 0) {
			System.err.println("window failed to create");
			return;
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (videoMode.width() - width) /2, (videoMode.height() - height) /2);
		
		GLFW.glfwShowWindow(window);
		
		GLFW.glfwSwapInterval(1);
	}
	
	public void update() {
		GLFW.glfwPollEvents();
	}
	
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}
	
	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
}