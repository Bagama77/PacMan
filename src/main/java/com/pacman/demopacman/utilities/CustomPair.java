package com.pacman.demopacman.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class CustomPair {
    int positionX;
    int positionY;
}
