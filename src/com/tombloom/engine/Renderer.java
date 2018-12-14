package com.tombloom.javaengine;

import java.awt.image.DataBufferInt;

public class Renderer {
    private int pixelWidth, pixelHeight;
    private int[] pixels;

    public Renderer(GameContainer gameContainer){
        pixelWidth = gameContainer.getWidth();
        pixelHeight = gameContainer.getWidth();
        // Gives pixels access to pixel data of image in the window
        pixels = ((DataBufferInt)gameContainer.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clearScreen(){
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
        }
    }
}
