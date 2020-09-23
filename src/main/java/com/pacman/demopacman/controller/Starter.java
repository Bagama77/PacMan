package com.pacman.demopacman.controller;

import com.pacman.demopacman.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class Starter {

    @Autowired
    GameObjectCreator gameObjectCreator;

    @Autowired
    DirectionController directionController;

    @Autowired
    StepController stepController;

    public void fillGameMap(){
        int wallsLength = 10;
        int monsters = 4;

        Pacman pacman = (Pacman)GameObjectCreator.gameObjectFactory(GameObjectType.PACMAN);
        pacman.setPosX(5); pacman.setPosY(5);
        GameObjectCreator.putGameObjectIntoMap(pacman, pacman.getPosX(), pacman.getPosY());

        Stream.of(1 - monsters).forEach(count -> {
                                           Monster monster =  (Monster)GameObjectCreator.gameObjectFactory(GameObjectType.MONSTER);
                                           GameObjectCreator.putGameObjectIntoMap(monster, 3, count);
        });

        //fill gameMap of bricks
        Stream.of(1 - wallsLength).forEach(count -> {
                                        Brick brick = (Brick)GameObjectCreator.gameObjectFactory(GameObjectType.BRICK);brick.setPosX(count);brick.setPosY(10);
                                        GameObjectCreator.putGameObjectIntoMap(brick, count, 10);

                                        Brick brick2 = (Brick)GameObjectCreator.gameObjectFactory(GameObjectType.BRICK);brick2.setPosX(count);brick2.setPosY(0);
                                        GameObjectCreator.putGameObjectIntoMap(brick2, count, 0);

                                        Brick brick3 = (Brick)GameObjectCreator.gameObjectFactory(GameObjectType.BRICK);brick3.setPosX(0);brick3.setPosY(count);
                                        GameObjectCreator.putGameObjectIntoMap(brick3, 0, count);

                                        Brick brick4 = (Brick)GameObjectCreator.gameObjectFactory(GameObjectType.BRICK);brick4.setPosX(10);brick4.setPosY(count);
                                        GameObjectCreator.putGameObjectIntoMap(brick4, 10, count);
        });
    }


}
