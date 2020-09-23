package com.pacman.demopacman.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
@Scope("prototype")
public class DirectionWrapper {
    String activeDirection;
    Set<String> allowableDirections;
}

