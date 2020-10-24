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
            case MONSTER:
                Monster monster = new Monster(new DirectionWrapper());
                monster.setGameObjectType(GameObjectType.MONSTER);
                return monster;
            case PACMAN:
                Pacman pacman = new Pacman(new DirectionWrapper());
                pacman.setGameObjectType(GameObjectType.PACMAN);
                return pacman;
            case BRICK:
                Brick brick = new Brick();
                brick.setGameObjectType(GameObjectType.BRICK);
                return brick;
            case POINT:
                Point point = new Point();
                point.setGameObjectType(GameObjectType.POINT);
                return point;
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
                case BRICK:
                    pictureBytes = Files.readAllBytes(Paths.get("src/main/resources/static/brick3_40x40.png"));
                    break;
                case PACMAN:
                    pictureBytes = Files.readAllBytes(Paths.get("src/main/resources/static/pacman.png"));
                    break;
                case MONSTER:
                    pictureBytes = Files.readAllBytes(Paths.get("src/main/resources/static/monster.png"));
                    break;
                case POINT:
                    pictureBytes = Files.readAllBytes(Paths.get("src/main/resources/static/point.png"));
                    break;
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
