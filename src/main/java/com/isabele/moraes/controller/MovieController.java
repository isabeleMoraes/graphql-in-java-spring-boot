package com.isabele.moraes.controller;

import com.isabele.moraes.maintainer.ActorsMaintainer;
import com.isabele.moraes.maintainer.MoviesMaintainer;
import com.isabele.moraes.model.Actor;
import com.isabele.moraes.model.Gender;
import com.isabele.moraes.model.Movie;
import com.isabele.moraes.model.UpdateMovieInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MoviesMaintainer moviesBuilder;

    @Autowired
    private ActorsMaintainer actorsBuilder;

    @QueryMapping
    public Movie movieById(@Argument Long id){
        return moviesBuilder.getMovieById(id);
    }

    @QueryMapping("actorById")
    public Actor batatinhaActorById(@Argument Long id){
        return actorsBuilder.getActorById(id);
    }

    @MutationMapping
    public Movie createMovie(@Argument String name, @Argument List<Long> actorIds, @Argument LocalDate releasedDate, @Argument Gender gender){
        return moviesBuilder.addMovie(name, getActorsById(actorIds),releasedDate,gender);
    }

    @QueryMapping
    public List<Movie> findAllMovies(){
        return moviesBuilder.findAll();
    }

    @MutationMapping
    public Movie updateMovie(@Argument("movie") UpdateMovieInput updateMovieInput){
        return moviesBuilder.updateMovie(new Movie(updateMovieInput.id(), updateMovieInput.name(), getActorsById(updateMovieInput.actorIds()), updateMovieInput.releasedDate(), updateMovieInput.gender()));
    }

    private List<Actor> getActorsById(List<Long> actorIds){
        List<Actor> actors = null;
        if(actorIds != null){
            actors = new ArrayList<>(); 
            for(Long id: actorIds){
                actors.add(actorsBuilder.getActorById(id));
            }
        }
        return actors;
    }

    @MutationMapping
    public Movie deleteMovie(@Argument Long id){
        return moviesBuilder.deleteMovie(id);
    }

}
