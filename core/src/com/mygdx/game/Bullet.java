/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

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

    public Bullet(int speed, int damage) {
        //Set variables
        this.speed = speed;
        this.damage = damage;
        this.moveX = moveX;
        this.moveY = moveY;
    }
//Move x

    public void moveX() {
        while (true) {
            x = x + moveX*speed;
        }
    }

    //Move y
    public void moveY() {
        while (true) {
            y = y + (moveY * speed);
        }
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

}
