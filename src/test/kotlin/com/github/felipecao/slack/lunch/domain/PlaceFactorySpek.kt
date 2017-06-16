package com.github.felipecao.slack.lunch.domain

import com.winterbe.expekt.should
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class PlaceFactorySpek : Spek({

    describe("Place.Factory") {

        val userName = randomAlphanumeric(16)
        val placeName = randomAlphanumeric(16)
        val placeUrl = "http://www.${randomAlphanumeric(16)}.com"

        fun buildPlace(request: Map<String, String>): Place? {
            return Place.Factory.build(request)
        }

        it ("should return null if request is empty") {
            val place = buildPlace(hashMapOf())

            place.should.be.`null`
        }

        it ("should return null if request has no text") {
            val place = buildPlace(hashMapOf(Pair("user_name", userName)))

            place.should.be.`null`
        }

        it ("should return place with name only") {
            val place = buildPlace(hashMapOf(
                            Pair("user_name", userName),
                            Pair("text", placeName)
                    ))

            place.should.not.be.`null`
            place?.name.should.equal(placeName)
            place?.url.should.be.`null`
        }

        it ("should return place with name and url") {
            val place = buildPlace(hashMapOf(
                            Pair("user_name", userName),
                            Pair("text", "$placeName $placeUrl")
                    ))

            place.should.not.be.`null`
            place?.name.should.equal(placeName)
            place?.url.should.equal(placeUrl)
        }

    }
})