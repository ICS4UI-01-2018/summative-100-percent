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
 * @author phub1604
 */
public class SpeedUp extends Item {

    private Texture pic;

    /**
     * Initialize SpeedUp data.
     *
     * @param x the x position of the SpeedUp.
     * @param y the y position of the SpeedUp.
     * @param width the width of the SpeedUp.
     * @param height the height of the SpeedUp.
     * @param value the value of the SpeedUp.
     */
    public SpeedUp(float x, float y, int width, int height, float value) {
        super(x, y, width, height, value);
        this.pic = new Texture("PlayButton.jpg");
    }

    /**
     * Draws the SpeedUp.
     *
     * @param batch the SpriteBatch being used to draw.
     */
    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(pic, super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
}
