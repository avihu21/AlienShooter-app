package com.tegi.scrollingshooter;

import java.util.Random;

class AlienVerticalSpawnComponent implements SpawnComponent{

    public void spawn(Transform playerTransform,Transform t){
        //spawn just off screen randomly but
        //within the screen width
        Random random = new Random();
        float xPosition = random.nextInt((int)t.getmScreenSize().x);

        //set the height to vertically
        //just above the visible game
        float spawnHeight = random.nextInt(300) - t.getObjectHight();

        //spawn the ship
        t.setLocation(xPosition,spawnHeight);
        //always going down
        t.headDown();
    }
}
