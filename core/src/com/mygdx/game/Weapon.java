/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
    private boolean reloading;

    private BitmapFont text;

    private float time;

    public Weapon(float rateOfFire, float x, float y, int width, int height, int clipSize, float reloadTime, int totalAmmo) {
        this.rateOfFire = rateOfFire;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.clipSize = clipSize;
        this.bulletsInClip = 0;
        this.reloadTime = reloadTime;
        this.totalAmmo = totalAmmo;
        this.isClipEmpty = false;
        this.reloading = false;

        // font information
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("VCR_OSD_MONO_1.001.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        this.text = generator.generateFont(parameter); // font size 12 pixels
        this.text.setColor(Color.BLACK);
        this.time = 0;
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
        this.x = player.getX() + (player.getWidth() / 2) - (this.width / 2);
        this.y = player.getY() + player.getHeight();
    }

    // draw ammo top-left of screen from centre of player
    public void drawAmmo(SpriteBatch batch, Player player) {
        // bullets in clip
        text.draw(batch, String.valueOf(this.bulletsInClip), player.getX() + (player.getWidth() / 2) - 500, player.getY() + (player.getHeight()) + 500);
        // '/'
        text.draw(batch, "/", player.getX() + (player.getWidth() / 2) - 380, player.getY() + player.getHeight() + 500);
        // total ammo
        text.draw(batch, String.valueOf(this.totalAmmo), player.getX() + player.getWidth() / 2 - 300, player.getY() + player.getHeight() + 500);
    }

    /**
     * Display "RELOADING" when the gun is being reloaded.
     *
     * @param batch the SpriteBatch being used to draw.
     * @param player the Player used as a point
     */
    public void drawReloading(SpriteBatch batch, Player player) {
        text.draw(batch, "RELOADING", player.getX() + (player.getWidth() / 2) - 500, player.getY() + (player.getHeight() + 450));
    }

    public void drawKillCounter(SpriteBatch batch, Player player, int counter) {
        text.draw(batch, "KILLS: ", player.getX() + (player.getWidth() / 2) - 500, player.getY() + (player.getHeight() + 400));
        text.draw(batch, String.valueOf(counter), player.getX() + (player.getWidth() / 2) - 250, player.getY() + (player.getHeight() + 400));
    }

    public void calculateInitialAmmo() {
        if (this.totalAmmo - this.clipSize >= 0) {
            this.bulletsInClip = this.clipSize;
            this.totalAmmo = this.totalAmmo - this.clipSize;
        } else {
            this.bulletsInClip = this.bulletsInClip + this.totalAmmo;
            this.totalAmmo = 0;
        }
    }

    public void calculateAmmo() {
        // calculate bullets needed for full clip
        int bulletsMissing = this.clipSize - this.bulletsInClip;
        // check if there is still enough ammo
        if (this.totalAmmo - bulletsMissing >= 0) {
            this.bulletsInClip = this.bulletsInClip + bulletsMissing;
            this.totalAmmo = this.totalAmmo - bulletsMissing;
        } else if (this.totalAmmo - bulletsMissing < 0) {
            this.bulletsInClip = this.bulletsInClip + this.totalAmmo;
            this.totalAmmo = 0;
        } else if (this.totalAmmo == 0) {
            // NOT ENOUGH AMMO
        }
    }

    public void calculateTime(float deltaTime) {
        this.time = this.time + deltaTime;
    }

    public float getCalculatedTime() {
        return this.time;
    }

    public void decreaseBulletCount() {
        this.bulletsInClip -= 1;
    }

    //Get is clip empty
    public boolean isClipEmpty() {
        return this.isClipEmpty;
    }

    //Get can weapon shoot
    public boolean getReloading() {
        return this.reloading;
    }

    public void setReloading() {
        this.reloading = true;
    }

    public void stopReloading() {
        this.reloading = false;
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
        shapeBatch.rect(this.x, this.y, this.width, this.height);
    }

    // each gun will have their own sprite draw method (different images)
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

    public void addAmmo(AmmoBox item) {
        this.totalAmmo = this.totalAmmo + (int) item.getValue();
    }

}
