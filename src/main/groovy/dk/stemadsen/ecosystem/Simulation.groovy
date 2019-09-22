package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.world.World

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile

class Simulation {

    long numberOfCycles

    Simulation(long numberOfCycles = 100) {
        this.numberOfCycles = numberOfCycles
        prepareRecordState()
    }

    void run() {
        World world = new World()
        world.create()
        (1..numberOfCycles).each {
            world.recordState()
            world.advanceTime()
        }
        world.recordState()
    }

    private static void prepareRecordState() {
        writeToOutputFile "time;creatures"
    }
}
