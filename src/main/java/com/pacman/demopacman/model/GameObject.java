package com.pacman.demopacman.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class GameObject {
    GameObjectType gameObjectType;
    int posX;
    int posY;
    List<byte[]> picture;
    boolean isShowing = true;
}
