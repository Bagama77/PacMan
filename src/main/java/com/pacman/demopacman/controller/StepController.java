package com.pacman.demopacman.controller;

import com.pacman.demopacman.gameObjectInterfaces.Moveable;
import com.pacman.demopacman.model.GameMap;
import com.pacman.demopacman.model.GameObject;
import com.pacman.demopacman.utilities.CustomPair;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class StepController {

    @Autowired
    DirectionController directionController;

    boolean doStep(){
        for (Map.Entry entry: GameMap.gameMap.entrySet()) {
            if(entry.getValue().getClass().isInstance(Moveable.class)){
                Moveable movingObject = (Moveable) entry.getValue();

                directionController.checkDirection(movingObject);
                GameMap.gameMap.remove(entry.getKey());
                //if object could continue move into current direction
                if(movingObject.getDirectionWrapper().allowableDirections.contains(movingObject.getDirectionWrapper().activeDirection)) {
                    doStepInCurrentDirection(movingObject);
                } else {
                    //if we need to change direction
                    directionController.chooseRandomDirectionFromAllowable(movingObject);
                    doStepInNewDirection(movingObject);
                }
                GameMap.gameMap.put(new CustomPair(((GameObject) movingObject).getPosX(), ((GameObject) movingObject).getPosY()), (GameObject) movingObject);
            }
        }
        return true;
    }

    private Moveable doStepInCurrentDirection(Moveable movingObject){
        String currentDirection = movingObject.getDirectionWrapper().activeDirection;
        switch (currentDirection){
            case "UP": movingObject.moveUp();
            case "DOWN": movingObject.moveDown();
            case "LEFT": movingObject.moveLeft();
            case "RIGHT": movingObject.moveRight();
        }
        return movingObject;
    }

    private Moveable doStepInNewDirection(Moveable movingObject){
        String newDirection = directionController.chooseRandomDirectionFromAllowable(movingObject);
        movingObject.getDirectionWrapper().setActiveDirection(newDirection);
        return movingObject;
    }
}
