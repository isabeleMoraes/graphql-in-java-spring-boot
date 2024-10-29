package com.isabele.moraes.manual.resolver;

import com.isabele.moraes.maintainer.MoviesMaintainer;
import com.isabele.moraes.model.Movie;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public class QueryMovieResolver {

    private static MoviesMaintainer moviesBuilder = new MoviesMaintainer();

    public static Movie findMovieById(DataFetchingEnvironment environment){
        Long id = Long.parseLong(environment.getArgument("id"));

        return moviesBuilder.getMovieById(id);
    }

    public static List<Movie> findAllMovies(DataFetchingEnvironment environment){
        return moviesBuilder.findAll();
    }
}
