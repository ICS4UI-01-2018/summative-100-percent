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
    // array list of M1911Bullet
    private ArrayList<M1911Bullet> bullets;
    
    public M1911(int rateOfFire, float x, float y, int width, int height, int clipSize, float reloadTime, int totalAmmo) {
        //Send variables to super class
        super(rateOfFire, x, y, width, height, clipSize, reloadTime, totalAmmo);
        this.pic = new Texture("m1911.png");
        this.bullets = new ArrayList<M1911Bullet>();
    }

    @Override
    public void draw(SpriteBatch batch, Player player, float cursorX, float cursorY) {
        // origin of rotation is centre of player
        batch.draw(pic, super.getX(), super.getY(), 0 + (super.getWidth()/2), 0 - (player.getHeight()/2), super.getWidth(), super.getHeight(), 1, 1, player.getAngle(), 0, 0, pic.getWidth(), pic.getHeight(), false, false);
    }

    // add bullet when player clicks left mouse button
    public void addBullet(M1911Bullet bullet) {
        // add bullet
        this.bullets.add(new M1911Bullet(bullet.getSpeed(), bullet.getX(), bullet.getY(), bullet.getDamage(), bullet.getRadius()));
        
    }

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
