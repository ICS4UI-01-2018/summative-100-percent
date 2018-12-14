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
public class Bullet {

    //Initialise variables
    private ArrayList Weapon;
    private int speed;
    private float x;
    private float y;
    private int damage;
    private int moveX;
    private int moveY;
    private boolean isAlive = false;

    public Bullet(int speed, int damage, float x, float y, int moveX, int moveY) {
        //Set variables
        this.speed = speed;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.moveX = moveX;
        this.moveY = moveY;
    }
//Move x
    public void moveX() {
           x = x + (moveX*speed);
        
    }

    //Move y
    public void moveY() {
       
            y = y + (moveY * speed);
        
    }
    //Collision
    public void collision(){
        //
    }
//get moveY
    public int getMoveY() {
        return this.moveY;
    }

    //get moveX
    public int getMoveX() {
        return this.moveX;
    }

    //get the bullets X value
    public float getX() {
        return this.x;
    }

    //get the bullets y value
    public float getY() {
        return this.y;
    }
    //Get damage
    public int getDamage(){
        return this.damage;
    }
    //Get speed
    public int getSpeed(){
        return this.speed;
    }
    //Is the bullet alive
    public boolean getIsAlive(){
        return this.isAlive;
    }
    //Draw bullet
     public void drawBullet(ShapeRenderer shapeBatch, float x, float y){
        shapeBatch.circle(this.x, this.y, 5);
    }
     public void undrawBullet(ShapeRenderer shapeBatch, Bullet bullet){
         
     }
     //Shoot bullet
    public void bulletIsShot(){
        this.isAlive=true;
          //  if this collides with a zombie or barrier set to is not alive
          if(this.getX()>800||this.getX()<0||this.getY()<0||this.getY()>600){
   this.isAlive = false;
         }
        }
    public void setGunLocation(Weapon firstGun){
        this.x = firstGun.getX();
        this.y = firstGun.getY();
    }
    }


