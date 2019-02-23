package com.sa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import static com.sa.game.MainClass.HEIGHT;
import static com.sa.game.MainClass.WIDTH;

public class GameOverScreen extends ScreenAdapter {

    private MainClass game;

    private OrthographicCamera camera;
    Vector3 touchPoint;

    Rectangle toMenu;

    private int wincaseg;


    private Stage stage;

    private Label.LabelStyle label1Style;
    private BitmapFont myFont;
    private Label labelwin;


    public GameOverScreen (MainClass game, int wincase) {
        this.game = game;

        wincaseg = wincase;

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        touchPoint = new Vector3();

        toMenu = new Rectangle(WIDTH / 2 - 80, HEIGHT / 2 - 48, 160, 96);


        stage = new Stage();

        label1Style = new Label.LabelStyle();
        myFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.BLUE;

        labelwin = new Label("You Won", label1Style);
        labelwin.setSize(50, 30);
        labelwin.setPosition(WIDTH / 2 - 50 /2, HEIGHT / 2 - 30 / 2);
        labelwin.setAlignment(Align.center);
        stage.addActor(labelwin);
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (toMenu.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new MenuScreen(game));
                return;
            }
        }
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

        if (wincaseg == 0) {
            game.batch.draw(Assets.gameOver, WIDTH / 2 - 80, HEIGHT / 2 - 48, 160, 96);
        }

        game.batch.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();

        if (wincaseg == 0) {
            labelwin.setVisible(false);
        }
        else {
            labelwin.setVisible(true);
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void pause() {
    }

    @Override
    public void hide() {
        stage.dispose();
        myFont.dispose();
    }
}