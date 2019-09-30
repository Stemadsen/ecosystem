package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.model.world.World

import static dk.stemadsen.ecosystem.utils.FileWriter.writeToOutputFile

class Simulation {

    long noOfIterations

    Simulation(long noOfIterations) {
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
                println "Animals died out after $it iterations!"
                return
            }
        }
        println "(Bunnies, Foxes) in the end: (${world.bunnies.size()}, ${world.foxes.size()})"
    }

    private static void prepareRecordState() {
        writeToOutputFile "Time;Bunnies;Foxes"
    }
}
