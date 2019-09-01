package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.creatures.Creature

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile

class World {

    long age
    List<Creature> creatures = []

    World() {
        age = 0
        createCreates()
    }

    void createCreates() {
        creatures += [new Bunny()]
    }

    void advanceTime() {
        age++
        creatures.each {
            it.act()
        }
    }

    void recordState() {
        writeToOutputFile "$age;${creatures[0].position.x};${creatures[0].position.y}"
    }
}
