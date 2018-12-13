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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeBatch;

    private Player player;
    private Enemies enemy1;
    private M1911 firstGun;
    private M1911Bullet bullet;
    private OrthographicCamera cam;
    private FitViewport viewport;
    private int bulletMoveX;
    private int bulletMoveY;

    private Vector3 touch = new Vector3(0, 0, 0);

    @Override
    public void create() {
        batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");

        shapeBatch = new ShapeRenderer();

        // set camera resolution
        cam = new OrthographicCamera(800, 600);

        // link viewport with camera
        viewport = new FitViewport(800, 600, cam);

        viewport.apply();

        // set camera position (focuses on middle of screen)
        cam.position.x = 400;
        cam.position.y = 300;
        // update camera
        cam.update();

        player = new Player(100, 5, 400, 300, 10, 10, 0, 1);
        enemy1 = new Enemies(100, 1, 300, 200, 15, 15, 0, 0);
        firstGun = new M1911(player.getX(), player.getY());
        shapeBatch.setColor(Color.BLACK);
        bullet = new M1911Bullet(firstGun.getX(), firstGun.getY(), bulletMoveX, bulletMoveY);        
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touch);

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move();
            firstGun.moveX(player);
            firstGun.moveY(player);
        }

        enemy1.move(player);

        shapeBatch.setProjectionMatrix(cam.combined);
        // start drawing mode
        // filled shapes
        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);

        shapeBatch.setColor(Color.FOREST);
        shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // change colour to white
        shapeBatch.setColor(Color.WHITE);

        // draw shapes
        player.draw(shapeBatch);
        enemy1.draw(shapeBatch);
        firstGun.drawGun(shapeBatch, player);

        if (Gdx.input.isTouched()) {
            
          bullet.setGunLocation(firstGun);
            shapeBatch.setColor(Color.GOLD);
            if (touch.x > bullet.getX()) {
                bulletMoveX = 100;
            } else if (bullet.getX() == touch.x) {
                bulletMoveX = 0;
            } else {
                bulletMoveX = -100;
            }
            if (touch.y > bullet.getY()) {
                bulletMoveY = 100;
            } else if (bullet.getY() == touch.y) {
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
            bullet.drawBullet(shapeBatch, bullet.getX(), bullet.getY());
        }
        System.out.println(bullet.getX());
        System.out.println(bullet.getY());

        shapeBatch.end();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
