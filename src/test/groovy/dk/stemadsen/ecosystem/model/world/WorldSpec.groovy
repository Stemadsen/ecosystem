package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.TestAnimal
import dk.stemadsen.ecosystem.model.animals.Animal
import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createAnimal
import static dk.stemadsen.ecosystem.TestDataUtil.createBunny

class WorldSpec extends Specification {

    def "it should initialize world"() {
        given:
            World world = new World()

        expect: "its time is initialized correctly"
            world.time == 0

        when:
            world.create()

        then: "its animals are initialized correctly"
            world.animals.size() == 2
            world.animals.every { it.position }

        and: "every animal's position is occupied in the terrain"
            world.animals.every { !world.terrain.isFree(it.position) }
    }

    def "it should advance time"() {
        given: "a world with breeding animals"
            int noOfAnimalsToSpawn = 3
            List<TestAnimal> animals = (1..noOfAnimalsToSpawn).collect { createAnimal() }
            World world = new World(animals: animals)

        when: "time advances"
            world.advanceTime()

            List<TestAnimal> parents = animals.findAll { it.age > 0 }
            List<Animal> offspring = animals.findAll { it.age == 0 } as List<Animal>

        then: "time is increased"
            world.time == 1

        and: "every parent's act method has been called"
            parents.every { it.actedTimes == 1 }

        and: "the animal's offspring have been saved in the world"
            offspring.size() >= 3
    }

    def "it should remove dead animals from the world"() {
        given:
            Animal animal = createBunny()
            World world = new World(animals: [animal])

        when:
            world.removeAnimal(animal)

        then: "the animal is removed from the world's list of animals"
            !world.animals.contains(animal)

        and: "the animal's position in the terrain is marked as free"
            world.terrain.isFree(animal.position)
    }
}
