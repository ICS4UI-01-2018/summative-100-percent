/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

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
    
    public Item(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isCollided = false;
        item = new Rectangle (x, y, width, height);
    }
    
}
