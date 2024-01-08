package mk.ukim.finki.labmoviesapp2.domain.movie.retrofit

import mk.ukim.finki.labmoviesapp2.domain.movie.RemoteMovieDataSource
import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse

class RetrofitMovieDataSource(private val movieDbApi: MovieDbApi) : RemoteMovieDataSource {
    override suspend fun search(query: String): List<Movie> {
        val movieResponse = movieDbApi.search(query)
        val responseBody = movieResponse.body()
        if (movieResponse.isSuccessful && responseBody != null) {
            return responseBody.search
        }
        throw Exception(movieResponse.message())

    }

    override suspend fun getMovieDetails(imdbID: String): MovieDetailsResponse {
        val movieResponse = movieDbApi.showDetails(imdbID)
        val responseBody = movieResponse.body()
        if (movieResponse.isSuccessful && responseBody != null) {
            return responseBody
        }
        throw Exception(movieResponse.message())
    }
}