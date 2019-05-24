package com.tegi.scrollingshooter;

import android.graphics.PointF;

class AlienChaseSpec extends ObjectSpec {
    //this is all the unique specifications
    //for an alien that chases the player
    private static final String tag = "alien";
    private static final String bitmapName = "alien_ship1";
    private static final float speed = 4f;
    private static final PointF relativeScale = new PointF(15f,15f);
    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "alienChaseMovementComponent",
            "AlienHorizontalSpawnComponent"};
    AlienChaseSpec(){
        super(tag,bitmapName,speed,relativeScale,components); //inherits from ObjectSpec
    }

}
