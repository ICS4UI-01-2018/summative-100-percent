/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Starm7481
 */
public class Barricade {
    private int HP;
  //  private Healthbar healthbar;
    private int width;
    private int height; 
    private float x;
    private float y;
    private boolean canWalkThrough;
    private Rectangle shape;
    
      public Barricade(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
           shape = new Rectangle(x,y,width,height);
    }
      
      public int getHP(){
          return this.HP;
      }
      
      public boolean isDestroyed(){
          
          if (this.HP >0){
              return false;
          }
          if(this.HP <= 0){
              return true;
          }
          return false;
      }
       public void repairBarricade(){
         this.HP = this.HP + 5;
     }
    
}
