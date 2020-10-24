package com.pacman.demopacman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileNotFoundException;
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
    public String start(Model model) throws FileNotFoundException, IOException {
        //fill up the map
        starter.fillGameMap();
        //make step
//        while (!GameOverFlag.isGameOver) {
//            starter.stepController.doStep();
//        }
//        logger.info("Game Over");
        return "home";
    }
}
