package dk.stemadsen.ecosystem.model.creatures

import dk.stemadsen.ecosystem.model.world.Position

class Bunny extends Creature {

    Bunny(Position position) {
        super(position)
    }

    void act() {
        hop()
    }

    void hop() {
        position.changeRandomly()
    }
}
