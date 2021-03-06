package com.tegi.scrollingshooter;

import android.graphics.PointF;

import java.util.Random;

class AlienChaseMovementComponent implements MovementComponent{
    private Random mShotRandom = new Random();

    //gives this class the ability to tell the game engine
    //to spawn a laser
    private AlienLaserSpawner alienLaserSpawner;
    AlienChaseMovementComponent(AlienLaserSpawner als){
        alienLaserSpawner = als;
    }

    @Override
    public boolean move(long fps,Transform t,Transform playerTransform){
        //1 in 100 chances of shot being fired
        //when in line with player
        final int TAKE_SHOT = 0; //arbitrary
        final int SHOT_CHANCE = 100;

        //how wide is the screen?
        float screenWidth = t.getmScreenSize().x;
        //where is the player?
        PointF  playerLocation = playerTransform.getLocation();

        //how tall is the ship
        float hight = t.getObjectHight();
        //is the ship facing right?
        boolean facingRight = t.getFacingRight();
        //how far off before the ship doesn't bother chasing?
        float mChasingDistance = t.getmScreenSize().x/3f;
        //how far can the AI see?
        float mSeeingDistance = t.getmScreenSize().x/1.5f;
        //where is the ship?
        PointF location = t.getLocation();
        //how fast is the ship?
        float speed = t.getSpeed();

        //relative speed difference with player
        float verticalSpeedDiference = .3f;
        float slowDownRelativeToPlayer = 1.8f;
        //prevent the ship locking on too accurately
        float verticalSearchBounce = 20f;

        //more code here next
        //move in the diraction of the player
        //but relative to the player's direction of travel
        if (Math.abs(location.x - playerLocation.x) > mChasingDistance){
            if (location.x < playerLocation.x){
                t.headRight();
            }else if (location.x > playerLocation.x){
                t.headLeft();
            }
        }

        //can the alien "see" the player? if so try and align vertically
        if (Math.abs(location.x - playerLocation.x) <= mSeeingDistance){
            //use a cast to get rid of unnecessary
            //floats that make ship judder
            if ((int)location.y - playerLocation.y > verticalSearchBounce){
                t.headDown();
            } else if ((int)location.y - playerLocation.y > verticalSearchBounce){
                t.headUp();
            }

            //compensate for movement relative to player
            //but only when in view
            //otherwise alien will disappear miles off to on side
            if (!playerTransform.getFacingRight()){
                location.x += speed * slowDownRelativeToPlayer/fps;
            } else {
                location.x -= speed * slowDownRelativeToPlayer/fps;
            }

            }


        else {
            //stop vertical movement otherwise alien will
            //disappear off the top or bottom
            t.stopVertical();

        }

        //more code here next
        //moving vertically is slower than horizontally
        //change this to make game harder
        if (t.headingDown()){
            location.y += speed * verticalSpeedDiference/fps;
        }
        else if (t.headingUp()){
            location.y -= speed * verticalSpeedDiference/fps;
        }

        //move horizontally
        if (t.headingLeft()){
            location.x -= (speed)/fps;
        }
        if (t.headingRight()){
            location.x += (speed)/fps;
        }

        //update the collider
        t.updateCollider();

        //shoot if the alien is within a ships hight above
        //below or in line with the player
        //this could be a hit or a miss
        if (mShotRandom.nextInt(SHOT_CHANCE) == TAKE_SHOT){
            if (Math.abs(playerLocation.y - location.y) < hight){
                //is the alien facing the right direction
                //and close enough to the player
                if ((facingRight && playerLocation.x > location.x || !facingRight && playerLocation.x < location.x) && Math.abs(playerLocation.x - location.x) < screenWidth){
                    //fire!
                    alienLaserSpawner.spawnAlienLaser(t);
                }
            }
        }

        return true;


    }





}
