package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.animals.Bunny
import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createAnimal
import static dk.stemadsen.ecosystem.TestDataUtil.createBunny
import static dk.stemadsen.ecosystem.TestDataUtil.createFox

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

    def "it should find all positions adjacent to middle position"() {
        given:
            Terrain terrain = new Terrain(10)
            int x = 5
            int y = 5

        when:
            List<Position> positions = terrain.findAllAdjacentPositions(new Position(x, y))

        then:
            positions.size() == 4
            positions.any { it.x == x - 1 && it.y == y }
            positions.any { it.x == x && it.y == y - 1 }
            positions.any { it.x == x && it.y == y + 1 }
            positions.any { it.x == x + 1 && it.y == y }
    }

    def "it should find all positions adjacent to top left corner"() {
        given:
            Terrain terrain = new Terrain(10)
            int x = 0
            int y = 0

        when:
            List<Position> positions = terrain.findAllAdjacentPositions(new Position(x, y))

        then:
            positions.size() == 2
            positions.any { it.x == x && it.y == y + 1 }
            positions.any { it.x == x + 1 && it.y == y }
    }

    def "it should find all positions adjacent to bottom right corner"() {
        given:
            Terrain terrain = new Terrain(10)
            int x = 9
            int y = 9

        when:
            List<Position> positions = terrain.findAllAdjacentPositions(new Position(x, y))

        then:
            positions.size() == 2
            positions.any { it.x == x - 1 && it.y == y }
            positions.any { it.x == x && it.y == y - 1 }
    }

    def "it should find all free adjacent positions"() {
        given: "two adjacent positions are free, two are occupied"
            Terrain terrain = new Terrain(10)
            int x = 5
            int y = 5
            terrain.markAsOccupied(createAnimal(new Position(x, y - 1)))
            terrain.markAsOccupied(createAnimal(new Position(x + 1, y)))

        when:
            List<Position> positions = terrain.findAllFreeAdjacentPositions(new Position(x, y))

        then: "the two free positions are returned"
            positions.size() == 2
            positions.any { it.x == x - 1 && it.y == y }
            positions.any { it.x == x && it.y == y + 1 }
    }

    def "it should find all adjacent Bunnies"() {
        given: "two adjacent Bunnies and one adjacent Fox"
            Terrain terrain = new Terrain(10)
            int x = 5
            int y = 5
            terrain.markAsOccupied(createBunny(new Position(x- 1, y )))
            terrain.markAsOccupied(createBunny(new Position(x, y - 1)))
            terrain.markAsOccupied(createFox(new Position(x + 1, y)))

        when:
            List<Bunny> bunnies = terrain.findAllAdjacentBunnies(new Position(x, y))

        then: "the two Bunniesare returned"
            bunnies.size() == 2
    }
}
