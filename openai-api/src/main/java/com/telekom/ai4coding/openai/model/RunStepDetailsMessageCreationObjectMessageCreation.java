package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * RunStepDetailsMessageCreationObjectMessageCreation
 */

@JsonTypeName("RunStepDetailsMessageCreationObject_message_creation")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsMessageCreationObjectMessageCreation {

  private String messageId;

  public RunStepDetailsMessageCreationObjectMessageCreation() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsMessageCreationObjectMessageCreation(String messageId) {
    this.messageId = messageId;
  }

  public RunStepDetailsMessageCreationObjectMessageCreation messageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  /**
   * The ID of the message that was created by this run step.
   * @return messageId
  */
  @NotNull 
  @Schema(name = "message_id", description = "The ID of the message that was created by this run step.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message_id")
  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsMessageCreationObjectMessageCreation runStepDetailsMessageCreationObjectMessageCreation = (RunStepDetailsMessageCreationObjectMessageCreation) o;
    return Objects.equals(this.messageId, runStepDetailsMessageCreationObjectMessageCreation.messageId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsMessageCreationObjectMessageCreation {\n");
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
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