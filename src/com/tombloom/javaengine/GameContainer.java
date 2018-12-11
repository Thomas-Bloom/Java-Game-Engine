package com.tombloom.javaengine;

public class GameContainer implements Runnable{

    private Thread thread;
    private Window window;
    private boolean isRunning;
    // At the moment capping fps to 60
    private double updateCap = 1.0/60.0;
    private int framesPerSec = 0;

    private int width, height;
    private float scale = 1f;
    private String title = "Java Engine v0.001";

    public GameContainer(){
        width = 1280;
        height = 720;

        isRunning = false;
    }

    public void start(){
        window = new Window(this);

        // Creates a thread -> GameContainer is the thread
        thread = new Thread(this);
        // Run, it is the main thread
        thread.run();
    }

    public void stop(){

    }

    public void run(){
        isRunning = true;
        boolean doRender;
        // Converts nano time to milliseconds
        double timeSimplifier = 1000000000.0;
        double firstTime;
        double lastTime = System.nanoTime() / timeSimplifier;
        double passedTime;
        double unprocessedTime = 0;

        double frameTime = 0;
        int framesPassed = 0;
        framesPerSec = 0;

        while(isRunning){
            doRender = false;

            // Get current time
            firstTime = System.nanoTime() / timeSimplifier;
            // How long has it been since line below was executed
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= updateCap){
                // Makes up for lost frames
                // Updates things it missed
                unprocessedTime -= updateCap;

                // Only render if something changes on-screen
                doRender = true;

                // Updates game

                // Turns it into one second
                if(frameTime >= 1f){
                    frameTime = 0;
                    framesPerSec = framesPassed;
                    framesPassed = 0;
                    System.out.println("FPS: " + framesPerSec);
                }
            }

            if(doRender){
                // TODO: Render code
                window.updateWindow();
                framesPassed++;
            }
            // Help processor, allows it to not do any processing if there is nothing to process
            else{
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void dispose(){

    }

    public static void main(String[] args) {
        GameContainer gameContainer = new GameContainer();
        gameContainer.start();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
