package com.github.felipecao.slack.lunch.controller

import com.github.felipecao.slack.lunch.domain.Places
import com.github.felipecao.slack.lunch.domain.Place
import com.mongodb.client.FindIterable
import com.winterbe.expekt.should
import org.apache.commons.lang3.RandomStringUtils
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(JUnitPlatform::class)
class RandomControllerSpek: Spek({

    val WALLABIES_THAI = "Wallabies Thai"
    val CHINA_IN_BOX = "China in Box"

    describe("RandomController") {

        val places = mock<Places>()
        val controller = RandomController(places)
        val userName = RandomStringUtils.randomAlphanumeric(16)
        val request = hashMapOf(Pair("user_name", userName))

        describe("#getAllPlaces") {

            it("should return the name of a random place that exists in the storage") {
                `when`(places.findAll()).thenReturn(arrayListOf<Place>(Place(WALLABIES_THAI), Place(CHINA_IN_BOX)))

                with (controller.findRandomPlace(request)) {
                    val randomPlaceName = text.substring(text.indexOf("*") + 1, text.lastIndexOf("*"))

                    response_type.should.equal("in_channel")
                    text.should.contain("@$userName you should have lunch at: ")
                    arrayListOf(WALLABIES_THAI, CHINA_IN_BOX).should.contain(randomPlaceName)
                }
            }

            it("should suggest using `/add` in case there are no places") {
                `when`(places.findAll()).thenReturn(arrayListOf<Place>())

                with (controller.findRandomPlace(request)) {
                    response_type.should.equal("in_channel")
                    text.should.equal("@$userName there are no places yet! Why don't you try to create the first one by using the `/add` command?")
                }
            }

        }
    }
})