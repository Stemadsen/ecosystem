package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.creatures.Creature
import spock.lang.Ignore
import spock.lang.Specification

class WorldSpec extends Specification {

    def "it should initialize world"() {
        when:
            World world = new World()

        then: "its age is initialized correctly"
            world.age == 0

        and: "its creatures are initialized correctly"
            world.creatures.size() == 100
            world.creatures.every { it.position }

        and: "every creature's position is occupied in the terrain"
            world.creatures.every { !world.terrain.isFree(it.position) }
    }

    @Ignore("java.lang.ArrayIndexOutOfBoundsException: 0")
    def "it should advance time"() {
        given:
            List<Creature> creatures = [new Creature() {
                boolean actHasBeenCalled = false
                @Override
                void act() {
                    actHasBeenCalled = true
                }
            }] * 3
            World world = Mock(World) {
                it.creatures = creatures
            }

        when:
            world.advanceTime()

        then: "age is increased"
            world.age == 1

        and: "every creature's act method has been called"
            creatures.every { it.actHasBeenCalled }
    }
}
