package com.pacman.demopacman.controller;

import com.pacman.demopacman.model.GameMap;
import com.pacman.demopacman.model.GameObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.logging.Logger;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    Starter starter;

    Logger logger = Logger.getLogger("MainController");

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/start")
    public String start(Model model) throws IOException {
        //fill up the map
        starter.fillGameMap();
        //make steps
        int counter = 0;
        while (counter < 3) {

            //to log the position of moving objects
            GameMap.gameMap.entrySet().forEach(entry -> {
                if ((entry.getValue().getGameObjectType() == GameObjectType.MONSTER) || (entry.getValue().getGameObjectType() == GameObjectType.PACMAN)) {
                    logger.info("object: " + entry.getValue() + "; position = " + entry.getKey());
                }
            });
            counter++;
        }
        logger.info("Game Over");
        return "home";
    }
}
