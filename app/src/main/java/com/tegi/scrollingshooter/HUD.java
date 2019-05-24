package com.tegi.scrollingshooter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import java.util.ArrayList;

class HUD {

    private int mTextFormatting;
    private int mScreenHight;
    private int mScreenWidth;

    private ArrayList<Rect> controls;

    static int UP = 0;
    static int DOWN = 1;
    static int FLIP = 2;
    static int SHOOT = 3;
    static int PAUSE = 4;

    HUD(Point size){
        mScreenHight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x/50;

        prepareControls();
    }

    private void prepareControls(){
        int buttonWidth = mScreenWidth/14;
        int buttonHight = mScreenHight/12;
        int buttonPadding = mScreenWidth/90;

        //calculate the positions of the buttons
        Rect up = new Rect(buttonPadding,mScreenHight - (buttonHight*2) - (buttonPadding*2),buttonWidth + buttonPadding,mScreenHight - buttonHight - (buttonPadding*2));
        Rect down = new Rect(buttonPadding,mScreenHight - buttonHight - buttonPadding,buttonWidth + buttonPadding,mScreenHight - buttonPadding);
        Rect flip = new Rect(mScreenWidth - buttonPadding - buttonWidth,mScreenHight - buttonHight - buttonPadding,mScreenWidth - buttonPadding,mScreenHight - buttonPadding);
        Rect shoot = new Rect(mScreenWidth - buttonPadding - buttonWidth,mScreenHight - (buttonHight*2) - (buttonPadding*2),mScreenWidth - buttonPadding,mScreenHight - buttonHight - (buttonPadding*2));
        Rect pause = new Rect(mScreenWidth - buttonPadding - buttonWidth,buttonPadding,mScreenWidth - buttonPadding,buttonPadding + buttonHight);

        controls = new ArrayList<>();
        controls.add(UP,up);
        controls.add(DOWN,down);
        controls.add(FLIP,flip);
        controls.add(SHOOT,shoot);
        controls.add(PAUSE,pause);
    }

    void draw(Canvas c,Paint p,GameState gs){

        //draw the HUD
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(mTextFormatting);
        c.drawText("Hi:" + gs.getHighScore(),mTextFormatting,mTextFormatting,p);

        c.drawText("Score:" + gs.getScore(),mTextFormatting,mTextFormatting*2,p);

        c.drawText("Lives:" + gs.getmNumShips(),mTextFormatting,mTextFormatting*3,p);

        if (gs.getGameOver()){
            p.setTextSize(mTextFormatting*5);
            c.drawText("PRESS PLAY",mScreenWidth/4,mScreenHight/2,p);
        }

        if (gs.getPaused() && !gs.getGameOver()){
            p.setTextSize(mTextFormatting*5);
            c.drawText("PAUSED",mScreenWidth/3,mScreenHight/2,p);
        }

        drawControls(c,p);
    }

    private void drawControls(Canvas c,Paint p){
        p.setColor(Color.argb(100,255,255,255));

        for (Rect r:controls){
            c.drawRect(r.left,r.top,r.right,r.bottom,p);
        }

        //set the colors back
        p.setColor(Color.argb(255,255,255,255));
    }

    ArrayList<Rect>getControls(){
        return controls;
    }
}
