package mk.ukim.finki.labmoviesapp2.domain.movie.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.domain.movie.model.MovieDetailsResponse


@Database(entities = [Movie::class, MovieDetailsResponse::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    // Static object that will represent the Database
    // Singleton Function that will always return 1 instance of db
    companion object {


        // Volatile = JVM to know this instance can be changed from multiple threads and any change to be sent to all the other threads
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic // jvm to know this is a static function
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "movies_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}