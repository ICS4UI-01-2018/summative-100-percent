/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author phub1604
 */
public class Player extends Entity {

    private Rectangle player;

    // private HealthBar health
    private int armor;
    private int lives;

    // private ArrayList<Weapon> weapons;
    // or
    // private Weapon weapon;
    public Player(int HP, float speed, float x, float y, int width, int height) {
        super(HP, speed, x, y, width, height);
        this.armor = armor;
        this.lives = lives;
        //DO WE HAVE TO MAKE A RECTANGLE HERE IF ITS ALREADY IN SUPERCLASS???

//        this.speed = speed;
//        this.HP = 100;
//        this.armor = 0;
//        player = new Rectangle(x, y, width, height);
    }


    // return Player parts in floats
    public float getLeft() {
        return player.x;
    }

    public float getRight() {
        return player.x + player.width;
    }

    public float getFront() {
        return player.y + player.height;
    }

    public float getBack() {
        return player.y;
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

    public void aim() {

    }

    public void dead() {

    }

    // draw player out
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(player.x, player.y, player.width, player.height);
    }

    @Override
    public void move() {
        // move left
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.x = player.x - super.getSpeed();
        }

        // move right
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.x = player.x + super.getSpeed();
        }

        // move up
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.y = player.y + super.getSpeed();
        }

        // move down
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.y = player.y - super.getSpeed();
        }
    }

}
