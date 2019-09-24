package dk.stemadsen.ecosystem.model.world

import spock.lang.Specification

class PositionSpec extends Specification {

    def "two positions should be equal iff they have the same coordinates"() {
        given:
            Position position1 = new Position(1, 2)
            Position position2 = new Position(x, y)

        expect:
            (position1 == position2) == expected

        where:
            x | y | expected
            2 | 1 | false
            1 | 2 | true
    }
}
