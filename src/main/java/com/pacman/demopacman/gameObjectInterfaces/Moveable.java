package com.pacman.demopacman.gameObjectInterfaces;

import com.pacman.demopacman.controller.DirectionWrapper;
import com.pacman.demopacman.model.GameObject;
import com.pacman.demopacman.utilities.CustomPair;
//import javafx.util.Pair;

public interface Moveable {

    default CustomPair moveUp(){
        return new CustomPair(((GameObject)this).getPosX(), ((GameObject)this).getPosY()+1);
    };
    default CustomPair moveDown(){
        return new CustomPair(((GameObject)this).getPosX(), ((GameObject)this).getPosY()-1);
    }
    default CustomPair moveLeft(){
        return new CustomPair(((GameObject)this).getPosX() - 1, ((GameObject)this).getPosY());
    }
    default CustomPair moveRight(){
        return new CustomPair(((GameObject)this).getPosX() + 1, ((GameObject)this).getPosY());
    }

    DirectionWrapper getDirectionWrapper();
}


