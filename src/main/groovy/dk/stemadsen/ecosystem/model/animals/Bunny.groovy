package dk.stemadsen.ecosystem.model.animals

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

class Bunny extends Animal {

    Bunny(Terrain terrain, Position position, boolean randomAge) {
        super(terrain, position, randomAge)
    }

    @Override
    protected double getBreedingProbability() {
        return 0.1 // Bunnies breed like rabbits ...
    }

    @Override
    protected int getLitterSize() {
        return 4
    }

    @Override
    protected int getMaxAge() {
        return 10
    }

    @Override
    protected Animal getNewBorn(Position position) {
        return new Bunny(terrain, position, false)
    }

    @Override
    boolean act(Collection<Animal> newbornsOut) {
        if (!super.act(newbornsOut))
            return false

        hop()
        return true
    }

    private void hop() {
        Position newPosition = terrain.findFreeAdjacentPosition(position)
        if (newPosition) {
            terrain.markAsFree(position)
            terrain.markAsOccupied(newPosition)
            position = newPosition
        }
    }
}
