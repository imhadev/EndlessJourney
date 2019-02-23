package com.sa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import static com.sa.game.MainClass.HEIGHT;
import static com.sa.game.MainClass.WIDTH;

public class GameScreen extends ScreenAdapter {

    //musthave
    private MainClass game;
    private OrthographicCamera camera;

    private Viewport viewport;

    Vector3 touchPoint;

    //game
    private int levelnum;
    private Random rand = new Random();

    private int enemyatk1;
    private int enemyatk2;
    private int enemydef1;
    private int enemydef2;
    private boolean[] enemyup;
    private  int randadd;
    private int randup;
    private int randstop;

    private int enemyatknum;
    private int enemydefnum;

    boolean buttondisable = false;

    //characters
    gamechar character;
    gamechar enemy;

    //ui
    private Stage stage;
    private Skin mySkin;

    private Button buttonatk1;
    private Button buttonatk2;
    private Button buttonatk3;
    private Button buttondef1;
    private Button buttondef2;
    private Button buttondef3;

    private Button buttonincup;
    private Button buttonatkup;
    private Button buttondefup;

    private Button buttonaddatkchar;
    private Button buttonadddefchar;


    private Button buttonatken1;
    private Button buttonatken2;
    private Button buttonatken3;
    private Button buttondefen1;
    private Button buttondefen2;
    private Button buttondefen3;

    private Button buttonincupen;
    private Button buttonatkupen;
    private Button buttondefupen;

    private Button buttonaddatken;
    private Button buttonadddefen;


    private Button buttonfight;

    private ButtonGroup buttonGroup1;
    private ButtonGroup buttonGroup2;

    private Label.LabelStyle label1Style;
    private BitmapFont myFont;
    private Label labelhpchar;
    private Label labelgoldchar;
    private Label labelincchar;
    private Label labelatkchar;
    private Label labeldefchar;

    private Label labelhpen;
    private Label labelgolden;
    private Label labelincen;
    private Label labelatken;
    private Label labeldefen;

    //screen
    /*private final int height1 = Gdx.app.getGraphics().getHeight();
    private final int width1 = Gdx.app.getGraphics().getWidth();*/
    private int btn_size1 = WIDTH / 20;
    private int btn_size2 = WIDTH / 14;

    public GameScreen(MainClass game, int levelnum, int atk, int def) {

        this.game = game;
        this.levelnum = levelnum;

        camera = new OrthographicCamera();
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);

        viewport = new ScreenViewport(camera);

        touchPoint = new Vector3();

        character = new gamechar(100, 10, 5, atk, def);
        switch(levelnum) {
            case 2: {
                enemy = new gamechar(110, 15, 5, 17, 5);
                break;
            }
            case 3: {
                enemy = new gamechar(150, 10, 5, 11, 5);
                break;
            }

            default: {
                enemy = new gamechar(100, 10, 5, 7, 3);
                break;
            }
        }


        //ui
        addbuttons();
        addlabels();
    }

    public void addbuttons() {
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        //stage = new Stage();
        //stage = new Stage(new ExtendViewport(1280, 720, camera));
        Gdx.input.setInputProcessor(stage);

        mySkin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));

        //character atk/def buttons
        buttonatk1 = new TextButton("", mySkin, "toggle");
        buttonatk1.setSize(btn_size1, btn_size1);
        buttonatk1.setPosition(WIDTH / 4 + btn_size1 * 5 / 2, HEIGHT / 4 * 3 - btn_size1 / 4 * 3);
        buttonatk1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonatk1);

        buttonatk2 = new TextButton("", mySkin, "toggle");
        buttonatk2.setSize(btn_size1, btn_size1);
        buttonatk2.setPosition(WIDTH / 4 + btn_size1 * 5 / 2, HEIGHT / 4 * 3 - btn_size1 * 3 / 2 - btn_size1 / 4 * 3);
        buttonatk2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonatk2);

        buttonatk3 = new TextButton("", mySkin, "toggle");
        buttonatk3.setSize(btn_size1, btn_size1);
        buttonatk3.setPosition(WIDTH / 4 + btn_size1 * 5 / 2, HEIGHT / 4 * 3 - btn_size1 * 6 / 2 - btn_size1 / 4 * 3);
        buttonatk3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonatk3);

        buttonGroup1 = new ButtonGroup(buttonatk1, buttonatk2, buttonatk3);
        buttonGroup1.setMaxCheckCount(1);
        buttonGroup1.setMinCheckCount(0);
        buttonGroup1.setUncheckLast(true);


        buttondef1 = new TextButton("", mySkin, "toggle");
        buttondef1.setSize(btn_size1, btn_size1);
        buttondef1.setPosition(WIDTH / 4 + btn_size1, HEIGHT / 4 * 3 - btn_size1 / 4 * 3);
        buttondef1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttondef1);

        buttondef2 = new TextButton("", mySkin, "toggle");
        buttondef2.setSize(btn_size1, btn_size1);
        buttondef2.setPosition(WIDTH / 4 + btn_size1, HEIGHT / 4 * 3 - btn_size1 * 3 / 2 - btn_size1 / 4 * 3);
        buttondef2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttondef2);

        buttondef3 = new TextButton("", mySkin, "toggle");
        buttondef3.setSize(btn_size1, btn_size1);
        buttondef3.setPosition(WIDTH / 4 + btn_size1, HEIGHT / 4 * 3 - btn_size1 * 6 / 2 - btn_size1 / 4 * 3);
        buttondef3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttondef3);

        buttonGroup2 = new ButtonGroup(buttondef1, buttondef2, buttondef3);
        buttonGroup2.setMaxCheckCount(1);
        buttonGroup2.setMinCheckCount(0);
        buttonGroup2.setUncheckLast(true);



        //character upgrade buttons
        buttonincup = new TextButton("inc +5", mySkin, "toggle");
        buttonincup.setSize(btn_size2, btn_size2);
        buttonincup.setPosition(WIDTH / 20, HEIGHT / 17);
        buttonincup.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!buttonincup.isChecked()) {
                    character.setGold(character.getGold() - 10);
                    character.setInc(character.getInc() + 5);
                }
                else {
                    character.setInc(character.getInc() - 5);
                    character.setGold(character.getGold() + 10);
                }
                return true;
            }
        });
        stage.addActor(buttonincup);

        buttonatkup = new TextButton("atk +4", mySkin, "toggle");
        buttonatkup.setSize(btn_size2, btn_size2);
        buttonatkup.setPosition(WIDTH / 20 + btn_size2 / 2 + btn_size1, HEIGHT / 17);
        buttonatkup.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!buttonatkup.isChecked()) {
                    character.setGold(character.getGold() - 10);
                    character.setAtk(character.getAtk() + 4);
                }
                else {
                    character.setAtk(character.getAtk() - 4);
                    character.setGold(character.getGold() + 10);
                }
                return true;
            }
        });
        stage.addActor(buttonatkup);

        buttondefup = new TextButton("def +2", mySkin, "toggle");
        buttondefup.setSize(btn_size2, btn_size2);
        buttondefup.setPosition(WIDTH / 20 + btn_size2 + btn_size1 * 2, HEIGHT / 17);
        buttondefup.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!buttondefup.isChecked()) {
                    character.setGold(character.getGold() - 10);
                    character.setDef(character.getDef() + 2);
                }
                else {
                    character.setDef(character.getDef() - 2);
                    character.setGold(character.getGold() + 10);
                }
                return true;
            }
        });
        stage.addActor(buttondefup);



        //character add atk/def buttons
        buttonaddatkchar = new TextButton("+1 atk", mySkin, "toggle");
        buttonaddatkchar.setSize(btn_size1 + 10, btn_size1 + 10);
        buttonaddatkchar.setPosition(WIDTH / 20 + btn_size1, HEIGHT / 4);
        buttonaddatkchar.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!buttonaddatkchar.isChecked()) {
                    character.setGold(character.getGold() - 15);
                }
                else {
                    character.setGold(character.getGold() + 15);
                    buttonatk1.setChecked(false);
                    buttonatk2.setChecked(false);
                    buttonatk3.setChecked(false);
                }
                return true;
            }
        });
        stage.addActor(buttonaddatkchar);


        buttonadddefchar = new TextButton("+1 def", mySkin, "toggle");
        buttonadddefchar.setSize(btn_size1 + 10, btn_size1 + 10);
        buttonadddefchar.setPosition(WIDTH / 20 + btn_size1 + btn_size2, HEIGHT / 4);
        buttonadddefchar.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!buttonadddefchar.isChecked()) {
                    character.setGold(character.getGold() - 15);
                }
                else {
                    character.setGold(character.getGold() + 15);
                    buttondef1.setChecked(false);
                    buttondef2.setChecked(false);
                    buttondef3.setChecked(false);
                }
                return true;
            }
        });
        stage.addActor(buttonadddefchar);



        //enemy atk/def buttons
        buttonatken1 = new TextButton("", mySkin, "toggle");
        buttonatken1.setSize(btn_size1, btn_size1);
        buttonatken1.setPosition(WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1, HEIGHT / 4 * 3 - btn_size1 / 4 * 3);
        buttonatken1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonatken1);

        buttonatken2 = new TextButton("", mySkin, "toggle");
        buttonatken2.setSize(btn_size1, btn_size1);
        buttonatken2.setPosition(WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1, HEIGHT / 4 * 3 - btn_size1 * 3 / 2 - btn_size1 / 4 * 3);
        buttonatken2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonatken2);

        buttonatken3 = new TextButton("", mySkin, "toggle");
        buttonatken3.setSize(btn_size1, btn_size1);
        buttonatken3.setPosition(WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1, HEIGHT / 4 * 3 - btn_size1 * 6 / 2 - btn_size1 / 4 * 3);
        buttonatken3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonatken3);



        buttondefen1 = new TextButton("", mySkin, "toggle");
        buttondefen1.setSize(btn_size1, btn_size1);
        buttondefen1.setPosition(WIDTH - (WIDTH / 4 + btn_size1) - btn_size1, HEIGHT / 4 * 3 - btn_size1 / 4 * 3);
        buttondefen1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttondefen1);

        buttondefen2 = new TextButton("", mySkin, "toggle");
        buttondefen2.setSize(btn_size1, btn_size1);
        buttondefen2.setPosition(WIDTH - (WIDTH / 4 + btn_size1) - btn_size1, HEIGHT / 4 * 3 - btn_size1 * 3 / 2 - btn_size1 / 4 * 3);
        buttondefen2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttondefen2);

        buttondefen3 = new TextButton("", mySkin, "toggle");
        buttondefen3.setSize(btn_size1, btn_size1);
        buttondefen3.setPosition(WIDTH - (WIDTH / 4 + btn_size1) - btn_size1, HEIGHT / 4 * 3 - btn_size1 * 6 / 2 - btn_size1 / 4 * 3);
        buttondefen3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttondefen3);


        buttonatken1.setTouchable(Touchable.disabled);
        buttonatken2.setTouchable(Touchable.disabled);
        buttonatken3.setTouchable(Touchable.disabled);
        buttondefen1.setTouchable(Touchable.disabled);
        buttondefen2.setTouchable(Touchable.disabled);
        buttondefen3.setTouchable(Touchable.disabled);



        //enemy upgrade buttons
        buttonincupen = new TextButton("inc +5", mySkin, "toggle");
        buttonincupen.setSize(btn_size2, btn_size2);
        buttonincupen.setPosition(WIDTH - (WIDTH / 20 + btn_size2 + btn_size1 * 2) - btn_size2, HEIGHT / 17);
        buttonincupen.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonincupen);

        buttonatkupen = new TextButton("atk +4", mySkin, "toggle");
        buttonatkupen.setSize(btn_size2, btn_size2);
        buttonatkupen.setPosition(WIDTH - (WIDTH / 20 + btn_size2 / 2 + btn_size1) - btn_size2, HEIGHT / 17);
        buttonatkupen.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonatkupen);

        buttondefupen = new TextButton("def +2", mySkin, "toggle");
        buttondefupen.setSize(btn_size2, btn_size2);
        buttondefupen.setPosition(WIDTH - (WIDTH / 20) - btn_size2, HEIGHT / 17);
        buttondefupen.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttondefupen);


        buttonincupen.setTouchable(Touchable.disabled);
        buttonatkupen.setTouchable(Touchable.disabled);
        buttondefupen.setTouchable(Touchable.disabled);


        //enemy add atk/def buttons
        buttonaddatken = new TextButton("+1 atk", mySkin, "toggle");
        buttonaddatken.setSize(btn_size1 + 10, btn_size1 + 10);
        buttonaddatken.setPosition(WIDTH - (WIDTH / 20 + btn_size1 + btn_size2) - btn_size2, HEIGHT / 4);
        buttonaddatken.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonaddatken);


        buttonadddefen = new TextButton("+1 def", mySkin, "toggle");
        buttonadddefen.setSize(btn_size1 + 10, btn_size1 + 10);
        buttonadddefen.setPosition(WIDTH - (WIDTH / 20 + btn_size1) - btn_size2, HEIGHT / 4);
        buttonadddefen.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(buttonadddefen);


        buttonaddatken.setTouchable(Touchable.disabled);
        buttonadddefen.setTouchable(Touchable.disabled);


        //fight button
        buttonfight = new TextButton("fight", mySkin, "toggle");
        buttonfight.setSize(btn_size2 * 2, btn_size2);
        buttonfight.setPosition(WIDTH / 2 - btn_size2, HEIGHT / 6);
        buttonfight.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!buttonfight.isChecked()) {

                    //enemy actions

                    enemyatknum = 1;
                    enemydefnum = 1;
                    if (enemy.getGold() >= 40) {
                        enemyatknum = 2;
                        enemydefnum = 2;
                        enemy.setGold(enemy.getGold() - 15 * 2);
                    }
                    else {
                        if (enemy.getGold() >= 15) {
                            randadd = rand.nextInt(8);
                            if (randadd == 0) {
                                enemyatknum = 2;
                                enemy.setGold(enemy.getGold() - 15);
                            }
                            if (randadd == 1) {
                                enemydefnum = 2;
                                enemy.setGold(enemy.getGold() - 15);
                            }
                        }
                    }


                    boolean enemyatkcase = false;
                    boolean enemydefcase = false;

                    if (enemyatknum == 2) {
                        enemyatk1 = rand.nextInt(3) + 1;
                        while (enemyatkcase == false) {
                            enemyatk2 = rand.nextInt(3) + 1;
                            if (enemyatk2 != enemyatk1) {
                                enemyatkcase = true;
                            }
                        }
                    }
                    else {
                        enemyatk1 = rand.nextInt(4);
                        enemyatk2 = 0;
                        if (enemyatk1 == 0) {
                            enemyatknum = 0;
                        }
                    }

                    if (enemydefnum == 2) {
                        enemydef1 = rand.nextInt(3) + 1;
                        while (enemydefcase == false) {
                            enemydef2 = rand.nextInt(3) + 1;
                            if (enemydef2 != enemydef1) {
                                enemydefcase = true;
                            }
                        }
                    }
                    else {
                        enemydef1 = rand.nextInt(3) + 1;
                        enemydef2 = 0;
                    }


                    enemyup = new boolean[3];
                    randup = rand.nextInt(3);
                    boolean f = true;


                    for (int i = 0; i < 3; i++) {
                        enemyup[i] = false;
                    }

                    while (f) {
                        randstop = rand.nextInt(10);
                        if (randstop > 8) {
                            break;
                        }
                        if (enemy.getGold() >= 10) {
                            if (enemyup[randup] == false) {
                                if (randup == 0) {
                                    enemy.setInc(enemy.getInc() + 5);
                                    enemy.setGold(enemy.getGold() - 10);
                                }
                                if (randup == 1) {
                                    enemy.setAtk(enemy.getAtk() + 4);
                                    enemy.setGold(enemy.getGold() - 10);
                                }
                                if (randup == 2) {
                                    enemy.setDef(enemy.getDef() + 2);
                                    enemy.setGold(enemy.getGold() - 10);
                                }

                                enemyup[randup] = true;

                                if (randup == 2) {
                                    randup = 0;
                                }
                                else {
                                    randup++;
                                }
                            }
                            else {
                                f = false;
                            }
                        }
                        else {
                            f = false;
                        }
                    }


                    //fight
                    if ((buttonatk1.isChecked()) && (enemydef1 != 1) && (enemydef2 != 1)) {
                        if (enemy.getDef() >= character.getAtk()) {
                            enemy.setHp(enemy.getHp() - 1);
                        }
                        else {
                            enemy.setHp(enemy.getHp() - (character.getAtk() - enemy.getDef()));
                        }
                    }
                    if ((buttonatk2.isChecked()) && (enemydef1 != 2) && (enemydef2 != 2)) {
                        if (enemy.getDef() >= character.getAtk()) {
                            enemy.setHp(enemy.getHp() - 1);
                        }
                        else {
                            enemy.setHp(enemy.getHp() - (character.getAtk() - enemy.getDef()));
                        }
                    }
                    if ((buttonatk3.isChecked()) && (enemydef1 != 3) && (enemydef2 != 3)) {
                        if (enemy.getDef() >= character.getAtk()) {
                            enemy.setHp(enemy.getHp() - 1);
                        }
                        else {
                            enemy.setHp(enemy.getHp() - (character.getAtk() - enemy.getDef()));
                        }
                    }

                    if (((enemyatk1 == 1) || (enemyatk2 == 1)) && (!buttondef1.isChecked())) {
                        if (character.getDef() >= enemy.getAtk()) {
                            character.setHp(character.getHp() - 1);
                        }
                        else {
                            character.setHp(character.getHp() - (enemy.getAtk() - character.getDef()));
                        }
                    }
                    if (((enemyatk1 == 2) || (enemyatk2 == 2)) && (!buttondef2.isChecked())) {
                        if (character.getDef() >= enemy.getAtk()) {
                            character.setHp(character.getHp() - 1);
                        }
                        else {
                            character.setHp(character.getHp() - (enemy.getAtk() - character.getDef()));
                        }
                    }
                    if (((enemyatk1 == 3) || (enemyatk2 == 3)) && (!buttondef3.isChecked())) {
                        if (character.getDef() >= enemy.getAtk()) {
                            character.setHp(character.getHp() - 1);
                        }
                        else {
                            character.setHp(character.getHp() - (enemy.getAtk() - character.getDef()));
                        }
                    }


                    //other
                    character.setGold(character.getGold() + character.getInc());
                    if ((!buttonatk1.isChecked()) && (!buttonatk2.isChecked()) && (!buttonatk3.isChecked())) {
                        character.setGold(character.getGold() + 5);
                    }

                    enemy.setGold(enemy.getGold() + enemy.getInc());
                    if (enemyatknum == 0) {
                        enemy.setGold(enemy.getGold() + 5);
                    }

                    buttondisable = true;
                    buttonatk1.setTouchable(Touchable.disabled);
                    buttonatk2.setTouchable(Touchable.disabled);
                    buttonatk3.setTouchable(Touchable.disabled);
                    buttondef1.setTouchable(Touchable.disabled);
                    buttondef2.setTouchable(Touchable.disabled);
                    buttondef3.setTouchable(Touchable.disabled);
                    buttonincup.setTouchable(Touchable.disabled);
                    buttonatkup.setTouchable(Touchable.disabled);
                    buttondefup.setTouchable(Touchable.disabled);
                    buttonaddatkchar.setTouchable(Touchable.disabled);
                    buttonadddefchar.setTouchable(Touchable.disabled);


                    if ((enemyatk1 == 1) || (enemyatk2 == 1)) {
                        buttonatken1.setChecked(true);
                    }
                    if ((enemyatk1 == 2) || (enemyatk2 == 2)) {
                        buttonatken2.setChecked(true);
                    }
                    if ((enemyatk1 == 3) || (enemyatk2 == 3)) {
                        buttonatken3.setChecked(true);
                    }

                    if ((enemydef1 == 1) || (enemydef2 == 1)) {
                        buttondefen1.setChecked(true);
                    }
                    if ((enemydef1 == 2) || (enemydef2 == 2)) {
                        buttondefen2.setChecked(true);
                    }
                    if ((enemydef1 == 3) || (enemydef2 == 3)) {
                        buttondefen3.setChecked(true);
                    }

                    if (enemyup[0] == true) {
                        buttonincupen.setChecked(true);
                    }
                    if (enemyup[1] == true) {
                        buttonatkupen.setChecked(true);
                    }
                    if (enemyup[2] == true) {
                        buttondefupen.setChecked(true);
                    }

                    if (enemyatknum == 2) {
                        buttonaddatken.setChecked(true);
                    }
                    if (enemydefnum == 2) {
                        buttonadddefen.setChecked(true);
                    }
                }
                else {
                    buttondisable = false;
                    buttonatk1.setChecked(false);
                    buttonatk2.setChecked(false);
                    buttonatk3.setChecked(false);
                    buttondef1.setChecked(false);
                    buttondef2.setChecked(false);
                    buttondef3.setChecked(false);
                    buttonincup.setChecked(false);
                    buttonatkup.setChecked(false);
                    buttondefup.setChecked(false);
                    buttonaddatkchar.setChecked(false);
                    buttonadddefchar.setChecked(false);

                    buttonatken1.setChecked(false);
                    buttonatken2.setChecked(false);
                    buttonatken3.setChecked(false);
                    buttondefen1.setChecked(false);
                    buttondefen2.setChecked(false);
                    buttondefen3.setChecked(false);
                    buttonincupen.setChecked(false);
                    buttonatkupen.setChecked(false);
                    buttondefupen.setChecked(false);
                    buttonaddatken.setChecked(false);
                    buttonadddefen.setChecked(false);

                    if (character.getHp() <= 0) {
                        game.setScreen(new GameOverScreen(game, 0));
                    }
                    if ((enemy.getHp() <= 0) && (character.getHp() > 0)) {
                        if (levelnum == 1) {
                            game.setScreen(new GameOverScreen(game, 1));
                        }
                        else {
                            game.setScreen(new GameScreen(game, levelnum+ 1, character.getAtk(), character.getDef()));
                        }
                    }
                }
                return true;
            }
        });
        stage.addActor(buttonfight);
    }

    public void addlabels() {
        label1Style = new Label.LabelStyle();
        myFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.BLUE;

        //character
        labelhpchar = new Label(String.valueOf(character.getHp()), label1Style);
        labelhpchar.setSize(50, 30);
        labelhpchar.setPosition(WIDTH / 20 + btn_size1 / 3, HEIGHT - (HEIGHT / 6));
        labelhpchar.setAlignment(Align.center);
        stage.addActor(labelhpchar);

        labelgoldchar = new Label(String.valueOf(character.getGold()), label1Style);
        labelgoldchar.setSize(50, 30);
        labelgoldchar.setPosition(WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3,HEIGHT - (HEIGHT / 6));
        labelgoldchar.setAlignment(Align.center);
        stage.addActor(labelgoldchar);

        labelincchar = new Label(String.valueOf(character.getInc()), label1Style);
        labelincchar.setSize(50, 30);
        labelincchar.setPosition(WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3,HEIGHT - (HEIGHT / 6));
        labelincchar.setAlignment(Align.center);
        stage.addActor(labelincchar);

        labelatkchar = new Label(String.valueOf(character.getAtk()), label1Style);
        labelatkchar.setSize(50, 30);
        labelatkchar.setPosition(WIDTH / 4 + btn_size1 * 5 / 2 + 30, HEIGHT / 4 * 3 + btn_size1 / 2);
        labelatkchar.setAlignment(Align.center);
        stage.addActor(labelatkchar);

        labeldefchar = new Label(String.valueOf(character.getDef()), label1Style);
        labeldefchar.setSize(50, 30);
        labeldefchar.setPosition(WIDTH / 4 + btn_size1 + 30, HEIGHT / 4 * 3 + btn_size1 / 2);
        labeldefchar.setAlignment(Align.center);
        stage.addActor(labeldefchar);



        //enemy
        labelhpen = new Label(String.valueOf(enemy.getHp()), label1Style);
        labelhpen.setSize(50, 30);
        labelhpen.setPosition(WIDTH - (WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3) - 50 + 40, HEIGHT - (HEIGHT / 6));
        labelhpen.setAlignment(Align.center);
        stage.addActor(labelhpen);

        labelgolden = new Label(String.valueOf(enemy.getGold()), label1Style);
        labelgolden.setSize(50, 30);
        labelgolden.setPosition(WIDTH - (WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3) - 50 + 40,HEIGHT - (HEIGHT / 6));
        labelgolden.setAlignment(Align.center);
        stage.addActor(labelgolden);

        labelincen = new Label(String.valueOf(enemy.getInc()), label1Style);
        labelincen.setSize(50, 30);
        labelincen.setPosition(WIDTH - (WIDTH / 20 + btn_size1 / 3) - 50 + 40,HEIGHT - (HEIGHT / 6));
        labelincen.setAlignment(Align.center);
        stage.addActor(labelincen);

        labelatken = new Label(String.valueOf(enemy.getAtk()), label1Style);
        labelatken.setSize(50, 30);
        labelatken.setPosition(WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1 + 30,HEIGHT / 4 * 3 + btn_size1 / 2);
        labelatken.setAlignment(Align.center);
        stage.addActor(labelatken);

        labeldefen = new Label(String.valueOf(enemy.getDef()), label1Style);
        labeldefen.setSize(50, 30);
        labeldefen.setPosition(WIDTH - (WIDTH / 4 + btn_size1) - btn_size1 + 30,HEIGHT / 4 * 3 + btn_size1 / 2);
        labeldefen.setAlignment(Align.center);
        stage.addActor(labeldefen);
    }

    public void update (float deltaTime) {
        if (!buttondisable) {
            buttonatk1.setTouchable(Touchable.enabled);
        }
        if (!buttondisable) {
            buttonatk2.setTouchable(Touchable.enabled);
        }
        if (!buttondisable) {
            buttonatk3.setTouchable(Touchable.enabled);
        }
        if (!buttondisable) {
            buttondef1.setTouchable(Touchable.enabled);
        }
        if (!buttondisable) {
            buttondef2.setTouchable(Touchable.enabled);
        }
        if (!buttondisable) {
            buttondef3.setTouchable(Touchable.enabled);
        }


        if (!buttondisable) {
            if ((character.getGold() < 10) && (buttonincup.isChecked() == false)) {
                buttonincup.setTouchable(Touchable.disabled);
            } else {
                buttonincup.setTouchable(Touchable.enabled);
            }
        }

        if (!buttondisable) {
            if ((character.getGold() < 10) && (buttonatkup.isChecked() == false)) {
                buttonatkup.setTouchable(Touchable.disabled);
            } else {
                buttonatkup.setTouchable(Touchable.enabled);
            }
        }

        if (!buttondisable) {
            if ((character.getGold() < 10) && (buttondefup.isChecked() == false)) {
                buttondefup.setTouchable(Touchable.disabled);
            } else {
                buttondefup.setTouchable(Touchable.enabled);
            }
        }


        if (!buttondisable) {
            if ((character.getGold() < 15) && (buttonaddatkchar.isChecked() == false)) {
                buttonaddatkchar.setTouchable(Touchable.disabled);
            } else {
                buttonaddatkchar.setTouchable(Touchable.enabled);
            }
        }

        if (buttonaddatkchar.isChecked()) {
            buttonGroup1.setMaxCheckCount(2);
        }
        else {
            buttonGroup1.setMaxCheckCount(1);
        }


        if (!buttondisable) {
            if ((character.getGold() < 15) && (buttonadddefchar.isChecked() == false)) {
                buttonadddefchar.setTouchable(Touchable.disabled);
            } else {
                buttonadddefchar.setTouchable(Touchable.enabled);
            }
        }

        if (buttonadddefchar.isChecked()) {
            buttonGroup2.setMaxCheckCount(2);
        }
        else {
            buttonGroup2.setMaxCheckCount(1);
        }
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(Assets.backgroundRegionlvl1, 0, 0, WIDTH, HEIGHT);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();

        game.batch.draw(Assets.hpicon, WIDTH / 20 + btn_size1 / 3 - 35 , HEIGHT - (HEIGHT / 6), 32, 32);
        game.batch.draw(Assets.hpicon, WIDTH - (WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3) - 50 + 40 - 35 , HEIGHT - (HEIGHT / 6), 32, 32);

        game.batch.draw(Assets.goldicon, WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3 - 35 , HEIGHT - (HEIGHT / 6), 32, 32);
        game.batch.draw(Assets.goldicon, WIDTH - (WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3) - 50 + 40 - 35 , HEIGHT - (HEIGHT / 6), 32, 32);

        game.batch.draw(Assets.incicon, WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3 - 35 , HEIGHT - (HEIGHT / 6), 32, 32);
        game.batch.draw(Assets.incicon, WIDTH - (WIDTH / 20 + btn_size1 / 3) - 50 + 40 - 35 , HEIGHT - (HEIGHT / 6), 32, 32);

        game.batch.draw(Assets.atkicon, WIDTH / 4 + btn_size1 * 5 / 2 - 5, HEIGHT / 4 * 3 + btn_size1 / 2 - 1, 32, 32);
        game.batch.draw(Assets.atkicon, WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1 - 5, HEIGHT / 4 * 3 + btn_size1 / 2 - 1, 32, 32);

        game.batch.draw(Assets.deficon, WIDTH / 4 + btn_size1 - 5, HEIGHT / 4 * 3 + btn_size1 / 2 - 1, 32, 32);
        game.batch.draw(Assets.deficon, WIDTH - (WIDTH / 4 + btn_size1) - btn_size1 - 5 , HEIGHT / 4 * 3 + btn_size1 / 2 - 1, 32, 32);

        game.batch.draw(Assets.character, WIDTH / 13 , HEIGHT / 2 - 40, 180, 240);

        if (levelnum == 1) {
            game.batch.draw(Assets.enemylvl1, WIDTH - (WIDTH / 13) - 200 , HEIGHT / 2 - 40, 200, 250);
        }
        game.batch.end();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();

        labelhpchar.setText(String.valueOf(character.getHp()));
        labelgoldchar.setText(String.valueOf(character.getGold()));
        labelincchar.setText(String.valueOf(character.getInc()));
        labelatkchar.setText(String.valueOf(character.getAtk()));
        labeldefchar.setText(String.valueOf(character.getDef()));


        labelhpen.setText(String.valueOf(enemy.getHp()));
        labelgolden.setText(String.valueOf(enemy.getGold()));
        labelincen.setText(String.valueOf(enemy.getInc()));
        labelatken.setText(String.valueOf(enemy.getAtk()));
        labeldefen.setText(String.valueOf(enemy.getDef()));

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, WIDTH, HEIGHT);
        camera.update();
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        stage.dispose();
        mySkin.dispose();
        myFont.dispose();
    }
}