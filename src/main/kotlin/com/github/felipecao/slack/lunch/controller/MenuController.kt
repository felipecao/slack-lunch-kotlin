package com.github.felipecao.slack.lunch.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/places/menu")
class MenuController {

    @RequestMapping(method = arrayOf<RequestMethod>(RequestMethod.POST), produces = arrayOf<String>(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getMenuText(@RequestBody request: Map<String, String>): SlackResponse {

        val message = "@${request.get("user_name")} these are the commands we have available:\n" +
                "`/add`: add a new place to have lunch\n" +
                "`/show`: shows all places saved to our database\n" +
                "`/random`: picks a random place for you to have lunch\n" +
                "`/menu`: displays this message"

        return SlackResponse(message)
    }
}