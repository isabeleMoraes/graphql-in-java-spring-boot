package com.isabele.moraes;

import com.isabele.moraes.maintainer.ActorsMaintainer;
import com.isabele.moraes.maintainer.MoviesMaintainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class GraphqlWithJavaApplicationTests {
    @Test
    void contextLoads() {
    }

    @Bean
    public MoviesMaintainer moviesMaintainerBean(){
        return new MoviesMaintainer();
    }

    @Bean
    public ActorsMaintainer actorsMaintainerBean(){
        return new ActorsMaintainer();
    }


}
