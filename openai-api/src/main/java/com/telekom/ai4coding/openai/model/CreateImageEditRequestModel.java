package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * The model to use for image generation. Only &#x60;dall-e-2&#x60; is supported at this time.
 */

@Schema(name = "CreateImageEditRequest_model", description = "The model to use for image generation. Only `dall-e-2` is supported at this time.")
@JsonTypeName("CreateImageEditRequest_model")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateImageEditRequestModel {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateImageEditRequestModel {\n");
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