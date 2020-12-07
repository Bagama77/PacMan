package com.pacman.demopacman.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameObject {
    GameObjectType gameObjectType;
    private String id;
    private int posX;
    private int posY;
    List<byte[]> picture;
    boolean isShowing = true;
    private int step;//counter of steps which were done by object

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setPosX(int posX) {
        this.posX = posX;
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
