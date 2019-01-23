/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Starm7481
 */
public class Room {

    private Rectangle shape;
    private int width;
    private int height;
    private float x;
    private float y;

    /**
     * Initialize Room data.
     *
     * @param x the x position of the Room.
     * @param y the y position of the Room.
     * @param width the width of the Room.
     * @param height the height of the Room.
     */
    public Room(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        shape = new Rectangle(x, y, width, height);
    }

    /**
     * Returns the x position of the Room.
     *
     * @return the float representing the Room's x position.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Returns the y position of the Room.
     *
     * @return the float representing the Room's y position.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Returns the width of the Room.
     *
     * @return the integer representing the Room's width.
     */
    public int getwidth() {
        return this.width;
    }

    /**
     * Returns the height of the Room.
     *
     * @return the integer representing the Room's height.
     */
    public int getheight() {
        return this.height;
    }

    /**
     * Draws the shape of the Room.
     *
     * @param shapeBatch the ShapeRenderer being used to draw.
     */
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(shape.x, shape.y, shape.width, shape.height);
    }

    /**
     * Returns the Rectangle of the Room.
     *
     * @return the Rectangle representing the Room.
     */
    public Rectangle getBounds() {
        return shape;
    }
}
