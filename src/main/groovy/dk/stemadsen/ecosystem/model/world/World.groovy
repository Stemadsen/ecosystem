package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.creatures.Creature

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile

class World {

    long time
    Terrain terrain = new Terrain(100)
    List<Creature> creatures = []

    void create() {
        time = 0
        spawnCreatures()
    }

    void spawnCreatures() {
        (1..100).each {
            Position position = terrain.newFreePosition
            if (!position)
                return
            Bunny bunny = new Bunny(terrain, position)
            creatures.add(bunny)
            terrain.markAsOccupied(position)
        }
    }

    void advanceTime() {
        time++
        Collection<Creature> deadCreatures = creatures.findAll {
            !it.act()
        }
        deadCreatures.each {
            removeCreature(it)
        }
    }

    private void removeCreature(Creature creature) {
        creatures.remove(creature)
        terrain.markAsFree(creature.getPosition())
    }

    void recordState() {
        writeToOutputFile "$time;${creatures.size()}"
    }
}
