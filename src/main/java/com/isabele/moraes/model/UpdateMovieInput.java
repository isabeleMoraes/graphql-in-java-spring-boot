package com.isabele.moraes.model;

import java.time.LocalDate;
import java.util.List;

public record UpdateMovieInput(
    Long id,
    String name,
    List<Long> actorIds,
    LocalDate releasedDate,
    Gender gender
){
}
