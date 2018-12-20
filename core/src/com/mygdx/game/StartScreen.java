/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author flakor
 */
public class StartScreen implements Screen {

    private static final int playButtonWidth = 100;
    private static final int playButtonHeight = 100;
    private static final int play = 230;

    MyGdxGame game;

    Texture PlayButton;

    public StartScreen(MyGdxGame game) {

        this.game = game;

        PlayButton = new Texture("PlayButton.jpg");

    }

    @Override
    public void show() {

    }

    @Override

    public void render(float f) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    //    game.getBatch().begin();
     //   int x = MyGdxGame.WIDTH / 2 - playButtonWidth / 2;
   //     game.batch.draw(PlayButton, x, play, playButtonWidth, playButtonHeight);
        if (Gdx.input.isTouched()) {
            this.dispose();

        }

      //  game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
