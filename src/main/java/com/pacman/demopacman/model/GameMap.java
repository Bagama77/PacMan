package com.pacman.demopacman.model;

import javafx.util.Pair;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Data
@Component
@Scope("singleton")
public class GameMap {
    public static ConcurrentHashMap<Pair<Integer, Integer>, GameObject> gameMap = new ConcurrentHashMap<>();

}
