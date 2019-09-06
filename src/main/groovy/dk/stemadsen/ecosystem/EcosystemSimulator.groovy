package dk.stemadsen.ecosystem

import dk.stemadsen.ecosystem.utils.FileWriter

class EcosystemSimulator {

    static void main(String[] args) {
        Simulation simulation = new Simulation()

        println "Running simulation ...\n"

        long startTime = System.currentTimeMillis()

        simulation.run()

        long duration = System.currentTimeMillis() - startTime

        println "Simulation over, took $duration milliseconds."
        println "Output saved to $FileWriter.OUTPUT_FILE_PATH"
    }
}
