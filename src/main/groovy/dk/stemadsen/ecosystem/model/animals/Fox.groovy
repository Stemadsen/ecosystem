package dk.stemadsen.ecosystem.model.animals

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain
import groovy.transform.ToString

import static dk.stemadsen.ecosystem.utils.RandomGenerator.randomInt

@ToString(includePackage = false, includeNames = true, includeSuper = true)
class Fox extends Animal {

    static final int MAX_FOOD_LEVEL = 20
    int foodLevel = randomInt(MAX_FOOD_LEVEL)

    Fox(Terrain terrain, Position position, boolean randomAge) {
        super(terrain, position, randomAge)
    }

    @Override
    protected double getBreedingProbability() {
        return 0.1
    }

    @Override
    protected int getLitterSize() {
        return 3
    }

    @Override
    protected int getMaxAge() {
        return 5
    }

    @Override
    protected Animal getNewBorn(Position position) {
        return new Fox(terrain, position, false)
    }

    @Override
    boolean act(Collection<Animal> newbornsOut) {
        if (!super.act(newbornsOut))
            return false

        return hunt()
    }

    private boolean hunt() {
        Bunny bunny = terrain.findAdjacentBunny(position)
        if (bunny) {
            eatBunny(bunny)
            return true
        }

        if (decreaseFoodLevel()) {
            moveToFreePosition()
            return true
        }

        return false
    }

    private void eatBunny(Bunny bunny) {
        bunny.die()
        foodLevel = MAX_FOOD_LEVEL
        moveToPosition(bunny.position)
    }

    private boolean decreaseFoodLevel() {
        foodLevel--
        if (foodLevel == 0) {
            die()
            return false
        }

        return true
    }
}
