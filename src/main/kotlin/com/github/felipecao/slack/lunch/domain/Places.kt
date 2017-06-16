package com.github.felipecao.slack.lunch.domain

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import org.litote.kmongo.save
import org.springframework.stereotype.Component

@Component
class Places {

    val client = KMongo.createClient("localhost", 27017)
    val database: MongoDatabase
    val collection: MongoCollection<Place>
    val COLLECTION_NAME: String = "places"

    init {
        database = client.getDatabase("lunch")
        collection = database.getCollection<Place>(COLLECTION_NAME)
    }

    fun findAll(): List<Place> {
        return collection.find().asSequence().map {
            it
        }.toList()
    }

    fun add(p: Place): Unit {
        collection.save(p)
    }

}