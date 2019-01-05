/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author prylz2189
 */
public abstract class Bullet {


    private float speed;
    private float x;
    private float y;
    private int damage;
    private float radius;
    private boolean isAlive;
    private float xDirection;
    private float yDirection;

    public Bullet(int speed, float x, float y, int damage, float radius) {
        //Set variables
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.radius = radius;
        this.isAlive = false;
        this.xDirection = 0;
        this.yDirection = 0;
    }

    // pass in Player
    public void move(Player player, float cursorX, float cursorY) {
        // set initial position to centre of player
        this.x = player.getX() + (player.getWidth()/2);
        this.y = player.getY() + (player.getHeight()/2);
        
        this.x = (this.x + this.xDirection) * this.speed;
        this.y = (this.y + this.yDirection) * this.speed;
        
        // calculate xDirection and yDirection
        
    }
    
    public float getX() {
        return this.x;
    }

    
    public float getY() {
        return this.y;
    }

    //Get damage
    public int getDamage() {
        return this.damage;
    }

    //Get speed
    public float getSpeed() {
        return this.speed;
    }

    //Is the bullet alive
    public boolean getIsAlive() {
        return this.isAlive;
    }

    //Draw bullet
    public void drawBullet(ShapeRenderer shapeBatch) {
        shapeBatch.circle(this.x, this.y, this.radius);
    }

//    public void undrawBullet(ShapeRenderer shapeBatch, Bullet bullet) {
//
//    }
    
    //Shoot bullet
    public void bulletIsShot() {
        this.isAlive = true;
        
    }

    public void setGunLocation(Weapon firstGun) {
//        this.x = firstGun.getX();
//        this.y = firstGun.getY();
    }
}
