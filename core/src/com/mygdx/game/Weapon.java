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
    
    private int clipSize;
    private int bulletsInClip;
    private float reloadTime;
    private int totalAmmo;
    private boolean isClipEmpty;
    private boolean reloading;
    private BitmapFont text;
    private float time;

    /**
     * Initializes Weapon data.
     *
     * @param clipSize the number of bullets in a clip of the Weapon.
     * @param reloadTime the time it takes to reload the Weapon.
     * @param totalAmmo the total number of bullets of the Weapon.
     */
    public Weapon(int clipSize, float reloadTime, int totalAmmo) {
        this.clipSize = clipSize;
        this.bulletsInClip = 0;
        this.reloadTime = reloadTime;
        this.totalAmmo = totalAmmo;
        this.reloading = false;

        // font information
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("VCR_OSD_MONO_1.001.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        this.text = generator.generateFont(parameter);
        this.text.setColor(Color.BLACK);
        this.time = 0;
    }

    /**
     * Returns the number of bullets in the current clip of the Weapon.
     *
     * @return the integer representing the number of bullets in the clip.
     */
    public int getBulletsInClip() {
        return this.bulletsInClip;
    }

    /**
     * Returns the total number of bullets of the Weapon.
     *
     * @return the integer representing the total number of bullets.
     */
    public int totalAmmo() {
        return this.totalAmmo;
    }

    /**
     * Draws the number of bullets in the current clip and the total remaining
     * bullets of the Weapon.
     *
     * @param batch the SpriteBatch being used to draw.
     * @param player the Player where the information is being drawn from.
     */
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
     * @param player the Player where "RELOADING" is being drawn from.
     */
    public void drawReloading(SpriteBatch batch, Player player) {
        text.draw(batch, "RELOADING", player.getX() + (player.getWidth() / 2) - 500, player.getY() + (player.getHeight() + 450));
    }

    /**
     * Draws the number of zombie kills.
     *
     * @param batch the SpriteBatch being used to draw.
     * @param player the Player where the number of kills is being drawn from.
     * @param counter the integer representing the number of kills.
     */
    public void drawKillCounter(SpriteBatch batch, Player player, int counter) {
        text.draw(batch, "KILLS: ", player.getX() + (player.getWidth() / 2) - 500, player.getY() + (player.getHeight() + 400));
        text.draw(batch, String.valueOf(counter), player.getX() + (player.getWidth() / 2) - 250, player.getY() + (player.getHeight() + 400));
    }

    /**
     * Calculates the starting ammo for the Weapon at the start of the game.
     */
    public void calculateInitialAmmo() {
        if (this.totalAmmo - this.clipSize >= 0) {
            this.bulletsInClip = this.clipSize;
            this.totalAmmo = this.totalAmmo - this.clipSize;
        } else {
            this.bulletsInClip = this.bulletsInClip + this.totalAmmo;
            this.totalAmmo = 0;
        }
    }

    /**
     * Calculates the ammo of the Weapon during gameplay.
     */
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
        }
    }

    /**
     * Keeps track of the total time during gameplay (used when reloading).
     *
     * @param deltaTime the float representing the time between each frame.
     */
    public void calculateTime(float deltaTime) {
        this.time = this.time + deltaTime;
    }

    /**
     * Returns the total time.
     *
     * @return the float representing the time.
     */
    public float getCalculatedTime() {
        return this.time;
    }

    /**
     * Decreases the number of bullets in the current clip by one.
     */
    public void decreaseBulletCount() {
        this.bulletsInClip = this.bulletsInClip - 1;
    }

    /**
     * Returns the reloading state of the Weapon.
     *
     * @return returns true if the Weapon is currently reloading, false
     * otherwise.
     */
    public boolean getReloading() {
        return this.reloading;
    }

    /**
     * Sets the Weapon in the reloading state.
     */
    public void setReloading() {
        this.reloading = true;
    }

    /**
     * Sets the Weapon in the non-reloading state.
     */
    public void stopReloading() {
        this.reloading = false;
    }

    /**
     * Returns the amount of time needed to reload the Weapon.
     *
     * @return the float representing the Weapon's reload time.
     */
    public float getReloadTime() {
        return this.reloadTime;
    }

    /**
     * Returns the size of the Weapon's clip.
     *
     * @return the integer representing the number of bullets able to fit in the
     * Weapon's clip.
     */
    public int getclipSize() {
        return this.clipSize;
    }

    /**
     * Increases the total number of bullets of the Weapon using an AmmoBox.
     *
     * @param item the AmmoBox that has been picked up by the Player.
     */
    public void addAmmo(AmmoBox item) {
        this.totalAmmo = this.totalAmmo + (int) item.getValue();
    }
}
