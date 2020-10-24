package com.pacman.demopacman.utilities;

import com.pacman.demopacman.controller.GameObjectCreator;
import com.pacman.demopacman.model.Brick;
import com.pacman.demopacman.model.GameObjectType;
import com.pacman.demopacman.model.Monster;
import com.pacman.demopacman.model.Pacman;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@Component
public class FieldReader {

    Logger logger = Logger.getLogger("FieldReader");
    private final String fieldPath = "src/main/resources/static/field_1.txt";

    public void readField() {
        try {
            BufferedReader reader = new LineNumberReader(new FileReader(new File(fieldPath)));
            String line;
            AtomicInteger lineNumber = new AtomicInteger(0);
            while ((line = reader.readLine()) != null) {
                String temp = line;
                logger.info("read line: " + line);
                IntStream.range(0, line.length()).forEach(position -> {
                    if (temp.charAt(position) == 'x') {
                        Brick brick = (Brick) GameObjectCreator.gameObjectFactory(GameObjectType.BRICK);
                        brick.setGameObjectType(GameObjectType.BRICK);
                        GameObjectCreator.putGameObjectIntoMap(brick, position, lineNumber.get());
                        logger.info("put object into map: x=" + position + ", y=" + lineNumber.get() + "; " + brick);
                    } else if(temp.charAt(position) == 'p') {
                        Pacman pacman = (Pacman) GameObjectCreator.gameObjectFactory(GameObjectType.PACMAN);
                        pacman.setGameObjectType(GameObjectType.PACMAN);
                        GameObjectCreator.putGameObjectIntoMap(pacman, position, lineNumber.get());
                        logger.info("put object into map: x=" + position + ", y=" + lineNumber.get() + "; " + pacman);
                    } else if (temp.charAt(position) == 'm') {
                        Monster monster = (Monster) GameObjectCreator.gameObjectFactory(GameObjectType.MONSTER);
                        monster.setGameObjectType(GameObjectType.MONSTER);
                        GameObjectCreator.putGameObjectIntoMap(monster, position, lineNumber.get());
                        logger.info("put object into map: x=" + position + ", y=" + lineNumber.get() + "; " + monster);
                    }
                });
                lineNumber.set(lineNumber.get() + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
