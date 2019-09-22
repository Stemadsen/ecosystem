package dk.stemadsen.ecosystem.model.animals

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

import static dk.stemadsen.ecosystem.utils.RandomGenerator.nextInt

abstract class Animal {

    int age
    Terrain terrain
    Position position
    boolean isDead = false

    Animal(Terrain terrain, Position position) {
        this.terrain = terrain
        this.position = position
        age = nextInt(maxAge + 1)
    }

    abstract int getMaxAge()

    /**
     * Performs all of this animal's actions.
     * @return true if the animal lives to perform actions, or false if the animal dies/is dead.
     */
    boolean act() {
        if (isDead)
            // A dead animal should never be called act() upon, but just in case ...
            return false
        if (age > maxAge) {
            die()
            return false
        }

        age++
        return true
    }

    void die() {
        isDead = true
    }
}
