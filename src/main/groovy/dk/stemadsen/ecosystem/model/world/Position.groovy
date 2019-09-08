package dk.stemadsen.ecosystem.model.world

import groovy.transform.ToString

@ToString(includePackage = false, includeNames = true)
class Position {

    int x
    int y

    Position(int x, int y) {
        this.x = x
        this.y = y
    }

    void changeRandomly() {
        x += new Random().nextGaussian()
        y += new Random().nextGaussian()
    }
}
