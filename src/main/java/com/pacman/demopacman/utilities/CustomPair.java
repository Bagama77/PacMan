package com.pacman.demopacman.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
@NoArgsConstructor
public class CustomPair {
    int positionX;
    int positionY;
}
