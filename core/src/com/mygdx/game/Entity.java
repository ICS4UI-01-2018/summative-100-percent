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
 * @author flakor
 */
public abstract class Entity {

    private int HP;
    private float speed;
    private float x;
    private float y;
    private int width;
    private int height;
    private Rectangle entity;

    public Entity(int HP, float speed, float x, float y, int width, int height) {
        this.HP = HP;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        entity = new Rectangle(x, y, width, height);
    }

    /**
     * 
     * @return the integer representing the HP.
     */
    public int getHP() {
        return this.HP;
    }

    /**
     * 
     * @return the float representing the speed. 
     */
    public float getSpeed() {
        return this.speed;
    }
    
    public Rectangle getRect(){
        return entity;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    /**
     * 
     * @return the integer representing the width.
     */
    public int getWidth() {
        return this.width;
    }
    // if it hits left side 
    public void setXL(Player p){
       x = getX()-1;
    }
    // if it hits right side 
    public void setXR(Player p){
        x = getX()+1;
    }
    // if it hits top 
    public void setYT(){
        y = getY()+1;
    }
    // if it hits bottom 
    public void setYB(){
       y = getY()-1; 
    }

    /**
     * 
     * @return the integer representing the height. 
     */
    public int getHeight() {
        return this.height;
    }

    // each subclass needs their own move() method
    // public abstract void move();

    // each subclass needs their own draw(ShapeRenderer shapeBatch) method
    public abstract void draw(ShapeRenderer shapeBatch);
    
    public void setXLeft() {
        // move left
        this.x = this.x - this.speed;
    }

    public void setXRight() {
        // move right
        this.x = this.x + this.speed;
    }

    public void setYUp() {
        // move up
        this.y = this.y + this.speed;
    }

    public void setYDown() {
        // move down
        this.y = this.y - this.speed;
    }

}
