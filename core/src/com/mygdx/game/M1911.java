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

    private Texture pic;
    private ArrayList<M1911Bullet> bullets;
    
    public M1911(int clipSize, float reloadTime, int totalAmmo) {
        //Send variables to super class
        super(clipSize, reloadTime, totalAmmo);
        this.pic = new Texture("m1911.png");
        this.bullets = new ArrayList<M1911Bullet>();
    }

    // add bullet when player clicks left mouse button
    public void addBullet(M1911Bullet bullet) {
        // add bullet
        this.bullets.add(new M1911Bullet(bullet.getSpeed(), bullet.getX(), bullet.getY(), bullet.getDamage(), bullet.getRadius()));
        super.decreaseBulletCount();
    }

    // draw ammo count top left
    
    
    public ArrayList<M1911Bullet> getList() {
        return this.bullets;
    }

    public void moveBullets(Player player, float cursorX, float cursorY) {
        
        for (M1911Bullet bullet : this.bullets) {
            if (bullet.getIsAlive() == false) {
                bullet.move(player, cursorX, cursorY);
                bullet.setAlive();
            }
        }
    }
    


    
    
}
