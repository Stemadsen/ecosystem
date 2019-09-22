package dk.stemadsen.ecosystem.utils

class RandomGenerator {

    private static final Random RANDOM = new Random()

    static final int nextInt(int bound) {
        return RANDOM.nextInt(bound)
    }
}
