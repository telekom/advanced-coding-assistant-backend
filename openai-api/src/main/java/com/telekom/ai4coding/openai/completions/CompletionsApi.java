/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.2.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.telekom.ai4coding.openai.completions;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Validated
@Tag(name = "Completions", description = "Given a prompt, the model will return one or more predicted completions, and can also return the probabilities of alternative tokens at each position.")
public interface CompletionsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /completions : Creates a completion for the provided prompt and parameters.
     *
     * @param completionRequest  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createCompletion",
        summary = "Creates a completion for the provided prompt and parameters.",
        tags = { "Completions" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CompletionResult.class))
            })
        },
        security = {
            @SecurityRequirement(name = "ApiKeyAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/completions",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<CompletionResult> createCompletion(
        @Parameter(name = "CompletionRequest", description = "", required = true) @Valid @RequestBody CompletionRequest completionRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"created\" : 5, \"usage\" : { \"completion_tokens\" : 7, \"prompt_tokens\" : 9, \"total_tokens\" : 3 }, \"model\" : \"model\", \"id\" : \"id\", \"choices\" : [ { \"finish_reason\" : \"stop\", \"index\" : 0, \"text\" : \"text\", \"logprobs\" : { \"top_logprobs\" : [ { \"key\" : 5.962133916683182 }, { \"key\" : 5.962133916683182 } ], \"token_logprobs\" : [ 1.4658129805029452, 1.4658129805029452 ], \"tokens\" : [ \"tokens\", \"tokens\" ], \"text_offset\" : [ 6, 6 ] } }, { \"finish_reason\" : \"stop\", \"index\" : 0, \"text\" : \"text\", \"logprobs\" : { \"top_logprobs\" : [ { \"key\" : 5.962133916683182 }, { \"key\" : 5.962133916683182 } ], \"token_logprobs\" : [ 1.4658129805029452, 1.4658129805029452 ], \"tokens\" : [ \"tokens\", \"tokens\" ], \"text_offset\" : [ 6, 6 ] } } ], \"system_fingerprint\" : \"system_fingerprint\", \"object\" : \"text_completion\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}