package com.pacman.demopacman.utilities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class GameOverFlag {
    public static boolean isGameOver = false;
}
