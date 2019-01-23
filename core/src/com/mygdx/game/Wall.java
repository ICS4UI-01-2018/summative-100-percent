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
public class Wall {

    private Rectangle shape;
    private int width;
    private int height;
    private float x;
    private float y;

    /**
     * Initialize Wall data.
     *
     * @param x the x position of the Wall.
     * @param y the y position of the Wall.
     * @param width the width of the Wall.
     * @param height the height of the Wall.
     */
    public Wall(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        shape = new Rectangle(x, y, width, height);
    }

    /**
     * Returns the x position of the Wall.
     *
     * @return the float representing the Wall's x position.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Returns the y position of the Wall.
     *
     * @return the float representing the Wall's y position.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Returns the width of the Wall.
     *
     * @return the integer representing the Wall's width.
     */
    public int getwidth() {
        return this.width;
    }

    /**
     * Returns the height of the Wall.
     *
     * @return the integer representing the Wall's height.
     */
    public int getheight() {
        return this.height;
    }

    /**
     * Draws the shape of the Wall.
     *
     * @param shapeBatch the ShapeRenderer being used to draw the Wall.
     */
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(shape.x, shape.y, shape.width, shape.height);
    }

    /**
     * Returns the Rectangle of the Wall.
     *
     * @return the Rectangle representing the Wall.
     */
    public Rectangle getBounds() {
        return shape;
    }
}
