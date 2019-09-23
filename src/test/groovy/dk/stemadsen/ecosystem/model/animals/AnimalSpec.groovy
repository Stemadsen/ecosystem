package dk.stemadsen.ecosystem.model.animals

import dk.stemadsen.ecosystem.model.world.Position
import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createAnimal

class AnimalSpec extends Specification {

    def "it should breed"() {
        given: "an animal with breeding probability 1 and plenty of space"
            Position parentPosition = new Position(5, 5)
            Animal animal = createAnimal(parentPosition)
            Collection<Animal> newBornsOut = []

        when:
            animal.breed(newBornsOut)

        then: "the animal has born 4 youngs around its own position"
            newBornsOut.size() == 4
            newBornsOut.count { it.position.x == parentPosition.x } == 2
            newBornsOut.count { it.position.y == parentPosition.y } == 2
    }
}
