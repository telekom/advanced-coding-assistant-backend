package com.telekom.ai4coding.chatbot.configuration;

import com.azure.core.http.ProxyOptions;
import com.telekom.ai4coding.chatbot.configuration.agent.CodeContextVerifyAgent;
import com.telekom.ai4coding.chatbot.configuration.agent.GeneralAgent;
import com.telekom.ai4coding.chatbot.configuration.agent.HypotheticalDocumentGenerator;
import com.telekom.ai4coding.chatbot.configuration.properties.AcaProperties;
import com.telekom.ai4coding.chatbot.configuration.properties.AwsProperties;
import com.telekom.ai4coding.chatbot.configuration.properties.AzureProperties;
import com.telekom.ai4coding.chatbot.configuration.properties.LocalChatbotProperties;
import com.telekom.ai4coding.chatbot.configuration.properties.OpenaiCompletionsProperties;
import com.telekom.ai4coding.chatbot.configuration.properties.OpenaiProperties;
import com.telekom.ai4coding.chatbot.configuration.properties.VertexProperties;
import com.telekom.ai4coding.chatbot.tools.graph.GraphRetrieval;
import dev.ai4j.openai4j.OpenAiClient;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.bedrock.BedrockAnthropicMessageChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.localai.LocalAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import software.amazon.awssdk.regions.Region;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Collection;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AiClientConfig {

    @Autowired
    ResourceLoader resourceLoader;

    /**
     * Configurations for the LLM provider profiles
     */
    @Configuration
    @EnableConfigurationProperties(VertexProperties.class)
    @Profile("vertex")
    public static class GoogleVertexAiProfileConfig {
        @Bean
        public VertexAiGeminiChatModel googleVertexAiChatModel(VertexProperties vertexProperties) {
            VertexAiGeminiChatModel.VertexAiGeminiChatModelBuilder builder = VertexAiGeminiChatModel.builder()
                    .project(vertexProperties.project())
                    .location(vertexProperties.location())
                    .modelName(vertexProperties.modelName());

            if (vertexProperties.temperature() != null) {
                builder.temperature(vertexProperties.temperature());
            }

            if (vertexProperties.maxOutputTokens() != null) {
                builder.maxOutputTokens(vertexProperties.maxOutputTokens());
            }

            if (vertexProperties.topK() != null) {
                builder.topK(vertexProperties.topK());
            }

            if (vertexProperties.topP() != null) {
                builder.topP(vertexProperties.topP());
            }

            if (vertexProperties.maxRetries() != null) {
                builder.maxRetries(vertexProperties.maxRetries());
            }

            return builder.build();
        }
    }

    @Configuration
    @EnableConfigurationProperties(AwsProperties.class)
    @Profile("bedrock")
    public static class BedrockProfileConfig {

        @Autowired
        private AwsProperties awsProperties;

        @PostConstruct
        public void setAwsCredentials() {
            System.setProperty("aws.accessKeyId", awsProperties.accessKeyId());
            System.setProperty("aws.secretAccessKey", awsProperties.secretKey());
        }

        @Bean
        public BedrockAnthropicMessageChatModel bedrockChatLanguageModel(AwsProperties awsProperties) {
            BedrockAnthropicMessageChatModel.BedrockAnthropicMessageChatModelBuilder builder = BedrockAnthropicMessageChatModel.builder()
                    .region(Region.of(awsProperties.region()))
                    .model(awsProperties.model());

            if (awsProperties.temperature() != null) {
                builder.temperature(awsProperties.temperature());
            }

            if (awsProperties.maxTokens() != null) {
                builder.maxTokens(awsProperties.maxTokens());
            }

            if (awsProperties.maxRetries() != null) {
                builder.maxRetries(awsProperties.maxRetries());
            }

            if (awsProperties.topK() != null) {
                builder.topK(awsProperties.topK());
            }

            if (awsProperties.anthropicVersion() != null) {
                builder.anthropicVersion(awsProperties.anthropicVersion());
            }

            if (awsProperties.stopSequences() != null) {
                builder.stopSequences(awsProperties.stopSequences());
            }

            if (awsProperties.topP() != null) {
                builder.topP(awsProperties.topP());
            }

            return builder.build();
        }
    }

    @Configuration
    @EnableConfigurationProperties(OpenaiProperties.class)
    @Profile("openai")
    public static class OpenaiProfileConfig {
        @Bean
        public OpenAiChatModel openAiChatModel(OpenaiProperties openaiProperties) {
            OpenAiChatModel.OpenAiChatModelBuilder builder = OpenAiChatModel.builder()
                    .apiKey(openaiProperties.apiKey());

            if(openaiProperties.proxy() != null && !openaiProperties.proxy().isEmpty()) {
                String[] proxyParts = openaiProperties.proxy().split(":");
                String proxyHost = proxyParts[0];
                int proxyPort = Integer.parseInt(proxyParts[1]);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                builder.proxy(proxy);
            }

            if (openaiProperties.baseUrl() != null) {
                builder.baseUrl(openaiProperties.baseUrl());
            }

            if (openaiProperties.modelName() != null) {
                builder.modelName(openaiProperties.modelName());
            }

            if (openaiProperties.temperature() != null) {
                builder.temperature(openaiProperties.temperature());
            }

            if (openaiProperties.topP() != null) {
                builder.topP(openaiProperties.topP());
            }

            if (openaiProperties.stop() != null) {
                builder.stop(openaiProperties.stop());
            }

            if (openaiProperties.maxTokens() != null) {
                builder.maxTokens(openaiProperties.maxTokens());
            }

            if (openaiProperties.presencePenalty() != null) {
                builder.presencePenalty(openaiProperties.presencePenalty());
            }

            if (openaiProperties.frequencyPenalty() != null) {
                builder.frequencyPenalty(openaiProperties.frequencyPenalty());
            }

            if (openaiProperties.seed() != null) {
                builder.seed(openaiProperties.seed());
            }

            if (openaiProperties.user() != null && !openaiProperties.proxy().isEmpty()) {
                builder.user(openaiProperties.user());
            }

            if (openaiProperties.maxRetries() != null) {
                builder.maxRetries(openaiProperties.maxRetries());
            }

            if (openaiProperties.timeout() != null) {
                builder.timeout(Duration.ofSeconds(openaiProperties.timeout()));
            }

            if (openaiProperties.organizationId() != null) {
                builder.organizationId(openaiProperties.organizationId());
            }

            if (openaiProperties.logRequests() != null) {
                builder.logRequests(openaiProperties.logRequests());
            }

            if (openaiProperties.logResponses() != null) {
                builder.logResponses(openaiProperties.logResponses());
            }

            return builder.build();
        }
    }

    @Configuration
    @EnableConfigurationProperties(LocalChatbotProperties.class)
    @Profile("local")
    public static class LocalProfileConfig {
        @Bean
        public LocalAiChatModel localAiChatModel(LocalChatbotProperties chatbotProperties) {
            return LocalAiChatModel.builder()
                    .baseUrl(chatbotProperties.endpoint())
                    .modelName("localAi")
                    .logRequests(true)
                    .logResponses(true)
                    .build();
        }
    }

    @Configuration
    @EnableConfigurationProperties(AzureProperties.class)
    @Profile("azure")
    public static class AzureProfileConfig {

        @Bean
        public AzureOpenAiChatModel azureOpenAiChatModel(AzureProperties azureProperties) {
            AzureOpenAiChatModel.Builder builder = AzureOpenAiChatModel.builder()
                    .apiKey(azureProperties.apiKey())
                    .endpoint(azureProperties.endpoint())
                    .deploymentName(azureProperties.model());

            if(azureProperties.proxy() != null && !azureProperties.proxy().isEmpty()) {
                String[] proxyParts = azureProperties.proxy().split(":");
                String proxyHost = proxyParts[0];
                int proxyPort = Integer.parseInt(proxyParts[1]);
                ProxyOptions proxyOptions = new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                builder.proxyOptions(proxyOptions);
            }

            if (azureProperties.logRequestsAndResponses() != null) {
                builder.logRequestsAndResponses(azureProperties.logRequestsAndResponses());
            }

            if (azureProperties.timeout() != null) {
                builder.timeout(Duration.ofMinutes(azureProperties.timeout()));
            }

            if (azureProperties.maxTokens() != null) {
                builder.maxTokens(azureProperties.maxTokens());
            }

            if (azureProperties.temperature() != null) {
                builder.temperature(azureProperties.temperature());
            }

            if (azureProperties.topP() != null) {
                builder.topP(azureProperties.topP());
            }

            if (azureProperties.presencePenalty() != null) {
                builder.presencePenalty(azureProperties.presencePenalty());
            }

            if (azureProperties.stop() != null) {
                builder.stop(azureProperties.stop());
            }

            if (azureProperties.frequencyPenalty() != null) {
                builder.frequencyPenalty(azureProperties.frequencyPenalty());
            }

            if (azureProperties.seed() != null) {
                builder.seed(azureProperties.seed());
            }

            if (azureProperties.n() != null) {
                builder.n(azureProperties.n());
            }

            if (azureProperties.user() != null && !azureProperties.proxy().isEmpty()) {
                builder.user(azureProperties.user());
            }

            return builder.build();
        }
    }

    @Bean
    EmbeddingModel embeddingModel(AcaProperties acaProperties) {
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        if(acaProperties.embeddingModel().useCustomModel()){
            try {
                Resource modelResource = resourceLoader.getResource(acaProperties.embeddingModel().modelClassPath());
                Resource tokenizerResource = resourceLoader.getResource(acaProperties.embeddingModel().tokenizerClassPath());
                File modelFile = File.createTempFile("model", ".onnx");
                File tokenizerFile = File.createTempFile("tokenizer", ".json");
                Files.copy(modelResource.getInputStream(), modelFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                Files.copy(tokenizerResource.getInputStream(), tokenizerFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                PoolingMode poolingMode = PoolingMode.valueOf(acaProperties.embeddingModel().poolingMode());
                embeddingModel = new OnnxEmbeddingModel(modelFile.getAbsolutePath(), tokenizerFile.getAbsolutePath(), poolingMode);
                log.info("Is using " + modelResource.getFilename() + " as custom embedding model");
            } catch (IOException e) {
                log.error("Failed to load custom embedding model");
                log.error(e.getMessage());
                log.error("Using the default embedding model (AllMiniLmL6V2EmbeddingModel)");
            }
        }
        return embeddingModel;
    }

    @Bean
    RetrievalAugmentor retrievalAugmentor(ChatLanguageModel chatLanguageModel,
                                          CodebaseContentRetriever codebaseContentRetriever) {
        QueryRouter queryRouter = new QueryRouter() {
            private final PromptTemplate PROMPT_TEMPLATE = PromptTemplate.from(
                    "Does it make sense to search the database " +
                            "and extract additional information based on the provided user query? " +
                            "To have better understanding of what the database contains: details on code repository " +
                            "and code." +
                            "It is vital to remember, the database you have access to contains details of the user's code " +
                            "and code repository" +
                            "Answer only 'yes' or 'no'. " +
                            "Query: {{it}}"
            );

            @Override
            public Collection<ContentRetriever> route(Query query) {

                Prompt prompt = PROMPT_TEMPLATE.apply(query.text());

                AiMessage aiMessage = chatLanguageModel.generate(prompt.toUserMessage()).content();
                log.info("Routing to Neo4j database: {}", aiMessage.text());

                if (aiMessage.text().toLowerCase().contains("no")) {
                    return emptyList();
                }

                return singletonList(codebaseContentRetriever);
            }
        };

        return DefaultRetrievalAugmentor.builder()
                .queryTransformer(new CompressingQueryTransformer(chatLanguageModel))
                .queryRouter(queryRouter)
                .build();
    }


    @Bean
    HypotheticalDocumentGenerator hypotheticalDocumentGenerator(ChatLanguageModel chatLanguageModel) {
        return AiServices.builder(HypotheticalDocumentGenerator.class)
                .chatLanguageModel(chatLanguageModel)
                .build();
    }

    @Bean
    CodeContextVerifyAgent codeContextVerifyAgent(ChatLanguageModel chatLanguageModel) {
        return AiServices.builder(CodeContextVerifyAgent.class)
                    .chatLanguageModel(chatLanguageModel)
                    .build();
    }

    @Bean
    @Profile("!state-machine")
    GeneralAgent agenticRAGgeneralAgent(ChatLanguageModel chatLanguageModel,
                              ChatMemoryStore chatMemoryStore,
                              Neo4jProperties neo4jProperties,
                              AcaProperties acaProperties) {
        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(500)
                .chatMemoryStore(chatMemoryStore)
                .build();

        return AiServices.builder(GeneralAgent.class)
                .chatLanguageModel(chatLanguageModel)
                .tools(new GraphRetrieval(neo4jProperties.getUri().toString(),
                        neo4jProperties.getAuthentication().getUsername(),
                        neo4jProperties.getAuthentication().getPassword(),
                        acaProperties.toolResultMaxToken()))
                .chatMemoryProvider(chatMemoryProvider)
                .build();
    }

    @Bean
    @Profile("state-machine")
    GeneralAgent stateMachineRAGGeneralAgent(ChatLanguageModel chatLanguageModel,
                              ChatMemoryStore chatMemoryStore,
                              RetrievalAugmentor retrievalAugmentor) {
        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(500)
                .chatMemoryStore(chatMemoryStore)
                .build();

        return AiServices.builder(GeneralAgent.class)
                .chatLanguageModel(chatLanguageModel)
                .retrievalAugmentor(retrievalAugmentor)
                .chatMemoryProvider(chatMemoryProvider)
                .build();
    }

    /**
     * Configuration for the OpenAI client used for the completions endpoint --> not for openai profile
     */
    @Configuration
    @EnableConfigurationProperties(OpenaiCompletionsProperties.class)
    public static class OpenaiConfig {
        @Bean
        public OpenAiClient openAiClient(OpenaiCompletionsProperties openaiCompletionsProperties) {
            OpenAiClient.Builder builder = OpenAiClient.builder()
                    .logRequests()
                    .logResponses();

            String baseUrl = openaiCompletionsProperties.endpoint();
            if (baseUrl == null || baseUrl.trim().isEmpty()) {
                builder.baseUrl("https://example"); // placeholder so that app can start
            } else {
                builder.baseUrl(baseUrl);
            }

            String apiKey = openaiCompletionsProperties.openaiApiKey();
            if (apiKey == null || apiKey.trim().isEmpty()) {
                apiKey = openaiCompletionsProperties.azureApiKey();
                if (apiKey != null && !apiKey.trim().isEmpty()) {
                    builder.azureApiKey(apiKey);
                } else {
                    builder.openAiApiKey("API_KEY"); // placeholder so that app can start
                }
            } else {
                builder.openAiApiKey(apiKey);
            }
            return builder.build();
        }
    }
}

