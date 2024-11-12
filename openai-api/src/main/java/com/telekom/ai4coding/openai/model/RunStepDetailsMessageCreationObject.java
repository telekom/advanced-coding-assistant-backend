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
 * Details of the message creation by the run step.
 */

@Schema(name = "RunStepDetailsMessageCreationObject", description = "Details of the message creation by the run step.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsMessageCreationObject implements RunStepObjectStepDetails {

  /**
   * Always `message_creation`.
   */
  public enum TypeEnum {
    MESSAGE_CREATION("message_creation");

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

  private RunStepDetailsMessageCreationObjectMessageCreation messageCreation;

  public RunStepDetailsMessageCreationObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsMessageCreationObject(TypeEnum type, RunStepDetailsMessageCreationObjectMessageCreation messageCreation) {
    this.type = type;
    this.messageCreation = messageCreation;
  }

  public RunStepDetailsMessageCreationObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `message_creation`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Always `message_creation`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepDetailsMessageCreationObject messageCreation(RunStepDetailsMessageCreationObjectMessageCreation messageCreation) {
    this.messageCreation = messageCreation;
    return this;
  }

  /**
   * Get messageCreation
   * @return messageCreation
  */
  @NotNull @Valid 
  @Schema(name = "message_creation", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message_creation")
  public RunStepDetailsMessageCreationObjectMessageCreation getMessageCreation() {
    return messageCreation;
  }

  public void setMessageCreation(RunStepDetailsMessageCreationObjectMessageCreation messageCreation) {
    this.messageCreation = messageCreation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsMessageCreationObject runStepDetailsMessageCreationObject = (RunStepDetailsMessageCreationObject) o;
    return Objects.equals(this.type, runStepDetailsMessageCreationObject.type) &&
        Objects.equals(this.messageCreation, runStepDetailsMessageCreationObject.messageCreation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, messageCreation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsMessageCreationObject {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    messageCreation: ").append(toIndentedString(messageCreation)).append("\n");
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