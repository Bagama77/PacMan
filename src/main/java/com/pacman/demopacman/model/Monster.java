package com.pacman.demopacman.model;

import com.pacman.demopacman.controller.DirectionWrapper;
import com.pacman.demopacman.gameObjectInterfaces.Eating;
import com.pacman.demopacman.gameObjectInterfaces.Moveable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
@Scope("prototype")
public class Monster extends GameObject implements Moveable, Eating {

    @Autowired
    DirectionWrapper directionWrapper;

}
