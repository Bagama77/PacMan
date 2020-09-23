package com.pacman.demopacman.gameObjectInterfaces;

import com.pacman.demopacman.controller.DirectionWrapper;
import com.pacman.demopacman.model.GameObject;
import javafx.util.Pair;

public interface Moveable {

    default Pair<Integer, Integer> moveUp(){
        return new Pair(((GameObject)this).getPosX(), ((GameObject)this).getPosY()+1);
    };
    default Pair<Integer, Integer> moveDown(){
        return new Pair(((GameObject)this).getPosX(), ((GameObject)this).getPosY()-1);
    }
    default Pair<Integer, Integer> moveLeft(){
        return new Pair(((GameObject)this).getPosX() - 1, ((GameObject)this).getPosY());
    }
    default Pair<Integer, Integer> moveRight(){
        return new Pair(((GameObject)this).getPosX() + 1, ((GameObject)this).getPosY());
    }

    DirectionWrapper getDirectionWrapper();
}


