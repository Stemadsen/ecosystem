package dk.stemadsen.ecosystem.model.world

import dk.stemadsen.ecosystem.model.animals.Animal
import dk.stemadsen.ecosystem.model.animals.Bunny

import static dk.stemadsen.ecosystem.utils.RandomGenerator.randomInt

class Terrain {

    int height
    int width
    /** The positions of the terrain, containing the animals occupying those positions. */
    Animal[][] grid

    Terrain(int height, int width) {
        this.height = height
        this.width = width
        grid = new Animal[height][width]
    }

    Terrain(int size) {
        this(size, size)
    }

    /**
     * Returns a random free position adjacent (not diagonally) to the given position, if one exists, or null otherwise.
     */
    Position findFreeAdjacentPosition(Position position) {
        List<Position> freeAdjacentPositions = findAllFreeAdjacentPositions(position)
        if (!freeAdjacentPositions)
            return null
        return freeAdjacentPositions[randomInt(freeAdjacentPositions.size())]
    }

    /**
     * Finds all free positions adjacent (not diagonally) to the given position.
     */
    List<Position> findAllFreeAdjacentPositions(Position position) {
        List<Position> adjacentPositions = findAllAdjacentPositions(position)
        return adjacentPositions.findAll { isFree(it) }
    }

    /**
     * Returns a random Bunny adjacent (not diagonally) to the given position, if one exists, or null otherwise.
     */
    Bunny findAdjacentBunny(Position position) {
        List<Bunny> adjacentBunnies = findAllAdjacentBunnies(position)
        if (!adjacentBunnies)
            return null
        return adjacentBunnies[randomInt(adjacentBunnies.size())]
    }

    /**
     * Finds all Bunnies adjacent (not diagonally) to the given position.
     */
    List<Bunny> findAllAdjacentBunnies(Position position) {
        List<Position> adjacentPositions = findAllAdjacentPositions(position)
        return adjacentPositions.findResults {
            Animal animal = grid[it.x][it.y]
            return animal instanceof Bunny ? animal : null
        }
    }

    /**
     * Finds all positions adjacent (not diagonally) to the given position.
     */
    List<Position> findAllAdjacentPositions(Position position) {
        List<Position> adjacentPositions = []
        int x = position.x
        int y = position.y
        if (x > 0) {
            adjacentPositions.add(new Position(x - 1, y))
        }
        if (y > 0) {
            adjacentPositions.add(new Position(x, y - 1))
        }
        if (y < width - 1) {
            adjacentPositions.add(new Position(x, y + 1))
        }
        if (x < height - 1) {
            adjacentPositions.add(new Position(x + 1, y))
        }
        return adjacentPositions
    }

    void markAsFree(Position position) {
        grid[position.x][position.y] = null
    }

    /**
     * Marks the animal's position in the terrain as occupied by the animal.
     */
    void markAsOccupied(Animal animal) {
        grid[animal.position.x][animal.position.y] = animal
    }

    boolean isFree(Position position) {
        return !grid[position.x][position.y]
    }
}
