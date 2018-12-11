package com.tombloom.javaengine;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window extends JFrame {
    // Where the game will be rendered to
    private BufferedImage image;
    // Where the image will be rendered to
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private GameContainer gameContainer;

    public Window(GameContainer gameContainer){
        this.gameContainer = gameContainer;

        image = new BufferedImage(gameContainer.getWidth(), gameContainer.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension((int)(gameContainer.getWidth() * gameContainer.getScale()),
                                                ((int)(gameContainer.getHeight() * gameContainer.getScale()))));

        // Sort out properties of JFrame
        setupWindow();

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    private void setupWindow(){
        setTitle(gameContainer.getTitle());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void updateWindow(){
        graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getWidth(), null);
        bufferStrategy.show();
    }
}
