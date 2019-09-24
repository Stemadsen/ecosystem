package dk.stemadsen.ecosystem.model.world

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
class Position {

    int x
    int y

    Position(int x, int y) {
        this.x = x
        this.y = y
    }
}
