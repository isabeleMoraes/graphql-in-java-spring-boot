package com.isabele.moraes.builder;

import com.isabele.moraes.model.Gender;
import com.isabele.moraes.model.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

}
