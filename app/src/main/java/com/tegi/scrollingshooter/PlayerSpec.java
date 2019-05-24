package com.tegi.scrollingshooter;

import android.graphics.PointF;

class PlayerSpec extends ObjectSpec{
    //this is all the unique specification
    //for a player
    private static final String tag = "Player";
    private static final String bitmapName = "Player_ship";
    private static final float speed = 1f;
    private static final PointF relativeScale = new PointF(15f,15f);
    private static final String[] components = new String[]{
            "PlayerInputComponent",
            "StdGraphicsComponent",
            "PlayerMovementComponent",
            "PlayerSpawnComponent"};
    PlayerSpec(){
        super(tag,bitmapName,speed,relativeScale,components);
    }
}
