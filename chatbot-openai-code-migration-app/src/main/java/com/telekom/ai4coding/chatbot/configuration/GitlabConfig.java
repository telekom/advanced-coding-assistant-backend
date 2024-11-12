package com.telekom.ai4coding.chatbot.configuration;

import com.telekom.ai4coding.chatbot.configuration.properties.AcaProperties;
import org.gitlab4j.api.GitLabApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AcaProperties.class)
public class GitlabConfig {

    @Bean
    public GitLabApi gitLabApi(AcaProperties acaProperties){
        GitLabApi gitlabapi = new GitLabApi("https://gitlab.devops.telekom.de", acaProperties.gitlab().token());
        gitlabapi.setRequestTimeout(10000,100000);
        return gitlabapi;
    }
}
