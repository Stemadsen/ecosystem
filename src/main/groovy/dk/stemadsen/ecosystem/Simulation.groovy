package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.world.World

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile

class Simulation {

    long noOfIterations

    Simulation(long noOfIterations = 500) {
        this.noOfIterations = noOfIterations
        prepareRecordState()
    }

    void run() {
        World world = new World()
        world.recordState()
        for (it in (1..noOfIterations)) {
            world.advanceTime()
            world.recordState()
            if (world.animals.isEmpty()) {
                println "Population died out after $it iterations!"
                return
            }
        }
        println "Animals in the end: ${world.animals.size()}"
    }

    private static void prepareRecordState() {
        writeToOutputFile "time;animals"
    }
}
