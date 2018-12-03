/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author prylz2189
 */
public class Enemies extends Entity {

    private boolean isDead;
    private int xDirection;
    private int yDirection;
    
    public Enemies (int HP, float speed, float x, float y, int width, int height) {
        super(HP, speed, x, y, width, height);
        this.isDead = false;
        this.xDirection = 0;
        this.yDirection = 0;
        
        
        
        
        
        
        
        
        
    }
    
    public void setDirectionToPlayer(Player player) {
        if(super.getX() < player.getX()) {
            xDirection = 1;
        } else if(super.getX() > player.getX()) {
            xDirection = -1;
        }
        
        if(super.getY() < player.getY()) {
            yDirection = 1;
        } else if(super.getY() > player.getY()) {
            yDirection = -1;
        }
        
    }
    
    @Override
    public void move() {
        
    }
    
    public void attack() {
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
