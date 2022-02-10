package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.logging.Logger;

public class Sounds {
    private static Logger logger = Logger.getLogger("BobbyGame");

    private static Sound runSound;
    private static Music jumpEffect;
    private static Music levelWonEffect;
    private static Music fallDeadEffect;

    private static Boolean isRunLooping = false;
    private static Boolean neverRun = true;

    public static void initialize() {
        runSound = Gdx.audio.newSound(Gdx.files.internal("sounds/run.wav"));
        jumpEffect = Gdx.audio.newMusic(Gdx.files.internal("sounds/jump.wav"));
        levelWonEffect = Gdx.audio.newMusic(Gdx.files.internal("sounds/level_won.wav"));
        fallDeadEffect = Gdx.audio.newMusic(Gdx.files.internal("sounds/fall_dead.wav"));

        runSound.loop();
        runSound.pause();
    }

    public static void reset() {
        isRunLooping = false;
        runSound.stop();
        neverRun = true;

        jumpEffect.stop();
        levelWonEffect.stop();
        fallDeadEffect.stop();
    }

    public static void run() {
        if (!isRunLooping) {
            isRunLooping = true;
            if (neverRun) {
                logger.severe("looping run");
                runSound.loop();
                neverRun = false;
            } else {
                runSound.resume();
            }
        }
    }

    public static void stopRun() {
        if (isRunLooping) {
            isRunLooping = false;
            runSound.pause();
        }
    }

    public static void jump() {
        logger.severe("Sounds.jump()");

        runSound.pause();

        jumpEffect.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music effect) {
            if (isRunLooping)
                runSound.resume();
            }
        });

        jumpEffect.play();
    }

    public static void levelWon(final Runnable cbk) {
        logger.severe("Sounds.levelWon()");

        runSound.pause();
        isRunLooping = false;

        jumpEffect.stop();

        levelWonEffect.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music effect) {
                logger.severe("levelWonEffect.onCompletion()");

                if (cbk != null)
                    cbk.run();
            }
        });

        levelWonEffect.play();
    }

    public static void fallDead(final Runnable cbk) {
        logger.severe("Sounds.fallDead()");
        runSound.pause();
        isRunLooping = false;

        jumpEffect.stop();

        fallDeadEffect.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music effect) {
                logger.severe("fallDeadEffect.onCompletion()");

                if (cbk != null)
                    cbk.run();
            }
        });

        fallDeadEffect.play();
    }
}
