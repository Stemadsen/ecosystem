package dk.stemadsen.ecosystem.model.animals

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain
import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createBunny

class BunnySpec extends Specification {

    def "it should update terrain when hopping"() {
        given:
            Position originalPosition = new Position(5, 5)
            Terrain terrain = new Terrain(10)
            terrain.markAsOccupied(originalPosition)
            Bunny bunny = createBunny(originalPosition, terrain)

        when:
            bunny.hop()

        then:
            bunny.position != originalPosition
            terrain.isFree(originalPosition)
            !terrain.isFree(bunny.position)
    }

    def "it should die of old age"() {
        given:
            Bunny bunny = createBunny()

        expect:
            bunny.maxAge == 10

        when:
            bunny.age = bunny.maxAge
            bunny.act([])

        then:
            bunny.alive

        when:
            bunny.act([])

        then:
            !bunny.alive
    }
}
