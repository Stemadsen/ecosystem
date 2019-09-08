package dk.stemadsen.ecosystem.model.creatures

import dk.stemadsen.ecosystem.model.world.Position

abstract class Creature {

    int age = 0
    Position position
    boolean isDead = false

    Creature(Position position) {
        this.position = position
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
