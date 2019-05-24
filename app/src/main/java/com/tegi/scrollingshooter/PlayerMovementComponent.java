package com.tegi.scrollingshooter;

import android.graphics.PointF;

class PlayerMovementComponent implements MovementComponent{

    @Override
    public boolean move(long fps,Transform t,Transform playerTransform){
        //how high is the screen?
        float screenHight = t.getmScreenSize().y;
        //where is the player?
        PointF location = t.getLocation();
        //how fast is it going?
        float speed = t.getSpeed();
        //how tall is the ship?
        float hight = t.getObjectHight();

        //move the ship up or down if needed
        if (t.headingDown()){
            location.y += speed/fps;
        }
        else if (t.headingUp()){
            location.y -= speed/fps;
        }

        //make sure the ship cant go off the screen
        if (location.y > screenHight - hight){
            location.y = screenHight - hight;
        }
        else if (location.y < 0){
            location.y = 0;
        }

        //update the collider
        t.updateCollider();

        return true;
    }
}
