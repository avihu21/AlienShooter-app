package com.tegi.scrollingshooter;

import android.graphics.PointF;

class PlayerLaserSpec extends ObjectSpec{
    //this is all the unique specification
    //for a player laser
    private static final String tag = "Player Laser";
    private static final String bitmapName = "Player_laser";
    private static final float speed = .65f;
    private static final PointF relativeScale = new PointF(8f,160f);
    private static final String[] components = new String[]{
            "StdGraphicsComponent",
            "LaserMovementComponent",
            "LaserSpawnComponent"};
    PlayerLaserSpec(){
        super(tag,bitmapName,speed,relativeScale,components);  //inherits from ObjectSpec
    }
}
