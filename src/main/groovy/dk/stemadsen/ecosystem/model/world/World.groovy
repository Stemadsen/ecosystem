package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.creatures.Creature

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile

class World {

    long time = 0
    Terrain terrain = new Terrain(100)
    List<Creature> creatures = []

    void create() {
        spawnCreatures()
    }

    void spawnCreatures() {
        int height = terrain.height
        int width = terrain.width
        int noOfCreatures = 100

        Random random = new Random()
        (1..noOfCreatures).each {
            Position position = new Position(random.nextInt(height), random.nextInt(width))
            while (!terrain.isFree(position)) {
                position.x = random.nextInt(height)
                position.y = random.nextInt(width)
            }
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
