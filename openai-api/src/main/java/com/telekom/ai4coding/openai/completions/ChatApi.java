/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.2.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.telekom.ai4coding.openai.completions;

import com.telekom.ai4coding.openai.model.CreateChatCompletionResponse;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "Chat", description = "Given a list of messages comprising a conversation, the model will return a response.")
public interface ChatApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /chat/completions : Creates a model response for the given chat conversation.
     *
     * @param createChatCompletionRequest  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createChatCompletion",
        summary = "Creates a model response for the given chat conversation.",
        tags = { "Chat" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CreateChatCompletionResponse.class))
            }, headers = {
                    @Header(name = "Conversation-Id", description = "The ID of the conversation", schema = @Schema(type = "string"))
            })
        },
        security = {
            @SecurityRequirement(name = "ApiKeyAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/chat/completions",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ChatCompletionResult> createChatCompletion(
        @Parameter(name = "CreateChatCompletionRequest", description = "", required = true) @Valid @RequestBody ChatCompletionRequest chatCompletionRequest,
        @Parameter(name = "Persist-Conversation", description = "Flag to indicate if the conversation should be stored", required = false) @RequestHeader(value = "Persist-Conversation", required = false) Boolean persistConversation,
        @Parameter(name = "Conversation-Id", description = "Identifier for existing conversations", required = false) @RequestHeader(value = "Conversation-Id", required = false) String conversationId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"created\" : 2, \"usage\" : { \"completion_tokens\" : 7, \"prompt_tokens\" : 9, \"total_tokens\" : 3 }, \"model\" : \"model\", \"id\" : \"id\", \"choices\" : [ { \"finish_reason\" : \"stop\", \"index\" : 0, \"message\" : { \"role\" : \"assistant\", \"function_call\" : { \"name\" : \"name\", \"arguments\" : \"arguments\" }, \"tool_calls\" : [ { \"function\" : { \"name\" : \"name\", \"arguments\" : \"arguments\" }, \"id\" : \"id\", \"type\" : \"function\" }, { \"function\" : { \"name\" : \"name\", \"arguments\" : \"arguments\" }, \"id\" : \"id\", \"type\" : \"function\" } ], \"content\" : \"content\" }, \"logprobs\" : { \"content\" : [ { \"top_logprobs\" : [ { \"logprob\" : 5.962133916683182, \"bytes\" : [ 5, 5 ], \"token\" : \"token\" }, { \"logprob\" : 5.962133916683182, \"bytes\" : [ 5, 5 ], \"token\" : \"token\" } ], \"logprob\" : 6.027456183070403, \"bytes\" : [ 1, 1 ], \"token\" : \"token\" }, { \"top_logprobs\" : [ { \"logprob\" : 5.962133916683182, \"bytes\" : [ 5, 5 ], \"token\" : \"token\" }, { \"logprob\" : 5.962133916683182, \"bytes\" : [ 5, 5 ], \"token\" : \"token\" } ], \"logprob\" : 6.027456183070403, \"bytes\" : [ 1, 1 ], \"token\" : \"token\" } ] } }, { \"finish_reason\" : \"stop\", \"index\" : 0, \"message\" : { \"role\" : \"assistant\", \"function_call\" : { \"name\" : \"name\", \"arguments\" : \"arguments\" }, \"tool_calls\" : [ { \"function\" : { \"name\" : \"name\", \"arguments\" : \"arguments\" }, \"id\" : \"id\", \"type\" : \"function\" }, { \"function\" : { \"name\" : \"name\", \"arguments\" : \"arguments\" }, \"id\" : \"id\", \"type\" : \"function\" } ], \"content\" : \"content\" }, \"logprobs\" : { \"content\" : [ { \"top_logprobs\" : [ { \"logprob\" : 5.962133916683182, \"bytes\" : [ 5, 5 ], \"token\" : \"token\" }, { \"logprob\" : 5.962133916683182, \"bytes\" : [ 5, 5 ], \"token\" : \"token\" } ], \"logprob\" : 6.027456183070403, \"bytes\" : [ 1, 1 ], \"token\" : \"token\" }, { \"top_logprobs\" : [ { \"logprob\" : 5.962133916683182, \"bytes\" : [ 5, 5 ], \"token\" : \"token\" }, { \"logprob\" : 5.962133916683182, \"bytes\" : [ 5, 5 ], \"token\" : \"token\" } ], \"logprob\" : 6.027456183070403, \"bytes\" : [ 1, 1 ], \"token\" : \"token\" } ] } } ], \"system_fingerprint\" : \"system_fingerprint\", \"object\" : \"chat.completion\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}