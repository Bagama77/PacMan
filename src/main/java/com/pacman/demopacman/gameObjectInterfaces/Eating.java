package com.pacman.demopacman.gameObjectInterfaces;

import com.pacman.demopacman.model.GameObject;

public interface Eating {
    default void eat(GameObject gameObject){
        gameObject.setShowing(false);
    }
}
