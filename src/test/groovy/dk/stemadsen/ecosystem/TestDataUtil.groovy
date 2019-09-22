package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.creatures.Bunny
import dk.stemadsen.ecosystem.model.world.Position

class TestDataUtil {

    private static Random random = new Random()

    static Bunny createBunny() {
        return new Bunny(new Position(random.nextInt(10), random.nextInt(10)))
    }
}
