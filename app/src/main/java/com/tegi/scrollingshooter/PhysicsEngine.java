package com.tegi.scrollingshooter;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

class PhysicsEngine {
    //this signature and much more will
    //change later in the project
    boolean update(long fps, ArrayList<GameObject> objects,GameState gs,SoundEngine se, ParticleSystem ps){
        //update all the GameObjects
        for (GameObject object:objects){
            if (object.checkActive()){
                object.update(fps,objects.get(Level.PLAYER_INDEX).getTransform());
            }
        }
        if (ps.mIsRunning){
            ps.update(fps);
        }
        return detectCollisions(gs,objects,se,ps);
    }

    //collision detection method will go here
    private boolean detectCollisions(GameState mGaemState,ArrayList<GameObject> objects,SoundEngine se,ParticleSystem ps){
        boolean playerHit = false;
        for (GameObject go1:objects){
            if (go1.checkActive()){
                //the 1st object is active
                //so worth checking

                for (GameObject go2:objects){
                    if (go2.checkActive()){
                        //the 2nd  object is active
                        //so worth checking
                        if (RectF.intersects(go1.getTransform().getCollider(),go2.getTransform().getCollider())){

                            //switch goes here
                            //there has been a collision
                            //but does it matter?
                            switch (go1.getTag() + " with " + go2.getTag()){
                                case "Player with Alien Laser":
                                    playerHit = true;
                                    mGaemState.lostLife(se);

                                    break;

                                case "Player with Alien":
                                    playerHit =true;
                                    mGaemState.lostLife(se);

                                    break;

                                case "Player Laser with Alien":
                                    mGaemState.increaseScore();
                                    //respawn the alien
                                    ps.emitParticles(new PointF(go2.getTransform().getLocation().x,go2.getTransform().getLocation().y));
                                    go2.setInactive();
                                    go2.spawn(objects.get(Level.PLAYER_INDEX).getTransform());
                                    go1.setInactive();
                                    se.playAlienExplode();

                                    break;

                                default:
                                    break;
                            }

                        }
                    }
                }
            }
        }
        return playerHit;
    }



}
