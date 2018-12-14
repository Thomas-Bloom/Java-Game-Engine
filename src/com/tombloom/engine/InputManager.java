package com.tombloom.javaengine;

import java.awt.event.*;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private GameContainer gameContainer;

    //
    //
    // KEYBOARD

    // KEYS
    private final int NUM_KEYS = 256;
    // Keys on current frame
    private boolean[] keys = new boolean[NUM_KEYS];
    // Keys on previous frame
    private boolean[] keysLast = new boolean[NUM_KEYS];


    //
    //
    // MOUSE

    // MOUSE BUTTONS
    private final int NUM_M_BUTTONS = 5;
    // Keys on current frame
    private boolean[] mButtons = new boolean[NUM_M_BUTTONS];
    // Keys on previous frame
    private boolean[] mButtonsLast = new boolean[NUM_M_BUTTONS];

    // MOUSE POSITION
    private int mousePosX, mousePosY;

    // MOUSE SCROLL WHEEL
    private int scrollNum;

    public InputManager(GameContainer gameContainer){
        // Initialisation of variables
        this.gameContainer = gameContainer;
        mousePosX = 0;
        mousePosY = 0;
        scrollNum = 0;

        // Attach InputManager listeners to canvas
        gameContainer.getWindow().getCanvas().addKeyListener(this);
        gameContainer.getWindow().getCanvas().addMouseListener(this);
        gameContainer.getWindow().getCanvas().addMouseMotionListener(this);
        gameContainer.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update(){
        // Reset scrollNum to 0
        scrollNum = 0;

        // Called at the end of the frame
        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);

        // Called at the end of the frame
        System.arraycopy(mButtons, 0, mButtonsLast, 0, NUM_M_BUTTONS);
    }

    // KEYBOARD KEYS
    public boolean isKey(int keyCode){
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode){
        // Is not down now, but was in the last frame
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode){
        // Is down now, and wasn't in last frame
        return keys[keyCode] && !keysLast[keyCode];
    }

    // MOUSE BUTTONS

    public boolean isMButton(int mButton){
        return mButtons[mButton];
    }

    public boolean isMButtonDown(int mButton){
        // Is not down now, but was in the last frame
        return !mButtons[mButton] && mButtonsLast[mButton];
    }

    public boolean isMButtonUp(int mButton) {
        // Is down now, and wasn't in last frame
        return mButtons[mButton] && !mButtonsLast[mButton];
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mButtons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mButtons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Mouse enters the window, don't really need this
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Mouse exits the window, don't really need this
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosX = (int)(e.getX() / gameContainer.getScale() + gameContainer.getWindow().getBounds().width);
        mousePosY = (int)(e.getY() / gameContainer.getScale() + gameContainer.getWindow().getBounds().height);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosX = (int)(e.getX() / gameContainer.getScale());
        mousePosY = (int)(e.getY() / gameContainer.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrollNum = e.getWheelRotation();
    }

    public int getMousePosX() {
        return mousePosX;
    }

    public int getMousePosY() {
        return mousePosY;
    }

    public int getScrollNum() {
        return scrollNum;
    }
}
