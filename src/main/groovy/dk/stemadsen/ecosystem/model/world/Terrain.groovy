package dk.stemadsen.ecosystem.model.world

import static dk.stemadsen.ecosystem.utils.RandomGenerator.nextInt

class Terrain {

    int height
    int width
    /** The cells of the terrain, each boolean indicating whether that cell is occupied. */
    boolean[][] grid

    Terrain(int height, int width) {
        this.height = height
        this.width = width
        grid = new boolean[height][width]
    }

    Terrain(int size) {
        this(size, size)
    }

    /**
     * Returns a random free position adjacent (not diagonally) to the given position, if one exists, or null otherwise.
     */
    Position findFreeAdjacentPosition(Position position) {
        List<Position> freeNeighbors = findAllFreeAdjacentPositions(position)
        if (!freeNeighbors)
            return null
        return freeNeighbors[nextInt(freeNeighbors.size())]
    }

    /**
     * Finds all free positions adjacent (not diagonally) to the given position.
     */
    List<Position> findAllFreeAdjacentPositions(Position position) {
        List<Position> freeNeighbors = []
        int x = position.x
        int y = position.y
        if (x > 0) {
            Position upperNeighbor = new Position(x - 1, y)
            if (isFree(upperNeighbor))
                freeNeighbors.add(upperNeighbor)
        }
        if (y > 0) {
            Position leftNeighbor = new Position(x, y - 1)
            if (isFree(leftNeighbor))
                freeNeighbors.add(leftNeighbor)
        }
        if (y < width - 1) {
            Position rightNeighbor = new Position(x, y + 1)
            if (isFree(rightNeighbor))
                freeNeighbors.add(rightNeighbor)
        }
        if (x < height - 1) {
            Position lowerNeighbor = new Position(x + 1, y)
            if (isFree(lowerNeighbor))
                freeNeighbors.add(lowerNeighbor)
        }
        return freeNeighbors
    }

    void markAsFree(Position position) {
        grid[position.x][position.y] = false
    }

    void markAsOccupied(Position position) {
        grid[position.x][position.y] = true
    }

    boolean isFree(Position position) {
        return !grid[position.x][position.y]
    }
}
