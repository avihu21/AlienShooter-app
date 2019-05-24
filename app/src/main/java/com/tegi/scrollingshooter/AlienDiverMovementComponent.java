package com.tegi.scrollingshooter;

import android.graphics.PointF;
import java.util.Random;

class AlienDiverMovementComponent implements MovementComponent{

    @Override
    public boolean move(long fps,Transform t,Transform playerTransform){

        //where is the ship?
        PointF location = t.getLocation();
        //how fast is the ship?
        float speed = t.getSpeed();
        //relative speed difference with player
        float slowDownRelativeToPlayer = 1.8f;
        /*
        compensate for movement relative to player
        but only when in view
        otherwise alien will disappear miles off to one side
         */
        if (!playerTransform.getFacingRight()){
            location.x += speed * slowDownRelativeToPlayer/fps;
        } else {
            location.x -= speed * slowDownRelativeToPlayer/fps;
        }

        //fall down then respawn at the top
        location.y += speed/fps;

        if (location.y > t.getmScreenSize().y){
            //respawn at top
            Random random = new Random();
            location.y = random.nextInt(300) - t.getObjectHight();
            location.x = random.nextInt((int)t.getmScreenSize().x);
        }

        //update the collider
        t.updateCollider();

        return true;
    }




}
