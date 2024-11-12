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

import java.util.Arrays;
import java.util.Objects;

/**
 * CreateChatCompletionStreamResponseChoicesInner
 */

@JsonTypeName("CreateChatCompletionStreamResponse_choices_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateChatCompletionStreamResponseChoicesInner {

  private ChatCompletionStreamResponseDelta delta;

  private JsonNullable<CreateChatCompletionResponseChoicesInnerLogprobs> logprobs = JsonNullable.<CreateChatCompletionResponseChoicesInnerLogprobs>undefined();

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
      return null;
    }
  }

  private JsonNullable<FinishReasonEnum> finishReason = JsonNullable.<FinishReasonEnum>undefined();

  private Integer index;

  public CreateChatCompletionStreamResponseChoicesInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateChatCompletionStreamResponseChoicesInner(ChatCompletionStreamResponseDelta delta, FinishReasonEnum finishReason, Integer index) {
    this.delta = delta;
    this.finishReason = JsonNullable.of(finishReason);
    this.index = index;
  }

  public CreateChatCompletionStreamResponseChoicesInner delta(ChatCompletionStreamResponseDelta delta) {
    this.delta = delta;
    return this;
  }

  /**
   * Get delta
   * @return delta
  */
  @NotNull @Valid 
  @Schema(name = "delta", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("delta")
  public ChatCompletionStreamResponseDelta getDelta() {
    return delta;
  }

  public void setDelta(ChatCompletionStreamResponseDelta delta) {
    this.delta = delta;
  }

  public CreateChatCompletionStreamResponseChoicesInner logprobs(CreateChatCompletionResponseChoicesInnerLogprobs logprobs) {
    this.logprobs = JsonNullable.of(logprobs);
    return this;
  }

  /**
   * Get logprobs
   * @return logprobs
  */
  @Valid 
  @Schema(name = "logprobs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("logprobs")
  public JsonNullable<CreateChatCompletionResponseChoicesInnerLogprobs> getLogprobs() {
    return logprobs;
  }

  public void setLogprobs(JsonNullable<CreateChatCompletionResponseChoicesInnerLogprobs> logprobs) {
    this.logprobs = logprobs;
  }

  public CreateChatCompletionStreamResponseChoicesInner finishReason(FinishReasonEnum finishReason) {
    this.finishReason = JsonNullable.of(finishReason);
    return this;
  }

  /**
   * The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, `tool_calls` if the model called a tool, or `function_call` (deprecated) if the model called a function. 
   * @return finishReason
  */
  @NotNull 
  @Schema(name = "finish_reason", description = "The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, `tool_calls` if the model called a tool, or `function_call` (deprecated) if the model called a function. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("finish_reason")
  public JsonNullable<FinishReasonEnum> getFinishReason() {
    return finishReason;
  }

  public void setFinishReason(JsonNullable<FinishReasonEnum> finishReason) {
    this.finishReason = finishReason;
  }

  public CreateChatCompletionStreamResponseChoicesInner index(Integer index) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateChatCompletionStreamResponseChoicesInner createChatCompletionStreamResponseChoicesInner = (CreateChatCompletionStreamResponseChoicesInner) o;
    return Objects.equals(this.delta, createChatCompletionStreamResponseChoicesInner.delta) &&
        equalsNullable(this.logprobs, createChatCompletionStreamResponseChoicesInner.logprobs) &&
        Objects.equals(this.finishReason, createChatCompletionStreamResponseChoicesInner.finishReason) &&
        Objects.equals(this.index, createChatCompletionStreamResponseChoicesInner.index);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(delta, hashCodeNullable(logprobs), finishReason, index);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateChatCompletionStreamResponseChoicesInner {\n");
    sb.append("    delta: ").append(toIndentedString(delta)).append("\n");
    sb.append("    logprobs: ").append(toIndentedString(logprobs)).append("\n");
    sb.append("    finishReason: ").append(toIndentedString(finishReason)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
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