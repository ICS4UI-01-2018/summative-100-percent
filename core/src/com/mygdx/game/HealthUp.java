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
public class HealthUp extends Item {

    private Texture pic;

    /**
     * Initializes HealthUp data.
     *
     * @param x the x position of the HealthUp.
     * @param y the y position of the HealthUp.
     * @param width the width of the HealthUp.
     * @param height the height of the HealthUp.
     * @param value the number of HP recovered in the HealthUp.
     */
    public HealthUp(float x, float y, int width, int height, float value) {
        super(x, y, width, height, value);
        this.pic = new Texture("health.png");
    }

    /**
     * Draws the HealthUp.
     *
     * @param batch the SpriteBatch being used to draw.
     */
    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(pic, super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

}
