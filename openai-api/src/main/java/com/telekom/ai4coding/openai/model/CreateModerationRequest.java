package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * CreateModerationRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateModerationRequest {

  private CreateModerationRequestInput input;

//  private CreateModerationRequestModel model = text-moderation-latest;
  private CreateModerationRequestModel model = null;

  public CreateModerationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateModerationRequest(CreateModerationRequestInput input) {
    this.input = input;
  }

  public CreateModerationRequest input(CreateModerationRequestInput input) {
    this.input = input;
    return this;
  }

  /**
   * Get input
   * @return input
  */
  @NotNull @Valid 
  @Schema(name = "input", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("input")
  public CreateModerationRequestInput getInput() {
    return input;
  }

  public void setInput(CreateModerationRequestInput input) {
    this.input = input;
  }

  public CreateModerationRequest model(CreateModerationRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  @Valid 
  @Schema(name = "model", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("model")
  public CreateModerationRequestModel getModel() {
    return model;
  }

  public void setModel(CreateModerationRequestModel model) {
    this.model = model;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateModerationRequest createModerationRequest = (CreateModerationRequest) o;
    return Objects.equals(this.input, createModerationRequest.input) &&
        Objects.equals(this.model, createModerationRequest.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input, model);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateModerationRequest {\n");
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
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