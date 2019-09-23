package dk.stemadsen.ecosystem.e2e

import dk.stemadsen.ecosystem.EcosystemSimulator
import spock.lang.Specification

class EcosystemSimulatorSpec extends Specification {

    def "it should run application"() {
        when: "the application is started"
            EcosystemSimulator.main()

        then: "it's all good"
            true
    }
}
