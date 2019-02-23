package com.sa.game;

import java.util.ArrayList;

public class gamechar {
    private int hp;
    private int gold;
    private int inc;
    private int atk;
    private int def;

    public gamechar(int hp, int gold, int inc, int atk, int def) {
        this.hp = hp;
        this.gold = gold;
        this.inc = inc;
        this.atk = atk;
        this.def = def;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}