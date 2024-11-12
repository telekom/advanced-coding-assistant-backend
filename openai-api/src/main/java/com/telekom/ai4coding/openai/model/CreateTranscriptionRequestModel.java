package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * ID of the model to use. Only &#x60;whisper-1&#x60; is currently available. 
 */

@Schema(name = "CreateTranscriptionRequest_model", description = "ID of the model to use. Only `whisper-1` is currently available. ")
@JsonTypeName("CreateTranscriptionRequest_model")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateTranscriptionRequestModel {

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
    sb.append("class CreateTranscriptionRequestModel {\n");
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