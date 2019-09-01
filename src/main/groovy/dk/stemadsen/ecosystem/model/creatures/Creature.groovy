package dk.stemadsen.ecosystem.model.creatures

import dk.stemadsen.ecosystem.model.world.Position

abstract class Creature {

    Position position

    Creature() {
        position = new Position()
    }

    abstract void act()
}
