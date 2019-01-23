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

    /**
     * Initializes AmmoBox data.
     *
     * @param x the x position of the AmmoBox.
     * @param y the y position of the AmmoBox.
     * @param width the width of the AmmoBox.
     * @param height the height of the AmmoBox.
     * @param value the amount of bullets in the AmmoBox.
     */
    public AmmoBox(float x, float y, int width, int height, float value) {
        super(x, y, width, height, value);
        this.pic = new Texture("ammo.png");
    }

    /**
     * Draws the AmmoBox.
     *
     * @param batch the SpriteBatch being used to draw.
     */
    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(this.pic, super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

}
