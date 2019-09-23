package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.animals.Bunny
import dk.stemadsen.ecosystem.model.animals.Animal

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile
import static dk.stemadsen.ecosystem.utils.RandomGenerator.randomInt

class World {

    long time = 0
    Terrain terrain = new Terrain(100)
    List<Animal> animals = []

    void create() {
        spawnAnimals()
    }

    void spawnAnimals() {
        int height = terrain.height
        int width = terrain.width
        int noOfAnimals = 100

        (1..noOfAnimals).each {
            Position position = new Position(randomInt(height), randomInt(width))
            while (!terrain.isFree(position)) {
                position.x = randomInt(height)
                position.y = randomInt(width)
            }
            Bunny bunny = new Bunny(terrain, position)
            animals.add(bunny)
            terrain.markAsOccupied(position)
        }
    }

    void advanceTime() {
        time++
        Collection<Animal> deadAnimals = animals.findAll {
            !it.act()
        }
        deadAnimals.each {
            removeAnimal(it)
        }
    }

    private void removeAnimal(Animal animal) {
        animals.remove(animal)
        terrain.markAsFree(animal.getPosition())
    }

    void recordState() {
        writeToOutputFile "$time;${animals.size()}"
    }
}
