package com.github.felipecao.slack.lunch.controller

import com.github.felipecao.slack.lunch.domain.Place
import com.github.felipecao.slack.lunch.domain.Places
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/places/add")
class AddController(@Autowired val places: Places) {

    @RequestMapping(method = arrayOf<RequestMethod>(RequestMethod.POST), produces = arrayOf<String>(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun addPlace(@RequestBody request: Map<String, String>): SlackResponse {

        val newPlace = Place.Factory.build(request)
        val userName = request.get("user_name")

        if (null == newPlace) {
            return SlackResponse("@${userName} you need to provide at least a name for the new place.")
        }

        places.add(newPlace)

        return SlackResponse("@${userName} your new place *${newPlace.name}* has been added!")
    }
}