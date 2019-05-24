package com.tegi.scrollingshooter;

//this allow an alien to communicate with the game engine
//and spawn a laser

interface AlienLaserSpawner {
    void spawnAlienLaser(Transform transform);
}
