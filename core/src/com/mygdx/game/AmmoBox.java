/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author phub1604
 */
public class AmmoBox extends Item {
    
    private Texture pic;
    
    public AmmoBox (float x, float y, int width, int height, float value) {
        super(x, y, width, height, value);
        this.pic = new Texture("ammo.png");
    }
    
    public void draw(SpriteBatch batch) {
        batch.draw(pic, super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }    
    
    
}
