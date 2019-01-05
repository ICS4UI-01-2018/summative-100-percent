/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author prylz2189
 */
public abstract class Weapon {

    //Initialise variables
    private float rateOfFire;
    private float x;
    private float y;
    private int width;
    private int height;
    private int clipSize;
    private int bulletsInClip;
    private float reloadTime;
    private int totalAmmo;
    private boolean isClipEmpty;
    private boolean canShoot;
    private ArrayList<Bullet> bullets;
    
    public Weapon(float rateOfFire, float x, float y, int width, int height, int clipSize, float reloadTime, int totalAmmo) {
        this.rateOfFire = rateOfFire;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.clipSize = clipSize;
        this.reloadTime = reloadTime;
        this.totalAmmo = totalAmmo;
        this.isClipEmpty = false;
        this.canShoot = false;
    }

    //Get number of bullets in clip
    public int getBulletsInClip() {
        return this.bulletsInClip;
    }

    //Get total ammo
    public int totalAmmo() {
        return this.totalAmmo;
    }

    // move gun
    public void move(Player player) {
        this.x = player.getX() + (player.getWidth()/2) - (this.width/2);
        this.y = player.getY() + player.getHeight();
    }
    

//    //Shoot
//    public void shoot(Bullet bullet) {
//        if (this.isClipEmpty == false || this.canShoot == true) {
//            //If the clip isnt empty subtract one bullet
//
//            this.totalAmmo = this.totalAmmo - 1;
//            bullet.bulletIsShot();
//        }
//       
//        //Dont allow gun to fire too often
//        //  while(Gdx.graphics.getDeltaTime() <= setTime + this.rateOfFire){
//        //  this.canShoot = false;
//        //}
//        //Allow the gun to shoot
//        //this.canShoot = true;
//    }

    

    //Reload
    public void reload() {
        //Record time
        float setTime = Gdx.graphics.getDeltaTime();
        //If reload time has passed
        while (Gdx.graphics.getDeltaTime() <= setTime + this.reloadTime) {
            this.canShoot = false;
        }
        //Allow the gun to shoot
        this.canShoot = true;
        //Find missing bullets
        int bulletsMissing = this.clipSize - this.bulletsInClip;
        //Subtract missing bullets from total ammo
        this.totalAmmo = this.totalAmmo - bulletsMissing;
        //Refill clip
        this.bulletsInClip = this.clipSize;
    }

    //Get is clip empty
    public boolean isClipEmpty() {
        return this.isClipEmpty;
    }

    //Get can weapon shoot
    public boolean getCanShoot() {
        return this.canShoot;
    }

    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void draw(ShapeRenderer shapeBatch, Player player) {
        shapeBatch.rect(this.x , this.y , this.width, this.height);
    }

    // each gun will have their own draw method (different images)
    public abstract void draw(SpriteBatch batch, Player player, float cursorX, float cursorY);
    
    //Get rate of fire
    public float getRateOfFire() {
        return this.rateOfFire;
    }

    //Get reload time
    public float getReloadTime() {
        return this.reloadTime;
    }

    //Get clip size
    public int getclipSize() {
        return this.clipSize;
    }

}
