package com.sa.game;

import java.util.Random;

public class gamechar {
    private int hp;
    private int gold;
    private int inc;
    private int atk;
    private int def;

    private int incupcost;
    private int atkupcost;
    private int defupcost;

    private int atkaddcost;
    private int defaddcost;

    private int incupnum;
    private int atkupnum;
    private int defupnum;

    private int bonusgold;

    private static Random rand = new Random();
    private static int upnum1;
    private static int upnum2;

    public gamechar(int hp, int gold, int inc, int atk, int def, int incupcost, int atkupcost, int defupcost, int atkaddcost, int defaddcost, int incupnum, int atkupnum, int defupnum, int bonusgold) {
        this.hp = hp;
        this.gold = gold;
        this.inc = inc;
        this.atk = atk;
        this.def = def;
        this.incupcost = incupcost;
        this.atkupcost = atkupcost;
        this.defupcost = defupcost;
        this.atkaddcost = atkaddcost;
        this.defaddcost = defaddcost;
        this.incupnum = incupnum;
        this.atkupnum = atkupnum;
        this.defupnum = defupnum;
        this.bonusgold = bonusgold;
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

    public int getIncupcost() {
        return incupcost;
    }

    public void setIncupcost(int incupcost) {
        this.incupcost = incupcost;
    }

    public int getAtkupcost() {
        return atkupcost;
    }

    public void setAtkupcost(int atkupcost) {
        this.atkupcost = atkupcost;
    }

    public int getDefupcost() {
        return defupcost;
    }

    public void setDefupcost(int defupcost) {
        this.defupcost = defupcost;
    }

    public int getAtkaddcost() {
        return atkaddcost;
    }

    public void setAtkaddcost(int atkaddcost) {
        this.atkaddcost = atkaddcost;
    }

    public int getDefaddcost() {
        return defaddcost;
    }

    public void setDefaddcost(int defaddcost) {
        this.defaddcost = defaddcost;
    }

    public int getIncupnum() {
        return incupnum;
    }

    public void setIncupnum(int incupnum) {
        this.incupnum = incupnum;
    }

    public int getAtkupnum() {
        return atkupnum;
    }

    public void setAtkupnum(int atkupnum) {
        this.atkupnum = atkupnum;
    }

    public int getDefupnum() {
        return defupnum;
    }

    public void setDefupnum(int defupnum) {
        this.defupnum = defupnum;
    }

    public int getBonusgold() {
        return bonusgold;
    }

    public void setBonusgold(int bonusgold) {
        this.bonusgold = bonusgold;
    }

    public static void fightlvl1(int[] actions, int round, gamechar character, gamechar enemy) {
        for(int i = 0; i < actions.length; i++) {
            actions[i] = 0;
        }

        if (enemy.getInc() < (enemy.getAtkupcost() + enemy.getDefupcost() + enemy.getAtkaddcost() + enemy.getDefaddcost())) {

            if (round > 5) {
                upnum1 = rand.nextInt(3) + 1;
                boolean enemyupcase = false;
                while (enemyupcase == false) {
                    upnum2 = rand.nextInt(3) + 1;
                    if (upnum2 != upnum1) {
                        enemyupcase = true;
                    }
                }

                if ((upnum1 == 1) || (upnum2 == 1)) {
                    enupgradeinc(actions, enemy);
                }
                if ((upnum1 == 2) || (upnum2 == 2)) {
                    enupgradeatk(actions, enemy);
                }
                if ((upnum1 == 3) || (upnum2 == 3)) {
                    enupgradedef(actions, enemy);
                }
            }
            else {
                if (round > 3) {
                    upnum1 = rand.nextInt(2) + 1;
                    enupgradeinc(actions, enemy);
                    if (upnum1 == 1) {
                        enupgradeatk(actions, enemy);
                    }
                    else {
                        enupgradedef(actions, enemy);
                    }
                }
                else {
                    enupgradeinc(actions, enemy);
                    enupgradedef(actions, enemy);
                }
            }


            if (round > 3) {
                enaddatk(actions, enemy);
            }
            else {
                actions[9] = 0;
            }
            enadddef(actions, enemy);
        }
        else {
            enupgradeatk(actions, enemy);
            enupgradedef(actions, enemy);
            enaddatk(actions, enemy);
            enadddef(actions, enemy);
        }
    }

    public static void enupgradeinc(int[] actions, gamechar enemy) {
        if (enemy.getGold() >= enemy.getIncupcost()) {
            enemy.setGold(enemy.getGold() - enemy.getIncupcost());
            enemy.setInc(enemy.getInc() + enemy.getIncupnum());

            actions[6] = 1;
        }
    }

    public static void enupgradeatk(int[] actions, gamechar enemy) {
        if (enemy.getGold() >= enemy.getAtkupcost()) {
            enemy.setGold(enemy.getGold() - enemy.getAtkupcost());
            enemy.setAtk(enemy.getAtk() + enemy.getAtkupnum());

            actions[7] = 1;
        }
    }

    public static void enupgradedef(int[] actions, gamechar enemy) {
        if (enemy.getGold() >= enemy.getDefupcost()) {
            enemy.setGold(enemy.getGold() - enemy.getDefupcost());
            enemy.setDef(enemy.getDef() + enemy.getDefupnum());

            actions[8] = 1;
        }
    }

    public static void enaddatk(int[] actions, gamechar enemy) {
        if (enemy.getGold() >= enemy.getAtkaddcost()) {
            enemy.setGold(enemy.getGold() - enemy.getAtkaddcost());

            atkfieldch2(actions);
        }
        else {
            atkfieldch1(actions);
        }
    }

    public static void atkfieldch1(int[] actions) {
        int atkfield;
        atkfield = rand.nextInt(3) + 1;

        actions[atkfield - 1] = 1;
        actions[9] = 1;
    }

    public static void atkfieldch2(int[] actions) {
        int atkfield1;
        int atkfield2 = 0;

        atkfield1 = rand.nextInt(3) + 1;

        boolean enemyatkcase = false;
        while (enemyatkcase == false) {
            atkfield2 = rand.nextInt(3) + 1;
            if (atkfield2 != atkfield1) {
                enemyatkcase = true;
            }
        }

        actions[atkfield1 - 1] = 1;
        actions[atkfield2 - 1] = 1;
        actions[9] = 2;
    }

    public static void enadddef(int[] actions, gamechar enemy) {
        if (enemy.getGold() >= enemy.getDefaddcost()) {
            enemy.setGold(enemy.getGold() - enemy.getDefaddcost());

            deffieldch2(actions);
        }
        else {
            deffieldch1(actions);
        }
    }

    public static void deffieldch1(int[] actions) {
        int deffield;
        deffield = rand.nextInt(3) + 1;

        actions[deffield + 2] = 1;
        actions[10] = 1;
    }

    public static void deffieldch2(int[] actions) {
        int deffield1;
        int deffield2 = 0;

        deffield1 = rand.nextInt(3) + 1;

        boolean enemydefcase = false;
        while (enemydefcase == false) {
            deffield2 = rand.nextInt(3) + 1;
            if (deffield2 != deffield1) {
                enemydefcase = true;
            }
        }

        actions[deffield1 + 2] = 1;
        actions[deffield2 + 2] = 1;
        actions[10] = 2;
    }
}