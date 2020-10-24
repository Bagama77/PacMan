package com.pacman.demopacman.controller;

import com.pacman.demopacman.model.GameMap;
import com.pacman.demopacman.utilities.FieldReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class Starter {

    Logger logger = Logger.getLogger("Starter");

    @Autowired
    GameObjectCreator gameObjectCreator;

    @Autowired
    DirectionController directionController;

    @Autowired
    StepController stepController;

    @Autowired
    FieldReader fieldReader;

    public void fillGameMap() throws FileNotFoundException, IOException {

        //fill gameMap of bricks
        fieldReader.readField();
        logger.info("GameMap after filling: " + GameMap.gameMap);
        logger.info("Game map size: " + GameMap.gameMap.entrySet().size());
    }
}
