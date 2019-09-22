package dk.stemadsen.ecosystem.model.creatures

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
            !bunny.isDead

        when:
            bunny.act()

        then:
            bunny.isDead
    }
}
