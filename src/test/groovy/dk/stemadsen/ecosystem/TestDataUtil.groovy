package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.animals.Bunny
import dk.stemadsen.ecosystem.model.animals.Fox
import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

import static dk.stemadsen.ecosystem.utils.RandomGenerator.randomInt

class TestDataUtil {

    private static final TERRAIN_SIZE = 10

    /**
     * Creates a TestAnimal with breeding probability 1, litter size 5, max age 10 and random age.
     */
    static TestAnimal createAnimal(Position position = null, Terrain terrain = null) {
        return new TestAnimal(terrain ?: createTerrain(), position ?: createPosition(), true)
    }

    static Bunny createBunny(Position position = null, Terrain terrain = null) {
        return new Bunny(terrain ?: createTerrain(), position ?: createPosition(), true)
    }

    static Fox createFox(Position position = null, Terrain terrain = null, Integer foodLevel = null) {
        Fox fox = new Fox(terrain ?: createTerrain(), position ?: createPosition(), true)
        fox.foodLevel = foodLevel ?: Fox.MAX_FOOD_LEVEL // make sure it doesn't die right away
        return fox
    }

    static Position createPosition() {
        return new Position(randomInt(TERRAIN_SIZE), randomInt(TERRAIN_SIZE))
    }

    static Terrain createTerrain() {
        return new Terrain(TERRAIN_SIZE)
    }
}
