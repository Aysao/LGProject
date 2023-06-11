package ww.werewolf.UI;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glEnd;

public class CardUI {

    private float x, y;
    private String path = "./../Assets/Card/loup.png";
    // private String path = "./Assets/Card/loup.png";
    private final int width = 250;
    private int reswidth = 250;
    private final int height = 400;
    private int resheight = 400;
    Texture tex2d;
    public CardUI() {
        this.x = 0;
        this.y = 0;
        tex2d = getTexture(path);
    }

    public void DrawCard(int scalex, int scaley) {
        CheckSize(scalex, scaley);
        glEnable(GL_TEXTURE_2D);
        // System.out.println("lancement de la récupération de la texture ");
        tex2d.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 1.0f);
        glVertex2f(x, y);
        glTexCoord2f(1.0f, 1.0f);
        glVertex2f(x + reswidth, y);
        glTexCoord2f(1.0f, 0.0f);
        glVertex2f(x + reswidth, y + resheight);
        glTexCoord2f(0.0f, 0.0f);
        glVertex2f(x, y + resheight);
        glEnd();
        glBindTexture(GL_TEXTURE_2D, 0);
        // System.out.println(reswidth + " / " + resheight);

    }

    private void CheckSize(int scalex, int scaley) {
        reswidth = (int) ((width * scalex) / 1920);
        resheight = (int) ((height * scaley) / 1080);
    }

    private Texture getTexture(String texturefile)
    {
        return new Texture(reswidth, resheight, texturefile);
    }
}
