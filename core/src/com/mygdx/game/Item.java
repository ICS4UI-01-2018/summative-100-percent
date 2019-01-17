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
    
    public Item(float x, float y, int width, int height, float value) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = value;
        this.isCollided = false;
        item = new Rectangle (x, y, width, height);
    }
    
    public boolean getCollided() {
        return this.isCollided;
    }
    
    public void setCollided() {
        this.isCollided = true;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Rectangle getRect() {
        item.x = this.x;
        item.y = this.y;
        return item;
    }
    
    
    
    public void draw (ShapeRenderer shapeBatch) {
        shapeBatch.rect(this.x, this.y, this.width, this.height);
    }
    
    public float getValue() {
        return this.value;
    }
    
    public void setPosition(Enemies enemy) {
        this.x = enemy.getX();
        this.y = enemy.getY();
    }
}
