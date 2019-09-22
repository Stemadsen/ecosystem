package dk.stemadsen.ecosystem.e2e

import dk.stemadsen.ecosystem.EcosystemSimulator
import spock.lang.Specification

class EcosystemSimulatorSpec extends Specification {

    def "it should run application"() {
        when:
            EcosystemSimulator.main()

        then:
            notThrown()
    }
}
