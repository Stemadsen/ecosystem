package dk.stemadsen.ecosystem.model.creatures

class Bunny extends Creature {

    void act() {
        hop()
    }

    void hop() {
        position.changeRandomly()
    }
}
