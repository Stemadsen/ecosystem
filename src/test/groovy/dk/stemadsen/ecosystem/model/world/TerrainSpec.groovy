package dk.stemadsen.ecosystem.model.world

import spock.lang.Specification

class TerrainSpec extends Specification {

    def "it should find all free positions adjacent to middle position"() {
        given:
            Terrain terrain = new Terrain(10)
            int x = 5
            int y = 5

        when:
            List<Position> freeAdjacentPositions = terrain.findAllFreeAdjacentPositions(new Position(x, y))

        then:
            freeAdjacentPositions.size() == 4
            freeAdjacentPositions.any { it.x == x - 1 && it.y == y }
            freeAdjacentPositions.any { it.x == x && it.y == y - 1 }
            freeAdjacentPositions.any { it.x == x && it.y == y + 1 }
            freeAdjacentPositions.any { it.x == x + 1 && it.y == y }
    }

    def "it should find all free positions adjacent to upper left corner"() {
        given:
            Terrain terrain = new Terrain(10)
            int x = 0
            int y = 0

        when:
            List<Position> freeAdjacentPositions = terrain.findAllFreeAdjacentPositions(new Position(x, y))

        then:
            freeAdjacentPositions.size() == 2
            freeAdjacentPositions.any { it.x == x && it.y == y + 1 }
            freeAdjacentPositions.any { it.x == x + 1 && it.y == y }
    }

    def "it should find all free positions adjacent to lower right corner"() {
        given:
            Terrain terrain = new Terrain(10)
            int x = 9
            int y = 9

        when:
            List<Position> freeAdjacentPositions = terrain.findAllFreeAdjacentPositions(new Position(x, y))

        then:
            freeAdjacentPositions.size() == 2
            freeAdjacentPositions.any { it.x == x - 1 && it.y == y }
            freeAdjacentPositions.any { it.x == x && it.y == y - 1 }
    }
}
