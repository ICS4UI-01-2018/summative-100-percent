/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author phub1604
 */
public class Player {

    private Rectangle player;
    private int HP;

    // private HealthBar health
    private int armor;
    private float speed;
    private int lives;

    // private ArrayList<Weapon> weapons;
    // or
    // private Weapon weapon;
    
    public Player(float x, float y, int width, int height, float speed) {
        this.speed = speed;
        this.HP = 100;
        this.armor = 0;
        player = new Rectangle(x, y, width, height);
    }

    // movement
    public void moveLeft() {
        player.x = player.x - speed;
    }

    public void moveRight() {
        player.x = player.x + speed;
    }

    public void moveUp() {
        player.y = player.y + speed;
    }

    public void moveDown() {
        player.y = player.y - speed;
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
    
    
    public int getHP() {
        return this.HP;
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
    
    
}
