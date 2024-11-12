package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Objects;

/**
 * CreateChatCompletionResponseChoicesInner
 */

@JsonTypeName("CreateChatCompletionResponse_choices_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateChatCompletionResponseChoicesInner {

  /**
   * The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, `tool_calls` if the model called a tool, or `function_call` (deprecated) if the model called a function. 
   */
  public enum FinishReasonEnum {
    STOP("stop"),
    
    LENGTH("length"),
    
    TOOL_CALLS("tool_calls"),
    
    CONTENT_FILTER("content_filter"),
    
    FUNCTION_CALL("function_call");

    private String value;

    FinishReasonEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static FinishReasonEnum fromValue(String value) {
      for (FinishReasonEnum b : FinishReasonEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private FinishReasonEnum finishReason;

  private Integer index;

  private ChatCompletionResponseMessage message;

  private JsonNullable<CreateChatCompletionResponseChoicesInnerLogprobs> logprobs = JsonNullable.<CreateChatCompletionResponseChoicesInnerLogprobs>undefined();

  public CreateChatCompletionResponseChoicesInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateChatCompletionResponseChoicesInner(FinishReasonEnum finishReason, Integer index, ChatCompletionResponseMessage message, CreateChatCompletionResponseChoicesInnerLogprobs logprobs) {
    this.finishReason = finishReason;
    this.index = index;
    this.message = message;
    this.logprobs = JsonNullable.of(logprobs);
  }

  public CreateChatCompletionResponseChoicesInner finishReason(FinishReasonEnum finishReason) {
    this.finishReason = finishReason;
    return this;
  }

  /**
   * The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, `tool_calls` if the model called a tool, or `function_call` (deprecated) if the model called a function. 
   * @return finishReason
  */
  @NotNull 
  @Schema(name = "finish_reason", description = "The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, `tool_calls` if the model called a tool, or `function_call` (deprecated) if the model called a function. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("finish_reason")
  public FinishReasonEnum getFinishReason() {
    return finishReason;
  }

  public void setFinishReason(FinishReasonEnum finishReason) {
    this.finishReason = finishReason;
  }

  public CreateChatCompletionResponseChoicesInner index(Integer index) {
    this.index = index;
    return this;
  }

  /**
   * The index of the choice in the list of choices.
   * @return index
  */
  @NotNull 
  @Schema(name = "index", description = "The index of the choice in the list of choices.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("index")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public CreateChatCompletionResponseChoicesInner message(ChatCompletionResponseMessage message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @NotNull @Valid 
  @Schema(name = "message", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public ChatCompletionResponseMessage getMessage() {
    return message;
  }

  public void setMessage(ChatCompletionResponseMessage message) {
    this.message = message;
  }

  public CreateChatCompletionResponseChoicesInner logprobs(CreateChatCompletionResponseChoicesInnerLogprobs logprobs) {
    this.logprobs = JsonNullable.of(logprobs);
    return this;
  }

  /**
   * Get logprobs
   * @return logprobs
  */
  @NotNull @Valid 
  @Schema(name = "logprobs", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("logprobs")
  public JsonNullable<CreateChatCompletionResponseChoicesInnerLogprobs> getLogprobs() {
    return logprobs;
  }

  public void setLogprobs(JsonNullable<CreateChatCompletionResponseChoicesInnerLogprobs> logprobs) {
    this.logprobs = logprobs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateChatCompletionResponseChoicesInner createChatCompletionResponseChoicesInner = (CreateChatCompletionResponseChoicesInner) o;
    return Objects.equals(this.finishReason, createChatCompletionResponseChoicesInner.finishReason) &&
        Objects.equals(this.index, createChatCompletionResponseChoicesInner.index) &&
        Objects.equals(this.message, createChatCompletionResponseChoicesInner.message) &&
        Objects.equals(this.logprobs, createChatCompletionResponseChoicesInner.logprobs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(finishReason, index, message, logprobs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateChatCompletionResponseChoicesInner {\n");
    sb.append("    finishReason: ").append(toIndentedString(finishReason)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    logprobs: ").append(toIndentedString(logprobs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}