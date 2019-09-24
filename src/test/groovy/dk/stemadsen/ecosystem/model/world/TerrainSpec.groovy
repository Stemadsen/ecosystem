package dk.stemadsen.ecosystem.model.world

import spock.lang.Specification

class TerrainSpec extends Specification {

    def "it should initialize correctly"() {
        given:
            Terrain terrain1 = new Terrain(5)
            Terrain terrain2 = new Terrain(3, 14)

        expect:
            terrain1.height == 5
            terrain1.width == 5
            terrain2.height == 3
            terrain2.width == 14
            [(0..4), (0..4)].combinations().every { List<Integer> pair ->
                terrain1.isFree(new Position(pair[0], pair[1]))
            }
    }

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
