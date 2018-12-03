/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author prylz2189
 */
public class M1911Bullet extends Bullet{
    //Initialise and set variables
    int speed = 5;
    int damage = 5;
    public M1911Bullet(int speed, int damage) {
        //Send variables to super class
        super(speed, damage);
}
}
