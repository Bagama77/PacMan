package com.pacman.demopacman.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

//@Data
@Component
public class GameObject {
    GameObjectType gameObjectType;
    int posX;
    int posY;
    List<byte[]> picture;
    boolean isShowing = true;

    public void setPosX(int posX) {
    }


    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    public void setGameObjectType(GameObjectType gameObjectType) {
        this.gameObjectType = gameObjectType;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public List<byte[]> getPicture() {
        return picture;
    }

    public void setPicture(List<byte[]> picture) {
        this.picture = picture;
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }
}
