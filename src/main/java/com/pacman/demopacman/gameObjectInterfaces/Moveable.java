package com.pacman.demopacman.gameObjectInterfaces;

import com.pacman.demopacman.controller.MoveableDirection;
import com.pacman.demopacman.model.*;
import com.pacman.demopacman.utilities.CustomPair;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

public interface Moveable {

    Logger logger = Logger.getLogger("Moveable");

    MoveableDirection getActiveDirection();

    Set<MoveableDirection> getAllowableDirections();

    void setActiveDirection(MoveableDirection newDirection);

    static Moveable checkDirection(Moveable moveable) {
        int positionX = ((GameObject) moveable).getPosX();
        int positionY = ((GameObject) moveable).getPosY();
        Set<MoveableDirection> allowableDirections = moveable.getAllowableDirections();
        Arrays.stream(MoveableDirection.values()).forEach(allowableDirections::add);

        if (GameMap.gameMap.get(new CustomPair(positionX + 1, positionY)) != null
                && GameMap.gameMap.get(new CustomPair(positionX + 1, positionY)).getClass() == Brick.class) {
            allowableDirections.remove(MoveableDirection.RIGHT);
        }
        if (GameMap.gameMap.get(new CustomPair(positionX - 1, positionY)) != null
                && GameMap.gameMap.get(new CustomPair(positionX - 1, positionY)).getClass() == Brick.class) {
            allowableDirections.remove(MoveableDirection.LEFT);
        }
        if (GameMap.gameMap.get(new CustomPair(positionX, positionY - 1)) != null
                && GameMap.gameMap.get(new CustomPair(positionX, positionY - 1)).getClass() == Brick.class) {
            allowableDirections.remove(MoveableDirection.UP);
        }
        if (GameMap.gameMap.get(new CustomPair(positionX, positionY + 1)) != null
                && GameMap.gameMap.get(new CustomPair(positionX, positionY + 1)).getClass() == Brick.class) {
            allowableDirections.remove(MoveableDirection.DOWN);
        }
        return moveable;
    }

    default String setOppositeDirection(Moveable moveable) {
        switch (moveable.getActiveDirection().name()) {
            case "UP":
                return "DOWN";
            case "DOWN":
                return "UP";
            case "RIGHT":
                return "LEFT";
            case "LEFT":
                return "RIGHT";
        }
        return null;
    }

    static MoveableDirection chooseRandomDirectionFromAllowable(Moveable moveable) {
        int randomDirection = new Random().nextInt(moveable.getAllowableDirections().size());
        switch (randomDirection) {
            case 0:
                return (MoveableDirection) moveable.getAllowableDirections().toArray()[0];
            case 1:
                return (MoveableDirection) moveable.getAllowableDirections().toArray()[1];
            case 2:
                return (MoveableDirection) moveable.getAllowableDirections().toArray()[2];
            case 3:
                return (MoveableDirection) moveable.getAllowableDirections().toArray()[3];
            default:
                return null;
        }
    }

    static boolean doStep(Moveable movingObject) {
        checkDirection(movingObject);
        CustomPair key = new CustomPair(((GameObject) movingObject).getPosX(), ((GameObject) movingObject).getPosY());
        GameObject removedObject = GameMap.gameMap.remove(key);
        //check if Pacman is visible for monster
        if(removedObject.getClass() == Monster.class){
            hunt((Monster)removedObject);
        }



        //if object could continue move into current direction
        if (movingObject.getAllowableDirections().contains(movingObject.getActiveDirection())) {
            doStepInCurrentDirection(removedObject);
//                logger.info("doing step in current direction.., object = " + movingObject);
        } else {
            //if we need to change direction
            MoveableDirection newDirection = chooseRandomDirectionFromAllowable((Moveable) removedObject);
            doStepInNewDirection(newDirection, removedObject);
//                logger.info("doing step in new direction.., object" + movingObject);
        }
        GameMap.gameMap.put(new CustomPair(removedObject.getPosX(), removedObject.getPosY()), removedObject);
        return true;
    }

    static Moveable doStepInCurrentDirection(GameObject movingObject) {
        MoveableDirection currentDirection = ((Moveable) movingObject).getActiveDirection();
        switch (currentDirection.name()) {
            case "UP":
                movingObject.setPosY(movingObject.getPosY() - 1);//moveUp(movingObject);
                break;
            case "DOWN":
                movingObject.setPosY(movingObject.getPosY() + 1);//movingObject.moveDown(movingObject);
                break;
            case "LEFT":
                movingObject.setPosX(movingObject.getPosX() - 1);//movingObject.moveLeft(movingObject);
                break;
            case "RIGHT":
                movingObject.setPosX(movingObject.getPosX() + 1);//movingObject.moveRight(movingObject);
                break;
        }
        return (Moveable) movingObject;
    }

    static Moveable doStepInNewDirection(MoveableDirection newDirection, GameObject movingObject) {
        ((Moveable)movingObject).setActiveDirection(newDirection);
        switch (newDirection.name()) {
            case "UP":
                movingObject.setPosY(movingObject.getPosY() - 1);//moveUp(movingObject);
                break;
            case "DOWN":
                movingObject.setPosY(movingObject.getPosY() + 1);//movingObject.moveDown(movingObject);
                break;
            case "LEFT":
                movingObject.setPosX(movingObject.getPosX() - 1);//movingObject.moveLeft(movingObject);
                break;
            case "RIGHT":
                movingObject.setPosX(movingObject.getPosX() - 1);//movingObject.moveRight(movingObject);
                break;
        }
        return (Moveable) movingObject;
    }

    static void hunt(Moveable monster){
        Optional<GameObject> pacman = GameMap.gameMap.values()
                .stream()
                .filter(object -> object.getClass() == Pacman.class)
                .findFirst();

        MoveableDirection huntDirection;

        //if Pacman and monster are in one vertical line
        if(pacman.get().getPosX() == ((Monster)monster).getPosX()
                && !isThereWallBetweenPoints("vert", (Monster)monster)){

            if(pacman.get().getPosY() <  ((Monster)monster).getPosY()) {
                huntDirection = MoveableDirection.UP;
            } else {
                huntDirection = MoveableDirection.DOWN;
            }
            monster.setActiveDirection(huntDirection);
        }

        //if Pacman and monster are in one horizontal line
        if(pacman.get().getPosY() == ((Monster)monster).getPosY()
                && !isThereWallBetweenPoints("horiz", (Monster)monster)){// && ((Monster)monster).getAllowableDirections() == huntDirection){

            if(pacman.get().getPosX() <  ((Monster)monster).getPosX()) {
                huntDirection = MoveableDirection.LEFT;
            } else {
                huntDirection = MoveableDirection.RIGHT;
            }
            monster.setActiveDirection(huntDirection);
        }

    }

    static boolean isThereWallBetweenPoints(String lineDirection, GameObject monster){
        //get pacman from gameMap
        GameObject pacman = GameMap.gameMap.values()
                .stream()
                .filter(object -> object.getClass() == Pacman.class)
                .findFirst().get();

        //case when line is vertical
        if(lineDirection.equals("vert")){
            int minYPos, maxYPos;
            if(monster.getPosY() < pacman.getPosY()) {
                minYPos = monster.getPosY();
                maxYPos = pacman.getPosY();
            } else {
                minYPos = pacman.getPosY();
                maxYPos = monster.getPosY();
            }

            for(int i = minYPos; i < maxYPos; i++){
                if(GameMap.gameMap.get(new CustomPair(monster.getPosX(), i)) != null &&
                        GameMap.gameMap.get(new CustomPair(monster.getPosX(), i)).getClass() == Brick.class)
                    return true;
            }
        //when line is horizontal
        } else {
            int minXPos, maxXPos;
            if(monster.getPosX() < pacman.getPosX()) {
                minXPos = monster.getPosX();
                maxXPos = pacman.getPosX();
            } else {
                minXPos = pacman.getPosX();
                maxXPos = monster.getPosX();
            }

            for(int i = minXPos; i < maxXPos; i++){
                if(GameMap.gameMap.get(new CustomPair(i, monster.getPosY())) != null &&
                GameMap.gameMap.get(new CustomPair(i, monster.getPosY())).getClass() == Brick.class)
                    return true;
            }
        }
        return false;
    }
}


