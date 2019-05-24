package com.tegi.scrollingshooter;

import android.graphics.PointF;
import android.graphics.RectF;

class Transform {

    //these two members are for scrolling background
    private int mXClip;
    private boolean mReversedFirst = false;

    private RectF mCollider;
    private PointF mLocation;
    private boolean mFacingRight = true;
    private boolean mHeadinUp = false;
    private boolean mHeadingDown = false;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;
    private float mSpeed;
    private float mObjectHight;
    private float mObjectWidth;
    private static PointF mScreenSize;

    Transform(float speed,float objectWidth,float objectHight,PointF startingLocation,PointF screenSize){
        mCollider =  new RectF();
        mSpeed = speed;
        mObjectHight = objectHight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;
    }

    //here are some helper methods that the background will use
    boolean getReversedFirst(){
        return mReversedFirst;
    }

    void flipReversedFirst(){
        mReversedFirst = !mReversedFirst;
    }

    int getXClip(){
        return mXClip;
    }

    void setXClip(int newXClip){
        mXClip = newXClip;
    }

    PointF getmScreenSize(){
        return mScreenSize;
    }

    void headUp(){
        mHeadinUp = true;
        mHeadingDown = false;
    }

    void headDown(){
        mHeadingDown = true;
        mHeadinUp = false;
    }

    void headRight(){
        mHeadingRight = true;
        mHeadingLeft = false;
        mFacingRight = true;
    }

    void headLeft(){
        mHeadingLeft = true;
        mHeadingRight = false;
        mFacingRight = false;
    }

    boolean headingUp(){
        return mHeadinUp;
    }

    boolean headingDown(){
        return mHeadingDown;
    }

    boolean headingRight(){
        return mHeadingRight;
    }

    boolean headingLeft(){
        return mHeadingLeft;
    }

    void updateCollider(){
        //pull the borders in a bit (10%)
        mCollider.top = mLocation.y + (mObjectHight/10);
        mCollider.left = mLocation.x + (mObjectWidth/10);
        mCollider.bottom = (mCollider.top + mObjectHight) - mObjectHight/10;
        mCollider.right = (mCollider.left + mObjectWidth) - mObjectWidth/10;
    }

    float getObjectHight(){
        return mObjectHight;
    }

    void stopVertical(){
        mHeadingDown = false;
        mHeadinUp = false;
    }

    float getSpeed(){
        return mSpeed;
    }

    void setLocation(float horizontal,float vertical){
        mLocation = new PointF(horizontal,vertical);
        updateCollider();
    }

    PointF getLocation(){
        return mLocation;
    }

    PointF getSize(){
        return new PointF((int)mObjectWidth,(int)mObjectHight);
    }

    void flip(){
        mFacingRight = !mFacingRight;
    }

    boolean getFacingRight(){
        return mFacingRight;
    }

    RectF getCollider(){
        return mCollider;
    }

    PointF getFiringLocation(float laserLength){
        PointF mFiringLocation = new PointF();

        if (mFacingRight){
            mFiringLocation.x = mLocation.x + (mObjectWidth/8f);
        }else
        {
            mFiringLocation.x = mLocation.x +(mObjectWidth/8f) - (laserLength);
        }
        //move the hight down a bit of ship hight from origin
        mFiringLocation.y = mLocation.y + (mObjectHight/1.28f);
        return mFiringLocation;
    }




}
