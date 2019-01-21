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
public class Enemies extends Entity {

    private boolean isDead;
    private int xDirection;
    private int yDirection;

    private int damage;
    
    // alive image
    private Texture pic;

    // dead image
    private Texture pic2;
    
    // might not night xDirection and yDirection
    public Enemies(int HP, float speed, float x, float y, int width, int height, int xDirection, int yDirection, int damage) {
        super(HP, speed, x, y, width, height);
        this.isDead = false;
        this.xDirection = 0;
        this.yDirection = 0;
        this.damage = damage;
        this.pic = new Texture("Zombie.jpg");
        this.pic2 = new Texture("badlogic.jpg");
    }

    public Rectangle getBounds() {
        return super.getRect();
    }

    public boolean collidesWith(Wall p) {
        return super.getRect().overlaps(p.getBounds());
    }

    public boolean collidesWith(Enemies p) {
        return super.getRect().overlaps(p.getBounds());
    }
    
    public Boolean collidesWith(Room p){
        return super.getRect().overlaps(p.getBounds());
    }

//    public void setDirectionToPlayer(Player player) {
//        // if Enemy is left of Player
//        if(super.getX() < player.getX()) {
//            xDirection = 1;
//        } else if(super.getX() > player.getX()) {
//            // else if enemy is right of Player
//            xDirection = -1;
//        }
//        
//        // if Enemy is below Player
//        if(super.getY() < player.getY()) {
//            yDirection = 1;
//        } else if(super.getY() > player.getY()) {
//            // else if enemy is above Player
//            yDirection = -1;
//        }
//        
//    }
    public void move(Player player) {
        // if Enemies is left of player
        if (super.getX() + (super.getWidth() / 2) < player.getX() + (player.getWidth() / 2)) {
            // move right
            super.setXRight();
        } else if (super.getX() + (super.getWidth() / 2) > player.getX() + (player.getWidth() / 2)) {
            // else if Enemy is right of player, move left
            super.setXLeft();
        }

        // if Enemies is below player
        if (super.getY() + (super.getHeight() / 2) < player.getY() + (player.getHeight() / 2)) {
            // move up
            super.setYUp();
        } else if (super.getY() + (super.getHeight() / 2) > player.getY() + (player.getHeight() / 2)) {
            // else if Enemy is above player, move down
            super.setYDown();
        }
    }
    
    public void MoveCoord(int x, int y) {
        // if Enemies is left of player
        if (super.getX() + (super.getWidth() / 2) < x){
            // move right
            super.setXRight();
        } else if (super.getX() + (super.getWidth() / 2) > x) {
            // else if Enemy is right of player, move left
            super.setXLeft();
        }

        // if Enemies is below player
        if (super.getY() + (super.getHeight() / 2) < y) {
            // move up
            super.setYUp();
        } else if (super.getY() + (super.getHeight() / 2) > y) {
            // else if Enemy is above player, move down
            super.setYDown();
        }
    }


@Override
        public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    /**
     * Draws the zombie facing towards the centre of the player.
     *
     * @param batch the SpriteBatch being used to draw
     * @param player the Player being chased
     */
    public void draw(SpriteBatch batch, Player player) {
        // calculate angles using inverse of tan
        // if Player in quadrant 1
        if (player.getX() + (player.getWidth() / 2) > super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) > super.getY() + (super.getHeight() / 2)) {
            float angle = (float) Math.atan(((player.getY() + player.getHeight() / 2) - (super.getY() + super.getHeight() / 2)) / ((player.getX() + player.getWidth() / 2) - (super.getX() + super.getWidth() / 2)));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, (angle) - 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);

        } else if (player.getX() + (player.getWidth() / 2) < super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) > super.getY() + (super.getHeight() / 2)) {
            // else if in quadrant 2
            float angle = (float) Math.atan(((player.getY() + player.getHeight() / 2) - (super.getY() + super.getHeight() / 2)) / ((super.getX() + super.getWidth() / 2) - (player.getX() + player.getWidth() / 2)));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 90 - (angle), 0, 0, pic.getWidth(), pic.getHeight(), false, false);

        } else if (player.getX() + (player.getWidth() / 2) < super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) < super.getY() + (super.getHeight() / 2)) {
            // else if in quadrant 3
            float angle = (float) Math.atan(((super.getY() + super.getHeight() / 2) - (player.getY() + player.getHeight() / 2)) / ((super.getX() + super.getWidth() / 2) - (player.getX() + player.getWidth() / 2)));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, angle + 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);

        } else if (player.getX() + (player.getWidth() / 2) > super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) < super.getY() + (super.getHeight() / 2)) {
            // else if in quadrant 4
            float angle = (float) Math.atan(((super.getY() + super.getHeight() / 2) - (player.getY() + player.getHeight() / 2)) / ((player.getX() + player.getWidth() / 2) - (super.getX() + super.getWidth() / 2)));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 270 - angle, 0, 0, pic.getWidth(), pic.getHeight(), false, false);

        } else if (player.getX() + (player.getWidth() / 2) == super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) > super.getY() + (super.getHeight() / 2)) {
            // else if directly above
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 0, 0, 0, pic.getWidth(), pic.getHeight(), false, false);

        } else if (player.getX() + (player.getWidth() / 2) == super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) < super.getY() + (super.getHeight() / 2)) {
            // else if directly below
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 180, 0, 0, pic.getWidth(), pic.getHeight(), false, false);

        } else if (player.getX() + (player.getWidth() / 2) > super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) == super.getY() + (super.getHeight() / 2)) {
            // else if directly right
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, -90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);

        } else if (player.getX() + (player.getWidth() / 2) < super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) == super.getY() + (super.getHeight() / 2)) {
            // else if directly left
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (player.getX() + (player.getWidth() / 2) == super.getX() + (super.getWidth() / 2) && player.getY() + (player.getHeight() / 2) == super.getY() + (super.getHeight() / 2)) {
            // else if directly on centre of Player
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 180, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        }

    }

    /**
     * 
     * @param batch the SpriteBatch being used to draw
     */
    public void deadDraw(SpriteBatch batch) {
        batch.draw(pic2, super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
    
    public void attack() {

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
     *
     * @return
     */
    public boolean getIsDead() {
        return this.isDead;
    }

}
