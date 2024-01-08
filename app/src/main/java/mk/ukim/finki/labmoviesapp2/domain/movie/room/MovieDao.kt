package mk.ukim.finki.labmoviesapp2.domain.movie.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse

// Dao is an interface to the Database
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    // :id - argument from the function inside of the query
    @Query("DELETE FROM movie WHERE imdbID=:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    // :query - argument from the function inside of the query
    @Query("SELECT * FROM movie WHERE title LIKE :query")
    suspend fun searchMovies(query: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(movie: MovieDetailsResponse)

    @Delete
    suspend fun deleteDetails(movie: MovieDetailsResponse)

    @Query("SELECT * FROM movieDetailsResponse WHERE imdbId=:imdbId")
    suspend fun searchMovieDetails(imdbId: String): MovieDetailsResponse?


}