package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.creatures.Creature
import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createBunny

class WorldSpec extends Specification {

    def "it should initialize world"() {
        given:
            World world = new World()

        when:
            world.create()

        then: "its time is initialized correctly"
            world.time == 0

        and: "its creatures are initialized correctly"
            world.creatures.size() == 100
            world.creatures.every { it.position }

        and: "every creature's position is occupied in the terrain"
            world.creatures.every { !world.terrain.isFree(it.position) }
    }

    def "it should advance time"() {
        given:
            List<Creature> creatures = [Mock(Bunny) {
                3 * act() >> true
            }] * 3
            World world = new World(creatures: creatures)

        when:
            world.advanceTime()

        then: "time is increased"
            world.time == 1

        and: "every creature's act method has been called"
            true
    }

    def "it should remove dead creatures from the world"() {
        given:
            Creature creature = createBunny()
            World world = new World(creatures: [creature])

        when:
            world.removeCreature(creature)

        then: "the create is removed from the world's list of creatures"
            !world.creatures.contains(creature)

        and: "the creature's position in the terrain is marked as free"
            world.terrain.isFree(creature.position)
    }
}
