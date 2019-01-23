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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author flakor
 */
public abstract class Entity {

    private int HP;
    private float speed;
    private float x;
    private float y;
    private int width;
    private int height;
    private Rectangle entity;
    private BitmapFont text;

    /**
     * Initialize Entity data.
     *
     * @param HP the number of hit points of the Entity.
     * @param speed the speed of the Entity.
     * @param x the x position of the Entity.
     * @param y the y position of the Entity.
     * @param width the width of the Entity.
     * @param height the height of the Entity.
     */
    public Entity(int HP, float speed, float x, float y, int width, int height) {
        this.HP = HP;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        entity = new Rectangle(x, y, width, height);
        this.speed = speed;

        this.text = new BitmapFont();
        // font information
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("VCR_OSD_MONO_1.001.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        // font size
        parameter.size = 36;
        this.text = generator.generateFont(parameter);
        this.text.setColor(Color.BLACK);
    }

    /**
     * Increases speed of Entity using the SpeedUp item.
     *
     * @param item the SpeedUp that has been picked up by the Entity.
     */
    public void increaseSpeed(SpeedUp item) {
        this.speed = this.speed + item.getValue();
        // max speed of 10
        if (this.speed > 10) {
            this.speed = 10;
        }
    }

    /**
     * Increases HP of Entity using the HealthUp item.
     *
     * @param item the HealthUp that has been picked up by the Entity.
     */
    public void increaseHP(HealthUp item) {
        this.HP = this.HP + (int) item.getValue();
        // max HP of 500
        if (this.HP > 500) {
            this.HP = 500;
        }
    }

    /**
     * Set the speed of the Entity to a specific value by adding a desired
     * value.
     *
     * @param x the float representing the desired value.
     */
    public void setSpeed(float x) {
        this.speed = this.speed + x;
    }

    /**
     * Set the position of the Entity to a desired location.
     *
     * @param x the x position of the desired location.
     * @param y the y position of the desired location.
     */
    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the HP of the Entity.
     *
     * @return the integer representing the HP of the Entity.
     */
    public int getHP() {
        return this.HP;
    }

    /**
     * Calculates the HP of the Entity by subtracting a desired value.
     *
     * @param x the integer representing the desired value.
     */
    public void calculateHP(int x) {
        this.HP = this.HP - x;
    }

    /**
     * Sets the HP of the Entity to zero.
     */
    public void setHPZero() {
        this.HP = 0;
    }

    /**
     * Returns the speed of the Entity.
     *
     * @return the float representing the speed of the Entity.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Returns the Rectangle of the Entity.
     *
     * @return the Rectangle representing the Entity.
     */
    public Rectangle getRect() {
        entity.x = this.x;
        entity.y = this.y;
        return entity;
    }

    /**
     * Returns the x position of the Entity.
     *
     * @return the float representing the Entity's x position.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Returns the y position of the Entity.
     *
     * @return the float representing the Entity's y position.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Returns the width of the Entity.
     *
     * @return the integer representing the Entity's width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the Entity.
     *
     * @return the integer representing the width.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Adjusts the Entity's position to move left using its speed.
     */
    public void setXL() {
        this.x = this.getX() - this.speed;
    }

    /**
     * Adjusts the Entity's position to move right using its speed.
     */
    public void setXR() {
        this.x = this.getX() + this.speed;
    }

    /**
     * Adjusts the Entity's position to move up using its speed.
     */
    public void setYT() {
        this.y = this.getY() + this.speed;
    }

    /**
     * Adjusts the Entity's position to move down using its speed.
     */
    public void setYB() {
        this.y = this.getY() - this.speed;
    }

    /**
     * Draws the shape of the Entity (testing hitboxes).
     *
     * @param shapeBatch the ShapeRenderer being used to draw.
     */
    public abstract void draw(ShapeRenderer shapeBatch);

    /**
     * Draws the HP of the Entity.
     *
     * @param batch the SpriteBatch being used to draw.
     */
    public void drawHP(SpriteBatch batch) {
        text.draw(batch, String.valueOf(this.HP), (this.x) + ((this.width) / 2), this.y + this.height + 40);
    }
}
