package com.sa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    private int state;
    private static Random rand = new Random();
    private int beforefight = 1;
    private int fightend = 0;

    private int statslvl1 = 0;
    private int statsbalance;

    private int levelnum;
    private int round;
    private int dialogue1 = 1;
    private int dialogue2 = 1;
    private int dialogue1max;
    private int dialogue2max;

    private boolean buttondisable = false;

    private int[] enemyactions = new int[13];

    private int hpdifchar;
    private int golddifchar;
    private int hpdifen;
    private int golddifen;

    //lvl2
    private int immune = 0;
    private int bonusupgrade = 0;

    //characters
    GameChar character;
    GameChar enemy;

    //ui
    private Stage stage;
    private Stage stagepause;
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
    private Button buttonpause;
    private Button buttonresume;
    private Button buttonmenu;
    private Button buttonnext;

    private ButtonGroup buttonGroup1;
    private ButtonGroup buttonGroup2;

    private Label.LabelStyle label1Style1;
    private Label.LabelStyle label1Style2;
    private Label.LabelStyle label1Style3;
    private Label.LabelStyle label1Style4;
    private BitmapFont myFont;
    private Label labelhpchar;
    private Label labelhpchardif;
    private Label labelgoldchar;
    private Label labelgoldchardif;
    private Label labelincchar;
    private Label labelatkchar;
    private Label labeldefchar;

    private Label labelhpen;
    private Label labelhpendif;
    private Label labelgolden;
    private Label labelgoldendif;
    private Label labelincen;
    private Label labelatken;
    private Label labeldefen;

    private Label labelstory1;
    private Label labelstory2;
    private Label labelround;
    private Label labelenemyabout1;
    private Label labelatkcharstory;
    private Label labeldefcharstory;

    private Label labelbonusinc;
    private Label labelbonusatk;
    private Label labelbonusdef;
    private Label labelimmune;

    //screen
    /*private final int height1 = Gdx.app.getGraphics().getHeight();
    private final int width1 = Gdx.app.getGraphics().getWidth();*/
    private int btn_size1 = WIDTH / 20;
    private int btn_size2 = WIDTH / 14;

    public GameScreen(MainClass game, int levelnum, int atk, int def, int dialogue1max, int dialogue2max) {
        state = 2;

        this.game = game;
        this.levelnum = levelnum;

        this.dialogue1max = dialogue1max;
        this.dialogue2max = dialogue2max;

        camera = new OrthographicCamera();
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);

        viewport = new ScreenViewport(camera);

        touchPoint = new Vector3();

        switch(levelnum) {
            case 2: {
                character = new GameChar(150, 11, 6, atk, def, 13, 16, 12 ,22 ,29, 8, 7, 5, 7);
                enemy = new GameChar(150, 11, 6, 20, 23, 14, 17, 14 ,24 ,31, 10, 8, 6, 7);
                break;
            }

            case 3: {
                enemy = new GameChar(100, 10, 5, atk, def, 10, 10, 10 ,15 ,15, 5, 3, 2, 5);
                break;
            }

            case 4: {
                enemy = new GameChar(100, 10, 5, atk, def, 10, 10, 10 ,15 ,15, 5, 3, 2, 5);
                break;
            }

            case 5: {
                enemy = new GameChar(100, 10, 5, atk, def, 10, 10, 10 ,15 ,15, 5, 3, 2, 5);
                break;
            }

            default: {
                character = new GameChar(150, 11, 6, atk, def, 13, 16, 12 ,22 ,29, 8, 7, 5, 7);
                enemy = new GameChar(150, 11, 6, atk, def, 13, 16, 12 ,22 ,29, 8, 7, 5, 7);
                break;
            }
        }
        round = 1;


        //ui
        addbuttons();
        addlabels();
    }

    public void addbuttons() {
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        stagepause = new Stage(new StretchViewport(WIDTH, HEIGHT));

        Gdx.input.setInputProcessor(stagepause);

        mySkin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));


        //character atk/def buttons
        buttonatk1 = LoadButtons.button1_create("", mySkin, WIDTH / 4 + btn_size1 * 5 / 2, HEIGHT / 4 * 3 - btn_size1 / 4 * 3, btn_size1);
        buttonatk2 = LoadButtons.button1_create("", mySkin, WIDTH / 4 + btn_size1 * 5 / 2, HEIGHT / 4 * 3 - btn_size1 * 3 / 2 - btn_size1 / 4 * 3, btn_size1);
        buttonatk3 = LoadButtons.button1_create("", mySkin, WIDTH / 4 + btn_size1 * 5 / 2, HEIGHT / 4 * 3 - btn_size1 * 6 / 2 - btn_size1 / 4 * 3, btn_size1);

        stage.addActor(buttonatk1);
        stage.addActor(buttonatk2);
        stage.addActor(buttonatk3);

        buttonGroup1 = new ButtonGroup(buttonatk1, buttonatk2, buttonatk3);
        buttonGroup1.setMaxCheckCount(1);
        buttonGroup1.setMinCheckCount(0);
        buttonGroup1.setUncheckLast(true);

        buttondef1 = LoadButtons.button1_create("", mySkin, WIDTH / 4 + btn_size1, HEIGHT / 4 * 3 - btn_size1 / 4 * 3, btn_size1);
        buttondef2 = LoadButtons.button1_create("", mySkin, WIDTH / 4 + btn_size1, HEIGHT / 4 * 3 - btn_size1 * 3 / 2 - btn_size1 / 4 * 3, btn_size1);
        buttondef3 = LoadButtons.button1_create("", mySkin, WIDTH / 4 + btn_size1, HEIGHT / 4 * 3 - btn_size1 * 6 / 2 - btn_size1 / 4 * 3, btn_size1);

        stage.addActor(buttondef1);
        stage.addActor(buttondef2);
        stage.addActor(buttondef3);

        buttonGroup2 = new ButtonGroup(buttondef1, buttondef2, buttondef3);
        buttonGroup2.setMaxCheckCount(1);
        buttonGroup2.setMinCheckCount(0);
        buttonGroup2.setUncheckLast(true);

        //character upgrade buttons
        buttonincup = LoadButtons.button2_1_create(mySkin, WIDTH / 20, HEIGHT / 17, btn_size2, character, beforefight);
        buttonatkup = LoadButtons.button2_2_create(mySkin, WIDTH / 20 + btn_size2 / 2 + btn_size1, HEIGHT / 17, btn_size2, character, beforefight);
        buttondefup = LoadButtons.button2_3_create(mySkin, WIDTH / 20 + btn_size2 + btn_size1 * 2, HEIGHT / 17, btn_size2, character, beforefight);

        stage.addActor(buttonincup);
        stage.addActor(buttonatkup);
        stage.addActor(buttondefup);

        //character add atk/def buttons
        buttonaddatkchar = LoadButtons.button3_1_create(mySkin, WIDTH / 20 + btn_size1, HEIGHT / 4, btn_size1, character, beforefight, buttonatk1, buttonatk2, buttonatk3);
        buttonadddefchar = LoadButtons.button3_2_create(mySkin, WIDTH / 20 + btn_size1 + btn_size2, HEIGHT / 4, btn_size1, character, beforefight, buttondef1, buttondef2, buttondef3);

        stage.addActor(buttonaddatkchar);
        stage.addActor(buttonadddefchar);

        //enemy atk/def buttons
        buttonatken1 = LoadButtons.button1_create("", mySkin, WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1, HEIGHT / 4 * 3 - btn_size1 / 4 * 3, btn_size1);
        buttonatken2 = LoadButtons.button1_create("", mySkin, WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1, HEIGHT / 4 * 3 - btn_size1 * 3 / 2 - btn_size1 / 4 * 3, btn_size1);
        buttonatken3 = LoadButtons.button1_create("", mySkin, WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1, HEIGHT / 4 * 3 - btn_size1 * 6 / 2 - btn_size1 / 4 * 3, btn_size1);

        stage.addActor(buttonatken1);
        stage.addActor(buttonatken2);
        stage.addActor(buttonatken3);

        buttondefen1 = LoadButtons.button1_create("", mySkin, WIDTH - (WIDTH / 4 + btn_size1) - btn_size1, HEIGHT / 4 * 3 - btn_size1 / 4 * 3, btn_size1);
        buttondefen2 = LoadButtons.button1_create("", mySkin, WIDTH - (WIDTH / 4 + btn_size1) - btn_size1, HEIGHT / 4 * 3 - btn_size1 * 3 / 2 - btn_size1 / 4 * 3, btn_size1);
        buttondefen3 = LoadButtons.button1_create("", mySkin, WIDTH - (WIDTH / 4 + btn_size1) - btn_size1, HEIGHT / 4 * 3 - btn_size1 * 6 / 2 - btn_size1 / 4 * 3, btn_size1);

        stage.addActor(buttondefen1);
        stage.addActor(buttondefen2);
        stage.addActor(buttondefen3);

        buttonatken1.setTouchable(Touchable.disabled);
        buttonatken2.setTouchable(Touchable.disabled);
        buttonatken3.setTouchable(Touchable.disabled);
        buttondefen1.setTouchable(Touchable.disabled);
        buttondefen2.setTouchable(Touchable.disabled);
        buttondefen3.setTouchable(Touchable.disabled);

        //enemy upgrade buttons
        buttonincupen = LoadButtons.button1_create("inc +" + String.valueOf(enemy.getIncupnum()) + "\n" + String.valueOf(enemy.getIncupcost()) + " gold", mySkin, WIDTH - (WIDTH / 20 + btn_size2 + btn_size1 * 2) - btn_size2, HEIGHT / 17, btn_size2);
        buttonatkupen = LoadButtons.button1_create("atk +" + String.valueOf(enemy.getAtkupnum()) + "\n" + String.valueOf(enemy.getAtkupcost()) + " gold", mySkin, WIDTH - (WIDTH / 20 + btn_size2 / 2 + btn_size1) - btn_size2, HEIGHT / 17, btn_size2);
        buttondefupen = LoadButtons.button1_create("def +" + String.valueOf(enemy.getDefupnum()) + "\n" + String.valueOf(enemy.getDefupcost()) + " gold", mySkin, WIDTH - (WIDTH / 20) - btn_size2, HEIGHT / 17, btn_size2);

        stage.addActor(buttonincupen);
        stage.addActor(buttonatkupen);
        stage.addActor(buttondefupen);

        buttonincupen.setTouchable(Touchable.disabled);
        buttonatkupen.setTouchable(Touchable.disabled);
        buttondefupen.setTouchable(Touchable.disabled);

        //enemy add atk/def buttons
        buttonaddatken = LoadButtons.button1_create("+1 atk" + "\n" + String.valueOf(enemy.getAtkaddcost()) + " gold", mySkin, WIDTH - (WIDTH / 20 + btn_size1 + btn_size2) - btn_size2, HEIGHT / 4, btn_size1);
        buttonadddefen = LoadButtons.button1_create("+1 def" + "\n" + String.valueOf(enemy.getDefaddcost()) + " gold", mySkin, WIDTH - (WIDTH / 20 + btn_size1) - btn_size2, HEIGHT / 4, btn_size1);

        stage.addActor(buttonaddatken);
        stage.addActor(buttonadddefen);

        buttonaddatken.setTouchable(Touchable.disabled);
        buttonadddefen.setTouchable(Touchable.disabled);

        //fight button
        buttonfight = new TextButton("fight", mySkin, "toggle");
        buttonfight.setSize(btn_size2 * 2, btn_size2);
        buttonfight.setPosition(WIDTH / 2 - btn_size2, HEIGHT / 5);
        buttonfight.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
                if (buttonfight.isChecked()) {

                    //enemy actions
                    if (levelnum == 1) {
                        GameChar.fightlvl1(enemyactions, round, character, enemy);
                    }

                    if (levelnum == 2) {
                        GameChar.fightlvl2(enemyactions, round, character, enemy, immune, bonusupgrade, buttonatkup.isChecked(), buttondefup.isChecked());
                    }

                    //fight
                    hpdifchar = 0;
                    golddifchar = character.getInc();
                    hpdifen = 0;
                    golddifen = enemy.getInc();

                    if ((buttonatk1.isChecked()) && (enemyactions[3] != 1)) {
                        if (enemy.getDef() >= character.getAtk()) {
                            hpdifen = hpdifen + 1;
                        } else {
                            hpdifen = hpdifen + (character.getAtk() - enemy.getDef());
                        }
                    }
                    if ((buttonatk2.isChecked()) && (enemyactions[4] != 1)) {
                        if (enemy.getDef() >= character.getAtk()) {
                            hpdifen = hpdifen + 1;
                        } else {
                            hpdifen = hpdifen + (character.getAtk() - enemy.getDef());
                        }
                    }
                    if ((buttonatk3.isChecked()) && (enemyactions[5] != 1)) {
                        if (enemy.getDef() >= character.getAtk()) {
                            hpdifen = hpdifen + 1;
                        } else {
                            hpdifen = hpdifen + (character.getAtk() - enemy.getDef());
                        }
                    }

                    if ((enemyactions[0] == 1) && (!buttondef1.isChecked())) {
                        if (character.getDef() >= enemy.getAtk()) {
                            hpdifchar = hpdifchar + 1;
                        } else {
                            hpdifchar = hpdifchar + (enemy.getAtk() - character.getDef());
                        }
                    }
                    if ((enemyactions[1] == 1) && (!buttondef2.isChecked())) {
                        if (character.getDef() >= enemy.getAtk()) {
                            hpdifchar = hpdifchar + 1;
                        } else {
                            hpdifchar = hpdifchar + (enemy.getAtk() - character.getDef());
                        }
                    }
                    if ((enemyactions[2] == 1) && (!buttondef3.isChecked())) {
                        if (character.getDef() >= enemy.getAtk()) {
                            hpdifchar = hpdifchar + 1;
                        } else {
                            hpdifchar = hpdifchar + (enemy.getAtk() - character.getDef());
                        }
                    }


                    //other
                    if ((!buttonatk1.isChecked()) && (!buttonatk2.isChecked()) && (!buttonatk3.isChecked())) {
                        golddifchar = golddifchar + character.getBonusgold();
                    }

                    if (enemyactions[9] == 0) {
                        golddifen = golddifen + enemy.getBonusgold();
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


                    if (enemyactions[0] == 1) {
                        buttonatken1.setChecked(true);
                    }
                    if (enemyactions[1] == 1) {
                        buttonatken2.setChecked(true);
                    }
                    if (enemyactions[2] == 1) {
                        buttonatken3.setChecked(true);
                    }

                    if (enemyactions[3] == 1) {
                        buttondefen1.setChecked(true);
                    }
                    if (enemyactions[4] == 1) {
                        buttondefen2.setChecked(true);
                    }
                    if (enemyactions[5] == 1) {
                        buttondefen3.setChecked(true);
                    }

                    if (enemyactions[6] == 1) {
                        buttonincupen.setChecked(true);
                    }
                    if (enemyactions[7] == 1) {
                        buttonatkupen.setChecked(true);
                    }
                    if (enemyactions[8] == 1) {
                        buttondefupen.setChecked(true);
                    }

                    if (enemyactions[9] == 2) {
                        buttonaddatken.setChecked(true);
                    }
                    if (enemyactions[10] == 2) {
                        buttonadddefen.setChecked(true);
                    }
                } else {
                    round++;

                    character.setHp(character.getHp() - hpdifchar);
                    character.setGold(character.getGold() + golddifchar);

                    if (levelnum != 2) {
                        enemy.setHp(enemy.getHp() - hpdifen);
                        enemy.setGold(enemy.getGold() + golddifen);
                    }

                    if (levelnum == 2) {
                        if (hpdifen > 0) {
                            if (immune == 0) {
                                enemy.setHp(enemy.getHp() - hpdifen);
                                immune = 1;
                            }
                            else {
                                immune = 0;
                            }
                        }
                        else {
                            if (immune == 1) {
                                immune = 0;
                            }
                        }

                        if (bonusupgrade == 0) {
                            bonusupgrade = 1;
                        } else {
                            bonusupgrade = 0;
                        }

                        enemy.setGold(enemy.getGold() + golddifen);
                    }


                    beforefight = 1;

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

                    beforefight = 0;

                    if (character.getHp() <= 0) {
                        game.setScreen(new GameOverScreen(game, 0));
                    }
                    if ((enemy.getHp() <= 0) && (character.getHp() > 0)) {
                        fightend = 1;
                        dialogue2 = 2;
                        state = 2;
                    }
                }
            }
        });
        stage.addActor(buttonfight);


        buttonpause = new TextButton("pause", mySkin);
        buttonpause.setSize(btn_size2 * 3 / 2, btn_size2 * 2 / 3);
        buttonpause.setPosition(WIDTH / 2 - btn_size2 * 3 / 4, HEIGHT / 15);
        buttonpause.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                pause();
                return true;
            }
        });
        stage.addActor(buttonpause);

        buttonresume = new TextButton("resume", mySkin);
        buttonresume.setSize(btn_size2 * 3 / 2, btn_size2 * 2 / 3);
        buttonresume.setPosition(WIDTH / 2 - btn_size2 * 3 / 4, HEIGHT / 15);
        buttonresume.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                resume();
                return true;
            }
        });
        stagepause.addActor(buttonresume);

        buttonmenu = new TextButton("menu", mySkin);
        buttonmenu.setSize(btn_size2 * 3 / 2, btn_size2 * 2 / 3);
        buttonmenu.setPosition(WIDTH / 20, HEIGHT / 15);
        buttonmenu.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
                return true;
            }
        });
        stagepause.addActor(buttonmenu);

        buttonnext = new TextButton("next", mySkin);
        buttonnext.setSize(btn_size2 * 3 / 2, btn_size2 * 2 / 3);
        buttonnext.setPosition(WIDTH  - WIDTH / 20 - btn_size2 * 3 / 2, HEIGHT / 15);
        buttonnext.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            if (dialogue1 < dialogue1max) {
                dialogue1++;
            }
            if (dialogue1 == dialogue1max) {
                dialogue1 = dialogue1max + 1;
                pause();
            }
            if (dialogue1 == dialogue1max + 1) {
                dialogue2++;
            }
            if (dialogue2 == dialogue2max) {
                if (levelnum == 1) {
                    game.setScreen(new GameScreen(game, levelnum + 1, character.getAtk(), character.getDef(), 3, 4));
                }
                if (levelnum == 2) {
                    game.setScreen(new GameOverScreen(game, 1));
                }
            }
            }
        });
        stagepause.addActor(buttonnext);
    }

    public void addlabels() {
        label1Style1 = new Label.LabelStyle();
        myFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        label1Style1.font = myFont;
        label1Style1.fontColor = Color.BLUE;

        label1Style2 = new Label.LabelStyle();
        myFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        label1Style2.font = myFont;
        label1Style2.fontColor = Color.RED;

        label1Style3 = new Label.LabelStyle();
        myFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        label1Style3.font = myFont;
        label1Style3.fontColor = Color.GREEN;

        label1Style4 = new Label.LabelStyle();
        myFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        label1Style4.font = myFont;
        label1Style4.fontColor = Color.WHITE;

        //character
        labelhpchar = LoadLabels.label_create(String.valueOf(character.getHp()), WIDTH / 20 + btn_size1 / 3, HEIGHT - (HEIGHT / 6), 50, 30, label1Style1, character);
        labelhpchardif = LoadLabels.label_create("-" + String.valueOf(hpdifchar), WIDTH / 20 + btn_size1 / 3, HEIGHT - (HEIGHT / 8), 50, 30, label1Style2, character);
        labelgoldchar = LoadLabels.label_create(String.valueOf(character.getGold()), WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3, HEIGHT - (HEIGHT / 6), 50, 30, label1Style1, character);
        labelgoldchardif = LoadLabels.label_create("+" + String.valueOf(golddifchar), WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3, HEIGHT - (HEIGHT / 8), 50, 30, label1Style3, character);
        labelincchar = LoadLabels.label_create(String.valueOf(character.getInc()), WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3, HEIGHT - (HEIGHT / 6), 50, 30, label1Style1, character);
        labelatkchar = LoadLabels.label_create(String.valueOf(character.getAtk()), WIDTH / 4 + btn_size1 * 5 / 2 + 30, HEIGHT / 4 * 3 + btn_size1 / 2, 50, 30, label1Style1, character);
        labeldefchar = LoadLabels.label_create(String.valueOf(character.getDef()), WIDTH / 4 + btn_size1 + 30, HEIGHT / 4 * 3 + btn_size1 / 2, 50, 30, label1Style1, character);

        stage.addActor(labelhpchar);
        stage.addActor(labelhpchardif);
        stage.addActor(labelgoldchar);
        stage.addActor(labelgoldchardif);
        stage.addActor(labelincchar);
        stage.addActor(labelatkchar);
        stage.addActor(labeldefchar);

        //enemy
        labelhpen = LoadLabels.label_create(String.valueOf(enemy.getHp()), WIDTH - (WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3) - 50 + 40, HEIGHT - (HEIGHT / 6), 50, 30, label1Style1, character);
        labelhpendif = LoadLabels.label_create("-" + String.valueOf(hpdifen), WIDTH - (WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3) - 50 + 40, HEIGHT - (HEIGHT / 8), 50, 30, label1Style2, character);
        labelgolden = LoadLabels.label_create(String.valueOf(enemy.getGold()), WIDTH - (WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3) - 50 + 40, HEIGHT - (HEIGHT / 6), 50, 30, label1Style1, character);
        labelgoldendif = LoadLabels.label_create("+" + String.valueOf(golddifen), WIDTH - (WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3) - 50 + 40, HEIGHT - (HEIGHT / 8), 50, 30, label1Style3, character);
        labelincen = LoadLabels.label_create(String.valueOf(enemy.getInc()), WIDTH - (WIDTH / 20 + btn_size1 / 3) - 50 + 40, HEIGHT - (HEIGHT / 6), 50, 30, label1Style1, character);
        labelatken = LoadLabels.label_create(String.valueOf(enemy.getAtk()), WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1 + 30, HEIGHT / 4 * 3 + btn_size1 / 2, 50, 30, label1Style1, character);
        labeldefen = LoadLabels.label_create(String.valueOf(enemy.getDef()), WIDTH - (WIDTH / 4 + btn_size1) - btn_size1 + 30, HEIGHT / 4 * 3 + btn_size1 / 2, 50, 30, label1Style1, character);

        stage.addActor(labelhpen);
        stage.addActor(labelhpendif);
        stage.addActor(labelgolden);
        stage.addActor(labelgoldendif);
        stage.addActor(labelincen);
        stage.addActor(labelatken);
        stage.addActor(labeldefen);

        //round
        labelhpen = LoadLabels.label_create("round" + String.valueOf(round), WIDTH / 2 - 50 / 2, HEIGHT - btn_size1 * 3 / 2, 50, 30, label1Style1, character);
        stage.addActor(labelround);

        //story
        labelstory1 = new Label("", label1Style1);
        labelstory1.setSize(50, 30);
        labelstory1.setAlignment(Align.left);
        stagepause.addActor(labelstory1);

        labelstory2 = new Label("", label1Style1);
        labelstory2.setSize(50, 30);
        labelstory2.setAlignment(Align.left);
        stagepause.addActor(labelstory2);

        //story stats
        labelatkcharstory = LoadLabels.label_create("atk " + String.valueOf(character.getAtk()), WIDTH / 20 + btn_size1 / 3, HEIGHT - (HEIGHT / 6), 50, 30, label1Style4, character);
        labeldefcharstory = LoadLabels.label_create("def " + String.valueOf(character.getDef()), WIDTH / 20 + btn_size1 * 3, HEIGHT - (HEIGHT / 6), 50, 30, label1Style4, character);

        stagepause.addActor(labelatkcharstory);
        stagepause.addActor(labeldefcharstory);

        //enemy about
        labelenemyabout1 = LoadLabels.label_create("", WIDTH / 2 - btn_size1 * 3, HEIGHT - (HEIGHT / 6), 50, 30, label1Style1, character);

        stagepause.addActor(labelenemyabout1);

        //lvl2 bonus immune labels
        labelbonusinc = LoadLabels.label_create("bonus", WIDTH - (WIDTH / 20 + btn_size2 + btn_size1 * 2) - btn_size2 * 2 / 3 - 10, HEIGHT / 17 - btn_size1 / 2, 50, 30, label1Style1, character);
        labelbonusatk = LoadLabels.label_create("bonus", WIDTH - (WIDTH / 20 + btn_size2 / 2 + btn_size1) - btn_size2 * 2 / 3 - 10, HEIGHT / 17 - btn_size1 / 2, 50, 30, label1Style1, character);
        labelbonusdef = LoadLabels.label_create("bonus", WIDTH - (WIDTH / 20) - btn_size2 * 2 / 3 - 10, HEIGHT / 17 - btn_size1 / 2, 50, 30, label1Style1, character);

        stage.addActor(labelbonusinc);
        stage.addActor(labelbonusatk);
        stage.addActor(labelbonusdef);

        labelimmune = LoadLabels.label_create("immune", WIDTH - (WIDTH / 20 + btn_size1 * 4) - 30 + 40, HEIGHT - (HEIGHT / 8), 50, 30, label1Style1, character);

        stage.addActor(labelimmune);
    }

    public void update (float deltaTime) {
        if (state == 1) {
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
                if (((character.getGold() < character.getIncupcost()) && (buttonincup.isChecked() == false)) || (buttonatkup.isChecked() && buttondefup.isChecked())) {
                    buttonincup.setTouchable(Touchable.disabled);
                } else {
                    buttonincup.setTouchable(Touchable.enabled);
                }
            }

            if (!buttondisable) {
                if (((character.getGold() < character.getAtkupcost()) && (buttonatkup.isChecked() == false)) || (buttonincup.isChecked() && buttondefup.isChecked())) {
                    buttonatkup.setTouchable(Touchable.disabled);
                } else {
                    buttonatkup.setTouchable(Touchable.enabled);
                }
            }

            if (!buttondisable) {
                if (((character.getGold() < character.getDefupcost()) && (buttondefup.isChecked() == false)) || (buttonincup.isChecked() && buttonatkup.isChecked())) {
                    buttondefup.setTouchable(Touchable.disabled);
                } else {
                    buttondefup.setTouchable(Touchable.enabled);
                }
            }


            if (!buttondisable) {
                if ((character.getGold() < character.getAtkaddcost()) && (buttonaddatkchar.isChecked() == false)) {
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
                if ((character.getGold() < character.getDefaddcost()) && (buttonadddefchar.isChecked() == false)) {
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

            if ((!buttonfight.isChecked()) || (hpdifchar == 0)) {
                labelhpchardif.setVisible(false);
            }
            else {
                labelhpchardif.setVisible(true);
            }

            if ((!buttonfight.isChecked()) || (golddifchar == 0)) {
                labelgoldchardif.setVisible(false);
            }
            else {
                labelgoldchardif.setVisible(true);
            }

            if ((!buttonfight.isChecked()) || (hpdifen == 0) || (immune == 1)) {
                labelhpendif.setVisible(false);
            }
            else {
                labelhpendif.setVisible(true);
            }

            if ((!buttonfight.isChecked()) || (golddifen == 0)) {
                labelgoldendif.setVisible(false);
            }
            else {
                labelgoldendif.setVisible(true);
            }

            if (enemyactions[12] == 1 && buttonfight.isChecked()) {
                labelbonusinc.setVisible(true);
            }
            else {
                labelbonusinc.setVisible(false);
            }

            if (enemyactions[12] == 2 && buttonfight.isChecked()) {
                labelbonusatk.setVisible(true);
            }
            else {
                labelbonusatk.setVisible(false);
            }

            if (enemyactions[12] == 3 && buttonfight.isChecked()) {
                labelbonusdef.setVisible(true);
            }
            else {
                labelbonusdef.setVisible(false);
            }

            if (immune == 1 && buttonfight.isChecked()) {
                labelimmune.setVisible(true);
            }
            else {
                labelimmune.setVisible(false);
            }
        }
        else {
            Gdx.input.setInputProcessor(stagepause);
            if (state == 2) {
                //lvl1
                if (levelnum == 1) {
                    //prestory
                    if (dialogue1 == 1) {
                        labelstory1.setText("rjulf-nj lfdyj" + "\n" +
                                "yf Ptvkt ;bkb ldt hfcs^" + "\n" +
                                "vjycnhs b k.lb/ Yj jlyf;ls" + "\n" +
                                "vt;le ybvb dcgs[yekf djqyf?" + "\n" + "\n" +
                                "gj,tle d rjnjhjq jlth;fkb" + "\n" +
                                "gjcktlybt/ Ctvm dtkbxfqib[" + "\n" +
                                "xtkjdtxtcrb[ vfujd pfgtxfnfkb" + "\n" +
                                "vjycnhjd gjl ptvk`q c gjvjom.");
                        labelstory1.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 4));
                    }
                    if (dialogue1 == 2) {
                        labelstory1.setText("oi a ya ne pomenyal raskladky" + "\n" + "\n" +
                                "ladno davaika eshe raz" + "\n" +
                                "rasskazhy zachem tebe" + "\n" +
                                "drat`sya s etimi monstra..." + "\n" + "\n" +
                                "ei a eto kto?" + "\n");
                        labelstory1.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 4));
                    }

                    //afterstory
                    if (dialogue2 == 2) {
                        labelstory1.setText("pohozhe pered tem kak" + "\n" +
                                "tu yspel nanesti poslednii" + "\n" +
                                "ydar medyak kyvurknylsya" + "\n" +
                                "i ischez" + "\n" + "\n" +
                                "ny da ladno" + "\n" +
                                "tu neploho postaralsya" + "\n" +
                                "a teper` pora v pyt`" + "\n");
                        labelstory1.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 4));
                    }
                }

                //lvl2
                if (levelnum == 2) {
                    if (dialogue1 == 1) {
                        labelstory1.setText("mne kazhetsya 4to posle" + "\n" +
                                "dolgih stranstvvii tu oslab" + "\n" + "\n" +
                                "na glaz ya bu skazal chto" + "\n" +
                                "primerno raza tak v 4");
                        labelstory1.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 4) + 20);
                    }
                    if (dialogue1 == 2) {
                        if (statslvl1 == 0) {
                            if (character.getAtk() / 4 >= 48) {
                                statsbalance = rand.nextInt(3) + 1;
                                character.setAtk(character.getAtk() / 4 - statsbalance);
                            }
                            else {
                                if (character.getAtk() / 4 < 40) {
                                    statsbalance = rand.nextInt(3) + 1;
                                    character.setAtk(character.getAtk() / 4 + statsbalance);
                                }
                                else {
                                    character.setAtk(character.getAtk() / 4);
                                }
                            }

                            if (character.getDef() / 4 >= 40) {
                                statsbalance = rand.nextInt(3) + 1;
                                character.setDef(character.getDef() / 4 - statsbalance);
                            }
                            else {
                                if (character.getDef() / 4 < 30) {
                                    statsbalance = rand.nextInt(3) + 1;
                                    character.setDef(character.getDef() / 4 + statsbalance);
                                }
                                else {
                                    character.setDef(character.getDef() / 4);
                                }
                            }

                            statslvl1 = 1;
                        }
                        labelstory2.setText("no ne beda y menya gde-to bulo" + "\n" +
                                "zel`e kotoroe vernet tebe sil..." + "\n" + "\n" +
                                "stoi tu tozhe eto slushal!?");
                        labelstory2.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 3) - 70);
                    }

                    //afterstory
                    if (dialogue2 == 2) {
                        labelstory2.setText("");

                        labelstory1.setText("vay ne dymal chto tu spravishsya" + "\n" + "\n" +
                                "a ya yzhe vupil zel`e i hotel" + "\n" +
                                "brosit`sya tebe na pomosh`" + "\n" + "\n" +
                                "ny nichego vrode tu opyat` " + "\n" +
                                "vernylsya v formy");
                        labelstory1.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 4));
                    }

                    if (dialogue2 == 3) {
                        labelstory2.setText("");

                        labelstory1.setText("oi a chto eto tam" + "\n" + "\n" +
                                "yps kazhetsya y tebya propalo" + "\n" +
                                "5 ataki i 5 broni");
                        labelstory1.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 4));
                    }
                }

                if (fightend == 0) {
                    labelatkcharstory.setVisible(true);
                    labeldefcharstory.setVisible(true);
                }

                labelenemyabout1.setVisible(false);
                labelstory1.setVisible(true);
                labelstory2.setVisible(true);

                buttonresume.setVisible(false);
                buttonnext.setVisible(true);
            }
            else {
                //lvl1 enemy about
                if (levelnum == 1) {
                    labelenemyabout1.setText("medyak" + "\n" + "\n" +
                            "s vidy nichem ne vudelyaetsya" + "\n" +
                            "smotri ne proigrai emy");
                    labelenemyabout1.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 4));
                }

                if (levelnum == 2) {
                    labelenemyabout1.setText("dorfi" + "\n" + "\n" +
                            "ochen` navorochennui tip lychshe" + "\n" +
                            "razobrat`sya s nim pobustree" + "\n" + "\n" +
                            "esli polychaet yron v sledyy`shem" + "\n" +
                            "raynde y nego immynitet" + "\n" + "\n" +
                            "cherez raynd mozhet potratit`" + "\n" +
                            "odno pole zashitu na apgreid");
                    labelenemyabout1.setPosition(WIDTH / 2 - btn_size1 * 4, HEIGHT - (HEIGHT / 3));
                }

                labelatkcharstory.setVisible(false);
                labeldefcharstory.setVisible(false);
                labelstory1.setVisible(false);
                labelstory2.setVisible(false);
                labelenemyabout1.setVisible(true);

                buttonresume.setVisible(true);
                buttonnext.setVisible(false);
            }
        }
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        if (state == 2) {

            game.batch.disableBlending();
            game.batch.begin();
            if (fightend == 0) {
                game.batch.draw(Assets.backgroundlvlmid, 0, 0, WIDTH, HEIGHT);
            }
            else {
                if (levelnum == 1) {
                    game.batch.draw(Assets.backgroundRegionlvl1, 0, 0, WIDTH, HEIGHT);
                }
                if (levelnum == 2) {
                    game.batch.draw(Assets.backgroundRegionlvl2, 0, 0, WIDTH, HEIGHT);
                }
            }
            game.batch.draw(Assets.backgroundRegion, WIDTH / 3 - 100 / 2, HEIGHT / 3, WIDTH / 3 + 100, HEIGHT / 2 + btn_size2);
            game.batch.end();


            game.batch.enableBlending();
            game.batch.begin();
            if (character.getDef() / 4 < 40) {
                game.batch.draw(Assets.character1, WIDTH / 13 - 30, HEIGHT / 2 - 40, 180, 192);
            }
            if ((character.getDef() / 4 < 80) && (character.getDef() / 4 >= 40)) {
                game.batch.draw(Assets.character2, WIDTH / 13 - 35, HEIGHT / 2 - 40, 180, 240);
            }
            if (character.getDef() / 4 >= 80) {
                game.batch.draw(Assets.character3, WIDTH / 13 - 35, HEIGHT / 2 - 40, 180, 240);
            }

            if (character.getAtk() / 4 < 60) {
                game.batch.draw(Assets.charweapon1, WIDTH / 13 + 50, HEIGHT / 2 + 25, 0, 0, 64, 168, 1, 1, -70);
            }
            if ((character.getAtk() / 4 < 120) && (character.getAtk() / 4 >= 60)) {
                game.batch.draw(Assets.charweapon2, WIDTH / 13 + 50, HEIGHT / 2 + 25, 0, 0, 64, 160, 1, 1, -70);
            }
            if (character.getAtk() / 4 >= 120) {
                game.batch.draw(Assets.charweapon3, WIDTH / 13 + 50, HEIGHT / 2 + 25, 0, 0, 70, 203, 1, 1, -70);
            }
            game.batch.end();

        }
        else {
            if (state == 1) {
                if (levelnum == 1) {
                    game.batch.disableBlending();
                    game.batch.begin();
                    game.batch.draw(Assets.backgroundRegionlvl1, 0, 0, WIDTH, HEIGHT);
                    game.batch.end();

                    game.batch.enableBlending();
                    game.batch.begin();
                    game.batch.draw(Assets.enemylvl1, WIDTH - (WIDTH / 13) - 200 + 30, HEIGHT / 2 - 40, 150, 240);
                    game.batch.end();
                }

                if (levelnum == 2) {
                    game.batch.disableBlending();
                    game.batch.begin();
                    game.batch.draw(Assets.backgroundRegionlvl2, 0, 0, WIDTH, HEIGHT);
                    game.batch.end();

                    game.batch.enableBlending();
                    game.batch.begin();
                    game.batch.draw(Assets.enemylvl2, WIDTH - (WIDTH / 13) - 200 + 30, HEIGHT / 2 - 40, 150, 240);
                    game.batch.end();
                }

                game.batch.enableBlending();
                game.batch.begin();

                game.batch.draw(Assets.hpicon, WIDTH / 20 + btn_size1 / 3 - 35, HEIGHT - (HEIGHT / 6), 32, 32);
                game.batch.draw(Assets.hpicon, WIDTH - (WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3) - 50 + 40 - 35, HEIGHT - (HEIGHT / 6), 32, 32);

                game.batch.draw(Assets.goldicon, WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3 - 35, HEIGHT - (HEIGHT / 6), 32, 32);
                game.batch.draw(Assets.goldicon, WIDTH - (WIDTH / 20 + btn_size1 * 3 / 2 + btn_size1 / 3) - 50 + 40 - 35, HEIGHT - (HEIGHT / 6), 32, 32);

                game.batch.draw(Assets.incicon, WIDTH / 20 + btn_size1 * 3 + btn_size1 / 3 - 35, HEIGHT - (HEIGHT / 6), 32, 32);
                game.batch.draw(Assets.incicon, WIDTH - (WIDTH / 20 + btn_size1 / 3) - 50 + 40 - 35, HEIGHT - (HEIGHT / 6), 32, 32);

                game.batch.draw(Assets.atkicon, WIDTH / 4 + btn_size1 * 5 / 2 - 5, HEIGHT / 4 * 3 + btn_size1 / 2 - 1, 32, 32);
                game.batch.draw(Assets.atkicon, WIDTH - (WIDTH / 4 + btn_size1 * 5 / 2) - btn_size1 - 5, HEIGHT / 4 * 3 + btn_size1 / 2 - 1, 32, 32);

                game.batch.draw(Assets.deficon, WIDTH / 4 + btn_size1 - 5, HEIGHT / 4 * 3 + btn_size1 / 2 - 1, 32, 32);
                game.batch.draw(Assets.deficon, WIDTH - (WIDTH / 4 + btn_size1) - btn_size1 - 5, HEIGHT / 4 * 3 + btn_size1 / 2 - 1, 32, 32);


                if (character.getDef() / 4 < 40) {
                    game.batch.draw(Assets.character1, WIDTH / 13 - 30, HEIGHT / 2 - 40, 180, 192);
                }
                if ((character.getDef() / 4 < 80) && (character.getDef() / 4 >= 40)) {
                    game.batch.draw(Assets.character2, WIDTH / 13 - 35, HEIGHT / 2 - 40, 180, 240);
                }
                if (character.getDef() / 4 >= 80) {
                    game.batch.draw(Assets.character3, WIDTH / 13 - 35, HEIGHT / 2 - 40, 180, 240);
                }

                if (character.getAtk() / 4 < 60) {
                    game.batch.draw(Assets.charweapon1, WIDTH / 13 + 50, HEIGHT / 2 + 25, 0, 0, 64, 168, 1, 1, -70);
                }
                if ((character.getAtk() / 4 < 120) && (character.getAtk() / 4 >= 60)) {
                    game.batch.draw(Assets.charweapon2, WIDTH / 13 + 50, HEIGHT / 2 + 25, 0, 0, 64, 160, 1, 1, -70);
                }
                if (character.getAtk() / 4 >= 120) {
                    game.batch.draw(Assets.charweapon3, WIDTH / 13 + 50, HEIGHT / 2 + 25, 0, 0, 70, 203, 1, 1, -70);
                }
                game.batch.end();
            }
            else {
                if (levelnum == 1) {
                    game.batch.disableBlending();
                    game.batch.begin();
                    game.batch.draw(Assets.backgroundRegionlvl1, 0, 0, WIDTH, HEIGHT);
                    game.batch.end();

                    game.batch.enableBlending();
                    game.batch.begin();
                    game.batch.draw(Assets.backgroundRegion, WIDTH / 3 - 100 / 2, HEIGHT / 3, WIDTH / 3 + 100, HEIGHT / 2 + btn_size2);
                    game.batch.draw(Assets.enemylvl1, WIDTH - (WIDTH / 13) - 200 + 30, HEIGHT / 2 - 40, 150, 240);
                    game.batch.end();
                }

                if (levelnum == 2) {
                    game.batch.disableBlending();
                    game.batch.begin();
                    game.batch.draw(Assets.backgroundRegionlvl2, 0, 0, WIDTH, HEIGHT);
                    game.batch.end();

                    game.batch.enableBlending();
                    game.batch.begin();
                    game.batch.draw(Assets.backgroundRegion, WIDTH / 3 - 100 / 2, HEIGHT / 3, WIDTH / 3 + 100, HEIGHT / 2 + btn_size2);
                    game.batch.draw(Assets.enemylvl2, WIDTH - (WIDTH / 13) - 200 + 30, HEIGHT / 2 - 40, 150, 240);
                    game.batch.end();
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();

        if (state == 1) {
            labelhpchar.setText(String.valueOf(character.getHp()));
            labelhpchardif.setText("-" + String.valueOf(hpdifchar));
            labelgoldchar.setText(String.valueOf(character.getGold()));
            labelgoldchardif.setText("+" + String.valueOf(golddifchar));
            labelincchar.setText(String.valueOf(character.getInc()));
            labelatkchar.setText(String.valueOf(character.getAtk()));
            labeldefchar.setText(String.valueOf(character.getDef()));


            labelhpen.setText(String.valueOf(enemy.getHp()));
            labelhpendif.setText("-" + String.valueOf(hpdifen));
            labelgolden.setText(String.valueOf(enemy.getGold()));
            labelgoldendif.setText("+" + String.valueOf(golddifen));
            labelincen.setText(String.valueOf(enemy.getInc()));
            labelatken.setText(String.valueOf(enemy.getAtk()));
            labeldefen.setText(String.valueOf(enemy.getDef()));

            labelround.setText("round" + String.valueOf(round));

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
        else {
            labelatkcharstory.setText("atk " + String.valueOf(character.getAtk()));
            labeldefcharstory.setText("def " + String.valueOf(character.getDef()));

            stagepause.act(Gdx.graphics.getDeltaTime());
            stagepause.draw();
        }
    }

    @Override
    public void pause() {
        state = 0;
    }

    public void resume() {
        if (state != 1) {
            state = 1;
            Gdx.input.setInputProcessor(stage);
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, WIDTH, HEIGHT);
        camera.update();
        stage.getViewport().update(width, height, true);
        stagepause.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        stage.dispose();
        stagepause.dispose();
        mySkin.dispose();
        myFont.dispose();
    }
}