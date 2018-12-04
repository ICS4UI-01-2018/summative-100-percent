package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
             // add in walls here and be able to call them in a for loop
                
	@Override
	public void create () {
		batch = new SpriteBatch();
                shapeBatch = new ShapeRenderer();
                cam = new OrthographicCamera();
                viewport = new FitViewport(2000,1600,cam);
                viewport.apply();
                cam.position.x = 1000;
                cam.position.y = 800;
                cam.update();
                walls[0] = new Wall(100,20,1800,80);
                walls[1] = new Wall(100,1500,1800,80);
                walls[2] = new Wall(20,20,80,630);
                walls[3] = new Wall(20,950,80,630);
                walls[4] = new Wall(1900,20,80,630);
                walls[5] = new Wall(1900,950,80,630);
                
             
                
                // x y width 
                
             
                
     
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//batch.draw(img, 0, 0);
                shapeBatch.setProjectionMatrix(cam.combined);
                shapeBatch.begin(ShapeRenderer.ShapeType.Line);
                shapeBatch.setColor(Color.BLACK);
                walls[0].draw(shapeBatch);
                walls[1].draw(shapeBatch);
                walls[2].draw(shapeBatch);
                walls[3].draw(shapeBatch);
                walls[4].draw(shapeBatch);
                walls[5].draw(shapeBatch);

                shapeBatch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
        
        @Override
        public void resize(int width, int height){
            viewport.update(width, height);
        }
}
