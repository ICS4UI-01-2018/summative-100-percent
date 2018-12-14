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
 * @author prylz2189
 */
public class Enemies extends Entity {

    private boolean isDead;
    private int xDirection;
    private int yDirection;
    private boolean canMove;
    
    // might not night xDirection and yDirection
    public Enemies (int HP, float speed, float x, float y, int width, int height, int xDirection, int yDirection, boolean canMove) {
        super(HP, speed, x, y, width, height);
        this.isDead = false;
        this.xDirection = 0;
        this.yDirection = 0;
        this.canMove = true;
        
    }
    public Rectangle getBounds(){
       return super.getRect();
   }
    
    public boolean collidesWith(Wall p){
       
       return super.getRect().overlaps(p.getBounds());
   }
    
    public boolean getMove(){
        return this.canMove;
    }
    
    public void setMoveF(){
        canMove = false;
    }
    public void setMoveT(){
        canMove = true;
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
        if(canMove == true){
        if(super.getX() < player.getX()) {
            super.setXRight();
        } else if(super.getX() > player.getX()) {
            super.setXLeft();
        }
        
        if(super.getY() < player.getY()) {
            super.setYUp();
        } else if(super.getY() > player.getY()) {
            super.setYDown();
        }
        }
    }
    
    @Override
    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
    
    public void attack() {
        
    }
    
    public boolean getIsDead() {
        return this.isDead;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
