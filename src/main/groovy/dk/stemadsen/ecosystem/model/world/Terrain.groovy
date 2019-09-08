package dk.stemadsen.ecosystem.model.world

class Terrain {

    Random random = new Random()

    final static int X_LENGTH = 100
    final static int Y_LENGTH = 100
    /** An array of arrays where each cell value indicates whether it is occupied. */
    Boolean[][] grid = new Boolean[X_LENGTH][Y_LENGTH]

    Position getNewFreePosition() {
        if (grid.every {
            it.every()
        }) {
            // No more room
            return null
        }

        Position position = new Position(random.nextInt(X_LENGTH), random.nextInt(Y_LENGTH))
        while (!isFree(position)) {
            position.x = random.nextInt(X_LENGTH)
            position.y = random.nextInt(Y_LENGTH)
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
