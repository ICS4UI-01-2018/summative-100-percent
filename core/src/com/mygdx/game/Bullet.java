/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
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
    
    private Rectangle bullet;

    private boolean collided;
    
    public Bullet(float speed, float x, float y, int damage, float radius) {
        //Set variables
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.radius = radius;

        this.isAlive = false;
        this.xDirection = 0;
        this.yDirection = 0;
        
        // create rectangle to deal with collision later
        bullet = new Rectangle(x, y, radius, radius);
        
        this.collided = false;
    }

    // pass in Player and cursor coordinates
    public void move(Player player, float cursorX, float cursorY) {
        if (this.isAlive == false) {
            float angle = (float) (Math.atan2((cursorY - (player.getY() + (player.getHeight() / 2))), (cursorX - (player.getX() + (player.getWidth() / 2)))));
            this.xDirection = (float) (Math.cos(angle));
            this.yDirection = (float) (Math.sin(angle));

            // set initial position to centre of player for unshot bullets
            this.x = player.getX() + (player.getWidth() / 2);
            this.y = player.getY() + (player.getHeight() / 2);
        }

        // move X and Y coordinates of bullet
        this.x = this.x + (this.xDirection * this.speed);
        this.y = this.y + (this.yDirection * this.speed);

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

    // get radius
    public float getRadius() {
        return this.radius;
    }

    //Is the bullet alive
    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setAlive() {
        this.isAlive = true;
    }

    //Draw bullet
    public void drawBullet(ShapeRenderer shapeBatch) {
        shapeBatch.circle(this.x, this.y, this.radius);
    }

    /**
     * Returns the rectangle of the bullet.
     * @return the rectangle representing the bullet.
     */
    public Rectangle getShape() {
        bullet.x = this.x;
        bullet.y = this.y;
        return bullet;
    }

    public boolean getCollided() {
        return this.collided;
    }
    
    public void setCollided() {
        this.collided = true;
        // set damage to 0 so that you cannot damage through walls
        this.damage = 0;
    }
    
}
