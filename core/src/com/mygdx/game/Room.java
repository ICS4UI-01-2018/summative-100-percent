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
    // creating the wall object
    public Room(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        shape = new Rectangle(x,y,width,height);
    }
    // getter methods
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public int getwidth(){
        return this.width;
    }
    public int getheight(){
        return this.height;
    }
     public void draw(ShapeRenderer shapeBatch){
        shapeBatch.rect(shape.x, shape.y, shape.width, shape.height);
    }
     
    public Rectangle getBounds(){
       return shape;
   }
}
