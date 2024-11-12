package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * CreateChatCompletionFunctionResponseChoicesInner
 */

@JsonTypeName("CreateChatCompletionFunctionResponse_choices_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateChatCompletionFunctionResponseChoicesInner {

  /**
   * The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, or `function_call` if the model called a function. 
   */
  public enum FinishReasonEnum {
    STOP("stop"),
    
    LENGTH("length"),
    
    FUNCTION_CALL("function_call"),
    
    CONTENT_FILTER("content_filter");

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

  public CreateChatCompletionFunctionResponseChoicesInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateChatCompletionFunctionResponseChoicesInner(FinishReasonEnum finishReason, Integer index, ChatCompletionResponseMessage message) {
    this.finishReason = finishReason;
    this.index = index;
    this.message = message;
  }

  public CreateChatCompletionFunctionResponseChoicesInner finishReason(FinishReasonEnum finishReason) {
    this.finishReason = finishReason;
    return this;
  }

  /**
   * The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, or `function_call` if the model called a function. 
   * @return finishReason
  */
  @NotNull 
  @Schema(name = "finish_reason", description = "The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, or `function_call` if the model called a function. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("finish_reason")
  public FinishReasonEnum getFinishReason() {
    return finishReason;
  }

  public void setFinishReason(FinishReasonEnum finishReason) {
    this.finishReason = finishReason;
  }

  public CreateChatCompletionFunctionResponseChoicesInner index(Integer index) {
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

  public CreateChatCompletionFunctionResponseChoicesInner message(ChatCompletionResponseMessage message) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateChatCompletionFunctionResponseChoicesInner createChatCompletionFunctionResponseChoicesInner = (CreateChatCompletionFunctionResponseChoicesInner) o;
    return Objects.equals(this.finishReason, createChatCompletionFunctionResponseChoicesInner.finishReason) &&
        Objects.equals(this.index, createChatCompletionFunctionResponseChoicesInner.index) &&
        Objects.equals(this.message, createChatCompletionFunctionResponseChoicesInner.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(finishReason, index, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateChatCompletionFunctionResponseChoicesInner {\n");
    sb.append("    finishReason: ").append(toIndentedString(finishReason)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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