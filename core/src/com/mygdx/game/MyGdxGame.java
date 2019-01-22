package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

    public SpriteBatch batch;
    private OrthographicCamera cam;
    private ShapeRenderer shapeBatch;
    private FitViewport viewport;
    private Wall wall;
    private Wall[] walls = new Wall[21];
    private boolean MainMenu;
    private boolean Instructions;
    private ArrayList<Enemies> enemies;
//    private Enemies[] enemies = new Enemies[2];
    private Texture img;

    private M1911 pistol;
    private M1911Bullet testBulletInfo;

    private Player player;
    private Enemies enemy1;
    private Enemies enemy2;

    private Room leftMain;
    private Room rightMain;
    private Room topMain;

    private Vector3 cursorPosition = new Vector3();

    private BitmapFont font;
    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton startButton;
    private TextButton instructionsButton;
    private Texture background;
    private Texture wallPat;
    // mouse clicks that correspond to each bullet
    private ArrayList<Float> cursorXPositions;
    private ArrayList<Float> cursorYPositions;

    // item Arrat Lists
    private ArrayList<SpeedUp> speeds;
    private ArrayList<AmmoBox> ammos;
    private ArrayList<HealthUp> healths;

    // 
    private int randomItemChance;
    // corresponds to which item will be created
    private int randomItem;
    private int randomZombieLocation;
    private int spawn;

    // variables used when spawning new zombies
    private float tempSpeed;
    private int tempWidth;
    private int tempHeight;
    // used to scale zombie attributes
    private int multiplier;

    // used for starting the game
    private boolean initialAmmoCalculated;

    // reloading variables
    private float aimedTime;
    private float time;

    private int killCounter;
    
    // add in walls here and be able to call them in a for loop
    @Override
    public void create() {
        killCounter = 0;
        
        this.multiplier = 1;

        player = new Player(100, (float) 4.5, 600, 500, 100, 100, 0, 1);
        leftMain = new Room(100, 20, 1850, 1600);
        rightMain = new Room(1950, 20, 1880, 1500 + 60);
        topMain = new Room(550, 1550, 2460 + 400, 700 + 400);

        this.speeds = new ArrayList<SpeedUp>();
        this.ammos = new ArrayList<AmmoBox>();
        this.healths = new ArrayList<HealthUp>();

        background = new Texture("abstract-geometric-background-triangles-and-lines-loop-4k-4096x2304_ekm8_sfzx__F0000.png");
        wallPat = new Texture("Metal-Pattern-Background-Blue.jpg");
        enemies = new ArrayList<Enemies>();
        enemies.add(new Enemies(100, (float) 2, (float) 300, (float) 200, 100, 100, 1));
        enemies.add(new Enemies(100, (float) 2, (float) 500, (float) 450, 100, 100, 1));
        enemies.add(new Enemies(100, (float) 2, (float) 500, (float) 450, 100, 100, 1));
        enemies.add(new Enemies(100, (float) 2, (float) 500, (float) 450, 100, 100, 1));
        enemies.add(new Enemies(100, (float) 2, (float) 300, (float) 200, 100, 100, 1));

        // centre gun on player
        pistol = new M1911(1, player.getX() + (25), player.getY() + (37), 50, 75, 12, (float) 2.5, 36);
        testBulletInfo = new M1911Bullet(25, player.getX() + (player.getWidth() / 2), player.getY() + (player.getHeight() / 2), 55, 10);
        //MAINMENU
        Instructions = false;
        MainMenu = true;
        font = new BitmapFont();
        font.setColor(Color.RED);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);

        table.setPosition(0, Gdx.graphics.getHeight());
        startButton = new TextButton("New Game", skin);
        instructionsButton = new TextButton("Instructions", skin);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button CLicked");
                MainMenu = false;

            }
        });

//        public void clicked(InputEvent event, float x, float y){
//        dialog.show(stage);
//        Timer.schedule(new Timer.Task(){
//         @Override
//         public void run(){
//             dialog.hide();
//         }
//        },5);
//        }      
//        });
        table.padTop(160);
        table.add(startButton).padBottom(30);
        table.row();
        table.add(instructionsButton);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        instructionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button CLicked");
                Instructions = true;
                MainMenu = false;

            }
        });

        cursorXPositions = new ArrayList<Float>();
        cursorYPositions = new ArrayList<Float>();

        batch = new SpriteBatch();
        shapeBatch = new ShapeRenderer();
        cam = new OrthographicCamera();
        viewport = new FitViewport(2000, 1600, cam);
        viewport.apply();
        cam.update();

        walls[0] = new Wall(100, 20, 1800, 80);
        walls[1] = new Wall(100, 1500, 450, 80); // top
        walls[2] = new Wall(1150, 1500, 900, 80); // top 
        walls[3] = new Wall(20, 20, 80, 630);
        walls[4] = new Wall(20, 950, 80, 630);
        walls[5] = new Wall(1900, 20, 80, 630);
        walls[6] = new Wall(1900, 950, 80, 630);////
        //  walls[7] = new Wall(1980, 950, 500, 80);
        // walls[8] = new Wall(1980, 570, 500, 80);
        //
        walls[7] = new Wall(1980, 20, 1800, 80);
        walls[8] = new Wall(1980, 1500, 650, 80); // top 1
        walls[9] = new Wall(3130 + 200, 1500, 450, 80); // top2 
        walls[10] = new Wall(1900, 20, 80, 630);
        walls[11] = new Wall(1900, 950, 80, 630);
        walls[12] = new Wall(3780, 20, 80, 630 + 920);
        walls[13] = new Wall(3780, 950, 80, 630);
        walls[14] = new Wall(550, 1500, 80, 900);//left large 
        walls[15] = new Wall(1150, 1500, 1560, 300); // left small 
        walls[16] = new Wall(2630, 1500, 80, 300);// right small
        walls[17] = new Wall(3330, 1500, 80, 900);// right large
        walls[18] = new Wall(1150, 1800, 1560, 80);
        walls[19] = new Wall(550, 2200 + 200, 2460 + 400, 80);
        walls[20] = new Wall(20, 20, 80, 1000);

        // x y width 
        cam.position.x = player.getX();
        cam.position.y = player.getY();

        initialAmmoCalculated = false;
    }

    @Override
    public void render() {
        if (MainMenu == true) {
            // main menu drawings

            cursorPosition.x = Gdx.input.getX();
            cursorPosition.y = Gdx.input.getY();
            cursorPosition.z = 0;
            cam.unproject(cursorPosition);
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();

            batch.begin();
            font.getData().setScale(2, 2);
            font.setColor(Color.RED);
            font.draw(batch, "ZOMBIE SHOOTER", 195, 400);
            batch.end();

        } else if (Instructions == true) {

            cursorPosition.x = Gdx.input.getX();
            cursorPosition.y = Gdx.input.getY();
            cursorPosition.z = 0;
            cam.unproject(cursorPosition);
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
            batch.begin();

            batch.end();

        } else if (MainMenu == false && Instructions == false) {
            // game drawings
            super.render();
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            cursorPosition.x = Gdx.input.getX();
            cursorPosition.y = Gdx.input.getY();
            cursorPosition.z = 0;
            cam.unproject(cursorPosition);

            // calculated initial ammo
            if (initialAmmoCalculated == false) {
                pistol.calculateInitialAmmo();
                initialAmmoCalculated = true;
                this.time = Gdx.graphics.getDeltaTime();
                this.aimedTime = Gdx.graphics.getDeltaTime();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.move();
            }

            shapeBatch.setProjectionMatrix(cam.combined);
            // start drawing mode
            // draw shapes
            // filled shapes

            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);

            // update time counter
            pistol.calculateTime(time);

            // reload using R
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                // check if gun can be reloaded 
                if ((pistol.getBulletsInClip() != pistol.getclipSize()) && (pistol.getReloading() == false) && (pistol.totalAmmo() != 0)) {
                    // set gun reloading to true
                    pistol.setReloading();
                    // find time needed to reach inorder to reload gun
                    this.aimedTime = pistol.getCalculatedTime() + pistol.getReloadTime();
                }
            }

            // if gun is reloading
            if (pistol.getReloading() == true) {
                if (pistol.getCalculatedTime() < this.aimedTime) {

                } else if (pistol.getCalculatedTime() > this.aimedTime) {
                    pistol.calculateAmmo();
                    pistol.stopReloading();
                }
            }
            
            // zombie AI
            for (Enemies enemy : enemies) {
                // if it's alive
                if (enemy.getIsDead() == false) {
                    if ((enemy.collidesWith(leftMain) && player.collidesWith(leftMain)) || (enemy.collidesWith(rightMain) && player.collidesWith(rightMain)) || enemy.collidesWith(topMain) && player.collidesWith(topMain)) {
                        enemy.move(player);
                        // in same room 
                    } else if (enemy.collidesWith(leftMain) && player.collidesWith(rightMain)) {
                        enemy.MoveCoord(1950, 735);
                        // left to right 

                    } else if (enemy.collidesWith(rightMain) && player.collidesWith(leftMain)) {
                        enemy.MoveCoord(1940, 735);
                        // right to left 

                    } else if (enemy.collidesWith(leftMain) && player.collidesWith(topMain)) {
                        enemy.MoveCoord(950, 1560);
                        // left to top 
                    } else if (enemy.collidesWith(rightMain) && player.collidesWith(topMain)) {
                        enemy.MoveCoord(3000, 1560);
                        // right to top
                    } else if (enemy.collidesWith(topMain) && player.collidesWith(leftMain)) {
                        enemy.MoveCoord(950, 1520);
                        // top to left 
                    } else if (enemy.collidesWith(topMain) && player.collidesWith(rightMain)) {
                        enemy.MoveCoord(3000, 1520);
                        // top to right
                    }
                }
            }

            pistol.move(player);

            // zombies set to slowdown  when hit player 
            // currently set to only slow down to a certain speed (also speed can't go negative or it will glitch) 
            for (Enemies enemy : enemies) {
                if (player.collidesWithZ(enemy)) {
                    if (enemy.getSpeed() - 0.15 > 0.7) {
                        enemy.setSpeed((float) -0.15);
                    }
                    // decrease HP only if its zombie first time hitting
                    if (enemy.getHitPlayer() == false) {
                        player.calculateHP(enemy.getDamage());
                        enemy.setHitPlayer();
                    }
                } else {
                    enemy.setNotHitPlayer();
                }

            }

            // zombie to zombie collision
            for (Enemies enemy : enemies) {
                for (Enemies enemy2 : enemies) {
                    if (enemy.getIsDead() == false && enemy2.getIsDead() == false) {
                        if (enemy.collidesWith(enemy2) && enemy != enemy2) {
                            // if they hit top 
                            if (enemy.getSpeed() < 2.00 && enemy2.getSpeed() < 2.00) {
                                // System.out.println(enemies[i].getSpeed() + " " + enemies[x].getSpeed());
                                enemy.setSpeed((float) +0.15);
                                enemy2.setSpeed((float) +0.15);
                            }
                            if (enemy.getY() <= enemy2.getY() + enemy2.getHeight() && enemy.getY() > enemy2.getY()) {
                                enemy.setYT();
                            }
                            // hits bottom 
                            if (enemy.getY() + enemy.getHeight() >= enemy2.getY() && enemy.getY() + enemy.getHeight() <= enemy2.getY() + enemy2.getHeight()) {
                                enemy.setYB();
                            }
                            // hits left 
                            if (enemy.getX() + enemy.getWidth() >= enemy2.getX() && enemy.getX() + enemy.getWidth() <= enemy2.getX() + enemy2.getWidth()) {
                                enemy.setXL();
                            }
                            // if  hits right of wall 
                            if (enemy.getX() <= enemy2.getX() + enemy2.getWidth() && enemy.getX() >= enemy2.getX()) {
                                enemy.setXR();
                            }
                        }
                    }

                }
            }

            // player to wall collision
            // for loop running through array of walls 
            for (int i = 0; i < 21; i++) {
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
            for (int g = 0; g < 21; g++) {
                // nested for each loop runs through enemy ArrayList
                for (Enemies enemy : enemies) {
                    // if an enemy hits a wall 
                    if (enemy.collidesWith(walls[g])) {
                        // System.out.println(walls[g].getBounds().contains(player.getRect()));
                        // if enemy hits top of wall
                        if (enemy.getY() <= walls[g].getY() + walls[g].getheight() && enemy.getY() > walls[g].getY()) {
                            enemy.setYT();
                        }
                        // if enemy hits bottom of a wall 
                        if (enemy.getY() + enemy.getHeight() >= walls[g].getY() && enemy.getY() + enemy.getHeight() <= walls[g].getY() + walls[g].getheight()) {
                            enemy.setYB();
                        }
                        //if enemy hits left side of a wall 
                        if (enemy.getX() + enemy.getWidth() >= walls[g].getX() && enemy.getX() + enemy.getWidth() <= walls[g].getX() + walls[g].getwidth()) {
                            enemy.setXL();
                        }
                        // if enemy hits right side of a wall 
                        if (enemy.getX() <= walls[g].getX() + walls[g].getwidth() && enemy.getX() >= walls[g].getX()) {
                            enemy.setXR();
                        }
                    }
                }

            }

            // if player wall glitches respawn in rooms 
            if ((player.collidesWith(topMain) != true) && (player.collidesWith(leftMain) != true) && (player.collidesWith(rightMain) != true)) {
                double random = Math.random();
                player.calculateHP(10);
                if (random <= 0.5) {
                    player.setXY(800, 900);
                } else {
                    player.setXY(3000, 900);
                }
            }
            // set camera position on player
            cam.position.x = player.getX();
            cam.position.y = player.getY();
            // update camera
            cam.update();

            //  shapeBatch.setColor(Color.RED);
            // shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
//        shapeBatch.setColor(Color.BLUE);
//       topMain.draw(shapeBatch);
            //    rightMain.draw(shapeBatch);
//        shapeBatch.setColor(Color.ORANGE);
//        leftMain.draw(shapeBatch);
            batch.begin();
            batch.draw(background, -1000, -1000, 7168, 4032);

            //drawing the array of walls 
            shapeBatch.setColor(Color.GRAY);
            for (int z = 0; z < 21; z++) {
                //  walls[z].draw(shapeBatch);

                batch.draw(wallPat, walls[z].getX(), walls[z].getY(), walls[z].getwidth(), walls[z].getheight());
            }
            batch.end();
//        shapeBatch.setColor(Color.GRAY);
//         for (int z = 0; z < 7; z++) {
//             walls[z].draw(shapeBatch);
//         }
//         shapeBatch.setColor(Color.BLUE);
//         for (int z = 8; z < 21; z++) {
//             walls[z].draw(shapeBatch);
            //  }
            // pistol.draw(shapeBatch, player);
            // player.draw(shapeBatch);
            // enemies[1].draw(shapeBatch);
            // shooting code
            if (Gdx.input.justTouched()) {
                // if there are bullets in the clip
                if (pistol.getBulletsInClip() != 0 && (pistol.getReloading() == false)) {
                    pistol.addBullet(testBulletInfo);
                    // store cursor coordinates into corresponding array lists
                    cursorXPositions.add(cursorPosition.x);
                    cursorYPositions.add(cursorPosition.y);

                }

            }

            // update bullets
            for (M1911Bullet bullet : pistol.getList()) {
                // move bullet from player position to where the cursor clicked
                bullet.move(player, cursorXPositions.get(pistol.getList().indexOf(bullet)), cursorYPositions.get(pistol.getList().indexOf(bullet)));
                // bullet is shot
                bullet.setAlive();
                // draw bullet if it hasn't collided with a wall or zombie
                if (bullet.getCollided() == false) {
                    bullet.drawBullet(shapeBatch);
                }

                // bullet collision with walls
                for (int i = 0; i < 21; i++) {
                    // if a bullet collides with a wall
                    if (walls[i].getBounds().contains(bullet.getShape())) {
                        bullet.setCollided();
                    }
                }

                // bullet collision with zombies
                for (Enemies enemy : this.enemies) {
                    // only collide with zombie if it's alive
                    if (enemy.getIsDead() == false) {
                        if (enemy.getBounds().contains(bullet.getShape())) {
                            // damage enemy first
                            enemy.calculateHP(bullet.getDamage());
                            // set bullet to collided state
                            bullet.setCollided();
                            // if zombie HP is less than or equal to 0

                            if (enemy.getHP() <= 0) {
                                killCounter = killCounter + 1;
                                
                                this.multiplier = this.multiplier + 1;

                                // get dead zombies info
                                this.tempSpeed = enemy.getSpeed();
                                this.tempWidth = enemy.getWidth();
                                this.tempHeight = enemy.getHeight();

                                // zombie dies
                                enemy.setDead();
                                this.randomItemChance = (int) (Math.random() * (1 - 0 + 1) + 0);
                                // System.out.println(randomItemChance);
                                // 50 % to spawn item
                                if (this.randomItemChance == 1) {
                                    // generate a random number from 0 to 2
                                    this.randomItem = (int) (Math.random() * (2 - 0 + 1) + 0);
                                    // depending on which number, spawn Item on zombie position
                                    if (this.randomItem == 0) {
                                        // spawn HealthUp
                                        healths.add(new HealthUp(enemy.getX(), enemy.getY(), 50, 40, 10));
                                    } else if (this.randomItem == 1) {
                                        // spawn AmmoBox
                                        ammos.add(new AmmoBox(enemy.getX(), enemy.getY(), 50, 40, 48));
                                    } else if (this.randomItem == 2) {
                                        // spawn SpeedUp
                                        speeds.add(new SpeedUp(enemy.getX(), enemy.getY(), 40, 30, (float) 0.5));
                                    }
                                }
                                // generate random number from 0 to 1 for zombie location
                                this.randomZombieLocation = (int) (Math.random() * (1 - 0 + 1) + 0);
                                // generate random number from 
                                // spawn a new zombie depending on where 
                                if (enemy.collidesWith(topMain)) {
                                    // leftMain
                                    if (this.randomZombieLocation == 0) {
                                        this.spawn = 1;
                                    } else if (this.randomZombieLocation == 1) {
                                        // rightMain
                                        this.spawn = 2;
                                    }

                                } else if (enemy.collidesWith(leftMain)) {
                                    // topMain
                                    if (this.randomZombieLocation == 0) {
                                        this.spawn = 3;
                                    } else if (this.randomZombieLocation == 1) {
                                        // rightMain
                                        this.spawn = 2;
                                    }
                                } else if (enemy.collidesWith(rightMain)) {
                                    // leftMain
                                    if (this.randomZombieLocation == 0) {
                                        this.spawn = 1;
                                    } else if (this.randomZombieLocation == 1) {
                                        // topMain
                                        this.spawn = 3;
                                    }
                                }
                            }
                        }
                    }
                }

                // spawn new zombies (tankier, faster, stronger)
                // leftMain
                if (this.spawn == 1) {
                    enemies.add(new Enemies(100 + (this.multiplier * 2), (float) (this.tempSpeed + 0.25), (float) 975, (float) 760, this.tempWidth, this.tempHeight, 1 + this.multiplier));

                } else if (this.spawn == 2) {
                    // rightMain
                    enemies.add(new Enemies(100 + (this.multiplier * 2), (float) (this.tempSpeed + 0.25), (float) 2515, (float) 790, this.tempWidth, this.tempHeight, 1 + this.multiplier));

                } else if (this.spawn == 3) {
                    // topMain
                    enemies.add(new Enemies(100 + (this.multiplier * 2), (float) (this.tempSpeed + 0.25), (float) 1605, (float) 2000, this.tempWidth, this.tempHeight, 1 + this.multiplier));

                }
                // reset variable to prevent unneccessary spawns
                this.spawn = 0;

                // item collisions with players
                
                for (SpeedUp item : this.speeds) {
                    if (player.getRect().contains(item.getRect())) {
                        if (item.getCollided() == false) {
                            player.increaseSpeed(item);
                            item.setCollided();
                        }
                    }
                }
                // ammo boxes
                for (AmmoBox item : this.ammos) {
                    if (player.getRect().contains(item.getRect())) {
                        if (item.getCollided() == false) {
                            // add ammo to gun
                            pistol.addAmmo(item);
                            item.setCollided();
                        }
                    }
                }
                // healthups
                for (HealthUp item : this.healths) {
                    if (player.getRect().contains(item.getRect())) {
                        if (item.getCollided() == false) {
                            player.increaseHP(item);
                            item.setCollided();
                        }
                    }
                }
            }
            // game closes when player reaches 0 HP
            if (player.getHP() <= 0) {
                System.exit(0);
            }

//        shapeBatch.setColor(Color.RED);
//        for(Enemies enemy: enemies) {
//            enemy.draw(shapeBatch);
//        }
//        shapeBatch.setColor(Color.BLUE);
//        player.draw(shapeBatch);
            shapeBatch.end();

            // sprite drawings
            batch.setProjectionMatrix(cam.combined);
            batch.begin();

            // draw zombies
            for (Enemies enemy : enemies) {
                // if they are alive
                if (enemy.getIsDead() == false) {
                    enemy.draw(batch, player);
                    enemy.drawHP(batch);
                } else {
                    enemy.deadDraw(batch);
                }
            }

            // Player drawings
            player.draw(batch, cursorPosition.x, cursorPosition.y);
            player.drawHP(batch);

            // gun drawing
            // pistol.draw(batch, player, cursorPosition.x, cursorPosition.y);
            pistol.drawAmmo(batch, player);

            // draw items
            // speedups
            for (SpeedUp item : this.speeds) {
                if (item.getCollided() == false) {
                    item.draw(batch);
                }
            }
            // ammo boxes
            for (AmmoBox item : this.ammos) {
                if (item.getCollided() == false) {
                    item.draw(batch);
                }
            }
            // healthups
            for (HealthUp item : this.healths) {
                if (item.getCollided() == false) {
                    item.draw(batch);
                }
            }

            // gun reloading
            if (pistol.getReloading() == true) {
                pistol.drawReloading(batch, player);
            }

            // killCounter
            pistol.drawKillCounter(batch, player, killCounter);
            
            batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height);
    }

}
