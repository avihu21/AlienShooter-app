package com.tegi.scrollingshooter;

import android.graphics.PointF;
import java.util.Random;

class AlienPatrolMovementComponent implements MovementComponent{
    private AlienLaserSpawner alienLaserSpawner;
    private Random mShotRandom = new Random();

    AlienPatrolMovementComponent(AlienLaserSpawner als){
        alienLaserSpawner = als;
    }

    @Override
    public boolean move(long fps,Transform t,Transform playerTransform){

        final int TAKE_SHOT = 0; //arbitrary
        //1 in 100 chance of shot being fired
        //when in line with player
        final int SHOT_CHANCE = 100;

        //where is player
        PointF playerLocation = playerTransform.getLocation();
        //the top of the screen
        final float MIN_VERTICAL_BOUNDS = 0;
        //the width and height of the screen
        float screenX = t.getmScreenSize().x;
        float screenY = t.getmScreenSize().y;
        //how far ahead can the alien see?
        float mSeeingDistance = screenX * .5f;
        //where is the alien?
        PointF loc = t.getLocation();
        //how fast is the alien?
        float speed = t.getSpeed();
        //how tall is the alien
        float height = t.getObjectHight();
        //stop alien going too far away
        float MAX_VERTICAL_BOUNDS = screenY - height;
        float MAX_HORIZONTAL_BOUNDS = 2*screenX;
        float MIN_HORIZONTAL_BOUNDS = 2*-screenX;
        /*
        adjust the horizontal speed relative
        to the player's heading
        default is no horizontal speed adjustment
         */
        float horizontalSpeedAdjustmentRelativeToPlayer = 0;
        //how much to speed up or slow down relative
        //to player's heading
        float horizontlaSpeedAdjustmentModifier = .8f;

        //more code here soon
        //can the alien "see" the player? if so make speed relative
        if (Math.abs(loc.x - playerLocation.x) < mSeeingDistance){
            if (playerTransform.getFacingRight() != t.getFacingRight()){
                //facing a different way speed up the alien
                horizontalSpeedAdjustmentRelativeToPlayer = speed * horizontlaSpeedAdjustmentModifier;
            }
            else {
                //facing the same way slow it down
                horizontalSpeedAdjustmentRelativeToPlayer = -(speed * horizontlaSpeedAdjustmentModifier);
            }
        }

        //move horizontally taking into account
        //the speed modification
        if (t.headingLeft()){
            loc.x -= (speed + horizontalSpeedAdjustmentRelativeToPlayer)/fps;

            //turn the ship around when it reaches the
            //extent of its horizontal patrol area
            if (loc.x < MIN_HORIZONTAL_BOUNDS){
                loc.x = MIN_HORIZONTAL_BOUNDS;
                t.headRight();
            }
        }
        else {
            loc.x += (speed + horizontalSpeedAdjustmentRelativeToPlayer)/fps;

            //turn the ship around when it reaches the
            //extent of its horizontal patrol area
            if (loc.x > MAX_HORIZONTAL_BOUNDS){
                loc.x = MAX_HORIZONTAL_BOUNDS;
                t.headLeft();
            }
        }

        //more code here soon
        //vertical speed remains same
        //not affected by speed adjustment
        if (t.headingDown()){
            loc.y += (speed)/fps;
            if (loc.y > MAX_VERTICAL_BOUNDS){
                t.headUp();
            }
        }
        else {
            loc.y -= (speed)/fps;
            if (loc.y < MIN_VERTICAL_BOUNDS){
                t.headDown();
            }
        }
        //update the collier
        t.updateCollider();

        /*
        shoot if the alien within a ships height above
        below or in line with the player
        this could be a hit or a miss
         */
        if (mShotRandom.nextInt(SHOT_CHANCE) == TAKE_SHOT){
            if (Math.abs(playerLocation.y - loc.y)< height){
                //is the alien facing the right direction
                //and close enough to the player
                if ((t.getFacingRight() && playerLocation.x > loc.x || !t.getFacingRight() && playerLocation.x < loc.x) && Math.abs(playerLocation.x - loc.x) < screenX){
                    //fire!
                    alienLaserSpawner.spawnAlienLaser(t);
                }
            }
        }

        return true;

    }//end of move method





}
