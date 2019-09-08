package dk.stemadsen.ecosystem.model.creatures

import dk.stemadsen.ecosystem.model.world.Position

abstract class Creature {

    Position position

    Creature(Position position) {
        this.position = position
    }

    abstract void act()
}
