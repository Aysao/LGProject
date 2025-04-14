package ww.werewolf.UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;
import org.lwjgl.opengl.GL46;

public class Simple2DShader {

    private int shaderProgramID;

    public Simple2DShader(String vertexShaderPath, String fragmentShaderPath) {
        createShaderProgram(vertexShaderPath, fragmentShaderPath);
    }

    private void createShaderProgram(String vertexShaderPath, String fragmentShaderPath) {

        String vertexShader = readShaderFile(vertexShaderPath);
        String fragmentShader = readShaderFile(fragmentShaderPath);

        System.out.println(vertexShader + "\n" + fragmentShader);

        int vertexShaderID = createShader(GL_VERTEX_SHADER, vertexShader);
        int fragmentShaderID = createShader(GL_FRAGMENT_SHADER, fragmentShader);

        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID, vertexShaderID);
        glAttachShader(shaderProgramID, fragmentShaderID);
        glLinkProgram(shaderProgramID);

        if (GL46.glGetProgrami(shaderProgramID, GL_LINK_STATUS) != GL_TRUE) {
            System.err.println("Échec du lien du programme de shader!");
            glGetProgramInfoLog(shaderProgramID);
            System.exit(-1);
        }

        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
    }

    private int createShader(int type, String source) {
        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, source);
        glCompileShader(shaderID);

        if (GL46.glGetShaderi(shaderID, GL_COMPILE_STATUS) != GL_TRUE) {
            System.err.println("Échec de la compilation du shader!");
            System.out.println("Compilation error : " + glGetShaderInfoLog(shaderID));
            System.exit(-1);
        }

        return shaderID;
    }

    public void start() {
        glUseProgram(shaderProgramID);
    }

    public void setUniform(String uniformName, Matrix4f transform) {
        int uniformLocation = glGetUniformLocation(shaderProgramID, uniformName);
        if (uniformLocation != -1) {

            GL46.glUniformMatrix4fv(uniformLocation, false, toFloatBuffer(transform));
        } else {
            System.err.println("Uniform introuvable : " + uniformName);
        }
    }

    public void setUniform(String uniformName, Vector4f color) {
        int uniformLocation = glGetUniformLocation(shaderProgramID, uniformName);
        if (uniformLocation != -1) {
            GL46.glUniform4fv(uniformLocation, toFloatBuffer(color));
        } else {
            System.err.println("Uniform introuvable : " + uniformName);
        }
    }

    public void setUniform(String uniformName, Vector2f color) {
        int uniformLocation = glGetUniformLocation(shaderProgramID, uniformName);
        if (uniformLocation != -1) {
            GL46.glUniform2f(uniformLocation, color.x,color.y);
        } else {
            System.err.println("Uniform introuvable : " + uniformName);
        }
    }

    public void setUniform(String uniformName, Vector3f color) {
        int uniformLocation = glGetUniformLocation(shaderProgramID, uniformName);
        if (uniformLocation != -1) {
            GL46.glUniform3f(uniformLocation, color.x,color.y,color.z);
        } else {
            System.err.println("Uniform introuvable : " + uniformName);
        }
    }

    public void end() {
        glUseProgram(0);
    }

    public void cleanUp() {
        glDeleteProgram(shaderProgramID);
    }

    public void create(){
        
    }

    public static FloatBuffer toFloatBuffer(Matrix4f matrix) {
        FloatBuffer buffer = FloatBuffer.allocate(16); // Allocate space for 16 floats (matrix elements)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buffer.put(matrix.get(i, j)); // Put each element in row-major order
            }
        }
        return buffer;
    }

    public static FloatBuffer toFloatBuffer(Vector4f vector) {
        FloatBuffer buffer = FloatBuffer.allocate(4); // Allocate space for 4 floats (vector components)

        buffer.put(vector.x);
        buffer.put(vector.y);
        buffer.put(vector.z);
        buffer.put(vector.w);

        buffer.rewind(); // Reset buffer position for reading
        return buffer;
    }

    public static String readShaderFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier shader: " + e.getMessage());
            System.exit(-1);
            return null;
        }
    }
}