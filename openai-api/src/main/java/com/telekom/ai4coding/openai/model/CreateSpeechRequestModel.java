package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * One of the available [TTS models](/docs/models/tts): &#x60;tts-1&#x60; or &#x60;tts-1-hd&#x60; 
 */

@Schema(name = "CreateSpeechRequest_model", description = "One of the available [TTS models](/docs/models/tts): `tts-1` or `tts-1-hd` ")
@JsonTypeName("CreateSpeechRequest_model")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateSpeechRequestModel {

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
    sb.append("class CreateSpeechRequestModel {\n");
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