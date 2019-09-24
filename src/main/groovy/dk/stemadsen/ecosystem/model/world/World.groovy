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

    void advanceTime() {
        time++
        letAnimalsAct()
    }

    void recordState() {
        writeToOutputFile "$time;${animals.size()}"
    }

    private void spawnAnimals() {
        int height = terrain.height
        int width = terrain.width
        int noOfAnimals = 2

        (1..noOfAnimals).each {
            Position position = new Position(randomInt(height), randomInt(width))
            while (!terrain.isFree(position)) {
                position.x = randomInt(height)
                position.y = randomInt(width)
            }
            animals.add(new Bunny(terrain, position, true))
            terrain.markAsOccupied(position)
        }
    }

    private void letAnimalsAct() {
        List<Animal> newBorns = []
        List<Animal> deadAnimals = animals.findAll {
            return !it.act(newBorns)
        }
        // Since the newborns are not added to the animals list before this moment,
        // they do not get a chance to act this time around. This is intentional.
        animals.addAll(newBorns)
        deadAnimals.each {
            removeAnimal(it)
        }
    }

    private void removeAnimal(Animal animal) {
        animals.remove(animal)
        terrain.markAsFree(animal.getPosition()) // TODO This should be done by the animal to avoid delay
    }
}
