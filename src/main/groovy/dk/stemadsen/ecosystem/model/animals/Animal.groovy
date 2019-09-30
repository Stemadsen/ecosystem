package dk.stemadsen.ecosystem.model.animals

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain
import groovy.transform.ToString

import static dk.stemadsen.ecosystem.utils.RandomGenerator.randomDouble
import static dk.stemadsen.ecosystem.utils.RandomGenerator.randomInt

@ToString(includePackage = false, includeNames = true, excludes = 'terrain')
abstract class Animal {

    int age
    Terrain terrain
    Position position
    boolean alive = true

    Animal(Terrain terrain, Position position, boolean randomAge) {
        this.terrain = terrain
        this.position = position
        if (randomAge)
            age = randomInt(maxAge + 1)
    }

    protected abstract double getBreedingProbability()

    protected abstract int getLitterSize()

    protected abstract int getMaxAge()

    /**
     * Creates and returns a newborn of this animal's species.
     * @param position a free position to let the animal come to life
     */
    protected abstract Animal getNewBorn(Position position)

    /**
     * Performs all of this animal's actions.
     * @param newbornsOut a collection that newborns will be added to.
     * @return true if the animal lives to perform actions, or false if the animal dies/is dead.
     */
    boolean act(Collection<Animal> newbornsOut) {
        if (!alive)
            // A dead animal should never be called act() upon, but just in case ...
            return false
        if (age > maxAge) {
            die()
            return false
        }

        breed(newbornsOut)
        age++
        return true
    }

    protected void moveToFreePosition() {
        Position freePosition = terrain.findFreeAdjacentPosition(position)
        if (freePosition) {
            moveToPosition(freePosition)
        }
    }

    protected void moveToPosition(Position newPosition) {
        terrain.markAsFree(position)
        position = newPosition
        terrain.markAsOccupied(this)
    }

    protected void breed(Collection<Animal> newbornsOut) {
        if (randomDouble() >= breedingProbability)
            return
        terrain.findAllFreeAdjacentPositions(position).take(litterSize).each { position ->
            Animal newBorn = getNewBorn(position)
            newbornsOut.add(newBorn)
            terrain.markAsOccupied(newBorn)
        }
    }

    protected void die() {
        alive = false
    }
}
