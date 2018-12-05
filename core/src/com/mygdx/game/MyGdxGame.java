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

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeBatch;
    
    private Player player;
    private Enemies enemy1;
    private OrthographicCamera cam;
    private FitViewport viewport;
    
    
    @Override
    public void create() {
        batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
        

        shapeBatch = new ShapeRenderer();

        // set camera resolution
        cam = new OrthographicCamera(800, 600);
        
        // link viewport with camera
        viewport = new FitViewport (800, 600, cam);
        
        viewport.apply();
        
        // set camera position (focuses on middle of screen)
        cam.position.x = 400;
        cam.position.y = 300;
        // update camera
        cam.update();

        player = new Player(100, 2, 400, 300, 10, 10, 0, 1);
        enemy1 = new Enemies(100, 2, 300, 200, 15, 15, 0, 0);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
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
        enemy1.draw(shapeBatch);
        
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
