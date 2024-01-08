package mk.ukim.finki.labmoviesapp2.adapters

import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mk.ukim.finki.labmoviesapp2.R
import mk.ukim.finki.labmoviesapp2.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp2.ui.movies.SecondFragment

class MovieAdapter(
    private val movies: ArrayList<Movie> = ArrayList<Movie>(),
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    class MovieViewHolder(view: View, private val fragmentManager: FragmentManager) :
        RecyclerView.ViewHolder(view) {
        private val movieTitle: TextView = view.findViewById(R.id.movie_title)
        private val movieImage: ImageView = view.findViewById(R.id.movie_image)
        private val movieYear: TextView = view.findViewById(R.id.movie_year_text)
        private val movieBtn: Button = view.findViewById(R.id.movie_btn)

        private var imdbID: String = ""

        fun bind(movie: Movie) {
            movieTitle.text = movie.title
            movieYear.text = movie.year.toString()
            Glide.with(movieImage).load(movie.poster).centerCrop()
                .placeholder(R.drawable.empty_movie).into(movieImage)

            // Set IMDb ID
            imdbID = movie.imdbID

            // Set click listener for the button
            movieBtn.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, SecondFragment().apply {
                        arguments = Bundle().apply {
                            putString("imdbID", imdbID)
                        }
                    })
                    .addToBackStack(null)
                    .commit()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view, fragmentManager)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        if (movies != null) {
            this.movies.addAll(movies)
        }
        notifyDataSetChanged()
    }

}