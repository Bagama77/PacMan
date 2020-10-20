package com.pacman.demopacman.controller;

import com.pacman.demopacman.model.*;
import com.pacman.demopacman.utilities.CustomPair;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Data
@AllArgsConstructor
@Component
public class GameObjectCreator {

    public static Logger logger = Logger.getLogger("GameObjectCreatorLogger");

    public static GameObject gameObjectFactory(GameObjectType gameObjectType){
        switch (gameObjectType){
            case MONSTER: return new Monster(new DirectionWrapper());
            case PACMAN: return new Pacman(new DirectionWrapper());
            case BRICK: return new Brick();
            case POINT: return new Point();
            default: return null;
        }
    }

    public static boolean putGameObjectIntoMap(GameObject gameObject, int posX, int posY){
        try {
            gameObject.setPosX(posX);
            gameObject.setPosY(posY);
            GameMap.gameMap.put(new CustomPair(posX, posY), gameObject);

            byte[] pictureBytes = new byte[1600];

            switch (gameObject.getGameObjectType()){
                case BRICK: pictureBytes = Files.readAllBytes(Paths.get("static/brick3_40x40.png"));
                case PACMAN: pictureBytes = Files.readAllBytes(Paths.get("static/pacman.png"));
                case MONSTER: pictureBytes = Files.readAllBytes(Paths.get("static/monster.png"));
                case POINT: pictureBytes = Files.readAllBytes(Paths.get("static/point.png"));
            }
            List<byte[]> pictureList = new ArrayList<>();
            pictureList.add(pictureBytes);
            gameObject.setPicture(pictureList);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
