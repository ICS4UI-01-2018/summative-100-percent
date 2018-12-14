/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author prylz2189
 */
public class Enemies extends Entity {

    private boolean isDead;
    private int xDirection;
    private int yDirection;

    // image file
    private Texture pic;

    // might not night xDirection and yDirection
    public Enemies(int HP, float speed, float x, float y, int width, int height, int xDirection, int yDirection) {
        super(HP, speed, x, y, width, height);
        this.isDead = false;
        this.xDirection = 0;
        this.yDirection = 0;
        this.pic = new Texture("badlogic.jpg");
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
        if (super.getX() < player.getX() + (player.getWidth() / 2) - (super.getWidth() / 2)) {
            // move right
            super.setXRight();
        } else if (super.getX() > player.getX() + (player.getWidth() / 2) - (super.getWidth() / 2)) {
            // else if Enemy is right of player, move left
            super.setXLeft();
        }

        // if Enemies is below player
        if (super.getY() < player.getY() + (player.getHeight() / 2) - (super.getHeight() / 2)) {
            // move up
            super.setYUp();
        } else if (super.getY() > player.getY() + (player.getHeight() / 2) - (super.getHeight() / 2)) {
            // else if Enemy is above player, move down
            super.setYDown();
        }
    }

    @Override
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    /**
     * Follows the centre of the player.
     *
     * @param batch
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
