package com.isabele.moraes.maintainer;

import com.isabele.moraes.model.Actor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActorsMaintainer {
    public List<Actor> actors = new ArrayList<>();

    public ActorsMaintainer() {
        buildActors();
    }

    private void buildActors(){
        actors.add(new Actor(1L, "Amanda Bines", LocalDate.of(1986,4,3)));
        actors.add(new Actor(2L, "Channing Tatum", LocalDate.of(1980,4,26)));
        actors.add(new Actor(3L, "Alexandra Breckenrige", LocalDate.of(1982,5,15)));
        actors.add(new Actor(4L, "Hilary Duff", LocalDate.of(1987,9,28)));
        actors.add(new Actor(5L, "Chad Michael Murray", LocalDate.of(1981,8,24)));
        actors.add(new Actor(6L, "Dan Byrd", LocalDate.of(1985,11,20)));
        actors.add(new Actor(7L, "Lindsay Lohan", LocalDate.of(1986,7,2)));
        actors.add(new Actor(8L, "Jamie Lee Curtis", LocalDate.of(1958,11,22)));
    }

    public List<Actor> getActors(Long... ids){
        List<Actor> actorList = new ArrayList<>();

        for(Long id: ids){
            actorList.add(getActorById(id));
        }

        return actorList;
    }

    public Actor getActorById(Long id){
        return actors.stream().filter(actor -> actor.id().equals(id)).findFirst().get();
    }

}
