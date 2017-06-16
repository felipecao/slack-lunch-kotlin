package com.github.felipecao.slack.lunch.controller

import org.mockito.Mockito

// @see https://android.jlelse.eu/keddit-part-9-unit-test-with-kotlin-mockito-spek-76709812e3b6
inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

