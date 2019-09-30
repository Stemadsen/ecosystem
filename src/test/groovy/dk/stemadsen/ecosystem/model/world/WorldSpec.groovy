package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.animals.Bunny
import dk.stemadsen.ecosystem.model.animals.Fox
import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createBunny
import static dk.stemadsen.ecosystem.TestDataUtil.createFox

class WorldSpec extends Specification {

    def "it should initialize world"() {
        given:
            World world = new World()

        expect:
            world.time == 0
            world.bunnies.size() == 32
            world.foxes.size() == 32
            world.animals.size() == 64
            world.animals.every { it.position }
            world.animals.every { !world.terrain.isFree(it.position) }
    }

    // No longer testable
    /*def "it should advance time"() {
        given: "a world with breeding animals"
            int noOfAnimalsToSpawn = 3
            List<Animal> animals = [ createBunny(), createBunny(), createFox() ]
            World world = new World()

        when: "time advances"
            world.advanceTime()

        then: "time is increased"
            world.time == 1

        and: "every parent animal's act method has been called"
            animals.findAll { it.age > 0 }.every { it.actedTimes == 1 }

        and: "the animal's offspring have been saved in the world"
            animals.findAll { it.age == 0 }.size() >= 3
    }*/

    def "it should remove dead animals from the world"() {
        given:
            Bunny bunny = createBunny()
            Fox fox = createFox()
            World world = new World(bunnies: [bunny], foxes: [fox])
            world.terrain.markAsOccupied(bunny)
            world.terrain.markAsOccupied(fox)

        when:
            world.removeAnimal(bunny)
            world.removeAnimal(fox)

        then: "the animals are removed from the world's lists of animals"
            !world.animals.contains(bunny)
            !world.animals.contains(fox)

        and: "the animals' positions in the terrain are now free"
            world.terrain.isFree(bunny.position)
            world.terrain.isFree(fox.position)
    }
}
