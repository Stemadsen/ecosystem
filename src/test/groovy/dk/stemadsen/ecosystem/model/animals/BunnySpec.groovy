package dk.stemadsen.ecosystem.model.animals

import spock.lang.Specification

import static dk.stemadsen.ecosystem.TestDataUtil.createBunny

class BunnySpec extends Specification {

    def "it should die of old age"() {
        given:
            Bunny bunny = createBunny()

        expect:
            bunny.maxAge == 10

        when:
            bunny.age = bunny.maxAge
            bunny.act()

        then:
            bunny.alive

        when:
            bunny.act()

        then:
            !bunny.alive
    }
}