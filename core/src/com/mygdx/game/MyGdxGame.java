package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private ShapeRenderer shapeBatch;
    private FitViewport viewport;
    private Wall wall;
    private Wall[] walls = new Wall[25];
    private Enemies[] enemies = new Enemies[2];
    private Texture img;

    private M1911 pistol;
//    private Bullet bullet;

    private Player player;
    private Enemies enemy1;
    private Enemies enemy2;

    private Vector3 cursorPosition = new Vector3();

    // add in walls here and be able to call them in a for loop
    @Override
    public void create() {
        player = new Player(100, (float) 5, 400, 300, 100, 100, 0, 1);
        enemies[0] = new Enemies(100, (float) 2, (float) 300, (float) 200, 30, 30, 0, 0);
        enemies[1] = new Enemies(100, (float) 2, (float) 500, (float) 450, 30, 30, 0, 0);
        pistol = new M1911(1, player.getX() + (player.getWidth()/2) - 5, player.getY() + player.getHeight(), 50, 100, 12, (float) 2.5, 36);
        
        
//        firstGun = new M1911(1, 12, 2, 36, player.getX(), player.getY());
//        bullet = new M1911Bullet(2, 10, firstGun.getX(), firstGun.getY(), 1, 1);

        batch = new SpriteBatch();
        shapeBatch = new ShapeRenderer();
        cam = new OrthographicCamera();
        viewport = new FitViewport(2000, 1600, cam);
        viewport.apply();
        cam.update();

        walls[0] = new Wall(100, 20, 1800, 80);
        walls[1] = new Wall(100, 1500, 650, 80); // top
        walls[2] = new Wall(1150, 1500, 900, 80); // top 
        walls[3] = new Wall(20, 20, 80, 630);
        walls[4] = new Wall(20, 950, 80, 630);
        walls[5] = new Wall(1900, 20, 80, 630);
        walls[6] = new Wall(1900, 950, 80, 630);////
        walls[7] = new Wall(1980, 950, 500, 80);
        walls[8] = new Wall(1980, 570, 500, 80);
        //
        walls[9] = new Wall(1980, 20, 1800, 80);
        walls[10] = new Wall(1980, 1500, 650, 80); // top 1
        walls[11] = new Wall(3130, 1500, 650, 80); // top2 
        walls[12] = new Wall(1900, 20, 80, 630);
        walls[13] = new Wall(1900, 950, 80, 630);
        walls[14] = new Wall(3780, 20, 80, 630 + 920);
        walls[15] = new Wall(3780, 950, 80, 630);
        walls[16] = new Wall(750, 1500, 80, 700);//left large 
        walls[17] = new Wall(1150, 1500, 80, 300); // left small 
        walls[18] = new Wall(2630, 1500, 80, 300);// right small
        walls[19] = new Wall(3130, 1500, 80, 700);// right large
        walls[20] = new Wall(1150, 1800, 1560, 80);
        walls[21] = new Wall(750, 2200, 2460, 80);

        // x y width 
        cam.position.x = player.getX();
        cam.position.y = player.getY();
    }

    @Override
    public void render() {
        // if startScreen == true

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cursorPosition.x = Gdx.input.getX();
        cursorPosition.y = Gdx.input.getY();
        cursorPosition.z = 0;
        cam.unproject(cursorPosition);
        
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move();
//            firstGun.moveX(player);
//            firstGun.moveY(player);
        }

        shapeBatch.setProjectionMatrix(cam.combined);
        // start drawing mode
        // draw shapes
        // filled shapes

        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);

        enemies[0].move(player);
        enemies[1].move(player);
        
        pistol.move(player);
        
        // zombies set to slowdown  when hit player 
        // currently set to only slow down to a certain speed (also speed can't go negative or it will glitch) 
        for (int i = 0; i < 2; i++) {
            if (player.collidesWithZ(enemies[i])) {
                player.setHP(5);
                //  System.out.println(player.getHP());

                if (enemies[i].getSpeed() - 0.15 > 0.7) {
                    enemies[i].setSpeed((float) -0.15);
                }
            }
        }
//     

        // zombie zombie collision 
        for (int i = 0; i < 2; i++) {
            for (int x = 0; x < 2; x++) {
                if (enemies[i].collidesWith(enemies[x]) && enemies[i] != enemies[x]) {
                    // if they hit top 
                    if (enemies[i].getSpeed() < 2.00 && enemies[x].getSpeed() < 2.00) {
                        System.out.println(enemies[i].getSpeed() + " " + enemies[x].getSpeed());
                        enemies[i].setSpeed((float) +0.15);
                        enemies[x].setSpeed((float) +0.15);
                    }
                    if (enemies[i].getY() <= enemies[x].getY() + enemies[x].getHeight() && enemies[i].getY() > enemies[x].getY()) {
                        enemies[i].setYT();
                    }
                    // hits bottom 
                    if (enemies[i].getY() + enemies[i].getHeight() >= enemies[x].getY() && enemies[i].getY() + enemies[i].getHeight() <= enemies[x].getY() + enemies[x].getHeight()) {
                        enemies[i].setYB();
                    }
                    // hits left 
                    if (enemies[i].getX() + enemies[i].getWidth() >= enemies[x].getX() && enemies[i].getX() + enemies[i].getWidth() <= enemies[x].getX() + enemies[x].getWidth()) {
                        enemies[i].setXL();
                    }
                    // if  hits right of wall 
                    if (enemies[i].getX() <= enemies[x].getX() + enemies[x].getWidth() && enemies[i].getX() >= enemies[x].getX()) {
                        enemies[i].setXR();
                    }
                }
            }

        }
        // for loop running through array of walls 
        for (int i = 0; i < 22; i++) {
            // if player touches a wall 
            if (player.collidesWith(walls[i])) {
                // if player hits top of wall
                if (player.getY() <= walls[i].getY() + walls[i].getheight() && player.getY() > walls[i].getY()) {
                    player.setYT();
                }
                // if player hits bottom of wall 
                if (player.getY() + player.getHeight() >= walls[i].getY() && player.getY() + player.getHeight() <= walls[i].getY() + walls[i].getheight()) {
                    player.setYB();
                }
                // if player hits left of wall 
                if (player.getX() + player.getWidth() >= walls[i].getX() && player.getX() + player.getWidth() <= walls[i].getX() + walls[i].getwidth()) {
                    player.setXL();
                }
                // if player hits right of wall 
                if (player.getX() <= walls[i].getX() + walls[i].getwidth() && player.getX() >= walls[i].getX()) {
                    player.setXR();
                }
            }
        }
        // zombie collision   
        // for loop runs through wall array    
        for (int g = 0; g < 22; g++) {
            // nested for loop runs through enemiy array 
            for (int m = 0; m < 2; m++) {
                // if an enemy hits a wall 
                if (enemies[m].collidesWith(walls[g])) {
                    System.out.println(walls[g].getBounds().contains(player.getRect()));
                    // if enemy hits top of wall
                    if (enemies[m].getY() <= walls[g].getY() + walls[g].getheight() && enemies[m].getY() > walls[g].getY()) {
                        enemies[m].setYT();
                    }
                    // if enemy hits bottom of a wall 
                    if (enemies[m].getY() + enemies[m].getHeight() >= walls[g].getY() && enemies[m].getY() + enemies[m].getHeight() <= walls[g].getY() + walls[g].getheight()) {
                        enemies[m].setYB();
                    }
                    //if enemy hits left side of a wall 
                    if (enemies[m].getX() + enemies[m].getWidth() >= walls[g].getX() && enemies[m].getX() + enemies[m].getWidth() <= walls[g].getX() + walls[g].getwidth()) {
                        enemies[m].setXL();
                    }
                    // if enemy hits right side of a wall 
                    if (enemies[m].getX() <= walls[g].getX() + walls[g].getwidth() && enemies[m].getX() >= walls[g].getX()) {
                        enemies[m].setXR();
                    }
                }
            }

        }
        // set camera position on player
        cam.position.x = player.getX();
        cam.position.y = player.getY();
        // update camera
        cam.update();

        shapeBatch.setColor(Color.RED);
        shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        
        //drawing the array of walls 
        shapeBatch.setColor(Color.GRAY);
        for (int z = 0; z < 22; z++) {
            walls[z].draw(shapeBatch);
        }

        shapeBatch.setColor(Color.YELLOW);

        pistol.draw(shapeBatch, player);
        
        // player.draw(shapeBatch);
        // enemies[1].draw(shapeBatch);

//        // draw gun
//        shapeBatch.setColor(Color.BLUE);
//        firstGun.drawGun(shapeBatch, player);
//
//        // draw bullet
//        shapeBatch.setColor(Color.YELLOW);
//        bullet.drawBullet(shapeBatch, bulletMoveX, bulletMoveY);
//        
//        if (Gdx.input.justTouched()) {
//            bullet.setGunLocation(firstGun);
//            shapeBatch.setColor(Color.GOLD);
//            if (cursorPosition.x > bullet.getX()) {
//                bulletMoveX = 100;
//            } else if (bullet.getX() == cursorPosition.x) {
//                bulletMoveX = 0;
//            } else {
//                bulletMoveX = -100;
//            }
//            if (cursorPosition.y > bullet.getY()) {
//                bulletMoveY = 100;
//            } else if (bullet.getY() == cursorPosition.y) {
//                bulletMoveY = 0;
//            } else {
//                bulletMoveY = -100;
//            }
//
//            firstGun.shoot(bullet);
//        }
//        if (bullet.getIsAlive() == true) {
//            firstGun.moveBullet(bullet);
//            bullet.getX();
//            bullet.getY();
//            bullet.drawBullet(shapeBatch, bullet.getX(), bullet.getY());
//        }
//        System.out.println(bullet.getX());
//        System.out.println(bullet.getY());
        // shapeBatch.setProjectionMatrix(defaultMatrix);
        shapeBatch.setColor(Color.MAGENTA);
        shapeBatch.rect(viewport.getWorldWidth() / 2 - 2, 0, 4, viewport.getWorldHeight());
        
        
        shapeBatch.end();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

//        enemies[0].draw(batch, player);
//        enemies[1].draw(batch, player);
        player.draw(batch, cursorPosition.x, cursorPosition.y);
        
        pistol.draw(batch, player);
        
        
        
        
        batch.end();
    }
}
