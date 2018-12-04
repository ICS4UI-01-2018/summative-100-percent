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

public class M1911 extends Weapon{
    //Initialise variables
    private int rateOfFire = 1;
    private int clipSize = 11;
    private int reloadTime = 5;
    public M1911(int rateOfFire, int reloadTime, int clipSize){
        //Send variables to super class
 super(rateOfFire, reloadTime, clipSize);
}
    //Get rate of fire
    public int getRateOfFire(){
        return this.rateOfFire;
    }
    //Get reload time
    public int getReloadTime(){
        return this.reloadTime;
    }
    //Get clip size
    public int getclipSize(){
        return this.clipSize;
    }
}