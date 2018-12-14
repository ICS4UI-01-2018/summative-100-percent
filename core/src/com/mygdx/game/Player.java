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
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author phub1604
 */
public class Player extends Entity {

    // private HealthBar health
    private int armor;
    private int lives;
    private boolean canMove;
    
    
    // private ArrayList<Weapon> weapons;
    // or
    // private Weapon weapon;
    public Player(int HP, float speed, float x, float y, int width, int height, int armor, int lives, boolean canMove) {
        super(HP, speed, x, y, width, height);
        this.armor = armor;
        this.lives = lives;
        this.canMove = canMove;
    }
 public Rectangle getBounds(){
       return super.getRect();
   }
    // return Player parts in floats
    public float getLeft() {
        return super.getX();
    }
   public boolean collidesWith(Wall p){
       // wall collision detection
       return super.getRect().overlaps(p.getBounds());
   }
   public boolean collidesWithZ(Enemies p){
       // zombie collision detection
       return super.getRect().overlaps(p.getBounds());
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
    
    public boolean getMove(){
        return this.canMove;
    }
    
    public void setMoveFalse(){
        canMove = false;
    }
    
    public void setMoveTrue(){
        canMove = true;
    }

    @Override
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
    
    public void move() {
        // move left if A is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(canMove == true){
            super.setXLeft();
            }
        }

        // move right if D is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(canMove == true){
            super.setXRight();
            }
        }

        // move up if W is press
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if(canMove == true){
            super.setYUp();
            }
        }

        // move down if S is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(canMove == true){
            super.setYDown();
            }
        }
    }
}