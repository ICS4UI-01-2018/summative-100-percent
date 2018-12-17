/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

/**
 *
 * @author prylz2189
 */
public abstract class Weapon {

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

    public Weapon(int rateOfFire, int clipSize, int reloadTime, int totalAmmo, float x, float y) {
        //Set variables
        this.rateOfFire = rateOfFire;
        this.reloadTime = reloadTime;
        this.clipSize = clipSize;
        this.totalAmmo = totalAmmo;
        this.x = x;
        this.y = y;
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
//Move x

    public void moveX(Player player) {

        this.x = player.getX();

    }

    //Move y
    public void moveY(Player player) {

        this.y = player.getY();

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
    public void shoot(Bullet bullet) {
        if (this.isClipEmpty == false || this.canShoot == true) {
            //If the clip isnt empty subtract one bullet

            this.totalAmmo = this.totalAmmo - 1;
            bullet.bulletIsShot();
        }

        //Dont allow gun to fire too often
        //  while(Gdx.graphics.getDeltaTime() <= setTime + this.rateOfFire){
        //  this.canShoot = false;
        //}
        //Allow the gun to shoot
        //this.canShoot = true;
    }

    public void moveBullet(Bullet bullet) {
        bullet.moveX();
        bullet.moveY();

    }

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

    public void drawGun(ShapeRenderer shapeBatch, Player player) {
        shapeBatch.rect(player.getX() + 10, player.getY() + 10, 10, 10);
    }

    //Get rate of fire
    public int getRateOfFire() {
        return this.rateOfFire;
    }

    //Get reload time
    public int getReloadTime() {
        return this.reloadTime;
    }

    //Get clip size
    public int getclipSize() {
        return this.clipSize;
    }

}
