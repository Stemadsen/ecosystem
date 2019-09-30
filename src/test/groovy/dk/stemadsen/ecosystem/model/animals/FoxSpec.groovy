package dk.stemadsen.ecosystem.model.animals

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain
import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createBunny
import static dk.stemadsen.ecosystem.TestDataUtil.createFox

class FoxSpec extends Specification {

    def "it should initialize"() {
        given:
            Terrain terrain = new Terrain(2)
            Position position = new Position(0, 1)

        when:
            Fox fox = new Fox(terrain, position, randomAge)

        then:
            fox.terrain == terrain
            fox.position == position
            !randomAge ? fox.age == 0 : true

        where:
            randomAge << [true, false]
    }

    def "it should hunt"() {
        given: "a Fox and an adjacent Bunny"
            Terrain terrain = new Terrain(10)
            Position foxPosition = new Position(5, 5)
            Fox fox = createFox(foxPosition, terrain, 0)
            terrain.markAsOccupied(fox)
            Position bunnyPosition = new Position(6, 5)
            Bunny bunny = createBunny(bunnyPosition, terrain)
            terrain.markAsOccupied(bunny)

        when:
            boolean alive = fox.hunt()

        then:
            alive
            !bunny.isAlive()
            fox.foodLevel == fox.MAX_FOOD_LEVEL
            fox.position == bunnyPosition
            terrain.isFree(foxPosition)
            !terrain.isFree(bunnyPosition)
    }

    def "it should move to a free position if there is no adjacent Bunny"() {
        given:
            Terrain terrain = new Terrain(10)
            Position originalPosition = new Position(5, 5)
            Fox fox = createFox(originalPosition, terrain)
            terrain.markAsOccupied(fox)

        when:
            boolean alive = fox.hunt()

        then: "the Fox has moved to an adjacent position"
            alive
            fox.position != originalPosition
            fox.position in terrain.findAllAdjacentPositions(originalPosition)
            terrain.isFree(originalPosition)
    }

    def "it should die of hunger"() {
        given: "a Fox low on food"
            Fox fox = new Fox(new Terrain(1), new Position(0, 0), false)
            fox.foodLevel = 2

        when: "the fox does not eat"
            boolean alive = fox.act([])

        then:
            fox.foodLevel == 1
            alive
            fox.isAlive()

        when: "the fox does not eat again"
            alive = fox.act([])

        then:
            fox.foodLevel == 0
            !alive
            !fox.isAlive()
    }
}
