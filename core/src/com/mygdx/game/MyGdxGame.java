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

    // weapon variables
    private M1911 pistol;
    private M1911Bullet bulletInfo;

    // entity variables
    private Player player;
    private ArrayList<Zombie> zombies;

    // level variables
    private Room leftMain;
    private Room rightMain;
    private Room topMain;
    private Texture background;
    private Texture wallPat;

    private Vector3 cursorPosition = new Vector3();
    // menu variables
    private boolean MainMenu;
    private BitmapFont font;
    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton startButton;
    private TextButton instructionsButton;

    // ArrayLists that store cursor clicks
    private ArrayList<Float> cursorXPositions;
    private ArrayList<Float> cursorYPositions;

    // item ArrayLists
    private ArrayList<SpeedUp> speeds;
    private ArrayList<AmmoBox> ammos;
    private ArrayList<HealthUp> healths;

    // determines whether an item will spawn or not
    private int randomItemChance;
    // determines which item spawns
    private int randomItem;
    private int randomZombieLocation;
    private int spawn;

    // variables used to store past zombie information
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

    // used to display number of kills
    private int killCounter;

    // add in walls here and be able to call them in a for loop
    @Override
    public void create() {
        // by default 0 kills
        killCounter = 0;

        // base of 1
        multiplier = 1;

        // create Player
        player = new Player(200, (float) 4.5, 600, 500, 100, 100);

        // create Rooms
        leftMain = new Room(100, 20, 1850, 1600);
        rightMain = new Room(1950, 20, 1880, 1500 + 60);
        topMain = new Room(550, 1550, 2460 + 400, 700 + 400);

        // initialize Item ArrayLists
        speeds = new ArrayList<SpeedUp>();
        ammos = new ArrayList<AmmoBox>();
        healths = new ArrayList<HealthUp>();

        // background and wall textures
        background = new Texture("abstract-geometric-background-triangles-and-lines-loop-4k-4096x2304_ekm8_sfzx__F0000.png");
        wallPat = new Texture("Metal-Pattern-Background-Blue.jpg");

        // create Zombies
        zombies = new ArrayList<Zombie>();
        zombies.add(new Zombie(100, (float) 2, (float) 1705, (float) 2100, 100, 100, 1));
        zombies.add(new Zombie(100, (float) 2, (float) 2200, (float) 2100, 100, 100, 1));
        zombies.add(new Zombie(100, (float) 2, (float) 1705, (float) 1350, 100, 100, 1));
        zombies.add(new Zombie(100, (float) 2, (float) 2500, (float) 1500, 100, 100, 1));
        zombies.add(new Zombie(100, (float) 2, (float) 2500, (float) 200, 100, 100, 1));

        // create Weapon and Bullet
        pistol = new M1911(12, (float) 2.5, 36);
        bulletInfo = new M1911Bullet(25, player.getX() + (player.getWidth() / 2), player.getY() + (player.getHeight() / 2), 55, 10);

        // main menu variables
        MainMenu = true;
        font = new BitmapFont();
        font.setColor(Color.RED);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);

        table.setPosition(0, Gdx.graphics.getHeight());
        startButton = new TextButton("Play Game", skin);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button CLicked");
                MainMenu = false;

            }
        });

        table.padTop(160);
        table.add(startButton).padBottom(30);
        table.row();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        // initialize cursor clicks ArrayLists
        cursorXPositions = new ArrayList<Float>();
        cursorYPositions = new ArrayList<Float>();

        batch = new SpriteBatch();
        shapeBatch = new ShapeRenderer();
        cam = new OrthographicCamera();
        viewport = new FitViewport(2000, 1600, cam);
        viewport.apply();
        cam.update();

        // create walls
        walls[0] = new Wall(100, 20, 1800, 80);
        walls[1] = new Wall(100, 1500, 450, 80); 
        walls[2] = new Wall(1150, 1500, 900, 80);  
        walls[3] = new Wall(20, 20, 80, 630);
        walls[4] = new Wall(20, 950, 80, 630);
        walls[5] = new Wall(1900, 20, 80, 630-100);
        walls[6] = new Wall(1900, 950+100, 80, 630-100);
        walls[7] = new Wall(1980, 20, 1800, 80);
        walls[8] = new Wall(1980, 1500, 650, 80); 
        walls[9] = new Wall(3130 + 200, 1500, 450, 80);  
        walls[10] = new Wall(0, 0,0, 0);
        walls[11] = new Wall(0, 0,0, 0);
        walls[12] = new Wall(3780, 20, 80, 630 + 920);
        walls[13] = new Wall(3780, 950, 80, 630);
        walls[14] = new Wall(550, 1500, 80, 900);
        walls[15] = new Wall(1150, 1500, 1560, 300);  
        walls[16] = new Wall(2630, 1500, 80, 300);
        walls[17] = new Wall(3330, 1500, 80, 900);
        walls[18] = new Wall(1150, 1800, 1560, 80);
        walls[19] = new Wall(550, 2200 + 200, 2460 + 400, 80);
        walls[20] = new Wall(20, 20, 80, 1000);

        // x y width 
        cam.position.x = player.getX();
        cam.position.y = player.getY();

        // calculates ammo at startup
        initialAmmoCalculated = false;
    }

    @Override
    public void render() {
        // main menu drawings
        if (MainMenu == true) {
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

        } else if (MainMenu == false) {
            // game drawings
            super.render();
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            cursorPosition.x = Gdx.input.getX();
            cursorPosition.y = Gdx.input.getY();
            cursorPosition.z = 0;
            cam.unproject(cursorPosition);

            // calculate initial ammo
            if (initialAmmoCalculated == false) {
                pistol.calculateInitialAmmo();
                initialAmmoCalculated = true;
                time = Gdx.graphics.getDeltaTime();
                aimedTime = Gdx.graphics.getDeltaTime();
            }

            // player movement using WASD
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.move();
            }

            // draw filled shapes
            shapeBatch.setProjectionMatrix(cam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);

            // update time counter in weapon
            pistol.calculateTime(time);

            // reload using R
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                // check if gun can be reloaded 
                if ((pistol.getBulletsInClip() != pistol.getclipSize()) && (pistol.getReloading() == false) && (pistol.totalAmmo() != 0)) {
                    // set gun reloading to true
                    pistol.setReloading();
                    // find time needed to reach inorder to reload gun
                    aimedTime = pistol.getCalculatedTime() + pistol.getReloadTime();
                }
            }

            // if gun is reloading
            if (pistol.getReloading() == true) {
                // once enough time passes, reload gun
                if (pistol.getCalculatedTime() > aimedTime) {
                    pistol.calculateAmmo();
                    pistol.stopReloading();
                }
            }

            // Zombie AI
            for (Zombie zombie : zombies) {
                // if it's alive
                if (zombie.getIsDead() == false) {
                    // move zombie accordingly where Player is
                    if ((zombie.collidesWith(leftMain) && player.collidesWith(leftMain)) || (zombie.collidesWith(rightMain) && player.collidesWith(rightMain)) || zombie.collidesWith(topMain) && player.collidesWith(topMain)) {
                        // if Zombie is in the same room as the Player
                        zombie.move(player);
                    } else if (zombie.collidesWith(leftMain) && player.collidesWith(rightMain)) {
                        // if Zombie is in leftMain and Player is in rightMain
                        zombie.MoveCoord(1950, 735);
                    } else if (zombie.collidesWith(rightMain) && player.collidesWith(leftMain)) {
                        // if Zombie is in rightMain and Player is in leftMain
                        zombie.MoveCoord(1940, 735);
                    } else if (zombie.collidesWith(leftMain) && player.collidesWith(topMain)) {
                        // if Zombie is in leftMain and Player is in topMain
                        zombie.MoveCoord(950, 1560);
                    } else if (zombie.collidesWith(rightMain) && player.collidesWith(topMain)) {
                        // if Zombie is in rightMain and Player is in topMain
                        zombie.MoveCoord(3000, 1560);
                    } else if (zombie.collidesWith(topMain) && player.collidesWith(leftMain)) {
                        // if Zombie is in topMain and Player is in leftMain
                        zombie.MoveCoord(950, 1520);
                    } else if (zombie.collidesWith(topMain) && player.collidesWith(rightMain)) {
                        // if Zombie is in topMain and Player is in rightMain
                        zombie.MoveCoord(3000, 1520);
                    }
                }
            }
            
            // for each Zombie in zombies ArrayList
            for (Zombie zombie : zombies) {
                // if Player collides with a Zombie, slow Zombie down
                if (player.collidesWithZ(zombie)) {
                    // minimum speed of 0.7
                    if (zombie.getSpeed() - 0.15 > 0.7) {
                        zombie.setSpeed((float) -0.15);
                    }
                    // decrease Player HP 
                    player.calculateHP(zombie.getDamage());
                    
                } 

            }

            // zombie to zombie collision
            for (Zombie zombie : zombies) {
                for (Zombie zombie2 : zombies) {
                    // if both Zombies are alive
                    if (zombie.getIsDead() == false && zombie2.getIsDead() == false) {
                        // if the Zombies that collide are not the same from the ArrayLists
                        if (zombie.collidesWith(zombie2) && zombie != zombie2) {
                            // max speed of 2.00 when Zombies collide with each other 
                            if (zombie.getSpeed() < 2.00 && zombie2.getSpeed() < 2.00) {
                                zombie.setSpeed((float) +0.15);
                                zombie2.setSpeed((float) +0.15);
                            }
                            // if they hit top
                            if (zombie.getY() <= zombie2.getY() + zombie2.getHeight() && zombie.getY() > zombie2.getY()) {
                                zombie.setYT();
                            }
                            // if they hit bottom
                            if (zombie.getY() + zombie.getHeight() >= zombie2.getY() && zombie.getY() + zombie.getHeight() <= zombie2.getY() + zombie2.getHeight()) {
                                zombie.setYB();
                            }
                            // if they hit left
                            if (zombie.getX() + zombie.getWidth() >= zombie2.getX() && zombie.getX() + zombie.getWidth() <= zombie2.getX() + zombie2.getWidth()) {
                                zombie.setXL();
                            }
                            // if they hit right
                            if (zombie.getX() <= zombie2.getX() + zombie2.getWidth() && zombie.getX() >= zombie2.getX()) {
                                zombie.setXR();
                            }
                        }
                    }
                }
            }

            // player to wall collision (checks wall array)
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
            // zombie to wall collision (checks wall array)    
            for (int g = 0; g < 21; g++) {
                // for each Zombie
                for (Zombie zombie : zombies) {
                    // if a Zombie hits a wall 
                    if (zombie.collidesWith(walls[g])) {
                        // if zombie hits top of wall
                        if (zombie.getY() <= walls[g].getY() + walls[g].getheight() && zombie.getY() > walls[g].getY()) {
                            zombie.setYT();
                        }
                        // if zombie hits bottom of a wall 
                        if (zombie.getY() + zombie.getHeight() >= walls[g].getY() && zombie.getY() + zombie.getHeight() <= walls[g].getY() + walls[g].getheight()) {
                            zombie.setYB();
                        }
                        //if zombie hits left side of a wall 
                        if (zombie.getX() + zombie.getWidth() >= walls[g].getX() && zombie.getX() + zombie.getWidth() <= walls[g].getX() + walls[g].getwidth()) {
                            zombie.setXL();
                        }
                        // if zombie hits right side of a wall 
                        if (zombie.getX() <= walls[g].getX() + walls[g].getwidth() && zombie.getX() >= walls[g].getX()) {
                            zombie.setXR();
                        }
                    }
                }
            }

            // if player glitches through wall, respawn in a Room 
            if ((player.collidesWith(topMain) != true) && (player.collidesWith(leftMain) != true) && (player.collidesWith(rightMain) != true)) {
                double random = Math.random();
                // punish Player
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

            batch.begin();
            // background texture
            batch.draw(background, -1000, -1000, 7168, 4100);

            shapeBatch.setColor(Color.GRAY);
            // draw each wall in array
            for (int z = 0; z < 21; z++) {
                batch.draw(wallPat, walls[z].getX(), walls[z].getY(), walls[z].getwidth(), walls[z].getheight());
            }
            batch.end();

            // if mouse is clicked
            if (Gdx.input.justTouched()) {
                // if there are bullets in the clip
                if (pistol.getBulletsInClip() != 0 && (pistol.getReloading() == false)) {
                    // shoot a bullet
                    pistol.addBullet(bulletInfo);
                    // store cursor coordinates into corresponding array lists
                    cursorXPositions.add(cursorPosition.x);
                    cursorYPositions.add(cursorPosition.y);
                }
            }

            shapeBatch.setColor(Color.BLACK);

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
                for (Zombie zombie : zombies) {
                    // only collide with zombie if it's alive
                    if (zombie.getIsDead() == false) {
                        if (zombie.getBounds().contains(bullet.getShape())) {
                            // damage zombie first
                            zombie.calculateHP(bullet.getDamage());
                            // set bullet to collided state
                            bullet.setCollided();

                            // if zombie HP is less than or equal to 0
                            if (zombie.getHP() <= 0) {
                                // killCounter increases
                                killCounter = killCounter + 1;

                                // multiplier for making stronger zombies increases
                                multiplier = multiplier + 1;

                                // get dead Zombie's info
                                tempSpeed = zombie.getSpeed();
                                tempWidth = zombie.getWidth();
                                tempHeight = zombie.getHeight();

                                // zombie dies
                                zombie.setDead();

                                // 50 % chance to spawn an Item (0 or 1)
                                randomItemChance = (int) (Math.random() * (1 - 0 + 1) + 0);
                                if (randomItemChance == 1) {
                                    // generate a random number from 0 to 2
                                    randomItem = (int) (Math.random() * (2 - 0 + 1) + 0);
                                    // depending on which number, spawn Item on zombie position
                                    if (randomItem == 0) {
                                        // spawn HealthUp
                                        healths.add(new HealthUp(zombie.getX() + (zombie.getWidth() / 2), zombie.getY() + (zombie.getHeight() / 2), 50, 40, 25));
                                    } else if (randomItem == 1) {
                                        // spawn AmmoBox
                                        ammos.add(new AmmoBox(zombie.getX() + (zombie.getWidth() / 2), zombie.getY() + (zombie.getHeight() / 2), 50, 40, 36));
                                    } else if (randomItem == 2) {
                                        // spawn SpeedUp
                                        speeds.add(new SpeedUp(zombie.getX() + (zombie.getWidth() / 2), zombie.getY() + (zombie.getHeight() / 2), 50, 40, (float) 0.5));
                                    }
                                }

                                // generate random number for Zombie spawn location (0 or 1)
                                randomZombieLocation = (int) (Math.random() * (1 - 0 + 1) + 0);
                                // spawn a new zombie depending on where dead Zombie was killed
                                // if dead Zombie was in topMain
                                if (zombie.collidesWith(topMain)) {
                                    // spawn new in leftMain
                                    if (randomZombieLocation == 0) {
                                        spawn = 1;
                                    } else if (randomZombieLocation == 1) {
                                        // spawn new in rightMain
                                        spawn = 2;
                                    }

                                } else if (zombie.collidesWith(leftMain)) {
                                    // else if in leftMain
                                    // spawn new in topMain
                                    if (randomZombieLocation == 0) {
                                        spawn = 3;
                                    } else if (randomZombieLocation == 1) {
                                        // spawn new in rightMain
                                        spawn = 2;
                                    }
                                } else if (zombie.collidesWith(rightMain)) {
                                    // else if in rightMain
                                    // spawn new in leftMain
                                    if (randomZombieLocation == 0) {
                                        spawn = 1;
                                    } else if (randomZombieLocation == 1) {
                                        // spawn new in topMain
                                        spawn = 3;
                                    }
                                }
                            }
                        }
                    }
                }

                // spawn new zombies depending on spawn variable (tankier, faster, stronger)
                // leftMain
                if (spawn == 1) {
                    zombies.add(new Zombie(100 + (multiplier * 2), (float) (tempSpeed + 0.25), (float) 975, (float) 760, tempWidth, tempHeight, 1));
                } else if (spawn == 2) {
                    // rightMain
                    zombies.add(new Zombie(100 + (multiplier * 2), (float) (tempSpeed + 0.25), (float) 2515, (float) 790, tempWidth, tempHeight, 1));

                } else if (spawn == 3) {
                    // topMain
                    zombies.add(new Zombie(100 + (multiplier * 2), (float) (tempSpeed + 0.25), (float) 1605, (float) 2200, tempWidth, tempHeight, 1));

                }

                // reset variable to spawn only one new Zombie
                spawn = 0;

                // item collisions with players
                // SpeedUps
                for (SpeedUp item : speeds) {
                    if (player.getRect().contains(item.getRect())) {
                        if (item.getCollided() == false) {
                            player.increaseSpeed(item);
                            item.setCollided();
                        }
                    }
                }
                // AmmoBoxes
                for (AmmoBox item : ammos) {
                    if (player.getRect().contains(item.getRect())) {
                        if (item.getCollided() == false) {
                            // add ammo to gun
                            pistol.addAmmo(item);
                            item.setCollided();
                        }
                    }
                }
                // HealthUps
                for (HealthUp item : healths) {
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
                // close program
                System.exit(0);
            }

            shapeBatch.end();

            // sprite drawings
            batch.setProjectionMatrix(cam.combined);
            batch.begin();

            // draw zombies
            for (Zombie zombie : zombies) {
                // if they are alive
                if (zombie.getIsDead() == false) {
                    zombie.draw(batch, player);
                    zombie.drawHP(batch);
                } else {
                    // draw blood puddle
                    zombie.deadDraw(batch);
                }
            }

            // Player drawings
            player.draw(batch, cursorPosition.x, cursorPosition.y);
            player.drawHP(batch);

            // draw ammo in weapon
            pistol.drawAmmo(batch, player);

            // draw items if they haven't been picked up by Player
            // SpeedUps
            for (SpeedUp item : speeds) {
                if (item.getCollided() == false) {
                    item.draw(batch);
                }
            }
            // AmmoBoxes
            for (AmmoBox item : ammos) {
                if (item.getCollided() == false) {
                    item.draw(batch);
                }
            }
            // HealthUps
            for (HealthUp item : healths) {
                if (item.getCollided() == false) {
                    item.draw(batch);
                }
            }

            // draw "RELOADING" when weapon is reloading
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
