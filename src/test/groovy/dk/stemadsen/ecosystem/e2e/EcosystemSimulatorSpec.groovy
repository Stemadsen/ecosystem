package dk.stemadsen.ecosystem.e2e

import dk.stemadsen.ecosystem.EcosystemSimulator
import spock.lang.Ignore
import spock.lang.Specification

class EcosystemSimulatorSpec extends Specification {

    @Ignore
    def "it should run application"() {
        when: "the application is started"
            EcosystemSimulator.main()

        then: "it's all good"
            true
    }
}
