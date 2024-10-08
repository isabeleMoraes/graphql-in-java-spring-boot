package com.isabele.moraes.model;

import java.time.LocalDate;
import java.util.List;

public record Movie(
    Long id,
    String name,
    List<Actor> actors,
    LocalDate releasedDate,
    Gender gender
){
    public Movie withName(String newName){
        return new Movie(id,newName,actors,releasedDate,gender);
    }
    public Movie withActors(List<Actor> newActors){
        return new Movie(id,name,newActors,releasedDate,gender);
    }
    public Movie withReleasedDate(LocalDate newReleasedDate){
        return new Movie(id,name,actors,newReleasedDate,gender);
    }
    public Movie withGender(Gender newGender){
        return new Movie(id,name,actors,releasedDate,newGender);
    }
}
