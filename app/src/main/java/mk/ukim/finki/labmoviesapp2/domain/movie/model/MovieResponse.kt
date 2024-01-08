package mk.ukim.finki.labmoviesapp2.domain.movie.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search") val search: List<Movie>
)