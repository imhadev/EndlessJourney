package com.sa.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.*;

public class MainClass extends Game {

	public SpriteBatch batch;
	public static final int v_width = 400;
	public static final int v_height = 208;

	public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    /*public static int HEIGHT = Gdx.app.getGraphics().getHeight();
    public static int WIDTH = Gdx.app.getGraphics().getWidth();*/
	
	@Override
	public void create() {
		batch = new SpriteBatch();
        Assets.load();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

		Assets.background.dispose();
		Assets.background1.dispose();
		Assets.backgroundlvl1.dispose();
		Assets.backgroundlvl2.dispose();
		Assets.backgroundlvl3.dispose();
		Assets.backgroundlvl4.dispose();
		Assets.backgroundlvl5.dispose();
		Assets.backgroundlvlmid.dispose();
		Assets.items.dispose();
		Assets.gui.dispose();
		Assets.characters.dispose();
		Assets.font.dispose();
	}
}
