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
    private boolean collided;
    private Rectangle bullet;

    /**
     * Initializes Bullet data.
     *
     * @param speed the speed of the Bullet.
     * @param x the x position of the Bullet.
     * @param y the y position of the Bullet.
     * @param damage the damage of the Bullet.
     * @param radius the radius of the Bullet.
     */
    public Bullet(float speed, float x, float y, int damage, float radius) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.radius = radius;

        // default variables
        this.isAlive = false;
        this.xDirection = 0;
        this.yDirection = 0;
        this.collided = false;

        // Rectangle for collision
        bullet = new Rectangle(x, y, radius, radius);
    }

    /**
     * Moves the Bullet from the centre of the Player to where the cursor is
     * clicked.
     *
     * @param player the Player where the angle is calculated from to the cursor
     * click.
     * @param cursorX the x position of where the cursor is clicked.
     * @param cursorY the y position of where the cursor is clicked.
     */
    public void move(Player player, float cursorX, float cursorY) {
        // if Bullet hasn't been shot
        if (this.isAlive == false) {
            // calculate angle and directions
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

    /**
     * Returns the x position of the Bullet.
     *
     * @return the float representing the Bullet's x position.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Returns the y position of the Bullet.
     *
     * @return the float representing the Bullet's y position.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Returns the damage of the Bullet.
     *
     * @return the integer representing the Bullet's damage.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Returns the speed of the Bullet.
     *
     * @return the float representing the Bullet's speed.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Returns the radius of the Bullet.
     *
     * @return the float representing the Bullet's radius.
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * Returns the alive state of the Bullet.
     *
     * @return returns true if Bullet has been shot, false if it hasn't.
     */
    public boolean getIsAlive() {
        return this.isAlive;
    }

    /**
     * Set the Bullet to be alive (has been shot).
     */
    public void setAlive() {
        this.isAlive = true;
    }

    /**
     * Draws the Bullet.
     *
     * @param shapeBatch the ShapeRenderer being used to draw.
     */
    public void drawBullet(ShapeRenderer shapeBatch) {
        shapeBatch.circle(this.x, this.y, this.radius);
    }

    /**
     * Returns the rectangle of the Bullet.
     *
     * @return the rectangle representing the Bullet.
     */
    public Rectangle getShape() {
        bullet.x = this.x;
        bullet.y = this.y;
        return bullet;
    }

    /**
     * Returns the collision state of the Bullet.
     *
     * @return returns true if the Bullet has collided, false otherwise.
     */
    public boolean getCollided() {
        return this.collided;
    }

    /**
     * Sets the Bullet in the collided state.
     */
    public void setCollided() {
        this.collided = true;
        // set damage to 0 so that you cannot damage through walls
        this.damage = 0;
    }
}
