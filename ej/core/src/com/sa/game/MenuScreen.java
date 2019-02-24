package com.sa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.sa.game.MainClass.HEIGHT;
import static com.sa.game.MainClass.WIDTH;

public class MenuScreen extends ScreenAdapter {

    private MainClass game;

    private Stage stage;
    private Skin mySkin;

    private Button buttongame;
    private Button buttonhelp;

    private OrthographicCamera camera;
    Vector3 touchPoint;


    public MenuScreen (MainClass game) {
        this.game = game;

        camera = new OrthographicCamera(MainClass.v_width, MainClass.v_height);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        touchPoint = new Vector3();

        addbuttons();
    }

    public void addbuttons() {
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        Gdx.input.setInputProcessor(stage);

        mySkin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));

        buttongame = new TextButton("play", mySkin);
        buttongame.setSize(WIDTH / 10, HEIGHT / 10);
        buttongame.setPosition(WIDTH / 2 - WIDTH / 20, HEIGHT / 2 - HEIGHT / 20);
        buttongame.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game, 1, 7, 3));
                return true;
            }
        });
        stage.addActor(buttongame);

        buttonhelp = new TextButton("help", mySkin);
        buttonhelp.setSize(WIDTH / 10, HEIGHT / 10);
        buttonhelp.setPosition(WIDTH / 2 - WIDTH / 20, HEIGHT / 2 - 4 * HEIGHT / 20);
        buttonhelp.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HelpScreen(game));
                return true;
            }
        });
        stage.addActor(buttonhelp);
    }

    public void update() {

    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(Assets.backgroundRegion, 0, 0, WIDTH, HEIGHT);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.gamenameregion, WIDTH / 2 - 490 / 2, HEIGHT / 4 * 3 - 50, 490, 49);
        game.batch.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void pause() {
    }

    @Override
    public void hide () {
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, WIDTH, HEIGHT);
        camera.update();
        stage.getViewport().update(width, height, true);
    }
}