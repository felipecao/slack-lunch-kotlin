package com.github.felipecao.slack.lunch.controller

import com.github.felipecao.slack.lunch.domain.Places
import com.winterbe.expekt.should
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class AddControllerSpek: Spek({

    describe("AddController") {

        val controller = AddController(mock<Places>())
        val userName = randomAlphanumeric(16)

        describe("#addPlace") {

            it("should confirm the place has been added") {
                val placeName = randomAlphanumeric(16)

                with (controller.addPlace(
                        hashMapOf(
                                Pair("user_name", userName),
                                Pair("text", placeName)
                        )
                )) {
                    response_type.should.equal("in_channel")
                    text.should.equal("@$userName your new place *$placeName* has been added!")
                }
            }

            it("should require a name for the place") {
                with (controller.addPlace(
                        hashMapOf(Pair("user_name", userName))
                )) {
                    response_type.should.equal("in_channel")
                    text.should.equal("@$userName you need to provide at least a name for the new place.")
                }
            }

        }
    }
})