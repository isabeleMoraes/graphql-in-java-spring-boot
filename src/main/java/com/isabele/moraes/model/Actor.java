package com.isabele.moraes.model;

import java.time.LocalDate;

public record Actor(
        Long id,
        String name,
        LocalDate dateOfBirth
){
}
