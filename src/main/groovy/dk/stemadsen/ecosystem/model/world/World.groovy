package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.creatures.Creature

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile

class World {

    long age
    Terrain terrain = new Terrain(100)
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
            Bunny bunny = new Bunny(terrain, position)
            creatures.add(bunny)
            terrain.markAsOccupied(position)
        }
    }

    void advanceTime() {
        age++
        creatures.each {
            boolean isDead = !it.act()
            if (isDead)
                removeCreature(it)
        }
    }

    private void removeCreature(Creature creature) {
        creatures.remove(creature)
        terrain.markAsFree(creature.getPosition())
    }

    void recordState() {
        writeToOutputFile "$age;${creatures[0].getPosition().x};${creatures[0].getPosition().y}"
    }
}
