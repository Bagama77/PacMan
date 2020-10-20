package com.pacman.demopacman.utilities;

import com.pacman.demopacman.controller.GameObjectCreator;
import com.pacman.demopacman.model.Brick;
import com.pacman.demopacman.model.GameMap;
import com.pacman.demopacman.model.GameObjectType;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@Component
public class FieldReader {

    Logger logger = Logger.getLogger("FieldReader");

    private String fieldPath = "/resources/field_1.txt";

    void readField() throws FileNotFoundException, IOException{
        try {
            BufferedReader reader = new LineNumberReader(new FileReader(fieldPath));
            String line;
            AtomicInteger lineNumber = new AtomicInteger(0);
            while((line = reader.readLine()) != null){
                String temp = line;
                IntStream.range(0, line.length()).forEach(position -> {
                    if(temp.charAt(position) == 'X') {
                        Brick brick = (Brick) GameObjectCreator.gameObjectFactory(GameObjectType.BRICK);
                        GameObjectCreator.putGameObjectIntoMap(brick, position, lineNumber.get());
                    }
                });
                lineNumber.set(lineNumber.get() + 1);
            }
            logger.info("Game map after reading field: " + GameMap.gameMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
