package com.github.felipecao.slack.lunch.controller

import com.github.felipecao.slack.lunch.domain.Places
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/places/random")
class RandomController(@Autowired val places: Places) {

    @RequestMapping(method = arrayOf<RequestMethod>(RequestMethod.POST), produces = arrayOf<String>(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun findRandomPlace(@RequestBody request: Map<String, String>): SlackResponse {
        val places = places.findAll()

        if (places.isEmpty()) {
            return SlackResponse("@${request["user_name"]} there are no places yet! Why don't you try to create the first one by using the `/add` command?")
        }

        val randomIndex = Random().nextInt(places.size)
        val randomPlace = places.get(randomIndex)

        return SlackResponse("@${request["user_name"]} you should have lunch at: *${randomPlace.name}*")
    }
}