package com.sa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import static com.sa.game.MainClass.HEIGHT;
import static com.sa.game.MainClass.WIDTH;

public class MenuScreen extends ScreenAdapter {

    private MainClass game;

    private OrthographicCamera camera;
    Vector3 touchPoint;

    Rectangle playBounds;

    public MenuScreen (MainClass game) {
        this.game = game;

        camera = new OrthographicCamera(MainClass.v_width, MainClass.v_height);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        touchPoint = new Vector3();

        playBounds = new Rectangle(WIDTH / 2 - 30, HEIGHT / 2 - 9, 60, 18);
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new GameScreen(game, 1, 7, 3));
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

        game.batch.draw(Assets.mainMenu, WIDTH / 2 - 30, HEIGHT / 2 - 9, 60, 18);

        game.batch.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    @Override
    public void pause() {
    }

    @Override
    public void hide () {
    }
}
