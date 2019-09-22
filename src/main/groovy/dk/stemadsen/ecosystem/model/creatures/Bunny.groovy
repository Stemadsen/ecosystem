package dk.stemadsen.ecosystem.model.creatures

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

class Bunny extends Creature {

    Bunny(Terrain terrain, Position position) {
        super(terrain, position)
    }

    @Override
    int getMaxAge() {
        return 10
    }

    @Override
    boolean act() {
        if (!super.act())
            return false

        hop()
        return true
    }

    void hop() {
        Position newPosition = terrain.findFreeAdjacentPosition(position)
        if (newPosition)
            position = newPosition
    }
}
