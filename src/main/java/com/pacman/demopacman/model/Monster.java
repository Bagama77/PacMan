package com.pacman.demopacman.model;

import com.pacman.demopacman.controller.MoveableDirection;
import com.pacman.demopacman.gameObjectInterfaces.Eating;
import com.pacman.demopacman.gameObjectInterfaces.Moveable;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@ToString(callSuper=true)
@Component
@Scope("prototype")
public class Monster extends GameObject implements Moveable, Eating {

    MoveableDirection activeDirection;
    Set<MoveableDirection> allowableDirections = new HashSet<>();

    public Monster(){
        this.activeDirection = MoveableDirection.UP;
        this.allowableDirections.add(MoveableDirection.UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monster)) return false;
        Monster monster = (Monster) o;
        GameObject gameObject = (GameObject)this;
        return gameObject.getPosX() == ((GameObject)monster).getPosX() &&
                gameObject.getPosY() == ((GameObject)monster).getPosY() &&
                getActiveDirection() == monster.getActiveDirection() &&
                getAllowableDirections().equals(monster.getAllowableDirections());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActiveDirection(), getAllowableDirections(), ((GameObject)this).getPosX(), ((GameObject)this).getPosY());
    }
}
