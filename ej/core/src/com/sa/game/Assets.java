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
    public static Texture background1;
    public static TextureRegion backgroundRegion1;

    public static Texture backgroundlvl1;
    public static TextureRegion backgroundRegionlvl1;
    public static Texture backgroundlvl2;
    public static TextureRegion backgroundRegionlvl2;
    public static Texture backgroundlvl3;
    public static TextureRegion backgroundRegionlvl3;
    public static Texture backgroundlvl4;
    public static TextureRegion backgroundRegionlvl4;
    public static Texture backgroundlvl5;
    public static TextureRegion backgroundRegionlvl5;
    public static Texture backgroundlvlmid;
    public static TextureRegion backgroundRegionlvlmid;

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
    public static TextureRegion character1;
    public static TextureRegion character2;
    public static TextureRegion character3;

    public static TextureRegion enemylvl1;
    public static TextureRegion enemylvl2;

    public static Texture weapon;
    public static TextureRegion charweapon1;
    public static TextureRegion charweapon2;
    public static TextureRegion charweapon3;

    public static Texture gamename;
    public static TextureRegion gamenameregion;

    public static BitmapFont font;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        gamename = new Texture(Gdx.files.internal("gamename.png"));
        gamenameregion = new TextureRegion(gamename, 0, 0, 490, 49);

        background = new Texture(Gdx.files.internal("background.png"));
        backgroundRegion = new TextureRegion(background, 0, 0, 400, 208);
        background1 = new Texture(Gdx.files.internal("background1.png"));
        backgroundRegion1 = new TextureRegion(background1, 0, 0, 192, 192);

        backgroundlvl1 = new Texture(Gdx.files.internal("lvl1.jpg"));
        backgroundRegionlvl1 = new TextureRegion(backgroundlvl1, 0, 0, 1400, 768);
        backgroundlvl2 = new Texture(Gdx.files.internal("lvl2.png"));
        backgroundRegionlvl2 = new TextureRegion(backgroundlvl2, 0, 0, 1920, 1080);
        backgroundlvl3 = new Texture(Gdx.files.internal("lvl3.png"));
        backgroundRegionlvl3 = new TextureRegion(backgroundlvl3, 0, 0, 1920, 1200);
        backgroundlvl4 = new Texture(Gdx.files.internal("lvl4.png"));
        backgroundRegionlvl4 = new TextureRegion(backgroundlvl4, 0, 0, 272, 160);
        backgroundlvl5 = new Texture(Gdx.files.internal("lvl5.jpg"));
        backgroundRegionlvl5 = new TextureRegion(backgroundlvl5, 0, 0, 1280, 720);
        backgroundlvlmid = new Texture(Gdx.files.internal("midback.jpg"));
        backgroundRegionlvlmid = new TextureRegion(backgroundlvlmid, 0, 0, 1920, 1080);

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
        character1 = new TextureRegion(characters, 161, 240, 15, 16);
        character2 = new TextureRegion(characters, 192, 236, 15, 20);
        character3 = new TextureRegion(characters, 240, 236, 15, 20);
        
        enemylvl1 = new TextureRegion(characters, 35, 224, 10, 16);
        enemylvl2 = new TextureRegion(characters, 51, 192, 10, 16);

        weapon = loadTexture("weapon.png");
        charweapon1 = new TextureRegion(weapon, 0, 8, 8, 21);
        charweapon2 = new TextureRegion(weapon, 10, 4, 10, 25);
        charweapon3 = new TextureRegion(weapon, 22, 0, 10, 29);

        font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
    }
}
