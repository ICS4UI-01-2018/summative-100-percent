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
    private Enemies enemy2;
    private OrthographicCamera cam;
    private FitViewport viewport;

    private Vector3 cursorPosition = new Vector3();
    
    
    @Override
    public void create() {
        player = new Player(100, (float) 1.5, 400, 300, 200, 100, 0, 1);
        enemy1 = new Enemies(100, (float) 0.5, 300, 200, 15, 15, 0, 0);
        enemy2 = new Enemies(100, (float) 0.8, 500, 450, 15, 15, 0, 0);

        batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");

        shapeBatch = new ShapeRenderer();

        // set camera resolution
        cam = new OrthographicCamera(800, 600);

        // link viewport with camera
        viewport = new FitViewport(800, 600, cam);

        viewport.apply();

        // set camera position on player
        cam.position.x = player.getX();
        cam.position.y = player.getY();
        // update camera
        cam.update();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cursorPosition.x = Gdx.input.getX();
        cursorPosition.y = Gdx.input.getY();
        
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move();
        }

        enemy1.move(player);
        enemy2.move(player);

        // set camera position on player
        cam.position.x = player.getX();
        cam.position.y = player.getY();
        // update camera
        cam.update();

        shapeBatch.setProjectionMatrix(cam.combined);
        // start drawing mode
        // filled shapes
        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);

        shapeBatch.setColor(Color.RED);
        shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // change colour to white
        shapeBatch.setColor(Color.WHITE);

        // draw shapes
       // player.draw(shapeBatch);
        enemy1.draw(shapeBatch);
        enemy2.draw(shapeBatch);

        shapeBatch.end();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        player.draw(batch, cursorPosition.x, cursorPosition.y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
