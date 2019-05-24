package com.tegi.scrollingshooter;

import android.graphics.Rect;
import android.view.MotionEvent;
import java.util.ArrayList;

class PlayerInputComponent implements InputComponent,InputObserver{

    private Transform mTransform;
    private PlayerLaserSpawner mPLS;

    PlayerInputComponent(GameEngine ger){
        ger.addObserver(this);
        mPLS = ger;

    }

    @Override
    public void setTransform(Transform transform){
        mTransform = transform;

    }

    //required method of inputObserver
    //interface called from the onTouchEvent method

    @Override
    public void handleInput(MotionEvent event,GameState gameState,ArrayList<Rect> buttons){
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        switch (event.getAction() & MotionEvent.ACTION_MASK){
             case MotionEvent.ACTION_UP:
                if (buttons.get(HUD.UP).contains(x,y) || buttons.get(HUD.DOWN).contains(x,y)){
                    //player has released either up or down
                    mTransform.stopVertical();
                }
                break;

             case MotionEvent.ACTION_DOWN:
                 if (buttons.get(HUD.UP).contains(x,y)){
                     //player has pressed up
                     mTransform.headUp();
                 } else if (buttons.get(HUD.DOWN).contains(x,y)){
                     //player pressed down
                     mTransform.headDown();
                 } else if (buttons.get(HUD.FLIP).contains(x,y)){
                     //player has released the FLIP button
                     mTransform.flip();
                 } else if (buttons.get(HUD.SHOOT).contains(x,y)){
                     //player pressed shoot button
                     mPLS.spawnPlayerLaser(mTransform);
                 }
                 break;

                 case MotionEvent.ACTION_POINTER_UP:
                 if (buttons.get(HUD.UP).contains(x,y) || buttons.get(HUD.DOWN).contains(x,y)){
                     //player has realeased either up or down
                     mTransform.stopVertical();
                 }
                 break;

            case MotionEvent.ACTION_POINTER_DOWN:
                if (buttons.get(HUD.UP).contains(x,y)){
                    //player has pressed up
                    mTransform.headUp();
                } else if (buttons.get(HUD.DOWN).contains(x,y)){
                    //player has pressed down
                    mTransform.headDown();
                } else if (buttons.get(HUD.FLIP).contains(x,y)){
                    //player has released FLIP
                    mTransform.flip();
                } else if (buttons.get(HUD.SHOOT).contains(x,y)){
                    //player has presed SHOOT
                    mPLS.spawnPlayerLaser(mTransform);
                }
                break;
        }

    }








}
