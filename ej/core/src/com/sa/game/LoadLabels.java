package com.sa.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class LoadLabels {
    public static Label label_create(String labeltext, int lbl_x, int lbl_y, int lbl_w, int lbl_h, Label.LabelStyle label1Style, GameChar character) {
        Label label1 = new Label(labeltext, label1Style);
        label1.setSize(lbl_w, lbl_h);
        label1.setPosition(lbl_x, lbl_y);
        label1.setAlignment(Align.center);
        return label1;
    }
}
