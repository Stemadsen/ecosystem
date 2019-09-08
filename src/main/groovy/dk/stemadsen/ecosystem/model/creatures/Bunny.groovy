package dk.stemadsen.ecosystem.model.creatures

import dk.stemadsen.ecosystem.model.world.Position

class Bunny extends Creature {

    Bunny(Position position) {
        super(position)
    }

    @Override
    int getMaxAge() {
        return 100
    }

    @Override
    boolean act() {
        if (!super.act())
            return false

        hop()
        return true
    }

    void hop() {
        position.changeRandomly()
    }
}
