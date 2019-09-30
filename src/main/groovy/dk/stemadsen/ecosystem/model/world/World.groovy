package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.animals.Bunny
import dk.stemadsen.ecosystem.model.animals.Animal
import dk.stemadsen.ecosystem.model.animals.Fox

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile
import static dk.stemadsen.ecosystem.utils.RandomGenerator.randomInt

class World {

    long time = 0
    Terrain terrain = new Terrain(64)
    List<Bunny> bunnies = []
    List<Fox> foxes = []

    World() {
        spawnAnimals()
    }

    void advanceTime() {
        time++
        letAnimalsAct()
    }

    void recordState() {
        writeToOutputFile "$time;${bunnies.size()};${foxes.size()}"
    }

    List<Animal> getAnimals() {
        return bunnies + foxes as List<Animal>
    }

    private void spawnAnimals() {
        int noOfBunnies = 32
        int noOfFoxes = 32

        (1..noOfBunnies).each {
            Position position = newFreePosition()
            Bunny bunny = new Bunny(terrain, position, true)
            terrain.markAsOccupied(bunny)
            bunnies.add(bunny)
        }
        (1..noOfFoxes).each {
            Position position = newFreePosition()
            Fox fox = new Fox(terrain, position, true)
            terrain.markAsOccupied(fox)
            foxes.add(fox)
        }
    }

    private Position newFreePosition() {
        // TODO Remove from maintained list of free positions instead
        int height = terrain.height
        int width = terrain.width
        Position position = new Position(randomInt(height), randomInt(width))
        while (!terrain.isFree(position)) {
            position.x = randomInt(height)
            position.y = randomInt(width)
        }
        return position
    }

    private void letAnimalsAct() {
        List<Animal> newBorns = []
        List<Animal> deadAnimals = animals.findAll {
            return !it.act(newBorns)
        }
        // Since the newborns are not added to the animals list before this moment,
        // they do not get a chance to act this time around. This is intentional.
        addAnimals(newBorns)
        deadAnimals.each {
            removeAnimal(it)
        }
    }

    private void addAnimals(Collection<Animal> animals) {
        animals.each {
            if (it instanceof Bunny)
                bunnies.add(it)
            else if (it instanceof Fox)
                foxes.add(it)
            else
                throw new RuntimeException("No such species: $it")
        }
    }

    private void removeAnimal(Animal animal) {
        if (animal instanceof Bunny)
            bunnies.remove(animal)
        else if (animal instanceof Fox)
            foxes.remove(animal)
        else
            throw new RuntimeException("No such species: $animal")
        terrain.markAsFree(animal.getPosition()) // TODO This should be done by the animal to avoid delay
    }
}
