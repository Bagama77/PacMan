package com.pacman.demopacman.controller;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Data
class Direction {
    public static Set<String> directions = new HashSet<>();
    static {
        directions.add("UP");
        directions.add("DOWN");
        directions.add("LEFT");
        directions.add("RIGHT");
    }
}
