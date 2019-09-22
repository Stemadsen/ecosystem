package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.animals.Bunny
import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

import static dk.stemadsen.ecosystem.utils.RandomGenerator.nextInt

class TestDataUtil {

    private static final TERRAIN_SIZE = 100

    static Bunny createBunny() {
        return new Bunny(new Terrain(TERRAIN_SIZE), new Position(nextInt(TERRAIN_SIZE), nextInt(TERRAIN_SIZE)))
    }
}
