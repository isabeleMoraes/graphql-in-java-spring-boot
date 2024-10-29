package com.isabele.moraes.manual.config;

import com.isabele.moraes.manual.resolver.CreateMovieDataFetcher;
import com.isabele.moraes.manual.resolver.QueryMovieResolver;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphQLConfiguration {
    public GraphQLSchema buildSchema() throws IOException {
        //Obtemos o schema em formato de string
        String schema = new String(Files.readAllBytes(Paths.get("src/main/resources/graphql/schema.graphqls")));

        //Parseamos o schema para transforma-lo em um registrador de definição de tipo
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schema);

        //Obtendo a ligação entre a mutation/query e o resolver.
        RuntimeWiring runtimeWiring = buildWiring();

        //Tornando o schema executavel
        return new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring(){
        // Criando um objeto que contem o tipo da operação e mapeando o nome do campo com o resolver que será utilizado
        return RuntimeWiring.newRuntimeWiring()
                .type("Mutation", typeWiring -> typeWiring.dataFetcher("createMovie", new CreateMovieDataFetcher()))
                .type("Query", typeWiring -> typeWiring.dataFetcher("movieById", QueryMovieResolver::findMovieById))
                .type("Query", typeWiring -> typeWiring.dataFetcher("findAllMovies", QueryMovieResolver::findAllMovies))
                .build();
    }
}
