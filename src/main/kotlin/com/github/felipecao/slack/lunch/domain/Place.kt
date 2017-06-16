package com.github.felipecao.slack.lunch.domain

data class Place (val name: String, val url: String? = null) {
    companion object Factory {
        fun build(requestBody: Map<String, String>): Place? {

            val text = requestBody["text"]

            if (null == text) {
                return null
            }

            val nameAndUrl = separateNameAndUrl(text)

            if (null != nameAndUrl.component2()) {
                return Place(nameAndUrl.component1(), nameAndUrl.component2())
            }

            return Place(nameAndUrl.component1())
        }

        private fun separateNameAndUrl(text: String): Pair<String, String?> {
            val splitText = " http"

            if(!text.contains(splitText)) {
                return Pair(text, null)
            }

            val name = text.substring(0, text.indexOf(splitText))
            val url = text.substring(text.indexOf(splitText) + 1, text.length)

            return Pair(name, url)
        }
    }
}
