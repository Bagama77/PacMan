package com.pacman.demopacman.controller;

import com.pacman.demopacman.gameObjectInterfaces.Moveable;
import com.pacman.demopacman.model.GameMap;
import com.pacman.demopacman.model.GameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@RestController
@PropertySource(value = "classpath:application.properties")
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    Starter starter;

    Logger logger = Logger.getLogger("MainController");

    @GetMapping("/")
    public void homePage(Model model) {
        model.addAttribute("appName", appName);
//        return "home";
    }

    @GetMapping("/start")
    public void start(Model model) throws IOException, InterruptedException {
        //fill up the map
        starter.fillGameMap();
        //make steps
        AtomicInteger counter = new AtomicInteger(1);
        while (counter.get() < 10) {

            //to log the position of moving objects
            for(Map.Entry entry: GameMap.gameMap.entrySet()){
                if (entry.getValue() instanceof Moveable && ((GameObject)entry.getValue()).getStep() < counter.get()) {
                    logger.info(String.format("Step=%d ... object:%s , id=%s, position = %s", counter.get(), entry.getValue(), ((GameObject) entry.getValue()).getId(), entry.getKey()));
//                    ((Moveable)entry.getValue()).doStep((Moveable) entry.getValue());
                    Moveable.doStep((Moveable) entry.getValue());
                    ((GameObject)entry.getValue()).setStep(counter.get());
                }
            }
            counter.getAndAdd(1);
            Thread.sleep(2000);
        }
        logger.info("Game Over");
    }
}
