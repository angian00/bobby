package com.angian.bobby.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.angian.bobby.BobbyGame;

import static com.angian.bobby.LevelConstants.SCREEN_HEIGHT;
import static com.angian.bobby.LevelConstants.SCREEN_WIDTH;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                //return new GwtApplicationConfiguration(true);

                // Fixed size application:
                return new GwtApplicationConfiguration(SCREEN_WIDTH, SCREEN_HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new BobbyGame();
        }
}