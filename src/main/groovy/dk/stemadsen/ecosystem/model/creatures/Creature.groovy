package dk.stemadsen.ecosystem.model.creatures

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

import static dk.stemadsen.ecosystem.utils.RandomGenerator.nextInt

abstract class Creature {

    int age
    Terrain terrain
    Position position
    boolean isDead = false

    Creature(Terrain terrain, Position position) {
        this.terrain = terrain
        this.position = position
        age = nextInt(maxAge + 1)
    }

    abstract int getMaxAge()

    /**
     * Performs all of this creature's actions.
     * @return true if the creature lives to perform actions, or false if the creature dies/is dead.
     */
    boolean act() {
        if (isDead)
            // A dead creature should never be called act() upon, but just in case ...
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
