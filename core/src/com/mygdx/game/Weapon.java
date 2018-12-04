/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

/**
 *
 * @author prylz2189
 */
public class Weapon {

    //Initialise variables
    private ArrayList Player;
    private int rateOfFire;
    private float x;
    private float y;
    private int xDirection;
    private int yDirection;
    private int clipSize;
    private int bulletsInClip;
    private int reloadTime;
    private int totalAmmo;
    private boolean isClipEmpty;
    private boolean canShoot;

    public Weapon(int rateOfFire, int clipSize, int reloadTime) {
        //Set variables
        this.rateOfFire = rateOfFire;
        this.reloadTime = reloadTime;
        this.clipSize = clipSize;
    }

    //Get number of bullets in clip
    public int getBulletsInClip() {
        return this.bulletsInClip;
    }

    //Get total ammo
    public int totalAmmo() {
        return this.totalAmmo;
    }
//Move x

    public void moveX() {
        while (true) {
            x = Player.get(x);
        }
    }

    //Move y
    public void moveY() {
        while (true) {
            y = Player.get(y);

        }
    }

    //get the bullets X value
    public float getX() {
        return this.x;
    }

    //get the bullets y value
    public float getY() {
        return this.y;
    }

    //Get damage
    public int xDirection() {
        return this.xDirection;
    }

    //Get speed
    public int yDirection() {
        return this.yDirection;
    }
    //Shoot
    public void shoot(){
      
    }
    //Reload
    public void reload(){
        //Record time
       float setTime = Gdx.graphics.getDeltaTime();
       //If reload time has passed
       while(Gdx.graphics.getDeltaTime() <= setTime + this.reloadTime){
           this.canShoot = false;
       }
            //Find missing bullets
           int bulletsMissing = this.clipSize - this.bulletsInClip;
           //Subtract missing bullets from total ammo
           this.totalAmmo = this.totalAmmo - bulletsMissing;
           //Refill clip
           this.bulletsInClip = this.clipSize;
        
    }
    //Get is clip empty
    public boolean isClipEmpty(){
        return this.isClipEmpty;
    }
    //Get can weapon shoot
    public boolean getCanShoot(){
        return this.canShoot;
    }
}
