package com.pacman.demopacman.gameObjectInterfaces;

import com.pacman.demopacman.controller.MoveableDirection;
import com.pacman.demopacman.model.Brick;
import com.pacman.demopacman.model.GameMap;
import com.pacman.demopacman.model.GameObject;
import com.pacman.demopacman.utilities.CustomPair;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
//import javafx.util.Pair;

public interface Moveable {

    Logger logger = Logger.getLogger("Moveable");

    MoveableDirection getActiveDirection();
    List<MoveableDirection> getAllowableDirections();
    void setActiveDirection(MoveableDirection newDirection);

    default CustomPair moveUp(){
        return new CustomPair(((GameObject)this).getPosX(), ((GameObject)this).getPosY()+1);
    };
    default CustomPair moveDown(){
        return new CustomPair(((GameObject)this).getPosX(), ((GameObject)this).getPosY()-1);
    }
    default CustomPair moveLeft(){
        return new CustomPair(((GameObject)this).getPosX() - 1, ((GameObject)this).getPosY());
    }
    default CustomPair moveRight(){
        return new CustomPair(((GameObject)this).getPosX() + 1, ((GameObject)this).getPosY());
    }

    default List<MoveableDirection> checkDirection(Moveable moveable) {
        int positionX = ((GameObject) moveable).getPosX();
        int positionY = ((GameObject) moveable).getPosY();


        if (GameMap.gameMap.get(new CustomPair(positionX + 1, positionY)) != null && GameMap.gameMap.get(new CustomPair(positionX + 1, positionY)).getClass() == Brick.class){
            List<MoveableDirection> allowableDirections = moveable.getAllowableDirections();
            allowableDirections.remove(MoveableDirection.RIGHT);
        }
        if(GameMap.gameMap.get(new CustomPair(positionX - 1, positionY)) != null && GameMap.gameMap.get(new CustomPair(positionX + 1, positionY)).getClass() == Brick.class) {
            List<MoveableDirection> allowableDirections = moveable.getAllowableDirections();
            allowableDirections.remove(MoveableDirection.LEFT);
        }
        if(GameMap.gameMap.get(new CustomPair(positionX, positionY + 1)) != null && GameMap.gameMap.get(new CustomPair(positionX + 1, positionY)).getClass() == Brick.class) {
            List<MoveableDirection> allowableDirections = moveable.getAllowableDirections();
            allowableDirections.remove(MoveableDirection.UP);
        }
        if(GameMap.gameMap.get(new CustomPair(positionX, positionY - 1)) != null && GameMap.gameMap.get(new CustomPair(positionX + 1, positionY)).getClass() == Brick.class) {
            List<MoveableDirection> allowableDirections = moveable.getAllowableDirections();
            allowableDirections.remove(MoveableDirection.DOWN);
        }
        return moveable.getAllowableDirections();
    }

    default String setOppositeDirection(Moveable moveable){
        switch (moveable.getActiveDirection().name()){
            case "UP": return "DOWN";
            case "DOWN": return "UP";
            case "RIGHT": return "LEFT";
            case "LEFT": return "RIGHT";
        }
        return null;
    }

    default MoveableDirection chooseRandomDirectionFromAllowable(Moveable moveable){
        int randomDirection = new Random().nextInt(moveable.getAllowableDirections().size());
        switch (randomDirection){
            case 0: return (MoveableDirection) moveable.getAllowableDirections().toArray()[0];
            case 1: return (MoveableDirection) moveable.getAllowableDirections().toArray()[1];
            case 2: return (MoveableDirection) moveable.getAllowableDirections().toArray()[2];
            case 3: return (MoveableDirection) moveable.getAllowableDirections().toArray()[3];
            default: return null;
        }
    }

    default boolean doStep(){
        for (Map.Entry entry: GameMap.gameMap.entrySet()) {
            if(entry.getValue().getClass().isInstance(Moveable.class)){
                Moveable movingObject = (Moveable) entry.getValue();
                logger.info("checking moving direction, object = " + movingObject);
                this.checkDirection(movingObject);
                GameMap.gameMap.remove(entry.getKey());
                //if object could continue move into current direction
                if(movingObject.getAllowableDirections().contains(movingObject.getActiveDirection())) {
                    doStepInCurrentDirection(movingObject);
                    logger.info("doing step in current direction..");
                } else {
                    //if we need to change direction
                    this.chooseRandomDirectionFromAllowable(movingObject);
                    doStepInNewDirection(movingObject);
                    logger.info("doing step in new direction..");
                }
                GameMap.gameMap.put(new CustomPair(((GameObject) movingObject).getPosX(), ((GameObject) movingObject).getPosY()), (GameObject) movingObject);
            }
        }
        return true;
    }

    default Moveable doStepInCurrentDirection(Moveable movingObject){
        MoveableDirection currentDirection = movingObject.getActiveDirection();
        switch (currentDirection.name()){
            case "UP": movingObject.moveUp();
            case "DOWN": movingObject.moveDown();
            case "LEFT": movingObject.moveLeft();
            case "RIGHT": movingObject.moveRight();
        }
        return movingObject;
    }

    default Moveable doStepInNewDirection(Moveable movingObject){
        MoveableDirection newDirection = this.chooseRandomDirectionFromAllowable(movingObject);
        movingObject.setActiveDirection(newDirection);
        return movingObject;
    }
}


