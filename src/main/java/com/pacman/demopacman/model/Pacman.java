package com.pacman.demopacman.model;

import com.pacman.demopacman.controller.MoveableDirection;
import com.pacman.demopacman.gameObjectInterfaces.Eating;
import com.pacman.demopacman.gameObjectInterfaces.Managable;
import com.pacman.demopacman.gameObjectInterfaces.Moveable;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
//@AllArgsConstructor
@Component
@Scope("singleton")
public class Pacman extends GameObject implements Moveable, Eating, Managable {

    MoveableDirection activeDirection;
    Set<MoveableDirection> allowableDirections = new HashSet<>();

    public Pacman(){
        this.activeDirection = MoveableDirection.RIGHT;
        this.allowableDirections.add(MoveableDirection.RIGHT);
        this.allowableDirections.add(MoveableDirection.LEFT);
    }

//    public Pacman(DirectionWrapper directionWrapper){
//        this.directionWrapper = new DirectionWrapper();
//        List<String> allowableDirections = new ArrayList<String>(this.directionWrapper.getAllowableDirections());
//        Collections.shuffle(allowableDirections);
//        this.directionWrapper.setActiveDirection(allowableDirections.get(0));
//    }
}
