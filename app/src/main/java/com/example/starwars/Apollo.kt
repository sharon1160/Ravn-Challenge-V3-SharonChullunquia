package com.example.starwars
import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
    .build()