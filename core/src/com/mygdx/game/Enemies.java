/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author prylz2189
 */
public class Enemies extends Entity {

    private boolean isDead;
    private int xDirection;
    private int yDirection;

    // might not night xDirection and yDirection
    public Enemies(int HP, float speed, float x, float y, int width, int height, int xDirection, int yDirection) {
        super(HP, speed, x, y, width, height);
        this.isDead = false;
        this.xDirection = 0;
        this.yDirection = 0;
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
    /**
     *
     * @param player the Player that is being chased
     */
    public void move(Player player) {
        // if Enemies is left of player
        if (super.getX() + super.getWidth() / 2 < player.getX() ) {
            // move right
            super.setXRight();
        } else if (super.getX() + super.getWidth() / 2 > player.getX() ) {
            // else if Enemy is right of player, move left
            super.setXLeft();
        }

        // if Enemies is below player
        if (super.getY() + super.getHeight() / 2 < player.getY()) {
            // move up
            super.setYUp();
        } else if (super.getY() + super.getHeight() / 2 > player.getY()) {
            // else if Enemy is above player, move down
            super.setYDown();
        }
    }

    @Override
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    public void attack() {

    }

    /**
     *
     * @return
     */
    public boolean getIsDead() {
        return this.isDead;
    }

}
