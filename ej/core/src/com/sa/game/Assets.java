package com.sa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.sa.game.MainClass.HEIGHT;
import static com.sa.game.MainClass.WIDTH;

public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;

    public static Texture backgroundlvl1;
    public static TextureRegion backgroundRegionlvl1;

    public static Texture items;
    public static TextureRegion mainMenu;
    public static TextureRegion gameOver;

    public static Texture gui;
    public static TextureRegion hpicon;
    public static TextureRegion goldicon;
    public static TextureRegion incicon;
    public static TextureRegion atkicon;
    public static TextureRegion deficon;

    public static Texture characters;
    public static TextureRegion character;
    public static TextureRegion enemylvl1;

    public static BitmapFont font;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        background = new Texture(Gdx.files.internal("background.png"));
        backgroundRegion = new TextureRegion(background, 0, 0, 400, 208);

        backgroundlvl1 = new Texture(Gdx.files.internal("q1.jpg"));
        backgroundRegionlvl1 = new TextureRegion(backgroundlvl1, 0, 0, 1400, 768);

        items = loadTexture("items.png");
        mainMenu = new TextureRegion(items, 90, 224, 120, 36);
        gameOver = new TextureRegion(items, 352, 256, 160, 96);

        gui = loadTexture("gui.png");
        hpicon = new TextureRegion(gui, 480, 384, 32, 32);
        goldicon = new TextureRegion(gui, 320, 384, 32, 32);
        incicon = new TextureRegion(gui, 416, 384, 32, 32);
        atkicon = new TextureRegion(gui, 288, 160, 32, 32);
        deficon = new TextureRegion(gui, 32, 192, 32, 32);

        characters = loadTexture("characters.png");
        character = new TextureRegion(characters, 192, 236, 15, 20);
        enemylvl1 = new TextureRegion(characters, 102, 182, 20, 25);

        font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
    }
}
