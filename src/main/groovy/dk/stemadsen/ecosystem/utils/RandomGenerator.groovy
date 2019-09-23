package dk.stemadsen.ecosystem.utils

class RandomGenerator {

    private static final Random RANDOM = new Random()

    static final int randomInt(int bound) {
        return RANDOM.nextInt(bound)
    }
}
