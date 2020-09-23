package com.pacman.demopacman.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
@Component
@Scope("prototype")
public class Point extends GameObject{
}
