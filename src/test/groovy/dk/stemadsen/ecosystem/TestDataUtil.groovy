package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.animals.Bunny
import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

import static dk.stemadsen.ecosystem.utils.RandomGenerator.randomInt

class TestDataUtil {

    private static final TERRAIN_SIZE = 100

    /**
     * Creates a TestAnimal with breeding probability 1, litter size 5, max age 10 and random age.
     */
    static TestAnimal createAnimal(Position position = null) {
        return new TestAnimal(createTerrain(), position ?: createPosition(), true)
    }

    static Bunny createBunny(Position position = null, Terrain terrain = null) {
        return new Bunny(terrain ?: createTerrain(), position ?: createPosition(), true)
    }

    static Position createPosition() {
        return new Position(randomInt(TERRAIN_SIZE), randomInt(TERRAIN_SIZE))
    }

    static Terrain createTerrain() {
        return new Terrain(TERRAIN_SIZE)
    }
}
