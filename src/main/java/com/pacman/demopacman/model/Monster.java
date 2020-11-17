package com.pacman.demopacman.model;

import com.pacman.demopacman.controller.MoveableDirection;
import com.pacman.demopacman.gameObjectInterfaces.Eating;
import com.pacman.demopacman.gameObjectInterfaces.Moveable;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
//@AllArgsConstructor
@Component
@Scope("prototype")
public class Monster extends GameObject implements Moveable, Eating {

    MoveableDirection activeDirection;
    List<MoveableDirection> allowableDirections = new ArrayList<>();

    public Monster(){
        this.activeDirection = MoveableDirection.UP;
        this.allowableDirections.add(MoveableDirection.UP);
    }

}
