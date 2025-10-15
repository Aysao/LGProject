package ww.werewolf.core.UI.menuPanel;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import ww.werewolf.core.UI.Component.TextLabel;
import ww.werewolf.core.UI.Component.UIButton;
import ww.werewolf.core.UI.shaders.Shader2D;
import ww.werewolf.core.UI.shaders.Mesh.BoxMesh;
import ww.werewolf.core.UI.shaders.Texture.TextureManager;
import ww.werewolf.core.enumCore.GameState;

public class MenuPanel {

    private UIButton[] buttonMenu;
    private TextLabel versionLabel;
    private GameState gameState = GameState.MENU;
    private BoxMesh buttonMesh; // Mesh du bouton 

    public MenuPanel(long glfwWindow, String title, String versionGame){
        buttonMesh = new BoxMesh();
        // Taille de la fenêtre pour le shader
		IntBuffer winw = BufferUtils.createIntBuffer(1);
		IntBuffer winh = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(glfwWindow, winw, winh);
		int w = winw.get(0);
		int h = winh.get(0);

		buttonMenu = new UIButton[4];
		buttonMenu[0] = new UIButton(new Vector2f(150, 200), new Vector2f(300, 60), new Vector3f(0.3f, 0.6f, 1.0f), "Serveur", () -> gameState = GameState.SERVER);
		buttonMenu[1] = new UIButton(new Vector2f(150, 300), new Vector2f(300, 60), new Vector3f(0.0f, 1.0f, 0.3f), "Client", () -> gameState = GameState.CLIENT);
		buttonMenu[2] = new UIButton(new Vector2f(150, 400), new Vector2f(300, 60), new Vector3f(0.3f, 0.6f, 0.3f), "Option", () -> gameState = GameState.OPTION);
		buttonMenu[3] = new UIButton(new Vector2f(150, 500), new Vector2f(300, 60), new Vector3f(1.0f, 0.3f, 0.3f), "Quitte", () -> glfwSetWindowShouldClose(glfwWindow, true));
		String versionText = title + ": " + versionGame;
		versionLabel = new TextLabel(versionText, new Vector2f(w-128,h-32), 0.5f);
    }
    public void getMenuPanel(long glfwWindow, DoubleBuffer mouseXBuf, DoubleBuffer mouseYBuf, Shader2D shader, TextureManager textureManager){
        // Nettoyage de l'écran
		glClearColor(0.1f, 0.1f, 0.15f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	
		// Taille de la fenêtre pour le shader
		IntBuffer winw = BufferUtils.createIntBuffer(1);
		IntBuffer winh = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(glfwWindow, winw, winh);
		int w = winw.get(0);
		int h = winh.get(0);

		glfwGetCursorPos(glfwWindow, mouseXBuf, mouseYBuf);
		double mx = mouseXBuf.get(0);
		double my = mouseYBuf.get(0);
		
		boolean mousePressed = glfwGetMouseButton(glfwWindow, GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS;
	
		shader.setUniform("screenSize", new Vector2f(w, h));
		// Dessiner les boutons avec couleur et position différentes
		for (int i = 0; i < buttonMenu.length; i++) {
			versionLabel.render(shader, buttonMesh, textureManager);
			buttonMenu[i].draw(shader, buttonMesh, textureManager);
			buttonMenu[i].update(mx, my, mousePressed);
		}
	
		// Unbind
		glBindVertexArray(0);
    }
}
