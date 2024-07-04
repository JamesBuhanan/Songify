package com.songify.library.spotify.usecase

import com.songify.library.spotify.model.Category

interface GetCategories {
    suspend operator fun invoke(): List<Category>
}