#version 330 core

in vec2 TexCoords;    // Coordonnées de texture
out vec4 FragColor;   // Couleur du fragment

// Uniforme pour la texture
uniform sampler2D tex0;

// Uniforme pour la couleur
uniform vec3 color;

// Uniforme pour basculer entre texture et couleur
uniform bool useTexture;

void main()
{
    if (useTexture)
    {
        // Si on utilise la texture, on récupère la couleur de la texture
        FragColor = texture(tex0, TexCoords);
    }
    else
    {
        // Sinon, on utilise la couleur uniforme
        FragColor = vec4(color,1.0f);
    }
}