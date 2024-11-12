package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * RunStepDetailsToolCallsCodeOutputImageObject
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsToolCallsCodeOutputImageObject implements RunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsInner {

  /**
   * Always `image`.
   */
  public enum TypeEnum {
    IMAGE("image");

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

  private RunStepDetailsToolCallsCodeOutputImageObjectImage image;

  public RunStepDetailsToolCallsCodeOutputImageObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsToolCallsCodeOutputImageObject(TypeEnum type, RunStepDetailsToolCallsCodeOutputImageObjectImage image) {
    this.type = type;
    this.image = image;
  }

  public RunStepDetailsToolCallsCodeOutputImageObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `image`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Always `image`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepDetailsToolCallsCodeOutputImageObject image(RunStepDetailsToolCallsCodeOutputImageObjectImage image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  */
  @NotNull @Valid 
  @Schema(name = "image", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("image")
  public RunStepDetailsToolCallsCodeOutputImageObjectImage getImage() {
    return image;
  }

  public void setImage(RunStepDetailsToolCallsCodeOutputImageObjectImage image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsToolCallsCodeOutputImageObject runStepDetailsToolCallsCodeOutputImageObject = (RunStepDetailsToolCallsCodeOutputImageObject) o;
    return Objects.equals(this.type, runStepDetailsToolCallsCodeOutputImageObject.type) &&
        Objects.equals(this.image, runStepDetailsToolCallsCodeOutputImageObject.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, image);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsToolCallsCodeOutputImageObject {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
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