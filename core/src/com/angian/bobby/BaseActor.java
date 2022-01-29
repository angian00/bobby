package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class BaseActor extends Group {
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;
    private Rectangle boundaryRectangle;
    private static Rectangle worldBounds;
    private Vector2 velocityVec;



    public BaseActor(Stage s) {
        this(0, 0, s);
    }

    public BaseActor(float x, float y, Stage s) {
        super();
        setPosition(x, y);
        s.addActor(this);

        animation = null;
        elapsedTime = 0;
        animationPaused = false;
        velocityVec = new Vector2(0, 0);
    }

    public void setAnimation(Animation<TextureRegion> anim) {
        animation = anim;
        TextureRegion tr = animation.getKeyFrame(0);

        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();
        setSize(w, h);
        setOrigin(w/2, h/2);

        if (boundaryRectangle == null)
            setBoundaryRectangle();
    }

    public void setAnimationPaused(boolean pause) {
        animationPaused = pause;
    }

    public void setSpeed(float speed) {
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }

    public float getSpeed() {
        return velocityVec.len();
    }

    public boolean isMoving() {
        return getSpeed() > 0;
    }

    public void setVelocity(float vx, float vy) {
        velocityVec = new Vector2(vx, vy);
    }

    public void setVelocityX(float vx) {
        velocityVec = new Vector2(vx, velocityVec.y);
    }

    public void setVelocityY(float vy) {
        velocityVec = new Vector2(velocityVec.x, vy);
    }

    public void applyPhysics(float dt) {
        moveBy(velocityVec.x * dt, velocityVec.y * dt);
    }

    public void act(float dt) {
        super.act(dt);

        if (!animationPaused)
            elapsedTime += dt;
    }

    public void draw(Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c);

        if (animation != null && isVisible()) {
            batch.draw(animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(),getRotation());
        }

        super.draw(batch, parentAlpha);
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(elapsedTime);
    }

    public void centerAtPosition(float x, float y) {
        setPosition(x - getWidth()/2 , y - getHeight()/2);
    }

    public void centerAtActor(BaseActor other) {
        centerAtPosition(other.getX() + other.getWidth()/2 , other.getY() + other.getHeight()/2);
    }

    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }

    public void setBoundaryRectangle() {
        float w = getWidth();
        float h = getHeight();
        boundaryRectangle = new Rectangle(0, 0, w, h);
    }
    public Rectangle getBoundaryRectangle() {
        return boundaryRectangle;
    }

    public static void setWorldBounds(float width, float height) {
        worldBounds = new Rectangle(0, 0, width, height);
    }
    public static void setWorldBounds(BaseActor ba) {
        setWorldBounds(ba.getWidth(), ba.getHeight());
    }
    public static Rectangle getWorldBounds() {
        return worldBounds;
    }

    public Animation<TextureRegion> createSolidTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        //for consistency with load* methods
        Array<TextureRegion> textureArray = new Array<>();
        textureArray.add(new TextureRegion(texture));
        Animation<TextureRegion> anim = new Animation<TextureRegion>(1, textureArray);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        if (animation == null)
            setAnimation(anim);

        return anim;
    }

    public Animation<TextureRegion> loadTexture(String fileName) {
        String[] fileNames = { fileName };
        return loadAnimationFromFiles(fileNames, 1, true);
    }

    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames, float frameDuration, boolean loop) {
        int fileCount = fileNames.length;
        Array<TextureRegion> textureArray = new Array<>();

        for (int n=0; n < fileCount; n++) {
            String fileName = fileNames[n];
            Texture texture = new Texture(fileName);
            //texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            textureArray.add(new TextureRegion(texture));
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);

        if (loop)
            anim.setPlayMode(Animation.PlayMode.LOOP);
        else
            anim.setPlayMode(Animation.PlayMode.NORMAL);

        if (animation == null)
            setAnimation(anim);

        return anim;
    }


    public Animation<TextureRegion> loadAnimationFromSheet(String fileName, int rows, int cols, float frameDuration, boolean loop) {
        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        //texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;

        TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);

        Array<TextureRegion> textureArray = new Array<>();

        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                textureArray.add(temp[r][c]);
            }
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);


        if (loop)
            anim.setPlayMode(Animation.PlayMode.LOOP);
        else
            anim.setPlayMode(Animation.PlayMode.NORMAL);

        if (animation == null)
            setAnimation(anim);

        return anim;
    }
}
