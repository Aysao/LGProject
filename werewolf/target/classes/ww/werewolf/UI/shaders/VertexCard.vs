#version 330 core

layout (location = 0) in vec2 in_position; 
layout (location = 1) in vec2 offset;    
layout (location = 2) in vec2 size;        
uniform vec2 screenSize;                   

void main() {
  vec2 pixelPos = offset + in_position * size;
  vec2 ndc = (pixelPos / screenSize) * 2.0 - 1.0;
  ndc.y = -ndc.y; 
  gl_Position = vec4(ndc, 0.0, 1.0);
}