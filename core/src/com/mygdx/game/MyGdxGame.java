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
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private ShapeRenderer shapeBatch;
    private FitViewport viewport;
    private Wall wall;
    private Wall[] walls = new Wall[20];
    private Texture img;

    private Player player;
    private Enemies enemy1;
    private Enemies enemy2;



    // add in walls here and be able to call them in a for loop
    @Override
    public void create() {
        player = new Player(100, (float) 5, 400, 300, 20, 10, 0, 1, true);
        enemy1 = new Enemies(100, (float) 0.5, 300, 200, 15, 15, 0, 0);
        enemy2 = new Enemies(100, (float) 0.8, 500, 450, 15, 15, 0, 0);

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
        walls[7] = new Wall(1980,950,500,80);
        walls[8] = new Wall(1980,570,500,80);
        //
        walls[9] = new Wall(1980, 20, 1800, 80);
        walls[10] = new Wall(1980, 1500, 650, 80); // top 1
        walls[11] = new Wall(3130, 1500, 650, 80); // top2 
        walls[12] = new Wall(1900, 20, 80, 630);
        walls[13] = new Wall(1900, 950, 80, 630);
        walls[14] = new Wall(3780, 20, 80, 630 + 920);
        walls[15] = new Wall(3780, 950, 80, 630);
      
        // x y width 
        
         cam.position.x = player.getX();
        cam.position.y = player.getY();
    }

   

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move();
        }

       
        shapeBatch.setProjectionMatrix(cam.combined);
        // start drawing mode
        // filled shapes
        
      
        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);

        enemy1.move(player);
        enemy2.move(player);
// loop for wall array


/// problem w/ collision detection, multiple methods moving player back at same time 
/// solution: only make one method usable at a time



            for (int i = 0; i < 16; i++) {
            
               // if player touches a wall 
            if(player.collidesWith(walls[i])){
                
                System.out.println(walls[i].getBounds().contains(player.getRect()));
                // if player hits top of wall
                 if(player.getY() <= walls[i].getY()+walls[i].getheight() && player.getY() > walls[i].getY()){
                       player.setYT();
                     System.out.println("hitting top");
                 }
                 if(player.getY()+player.getHeight() >= walls[i].getY() && player.getY()+player.getHeight() <= walls[i].getY() + walls[i].getheight()){
                     player.setYB();
                     System.out.println("hitting bot");
                 }
                 if(player.getX()+ player.getWidth() >= walls[i].getX() && player.getX()+ player.getWidth() <= walls[i].getX()+ walls[i].getwidth()){
                     player.setXL();
                     System.out.println("hitting left");
                 }
                 if(player.getX() <= walls[i].getX()+walls[i].getwidth() && player.getX() >= walls[i].getX()){
                     player.setXR();
                     System.out.println("hitting right");
                 }
                
                 // if player hits bottom of wall
                // if(player.getY() >= walls[i].getY()-5){
                  //   player.setYB();
                    // System.out.println("hitting bot");
                // }
                 
            }
            
        }
        // set camera position on player
        cam.position.x = player.getX();
        cam.position.y = player.getY();
        // update camera
        cam.update();


        // change colour to white
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
        
      
        
        
        shapeBatch.setColor(Color.WHITE);

        // draw shapes
        player.draw(shapeBatch);
        enemy1.draw(shapeBatch);
        enemy2.draw(shapeBatch);

        shapeBatch.end();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        shapeBatch.setColor(Color.RED);
      //  shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.end();
    }
    }
