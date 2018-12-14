/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author phub1604
 */
public class Player extends Entity {

    // private HealthBar health
    private int armor;
    private int lives;
    
    // image file
    private Texture pic;

    // private ArrayList<Weapon> weapons;
    // or
    // private Weapon weapon;
    public Player(int HP, float speed, float x, float y, int width, int height, int armor, int lives) {
        super(HP, speed, x, y, width, height);
        this.armor = armor;
        this.lives = lives;
        // this.r = new Rectangle(x, y, width, height);
        this.pic = new Texture("square.jpg");

    }

    // return Player parts in floats
    public float getLeft() {
        return super.getX();
    }

    public float getRight() {
        return super.getX() + super.getWidth();
    }

    public float getFront() {
        return super.getY() + super.getHeight();
    }

    public float getBack() {
        return super.getY();
    }

    public int getArmor() {
        return this.armor;
    }

    public void shoot() {

    }

    public void reload() {

    }

    public void repairBarricade() {

    }

    public void rotate() {

    }

    public void dead() {

    }
    public void setX(){
   //  player.
    }
    
    
    
    

    // draw player out
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    // pass in SpriteBatch and cursor coordinates
    public void draw(SpriteBatch batch, float cursorX, float cursorY) {
        // trigonometry - inverse of tan
        // if cursor in quadrant 1
        if (cursorX > super.getX() && cursorY > super.getY()) {
            float angle = (float) Math.atan((cursorY - (super.getY())) / (cursorX - (super.getX())));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX() , super.getY() , super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, (angle) - 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (cursorX < super.getX() && cursorY > super.getY()) {
            // else if in quadrant 2
            float angle = (float) Math.atan((cursorY - (super.getY())) / ((super.getX()) - cursorX));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY(), super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 90 - (angle), 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (cursorX < super.getX() && cursorY < super.getY()) {
            // else if in quadrant 3
            float angle = (float) Math.atan(((super.getY()) - cursorY) / ((super.getX()) - cursorX));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX() , super.getY() , super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, angle + 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (cursorX > super.getX() && cursorY < super.getY()) {
            // else if in quadrant 4
            float angle = (float) Math.atan(((super.getY()) - cursorY) / (cursorX - (super.getX())));
            angle = Math.abs((float) Math.toDegrees(angle));
            batch.draw(pic, super.getX(), super.getY() , super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 270 - angle, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (cursorX == super.getX() && cursorY > super.getY()) {
            // else if directly above
            batch.draw(pic, super.getX() , super.getY() , super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 0, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (cursorX == super.getX() && cursorY < super.getY()) {
            // else if directly below
            batch.draw(pic, super.getX() , super.getY() , super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 180, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (cursorX > super.getX() && cursorY == super.getY()) {
            // else if directly right
            batch.draw(pic, super.getX() , super.getY() , super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, -90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        } else if (cursorX < super.getX() && cursorY == super.getY()) {
            // else if directly left
            batch.draw(pic, super.getX() , super.getY() , super.getWidth() / 2, super.getHeight() / 2, super.getWidth(), super.getHeight(), 1, 1, 90, 0, 0, pic.getWidth(), pic.getHeight(), false, false);
        }

    }

    public void move() {
        // move left if A is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            
            super.setXLeft();
            
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // else move right if D is pressed
            super.setXRight();

        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            // else move up if W is press
            super.setYUp();

        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            // else move down if S is pressed
            super.setYDown();

        }
    }

}
