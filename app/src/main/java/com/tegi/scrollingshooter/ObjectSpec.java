package com.tegi.scrollingshooter;

import android.graphics.PointF;

abstract class ObjectSpec {

    private String mTag;
    private String mBitmapName;
    private float mSpeed;
    private PointF mSizeScale;
    private String[] mComponents;

    ObjectSpec(String tag,String bitmapName,float speed,PointF relativeScale,String[] Components){
        mTag = tag;
        mBitmapName = bitmapName;
        mSpeed = speed;
        mSizeScale = relativeScale;
        mComponents = Components;
    }

    String getTag(){
        return mTag;
    }

    String getBitmapName(){
        return mBitmapName;
    }

    float getSpeed(){
        return mSpeed;
    }

    PointF getScale(){
        return mSizeScale;
    }

    String[] getComponents(){
        return mComponents;
    }

}
