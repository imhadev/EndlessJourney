package com.sa.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class LoadButtons {
    public static Button button1_create(String button_label, Skin mySkin, int btn_x, int btn_y, int btn_size) {
        Button button1 = new TextButton(button_label, mySkin, "toggle");
        button1.setSize(btn_size, btn_size);
        button1.setPosition(btn_x, btn_y);
        button1.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            }
        });

        return button1;
    }

    public static Button button2_1_create(Skin mySkin, int btn_x, int btn_y, int btn_size, final GameChar character, final int beforefight) {
        final Button button2 = new TextButton("inc +" + String.valueOf(character.getIncupnum() + "\n" + String.valueOf(character.getIncupcost()) + " gold"), mySkin, "toggle");
        button2.setSize(btn_size, btn_size);
        button2.setPosition(btn_x, btn_y);
        button2.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
                if (beforefight == 0) {
                    if (button2.isChecked()) {
                        character.setGold(character.getGold() - character.getIncupcost());
                        character.setInc(character.getInc() + character.getIncupnum());
                    } else {
                        character.setInc(character.getInc() - character.getIncupnum());
                        character.setGold(character.getGold() + character.getIncupcost());
                    }
                }
            }
        });

        return button2;
    }

    public static Button button2_2_create(Skin mySkin, int btn_x, int btn_y, int btn_size, final GameChar character, final int beforefight) {
        final Button button2 = new TextButton("atk +" + String.valueOf(character.getAtkupnum() + "\n" + String.valueOf(character.getAtkupcost()) + " gold"), mySkin, "toggle");
        button2.setSize(btn_size, btn_size);
        button2.setPosition(btn_x, btn_y);
        button2.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
                if (beforefight == 0) {
                    if (button2.isChecked()) {
                        character.setGold(character.getGold() - character.getAtkupcost());
                        character.setAtk(character.getAtk() + character.getAtkupnum());
                    } else {
                        character.setAtk(character.getAtk() - character.getAtkupnum());
                        character.setGold(character.getGold() + character.getAtkupcost());
                    }
                }
            }
        });

        return button2;
    }

    public static Button button2_3_create(Skin mySkin, int btn_x, int btn_y, int btn_size, final GameChar character, final int beforefight) {
        final Button button2 = new TextButton("def +" + String.valueOf(character.getDefupnum() + "\n" + String.valueOf(character.getDefupcost()) + " gold"), mySkin, "toggle");
        button2.setSize(btn_size, btn_size);
        button2.setPosition(btn_x, btn_y);
        button2.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
                if (beforefight == 0) {
                    if (button2.isChecked()) {
                        character.setGold(character.getGold() - character.getDefupcost());
                        character.setDef(character.getDef() + character.getDefupnum());
                    } else {
                        character.setDef(character.getDef() - character.getDefupnum());
                        character.setGold(character.getGold() + character.getDefupcost());
                    }
                }
            }
        });

        return button2;
    }

    public static Button button3_1_create(Skin mySkin, int btn_x, int btn_y, int btn_size, final GameChar character, final int beforefight, final Button buttonatk1, final Button buttonatk2, final Button buttonatk3) {
        final Button button3 = new TextButton("+1 atk" + "\n" + String.valueOf(character.getAtkaddcost()) + " gold", mySkin, "toggle");
        button3.setSize(btn_size + 10, btn_size + 10);
        button3.setPosition(btn_x, btn_y);
        button3.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
                if (beforefight == 0) {
                    if (button3.isChecked()) {
                        character.setGold(character.getGold() - character.getAtkaddcost());
                    } else {
                        character.setGold(character.getGold() + character.getAtkaddcost());
                        buttonatk1.setChecked(false);
                        buttonatk2.setChecked(false);
                        buttonatk3.setChecked(false);
                    }
                }
            }
        });

        return button3;
    }

    public static Button button3_2_create(Skin mySkin, int btn_x, int btn_y, int btn_size, final GameChar character, final int beforefight, final Button buttondef1, final Button buttondef2, final Button buttondef3) {
        final Button button3 = new TextButton("+1 def" + "\n" + String.valueOf(character.getDefaddcost()) + " gold", mySkin, "toggle");
        button3.setSize(btn_size + 10, btn_size + 10);
        button3.setPosition(btn_x, btn_y);
        button3.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            if (beforefight == 0) {
                if (button3.isChecked()) {
                    character.setGold(character.getGold() - character.getDefaddcost());
                } else {
                    character.setGold(character.getGold() + character.getDefaddcost());
                    buttondef1.setChecked(false);
                    buttondef2.setChecked(false);
                    buttondef3.setChecked(false);
                }
            }
            }
        });

        return button3;
    }
}
