package com.isabele.moraes.manual.executor;

import com.isabele.moraes.manual.config.GraphQLConfiguration;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;

import java.io.IOException;

public class MutationExecutorMain {

    private static GraphQL graphQL;

    public static void main(String[] args) {
        try {
            GraphQLConfiguration configuration = new GraphQLConfiguration();
            graphQL = GraphQL.newGraphQL(configuration.buildSchema())
                    .queryExecutionStrategy(new AsyncExecutionStrategy())
                    .build();

            ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                    .query("""
                            mutation CreateMovie{
                              createMovie(name: "Teste", actorIds:[1,2,3],releasedDate: "2024-10-04", gender:COMEDY) {
                                id
                                name
                                actors{
                                  id
                                  name
                                }
                                releasedDate
                                gender
                              }
                            }
                            """)
                    .build();

            ExecutionResult executionResult = graphQL.execute(executionInput);

            if(executionResult.getErrors().isEmpty()){
                System.out.println("Resultado da execução do graphQl para criação: " + executionResult.getData().toString());
            }else{
                System.out.println("Erros na execução: " + executionResult.getErrors());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
