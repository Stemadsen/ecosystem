package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

class TestDataUtil {

    private static Random random = new Random()

    static Bunny createBunny() {
        return new Bunny(new Terrain(100), new Position(random.nextInt(10), random.nextInt(10)))
    }
}
