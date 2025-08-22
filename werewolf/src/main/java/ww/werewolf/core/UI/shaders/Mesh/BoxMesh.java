package ww.werewolf.core.UI.shaders.Mesh;


public class BoxMesh extends Mesh {
    private float[] vertices;
    private int[] indices;

    public BoxMesh() {
        float[] vertices = {
            // Position (x, y)
            -0.5f,  0.5f, // top-left
            -0.5f, -0.5f, // bottom-left
             0.5f, -0.5f, // bottom-right
             0.5f,  0.5f  // top-right
        };
        
        int[] indices = {
            0, 1, 2,
            2, 3, 0
        };
    
        init(vertices, indices);
    }
}
