package com.example.moviemaster.ui.moviedetails.mapper

import com.example.moviemaster.domain.movies.model.cast.Actor
import com.example.moviemaster.ui.ViewMapper
import com.example.moviemaster.ui.moviedetails.model.ActorsDialogView
import javax.inject.Inject

class ActorViewMapper @Inject constructor(): ViewMapper<List<Actor>, ActorsDialogView> {
    override suspend fun mapToView(input: List<Actor>): ActorsDialogView {
        return ActorsDialogView(loading = true, input)
    }
}