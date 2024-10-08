package com.isabele.moraes.builder;

import com.isabele.moraes.model.Actor;
import com.isabele.moraes.model.Gender;
import com.isabele.moraes.model.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class MoviesBuilder {

    private final ActorsBuilder actorBuilder = new ActorsBuilder();

    public List<Movie> movies = new ArrayList<>();

    public MoviesBuilder() {
        buildMovies();
    }

    public void buildMovies(){
        movies.add(new Movie(1L, "Ela é o cara", actorBuilder.getActors(1L,2L,3L), LocalDate.of(2006, 9, 29), Gender.COMEDY));
        movies.add(new Movie(2L, "A nova cinderela", actorBuilder.getActors(4L,5L,6L), LocalDate.of(2007, 10, 1), Gender.ROMANCE));
        movies.add(new Movie(3L, "Sexta-Feira Muito Louca", actorBuilder.getActors(7L,8L,5L), LocalDate.of(2003, 8, 6), Gender.COMEDY));
    }

    public Movie getMovieById(Long id){
        return movies.stream().filter(movie -> movie.id().equals(id)).findFirst().get();
    }
    public Movie addMovie(String name, List<Actor> actors, LocalDate releasedDate, Gender gender){
        Long id = new Random().nextLong();
        Movie movie = new Movie(id,name,actors,releasedDate,gender);
        movies.add(movie);

        return movie;
    }

    public List<Movie> findAll(){
        return movies;
    }

    public Movie updateMovie(Movie newMovie){
        Optional<Movie> movieOptional = movies.stream().filter(m -> m.id() == newMovie.id()).findAny();
        Movie oldMovie = null;

        if(movieOptional.isPresent()){
            oldMovie = movieOptional.get();
            int index = movies.indexOf(oldMovie);

            if(newMovie.name() != null){
                oldMovie = oldMovie.withName(newMovie.name());
            }
            if(newMovie.actors() != null){
                oldMovie = oldMovie.withActors(newMovie.actors());
            }
            if(newMovie.gender() != null){
                oldMovie = oldMovie.withGender(newMovie.gender());
            }
            if(newMovie.releasedDate() != null){
                oldMovie = oldMovie.withReleasedDate(newMovie.releasedDate());
            }

            movies.set(index, oldMovie);
        }

        return oldMovie;
    }

    public Movie deleteMovie(Long id){
        Optional<Movie> movieOptional = movies.stream().filter(m -> m.id() == id).findAny();
        Movie movie = null;
        if (movieOptional.isPresent()){
            movie = movieOptional.get();
            movies.remove(movie);
        }

        return movie;
    }

}
