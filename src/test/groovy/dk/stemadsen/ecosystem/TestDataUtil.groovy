package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.world.Position
import dk.stemadsen.ecosystem.model.world.Terrain

class TestDataUtil {

    private static Random random = new Random()

    static Bunny createBunny() {
        return new Bunny(new Position(random.nextInt(Terrain.X_LENGTH), random.nextInt(Terrain.Y_LENGTH)))
    }
}
