package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * ChatCompletionMessageToolCallChunk
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionMessageToolCallChunk {

  private Integer index;

  private String id;

  /**
   * The type of the tool. Currently, only `function` is supported.
   */
  public enum TypeEnum {
    FUNCTION("function");

    private String value;

    TypeEnum(String value) {
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
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  private ChatCompletionMessageToolCallChunkFunction function;

  public ChatCompletionMessageToolCallChunk() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionMessageToolCallChunk(Integer index) {
    this.index = index;
  }

  public ChatCompletionMessageToolCallChunk index(Integer index) {
    this.index = index;
    return this;
  }

  /**
   * Get index
   * @return index
  */
  @NotNull 
  @Schema(name = "index", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("index")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public ChatCompletionMessageToolCallChunk id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the tool call.
   * @return id
  */
  
  @Schema(name = "id", description = "The ID of the tool call.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ChatCompletionMessageToolCallChunk type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of the tool. Currently, only `function` is supported.
   * @return type
  */
  
  @Schema(name = "type", description = "The type of the tool. Currently, only `function` is supported.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public ChatCompletionMessageToolCallChunk function(ChatCompletionMessageToolCallChunkFunction function) {
    this.function = function;
    return this;
  }

  /**
   * Get function
   * @return function
  */
  @Valid 
  @Schema(name = "function", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("function")
  public ChatCompletionMessageToolCallChunkFunction getFunction() {
    return function;
  }

  public void setFunction(ChatCompletionMessageToolCallChunkFunction function) {
    this.function = function;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionMessageToolCallChunk chatCompletionMessageToolCallChunk = (ChatCompletionMessageToolCallChunk) o;
    return Objects.equals(this.index, chatCompletionMessageToolCallChunk.index) &&
        Objects.equals(this.id, chatCompletionMessageToolCallChunk.id) &&
        Objects.equals(this.type, chatCompletionMessageToolCallChunk.type) &&
        Objects.equals(this.function, chatCompletionMessageToolCallChunk.function);
  }

  @Override
  public int hashCode() {
    return Objects.hash(index, id, type, function);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionMessageToolCallChunk {\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    function: ").append(toIndentedString(function)).append("\n");
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