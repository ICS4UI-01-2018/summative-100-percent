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

public class MyGdxGame extends ApplicationAdapter {

    SpriteBatch batch;
    private OrthographicCamera cam;
    private ShapeRenderer shapeBatch;
    private FitViewport viewport;
    private Wall wall;
    private Wall[] walls = new Wall[25];
    private Enemies[] enemies = new Enemies[2];
    private Texture img;

    private Player player;
    private Enemies enemy1;
    private Enemies enemy2;
    private M1911 firstGun;
    private M1911Bullet bullet;
    private int bulletMoveX;
    private int bulletMoveY;

    private Vector3 cursorPosition = new Vector3();

    @Override
    public void create() {
        player = new Player(100, (float) 1.5, 1000, 800, 100, 100, 0, 1);
        enemy1 = new Enemies(100, (float) 0.5, 300, 200, 30, 30, 0, 0);
        enemy2 = new Enemies(100, (float) 0.8, 500, 450, 30, 30, 0, 0);

        firstGun = new M1911(1, 12, 2, 36, player.getX(), player.getY());
        bullet = new M1911Bullet(2, 10, firstGun.getX(), firstGun.getY(), 0, 0);

        batch = new SpriteBatch();
        shapeBatch = new ShapeRenderer();
        cam = new OrthographicCamera(2000, 1600);
        viewport = new FitViewport(2000, 1600, cam);
        viewport.apply();
        // x y width 
        cam.position.x = player.getX() + (player.getWidth() / 2);
        cam.position.y = player.getY() + (player.getHeight() / 2);
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

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cursorPosition.x = Gdx.input.getX();
        cursorPosition.y = Gdx.input.getY();
        cursorPosition.z = 0;
        cam.unproject(cursorPosition);

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move();
            firstGun.moveX(player);
            firstGun.moveY(player);
        }

        enemy1.move(player);
        enemy2.move(player);
// loop for wall array

/// problem w/ collision detection, multiple methods moving player back at same time 
/// solution: only make one method usable at a time
//        for (int i = 0; i < 16; i++) {
//
//            // if player touches a wall 
//            if (player.collidesWith(walls[i])) {
//
//                System.out.println(walls[i].getBounds().contains(player.getRect()));
//                // if player hits top of wall
//                if (player.getY() <= walls[i].getY() + walls[i].getheight() && player.getY() > walls[i].getY()) {
//                    player.setYT();
//                    System.out.println("hitting top");
//                }
//                if (player.getY() + player.getHeight() >= walls[i].getY() && player.getY() + player.getHeight() <= walls[i].getY() + walls[i].getheight()) {
//                    player.setYB();
//                    System.out.println("hitting bot");
//                }
//                if (player.getX() + player.getWidth() >= walls[i].getX() && player.getX() + player.getWidth() <= walls[i].getX() + walls[i].getwidth()) {
//                    player.setXL();
//                    System.out.println("hitting left");
//                }
//                if (player.getX() <= walls[i].getX() + walls[i].getwidth() && player.getX() >= walls[i].getX()) {
//                    player.setXR();
//                    System.out.println("hitting right");
//                }
//
//                // if player hits bottom of wall
//                // if(player.getY() >= walls[i].getY()-5){
//                //   player.setYB();
//                // System.out.println("hitting bot");
//                // }
//            }
//
//        }
        // set camera position on player
        cam.position.x = player.getX();
        cam.position.y = player.getY();
        // update camera
        cam.update();

        Matrix4 defaultMatrix = shapeBatch.getProjectionMatrix();
        shapeBatch.setProjectionMatrix(cam.combined);
        // start drawing mode
        // draw shapes
        // filled shapes
        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);

        shapeBatch.setColor(Color.RED);
        shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // change colour to gray
        shapeBatch.setColor(Color.GRAY);
        walls[0].draw(shapeBatch);
        walls[1].draw(shapeBatch);
        walls[2].draw(shapeBatch);
        walls[3].draw(shapeBatch);
        walls[4].draw(shapeBatch);
        walls[5].draw(shapeBatch);
        walls[6].draw(shapeBatch);
        walls[7].draw(shapeBatch);
        walls[8].draw(shapeBatch);
        walls[9].draw(shapeBatch);
        walls[10].draw(shapeBatch);
        walls[11].draw(shapeBatch);
        walls[12].draw(shapeBatch);
        walls[13].draw(shapeBatch);
        walls[14].draw(shapeBatch);
        walls[15].draw(shapeBatch);

        shapeBatch.setColor(Color.BLUE);
        enemy1.draw(shapeBatch);

        // change colour to white
        shapeBatch.setColor(Color.WHITE);

        player.draw(shapeBatch);

        shapeBatch.setColor(Color.BLUE);
        firstGun.drawGun(shapeBatch, player);

        if (Gdx.input.isTouched()) {

            bullet.setGunLocation(firstGun);
            shapeBatch.setColor(Color.GOLD);
            if (cursorPosition.x > bullet.getX()) {
                bulletMoveX = 100;
            } else if (bullet.getX() == cursorPosition.x) {
                bulletMoveX = 0;
            } else {
                bulletMoveX = -100;
            }
            if (cursorPosition.y > bullet.getY()) {
                bulletMoveY = 100;
            } else if (bullet.getY() == cursorPosition.y) {
                bulletMoveY = 0;
            } else {
                bulletMoveY = -100;
            }

            firstGun.shoot(bullet);
        }
        if (bullet.getIsAlive() == true) {
            firstGun.moveBullet(bullet);
            bullet.getX();
            bullet.getY();
            shapeBatch.setColor(Color.YELLOW);
            bullet.drawBullet(shapeBatch, bullet.getX(), bullet.getY());
        }
        
        System.out.println(bullet.getX());
        System.out.println(bullet.getY());

        shapeBatch.setProjectionMatrix(defaultMatrix);

        shapeBatch.setColor(Color.MAGENTA);
        shapeBatch.rect(viewport.getWorldWidth() / 2 - 2, 0, 4, viewport.getWorldHeight());
        shapeBatch.end();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        enemy1.draw(batch, player);
        enemy2.draw(batch, player);
        player.draw(batch, cursorPosition.x, cursorPosition.y);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
