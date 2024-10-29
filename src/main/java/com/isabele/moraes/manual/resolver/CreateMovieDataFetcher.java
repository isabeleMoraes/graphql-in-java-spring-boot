package com.isabele.moraes.manual.resolver;

import com.isabele.moraes.maintainer.ActorsMaintainer;
import com.isabele.moraes.maintainer.MoviesMaintainer;
import com.isabele.moraes.model.Actor;
import com.isabele.moraes.model.Gender;
import com.isabele.moraes.model.Movie;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CreateMovieDataFetcher implements DataFetcher<Movie> {

    private MoviesMaintainer moviesBuilder = new MoviesMaintainer();

    private ActorsMaintainer actorsBuilder = new ActorsMaintainer();

    @Override
    public Movie get(DataFetchingEnvironment environment) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //Recebe por parametro um objeto com os parametros enviados na request
        //Extrai os parametros passados.
        String name = environment.getArgument("name");
        List<String> actorIds = environment.getArgument("actorIds");
        LocalDate releasedDate = LocalDate.parse(environment.getArgument("releasedDate"), formatter);
        Gender gender = Gender.valueOf(environment.getArgument("gender"));

        return moviesBuilder.addMovie(name, getActorsById(actorIds),releasedDate,gender);
    }

    private List<Actor> getActorsById(List<String> actorIds){
        List<Actor> actors = null;
        if(actorIds != null){
            actors = new ArrayList<>();
            for(String id: actorIds){
                actors.add(actorsBuilder.getActorById(Long.valueOf(id)));
            }
        }
        return actors;
    }
}
