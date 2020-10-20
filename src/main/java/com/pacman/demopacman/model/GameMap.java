package com.pacman.demopacman.model;

import com.pacman.demopacman.utilities.CustomPair;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Data
@Component
@Scope("singleton")
public class GameMap {
    public static ConcurrentHashMap<CustomPair, GameObject> gameMap = new ConcurrentHashMap<>();

}
