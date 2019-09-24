package dk.stemadsen.ecosystem.model.animals

import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain
import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createAnimal

class AnimalSpec extends Specification {

    def "it should breed"() {
        given: "an animal with breeding probability 1 and plenty of space"
            Position parentPosition = new Position(5, 5)
            Terrain terrain = new Terrain(10)
            Animal animal = createAnimal(parentPosition, terrain)
            List<Animal> newBorns = []

        when:
            animal.breed(newBorns)

        then: "the animal has born 4 youngs around its own position"
            newBorns.size() == 4
            newBorns.count { it.position.x == parentPosition.x } == 2
            newBorns.count { it.position.y == parentPosition.y } == 2

        and: "the terrain is marked as occupied where the youngs were born"
            newBorns.every {
                !terrain.isFree(it.position)
            }
    }
}
