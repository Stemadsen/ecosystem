package dk.stemadsen.ecosystem.utils

class FileWriter {

    static final String UTF_8 = 'UTF-8'
    static final String OUTPUT_DIR_NAME = 'output'
    static final String OUTPUT_FILE_NAME = 'simulation.csv'
    static final String OUTPUT_FILE_PATH = "${OUTPUT_DIR_NAME}\\${OUTPUT_FILE_NAME}"
    static File outputFile

    static void writeToOutputFile(String string) {
        getOutputFile().append("${string}\n", UTF_8)
    }

    static File getOutputFile() {
        if (!outputFile) {
            initOutputFile()
        }
        return outputFile
    }

    /**
     * Initializes (and deletes/clears) the output file.
     */
    static void initOutputFile() {
        File fileDir = new File(OUTPUT_DIR_NAME)
        if (!fileDir.exists()) {
            fileDir.mkdir()
        }
        outputFile = new File(OUTPUT_FILE_PATH)
        outputFile.delete()
    }
}
