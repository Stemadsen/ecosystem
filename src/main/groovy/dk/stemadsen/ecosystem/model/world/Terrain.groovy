package dk.stemadsen.ecosystem.model.world

class Terrain {

    Random random = new Random()

    private final int X_LENGTH = 100
    private final int Y_LENGTH = 100
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

    void setOccupied(Position position) {
        grid[position.x][position.y] = true
    }

    boolean isFree(Position position) {
        return !grid[position.x][position.y]
    }
}
