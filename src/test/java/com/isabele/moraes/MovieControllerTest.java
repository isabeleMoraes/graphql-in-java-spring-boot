package com.isabele.moraes;

import com.isabele.moraes.controller.MovieController;
import com.isabele.moraes.maintainer.ActorsMaintainer;
import com.isabele.moraes.maintainer.MoviesMaintainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;


@GraphQlTest(MovieController.class)
public class MovieControllerTest {

    @MockBean
    private MoviesMaintainer moviesMaintainer;

    @MockBean
    private ActorsMaintainer actorsMaintainer;

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetMovie(){
        this.graphQlTester
                .documentName("movieDetails") //Nome definido no arquivo graphql de test
                .variable("id", "1")
                .execute()
                .path("movieById")
                .matchesJson("""
                        {
                                  "id": "1",
                                  "name": "Ela é o cara",
                                  "actors": [
                                      {
                                          "id": "1",
                                          "name": "Amanda Bines",
                                          "dateOfBirth": "1986-04-03"
                                      },
                                      {
                                          "id": "2",
                                          "name": "Channing Tatum",
                                          "dateOfBirth": "1980-04-26"
                                      },
                                      {
                                          "id": "3",
                                          "name": "Alexandra Breckenrige",
                                          "dateOfBirth": "1982-05-15"
                                      }
                                  ],
                                  "gender": "COMEDY"
                              }""");
    }
}
