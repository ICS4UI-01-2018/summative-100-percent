/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 *
 * @author prylz2189
 */
public class M1911 extends Weapon {

    private ArrayList<M1911Bullet> bullets;

    /**
     * Initialize M1911 data.
     *
     * @param clipSize the number of bullets in a clip of the M1911.
     * @param reloadTime the time it takes to reload the M1911.
     * @param totalAmmo the total number of bullets of the M1911.
     */
    public M1911(int clipSize, float reloadTime, int totalAmmo) {
        super(clipSize, reloadTime, totalAmmo);
        this.bullets = new ArrayList<M1911Bullet>();
    }

    /**
     * Creates a Bullet.
     *
     * @param bullet the Bullet information that is being made.
     */
    public void addBullet(M1911Bullet bullet) {
        // add Bullet to ArrayList
        this.bullets.add(new M1911Bullet(bullet.getSpeed(), bullet.getX(), bullet.getY(), bullet.getDamage(), bullet.getRadius()));
        super.decreaseBulletCount();
    }

    /**
     * Returns the ArrayList of M1911Bullets.
     *
     * @return the ArrayList representing the M1911Bullets.
     */
    public ArrayList<M1911Bullet> getList() {
        return this.bullets;
    }
}
