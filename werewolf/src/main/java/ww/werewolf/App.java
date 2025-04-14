package ww.werewolf;

import java.nio.IntBuffer;
import java.util.Scanner;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.Version;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_MAXIMIZED;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.system.MemoryUtil.NULL;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import ww.werewolf.Card.Cupidon;
import ww.werewolf.Card.WereWolf;
import ww.werewolf.Card.Witch;
import ww.werewolf.Enum.GameState;
import ww.werewolf.GameSystem.AvailableCard;
import ww.werewolf.GameSystem.Board;
import ww.werewolf.GameSystem.ListPlayer;
import ww.werewolf.GameSystem.Player;
import ww.werewolf.Network.GameClient;
import ww.werewolf.Network.GameServer;
import ww.werewolf.UI.Simple2DShader;
import ww.werewolf.UI.shaders.ButtonMesh;

public class App {
	private static Server server = null;

	private IntBuffer winw = BufferUtils.createIntBuffer(4);
    private IntBuffer winh = BufferUtils.createIntBuffer(4);

    private int width, height;
    private String title;
    private long glfwWindow;

    private Simple2DShader shader;
	private GameState gameState = GameState.MENU;


	private ButtonMesh buttonMesh; // Mesh du bouton 
	

	public static void main(String[] args) {
		boolean runnable = false;

		/*
		 * Affichage
		 */
		new App().run();

		if(runnable){

			ListPlayer players = new ListPlayer();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Serveur : 1, Client : 2");
			String choice = scanner.nextLine();

			/*
			* Initialisation GameServer
			*/
			if(choice.equals("1")){
				App.server = new GameServer().getServer();
			}

			/*
			* Initialisation Client
			*/
			GameClient networkClient = new GameClient();
			Client client = networkClient.getClient();
			client.sendTCP("DoneInit");

			/*
			* Mis en attente du serveur pour qu'il puisse récup tous les clients
			*/

			if(client.getID() == 1 && App.server != null){
				System.out.println("Ready ?");
				String test = scanner.nextLine();
				
				/*
				* Création block Joueur
				*/

				for(int i = 0; i < App.server.getConnections().length; i++){
						Player p = new Player();
						p.setClient(App.server.getConnections()[i].getID());
						players.add(p);
					}
				
				
			}

			
			
			/*
			* Création carte available
			*/

			AvailableCard cardAvailable = new AvailableCard();
			try{
				cardAvailable.addXCard(WereWolf.class, 5);
				//cardAvailable.addXCard(Villager.class, 1);
				cardAvailable.addXCard(Witch.class, 1);
				cardAvailable.addXCard(Cupidon.class, 1);

			}
			catch(Exception e){
				e.printStackTrace();
			}

			/*
			* Création du Board
			*/
			Board b = new Board(players, null, cardAvailable, client);
		}

		//Board b = new Board(user, null,cardAvailable);
		//System.out.println(b.toString());
		//Window w = Window.getWindow();
		//w.run();
	}
	public static Server getServer() {
		return server;
	}
	public static void setServer(Server server) {
		App.server = server;
	}

	public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

	public void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to init GLFW.");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //vaoID = glGenVertexArrays();
        //glBindVertexArray(vaoID);

		int width = 800;
        int height = 600;
		String title = "WereWolf Game";
        
        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        glfwSetKeyCallback(glfwWindow, (glfwWindow, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(glfwWindow, true); // We will detect this in the rendering loop
        });

        glfwMakeContextCurrent(glfwWindow);
        // V-sync
        glfwSwapInterval(1);

        glfwShowWindow(glfwWindow);
        GL.createCapabilities();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glEnable(GL_BLEND);
        glOrtho(0, width, 0, height, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);

        shader = new Simple2DShader(
                "werewolf\\src\\main\\java\\ww\\werewolf\\UI\\shaders\\VertexCard.vs", 
                "werewolf\\src\\main\\java\\ww\\werewolf\\UI\\shaders\\FragmentCard.fs");

		buttonMesh = new ButtonMesh();
    }

	public void loop() {
        
        while (!glfwWindowShouldClose(glfwWindow)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glfwGetWindowSize(glfwWindow, winw, winh);
	
			shader.start();
	
			if (gameState == GameState.MENU) {
				renderMenu();
				//handleMenuInput();
			} else if (gameState == GameState.GAME) {
				//renderGame(); // pour plus tard
			}
	
			shader.end();
	
			glfwSwapBuffers(glfwWindow);
			glfwPollEvents();
			glClearColor(0.2f, 0.0f, 0.0f, 1.0f);
		}

    }

	public void renderMenu() {
		// Nettoyage de l'écran
		glClearColor(0.1f, 0.1f, 0.15f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	
		// Taille de la fenêtre pour le shader
		IntBuffer winw = BufferUtils.createIntBuffer(1);
		IntBuffer winh = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(glfwWindow, winw, winh);
		int w = winw.get(0);
		int h = winh.get(0);
	
		shader.setUniform("screenSize", new Vector2f(w, h));
		System.out.println("Window size: " + w + "x" + h);
		// Dessiner les boutons avec couleur et position différentes
		buttonMesh.drawButton(shader, new Vector2f(150, 200), new Vector2f(300, 60), new Vector3f(0.3f, 0.6f, 1.0f)); // Serveur
		buttonMesh.drawButton(shader, new Vector2f(150, 300), new Vector2f(300, 60), new Vector3f(0.3f, 1.0f, 0.6f)); // Client
		buttonMesh.drawButton(shader, new Vector2f(150, 400), new Vector2f(300, 60), new Vector3f(1.0f, 0.3f, 0.3f)); // Quitter
	
		// Unbind
		glBindVertexArray(0);
	}

	private boolean inZone(int x, int y, int x1, int y1, int x2, int y2) {
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}


}
