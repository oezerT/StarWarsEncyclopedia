package com.example.starwarsencyclopedia.data.remote.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CategoryResponse<T>(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String? = null,
    @SerialName("previous") val previous: String? = null,
    @SerialName("results") val results: List<T> = emptyList()
)
