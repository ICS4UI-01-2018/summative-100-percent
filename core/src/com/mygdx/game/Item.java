/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author phub1604
 */
public abstract class Item {

    private float x;
    private float y;
    private int width;
    private int height;
    private boolean isCollided;
    private Rectangle item;
    private float value;

    /**
     * Initializes Item data.
     *
     * @param x the x position of the Item.
     * @param y the y position of the Item.
     * @param width the width of the Item.
     * @param height the height of the Item.
     * @param value the value of the Item.
     */
    public Item(float x, float y, int width, int height, float value) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = value;
        this.isCollided = false;
        item = new Rectangle(x, y, width, height);
    }

    /**
     * Draws the Item.
     *
     * @param batch the SpriteBatch being used to draw.
     */
    public abstract void draw(SpriteBatch batch);

    /**
     * Returns the collision state of the Item.
     *
     * @return true if the Item has been touched by the Player, false otherwise.
     */
    public boolean getCollided() {
        return this.isCollided;
    }

    /**
     * Sets the Item in the collided state.
     */
    public void setCollided() {
        this.isCollided = true;
    }

    /**
     * Returns the x position of the Item.
     *
     * @return the float representing the Item's x position.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Returns the y position of the Item.
     *
     * @return the float representing the Item's y position.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Returns the width of the Item.
     *
     * @return the integer representing the Item's width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the Item.
     *
     * @return the integer representing the Item's height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the Rectangle of the Item.
     *
     * @return the Rectangle representing the Item.
     */
    public Rectangle getRect() {
        item.x = this.x;
        item.y = this.y;
        return item;
    }

    /**
     * Draws the shape of the Item (testing hitboxes).
     *
     * @param shapeBatch the ShapeRenderer being used to draw.
     */
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(this.x, this.y, this.width, this.height);
    }

    /**
     * Returns the value of the Item.
     *
     * @return the float repsenting the Item's value.
     */
    public float getValue() {
        return this.value;
    }
}
