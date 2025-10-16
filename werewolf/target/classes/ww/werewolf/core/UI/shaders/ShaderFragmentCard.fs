#version 330 core

in vec2 TexCoords;    // Coordonnées de texture
out vec4 FragColor;   // Couleur du fragment

// Uniforme pour la texture
uniform sampler2D fontsAtlas;
uniform sampler2D cardsAtlas;
uniform sampler2D backgroundAtlas;

// Uniforme pour la couleur
uniform vec3 color;

// Uniforme pour basculer entre texture et couleur
uniform bool useTexture;
uniform int textureType;

void main()
{
    if (useTexture)
    {
        if(textureType == 0){
            // Si on utilise la texture, on récupère la couleur de la texture
            FragColor = texture(backgroundAtlas, TexCoords);
        }
        else if (textureType == 1){
            FragColor = texture(cardsAtlas, TexCoords);
        }
        else if (textureType == 2){
            FragColor = texture(fontsAtlas, TexCoords);
        }
    }
    else
    {
        // Sinon, on utilise la couleur uniforme
        FragColor = vec4(color,1.0f);
    }
}