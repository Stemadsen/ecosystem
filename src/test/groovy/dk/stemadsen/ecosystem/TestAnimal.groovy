package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.animals.Animal
import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

/**
 * A generic animal useful for testing the abstract Animal class.
 * Lets you know how many times its act method has been called.
 */
class TestAnimal extends Animal {
    int actedTimes

    TestAnimal(Terrain terrain, Position position, boolean randomAge) {
        super(terrain, position, randomAge)
    }

    @Override
    double getBreedingProbability() {
        return 1
    }

    @Override
    int getLitterSize() {
        return 5
    }

    @Override
    int getMaxAge() {
        return 10
    }

    @Override
    protected Animal getNewBorn(Position position) {
        return new TestAnimal(terrain, position, false)
    }

    @Override
    boolean act(Collection<Animal> newBornsOut) {
        actedTimes++
        return super.act(newBornsOut)
    }
}
