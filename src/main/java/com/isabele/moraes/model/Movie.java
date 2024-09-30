package com.isabele.moraes.model;

import java.time.LocalDate;
import java.util.List;

public record Movie(
    Long id,
    String name,
    List<Actor> actors,
    LocalDate releasedDate,
    Gender gender
){}
