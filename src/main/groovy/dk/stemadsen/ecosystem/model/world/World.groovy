package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.creatures.Creature

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile

class World {

    long age
    Terrain terrain = new Terrain()
    List<Creature> creatures = []

    void create() {
        age = 0
        spawnCreatures()
    }

    void spawnCreatures() {
        (1..100).each {
            Position position = terrain.newFreePosition
            if (!position)
                return
            Bunny bunny = new Bunny(position)
            creatures.add(bunny)
            terrain.setOccupied(position)
        }
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
