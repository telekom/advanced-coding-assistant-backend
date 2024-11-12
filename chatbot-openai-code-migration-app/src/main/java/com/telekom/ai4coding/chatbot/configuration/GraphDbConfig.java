package com.telekom.ai4coding.chatbot.configuration;

import java.util.Set;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.data.neo4j.core.convert.Neo4jConversions;

import java.util.HashSet;

import com.telekom.ai4coding.chatbot.repository.conversation.ToolExecutionRequestConverter;
import com.telekom.ai4coding.chatbot.repository.conversation.ToolExecutionResultMessageConverter;

@Configuration
@EnableNeo4jAuditing
public class GraphDbConfig {

    @Bean
    org.neo4j.cypherdsl.core.renderer.Configuration cypherDslConfiguration() {
        return org.neo4j.cypherdsl.core.renderer.Configuration.newConfig()
                .withDialect(Dialect.NEO4J_5).build();
    }

    @Bean
    public Neo4jConversions neo4jConversions() {
        Set<GenericConverter> additionalConverters = new HashSet<GenericConverter>();
        additionalConverters.add(new ToolExecutionRequestConverter());
        additionalConverters.add(new ToolExecutionResultMessageConverter());
        return new Neo4jConversions(additionalConverters);
    }

}
