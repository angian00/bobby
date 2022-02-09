package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    private static Sound runSound;
    private static Music jumpEffect;
    private static Music levelWonEffect;
    private static Music fallDeadEffect;

    private static boolean isRunLooping = false;
    public static boolean isEffectPlaying = false;

    public static void initialize() {
        runSound = Gdx.audio.newSound(Gdx.files.internal("sounds/run.wav"));
        jumpEffect = Gdx.audio.newMusic(Gdx.files.internal("sounds/jump.wav"));
        levelWonEffect = Gdx.audio.newMusic(Gdx.files.internal("sounds/level_won.wav"));
        fallDeadEffect = Gdx.audio.newMusic(Gdx.files.internal("sounds/fall_dead.wav"));
    }


    public static void run() {
        if (!isRunLooping) {
            isRunLooping = true;
            runSound.loop();
        }
    }

    public static void stopRun() {
        if (isRunLooping) {
            isRunLooping = false;
            runSound.pause();
        }
    }

    public static void jump() {
        runSound.pause();
        isEffectPlaying = true;

        jumpEffect.setOnCompletionListener(effect -> {
            isEffectPlaying = false;
            if (isRunLooping)
                runSound.loop();
        });

        jumpEffect.play();
    }

    public static void levelWon(Runnable cbk) {
        isEffectPlaying = true;
        runSound.stop();
        levelWonEffect.setOnCompletionListener(effect -> {
            isEffectPlaying = false;
            cbk.run();
        });

        levelWonEffect.play();
    }

    public static void fallDead(Runnable cbk) {
        isEffectPlaying = true;
        runSound.stop();
        jumpEffect.stop();
        fallDeadEffect.setOnCompletionListener(effect -> {
            isEffectPlaying = false;
            cbk.run();
        });

        fallDeadEffect.play();
    }
}
