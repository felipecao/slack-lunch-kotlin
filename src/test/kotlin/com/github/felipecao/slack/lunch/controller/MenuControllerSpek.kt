package com.github.felipecao.slack.lunch.controller

import com.winterbe.expekt.should
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class MenuControllerSpek : Spek({

    describe("MenuController") {
        val controller = MenuController()
        val userName = randomAlphanumeric(16)
        val request = hashMapOf(Pair("user_name", userName))

        describe("#getMenuText") {
            it("should return a fixed text containing the user name") {
                with (controller.getMenuText(request)) {
                    response_type.should.equal("in_channel")
                    text.should.equal("""
                    |@$userName these are the commands we have available:
                    |`/add`: add a new place to have lunch
                    |`/show`: shows all places saved to our database
                    |`/random`: picks a random place for you to have lunch
                    |`/menu`: displays this message
                    """.trimMargin())
                }
            }
        }
    }

})