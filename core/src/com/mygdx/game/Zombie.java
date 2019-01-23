/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author prylz2189
 */
public class Zombie extends Entity {

    private boolean isDead;
    private int damage;
    private boolean hitPlayer;
    // alive image
    private Texture pic;
    // dead image
    private Texture pic2;

    /**
     * Initialize Zombie data.
     *
     * @param HP the number of hit points of the Zombie.
     * @param speed the speed of the Zombie.
     * @param x the x position of the Zombie.
     * @param y the y position of the Zombie.
     * @param width the width of the Zombie.
     * @param height the height of the Zombie.
     * @param damage the damage of the Zombie.
     */
    public Zombie(int HP, float speed, float x, float y, int width, int height, int damage) {
        super(HP, speed, x, y, width, height);
        this.isDead = false;
        this.damage = damage;
        this.pic = new Texture("zombie2.png");
        this.pic2 = new Texture("blood.png");
        this.hitPlayer = false;
    }

    /**
     * Sets hitPlayer to true.
     */
    public void setHitPlayer() {
        this.hitPlayer = true;
    }

    /**
     * Sets hitPlayer to false.
     */
    public void setNotHitPlayer() {
        this.hitPlayer = false;
    }

    /**
     * Returns whether if the Zombie hit the Player or not.
     *
     * @return returns true if the Zombie touched the Player, false otherwise.
     */
    public boolean getHitPlayer() {
        return this.hitPlayer;
    }

    /**
     * Returns the Rectangle of the Zombie.
     *
     * @return the Rectangle representing the Zombie.
     */
    public Rectangle getBounds() {
        return super.getRect();
    }

    /**
     * Returns whether the Zombie has collided with a Wall or not.
     *
     * @param p the Wall being checked.
     * @return returns true if the Zombie is touching the Wall, false otherwise.
     */
    public boolean collidesWith(Wall p) {
        return super.getRect().overlaps(p.getBounds());
    }

    /**
     * Returns whether the Zombie has collided with a Zombie or not.
     *
     * @param p the Zombie being checked.
     * @return returns true if the Zombie is touching the Zombie, false
     * otherwise.
     */
    public boolean collidesWith(Zombie p) {
        return super.getRect().overlaps(p.getBounds());
    }

    /**
     * Returns whether the Zombie has collided with a Room or not.
     *
     * @param p the Room being checked.
     * @return returns true if the Zombie is touching the Room, false otherwise.
     */
    public Boolean collidesWith(Room p) {
        return super.getRect().overlaps(p.getBounds());
    }

    /**
     * Moves the Zombie towards the Player.
     *
     * @param player the Player being chased.
     */
    public void move(Player player) {
        // if Enemies is left of player
        if (super.getX() + (super.getWidth() / 2) < player.getX() + (player.getWidth() / 2)) {
            // move right
            super.setXR();
        } else if (super.getX() + (super.getWidth() / 2) > player.getX() + (player.getWidth() / 2)) {
            // else if Enemy is right of player, move left
            super.setXL();
        }

        // if Enemies is below player
        if (super.getY() + (super.getHeight() / 2) < player.getY() + (player.getHeight() / 2)) {
            // move up
            super.setYT();
        } else if (super.getY() + (super.getHeight() / 2) > player.getY() + (player.getHeight() / 2)) {
            // else if Enemy is above player, move down
            super.setYB();
        }
    }

    /**
     * Moves the Zombie towards a specific coordinate.
     *
     * @param x the x position of the coordinate.
     * @param y the y position of the coordinate.
     */
    public void MoveCoord(int x, int y) {
        // if Enemies is left of coordinate
        if (super.getX() + (super.getWidth() / 2) < x) {
            // move right
            super.setXR();
        } else if (super.getX() + (super.getWidth() / 2) > x) {
            // else if Enemy is right of coordinate, move left
            super.setXL();
        }

        // if Enemies is below coordniate
        if (super.getY() + (super.getHeight() / 2) < y) {
            // move up
            super.setYT();
        } else if (super.getY() + (super.getHeight() / 2) > y) {
            // else if Enemy is above coordniate, move down
            super.setYB();
        }
    }

    /**
     * Draws the shape of the Zombie (testing hitbox).
     *
     * @param shapeBatch the ShapeRenderer being used to draw.
     */
    @Override
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    /**
     * Draws the zombie facing towards the centre of the player.
     *
     * @param batch the SpriteBatch being used to draw
     * @param player the Player from where the angle is calculated from.
     */
    public void draw(SpriteBatch batch, Player player) {
        // if Player in quadrant 1
        if (player.getX() + (player.getWidth() / 2) > super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) > super.getY() + (super.getHeight() / 2)) {
            float angle = (float) Math.atan(((player.getY() + player.getHeight() / 2) - (super.getY() + super.getHeight() / 2)) / ((player.getX() + player.getWidth() / 2) - (super.getX() + super.getWidth() / 2)));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, (angle) - 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) < super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) > super.getY() + (super.getHeight() / 2)) {
            // else if in quadrant 2
            float angle = (float) Math.atan(((player.getY() + player.getHeight() / 2) - (super.getY() + super.getHeight() / 2)) / ((super.getX() + super.getWidth() / 2) - (player.getX() + player.getWidth() / 2)));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, 90 - (angle), 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) < super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) < super.getY() + (super.getHeight() / 2)) {
            // else if in quadrant 3
            float angle = (float) Math.atan(((super.getY() + super.getHeight() / 2) - (player.getY() + player.getHeight() / 2)) / ((super.getX() + super.getWidth() / 2) - (player.getX() + player.getWidth() / 2)));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, angle + 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) > super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) < super.getY() + (super.getHeight() / 2)) {
            // else if in quadrant 4
            float angle = (float) Math.atan(((super.getY() + super.getHeight() / 2) - (player.getY() + player.getHeight() / 2)) / ((player.getX() + player.getWidth() / 2) - (super.getX() + super.getWidth() / 2)));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, 270 - angle, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) == super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) > super.getY() + (super.getHeight() / 2)) {
            // else if directly above
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, 0, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) == super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) < super.getY() + (super.getHeight() / 2)) {
            // else if directly below
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, 180, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) > super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) == super.getY() + (super.getHeight() / 2)) {
            // else if directly right
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, -90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) < super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) == super.getY() + (super.getHeight() / 2)) {
            // else if directly left
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) == super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) == super.getY() + (super.getHeight() / 2)) {
            // else if directly on centre of Player
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), (float) 1.7, (float) 1.7, 180, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        }
    }

    /**
     * Draws a blood puddle.
     *
     * @param batch the SpriteBatch being used to draw
     */
    public void deadDraw(SpriteBatch batch) {
        batch.draw(pic2, super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    /**
     * Sets the zombie into a dead state.
     */
    public void setDead() {
        super.setHPZero();
        // set damage to 0 so that player doesn't damage from a dead zombie
        this.damage = 0;
        this.isDead = true;
    }

    /**
     * Returns the dead state of the Zombie.
     *
     * @return returns true if the Zombie is dead, false otherwise.
     */
    public boolean getIsDead() {
        return this.isDead;
    }

    /**
     * Returns the damage of the Zombie.
     *
     * @return the integer representing the Zombie's damage.
     */
    public int getDamage() {
        return this.damage;
    }
}
