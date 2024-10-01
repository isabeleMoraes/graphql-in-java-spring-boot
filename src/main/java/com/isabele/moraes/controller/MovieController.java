package com.isabele.moraes.controller;

import com.isabele.moraes.builder.ActorsBuilder;
import com.isabele.moraes.builder.MoviesBuilder;
import com.isabele.moraes.model.Actor;
import com.isabele.moraes.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MovieController {

    @Autowired
    private MoviesBuilder moviesBuilder;

    @Autowired
    private ActorsBuilder actorsBuilder;

    @QueryMapping
    public Movie movieById(@Argument Long id){
        return moviesBuilder.getMovieById(id);
    }

    @QueryMapping("actorById")
    public Actor batatinhaActorById(@Argument Long id){
        return actorsBuilder.getActorById(id);
    }

    //TODO: O que ainda desejo fazer
    /**
     * Desenvolver esse mesmo controller no java puro para entender o que tem por baixo das anotations.
     * Desenvolver os testes de integração descente, nao só o slice of teste
     */

}
