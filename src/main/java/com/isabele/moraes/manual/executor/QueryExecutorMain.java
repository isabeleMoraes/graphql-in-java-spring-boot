package com.isabele.moraes.manual.executor;

import com.isabele.moraes.manual.config.GraphQLConfiguration;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;

import java.io.IOException;

public class QueryExecutorMain {

    private static GraphQL graphQL;

    public static void main(String[] args) throws IOException {
        GraphQLConfiguration configuration = new GraphQLConfiguration();

        // Criamos um graphQL defininfo a estratégia de execução e a configuração a que será utilizada.
        graphQL = GraphQL.newGraphQL(configuration.buildSchema())
                .queryExecutionStrategy(new AsyncExecutionStrategy())
                .build();

        //Definimos o input de dado que será passado na execussão do graphQL
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query("""
                query movie{
                  movieById(id: 1){
                    id
                    name
                    actors{
                      id
                      name
                      dateOfBirth
                    }
                    releasedDate
                    gender
                  }
                }
                """).build();

        ExecutionResult executionResult = graphQL.execute(executionInput);

        if(executionResult.getErrors().isEmpty()){
            System.out.println("Resultado da execução do graphQl para busca: " + executionResult.getData().toString());
        }else{
            System.out.println("Erros na execução: " + executionResult.getErrors());
        }
    }
}
