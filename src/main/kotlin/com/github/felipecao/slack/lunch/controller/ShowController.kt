package com.github.felipecao.slack.lunch.controller

import com.github.felipecao.slack.lunch.domain.Places
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/places/show")
class ShowController(@Autowired val places: Places) {

    @RequestMapping(method = arrayOf<RequestMethod>(RequestMethod.POST), produces = arrayOf<String>(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getAllPlaces(@RequestBody request: Map<String, String>): SlackResponse {
        val places = places.findAll()

        if (places.isEmpty()) {
            return SlackResponse("@${request["user_name"]} there are no places yet! Why don't you try to create the first one by using the `/add` command?")
        }

        val names = places.map { p -> "*${p.name}*" }.joinToString("\n")

        return SlackResponse("@${request["user_name"]} these are the places in our database:\n${names}")
    }
}