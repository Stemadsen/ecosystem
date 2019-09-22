package dk.stemadsen.ecosystem.model.world

class Terrain {

    Random random = new Random()

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

    Position getNewFreePosition() {
        if (grid.every {
            it.every()
        })
            // No more room
            return null

        Position position = new Position(random.nextInt(height), random.nextInt(width))
        while (!isFree(position)) {
            position.x = random.nextInt(height)
            position.y = random.nextInt(width)
        }
        return position
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
