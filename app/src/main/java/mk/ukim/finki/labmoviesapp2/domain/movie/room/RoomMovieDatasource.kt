package mk.ukim.finki.labmoviesapp2.domain.movie.room

import mk.ukim.finki.labmoviesapp2.domain.movie.LocalMovieDataSource
import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse

class RoomMovieDataSource(private val movieDao: MovieDao) : LocalMovieDataSource {

    // Movie List:
    override suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    override suspend fun saveAll(movies: List<Movie>) {
        for (movie in movies) {
            movieDao.insert(movie)
        }
    }

    override suspend fun delete(id: Int) {
        movieDao.delete(id)
    }

    override suspend fun getAll(): List<Movie> {
        return movieDao.getAll()
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return movieDao.searchMovies(query)
    }


    // MovieDetails:
    override suspend fun insertDetails(movieDetails: MovieDetailsResponse) {
        movieDao.insertDetails(movieDetails)
    }

    override suspend fun searchMovieDetails(imdbId: String): MovieDetailsResponse? {
        return movieDao.searchMovieDetails(imdbId)
    }
}