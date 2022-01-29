package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Styles {
    public static Label.LabelStyle labelStyle;

    static {
        initStylesBitmap();
    }


    private static void initStylesBitmap() {
        BitmapFont customFont = new BitmapFont(Gdx.files.internal("digits_font.fnt"));
        customFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        customFont.getData().setScale(LevelLayout.SCALE_FACTOR);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = customFont;
    }
}
