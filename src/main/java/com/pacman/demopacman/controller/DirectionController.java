package com.pacman.demopacman.controller;

import com.pacman.demopacman.gameObjectInterfaces.Moveable;
import com.pacman.demopacman.model.Brick;
import com.pacman.demopacman.model.GameMap;
import com.pacman.demopacman.model.GameObject;
import javafx.util.Pair;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

@Data
@Component
public class DirectionController {

    Set<String> checkDirection(Moveable moveable){
        int positionX = ((GameObject) moveable).getPosX();
        int positionY = ((GameObject) moveable).getPosY();

        Direction.directions.forEach(direction -> moveable.getDirectionWrapper().allowableDirections.add(direction));

        if(GameMap.gameMap.get(new Pair(positionX + 1, positionY)) != null && GameMap.gameMap.get(new Pair(positionX + 1, positionY)).getClass() == Brick.class)
            moveable.getDirectionWrapper().allowableDirections.remove("RIGHT");
        if(GameMap.gameMap.get(new Pair(positionX - 1, positionY)) != null && GameMap.gameMap.get(new Pair(positionX + 1, positionY)).getClass() == Brick.class)
            moveable.getDirectionWrapper().allowableDirections.remove("LEFT");
        if(GameMap.gameMap.get(new Pair(positionX, positionY + 1)) != null && GameMap.gameMap.get(new Pair(positionX + 1, positionY)).getClass() == Brick.class)
            moveable.getDirectionWrapper().allowableDirections.remove("UP");
        if(GameMap.gameMap.get(new Pair(positionX, positionY - 1)) != null && GameMap.gameMap.get(new Pair(positionX + 1, positionY)).getClass() == Brick.class)
            moveable.getDirectionWrapper().allowableDirections.remove("DOWN");
        return moveable.getDirectionWrapper().allowableDirections;
    }

    String setOppositeDirection(Moveable moveable){
        switch (moveable.getDirectionWrapper().activeDirection){
            case "UP": return "DOWN";
            case "DOWN": return "UP";
            case "RIGHT": return "LEFT";
            case "LEFT": return "RIGHT";
        }
        return null;
    }

    String chooseRandomDirectionFromAllowable(Moveable moveable){
        int randomDirection = new Random().nextInt(moveable.getDirectionWrapper().allowableDirections.size());
        switch (randomDirection){
            case 0: return (String) moveable.getDirectionWrapper().allowableDirections.toArray()[0];
            case 1: return (String) moveable.getDirectionWrapper().allowableDirections.toArray()[1];
            case 2: return (String) moveable.getDirectionWrapper().allowableDirections.toArray()[2];
            case 3: return (String) moveable.getDirectionWrapper().allowableDirections.toArray()[3];
            default: return null;
        }
    }
}
