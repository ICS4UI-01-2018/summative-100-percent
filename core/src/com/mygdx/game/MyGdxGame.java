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
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private ShapeRenderer shapeBatch;
    private FitViewport viewport;
    private Wall wall;
    private Wall[] walls = new Wall[10];
    private Texture img;

    private Player player;
    private Enemies enemy1;

    // add in walls here and be able to call them in a for loop
    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeBatch = new ShapeRenderer();
        cam = new OrthographicCamera();
        viewport = new FitViewport(2000, 1600, cam);
        viewport.apply();
        cam.position.x = 1000;
        cam.position.y = 800;
        cam.update();
        walls[0] = new Wall(100, 20, 1800, 80);
        walls[1] = new Wall(100, 1500, 1800, 80);
        walls[2] = new Wall(20, 20, 80, 630);
        walls[3] = new Wall(20, 950, 80, 630);
        walls[4] = new Wall(1900, 20, 80, 630);
        walls[5] = new Wall(1900, 950, 80, 630);
        player = new Player(100, 2, 400, 300, 10, 10, 0, 1);
       // enemy1 = new Enemies(100, 2, 300, 200, 15, 15, 0, 0);
        // x y width 
    }

   

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move();
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

        shapeBatch.end();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
    }
    
        @Override
        public void resize
        (int width, int height){
            viewport.update(width, height);
        }
    }
