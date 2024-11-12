package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * CreateRunRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateRunRequest {

  private String assistantId;

  private JsonNullable<String> model = JsonNullable.<String>undefined();

  private JsonNullable<String> instructions = JsonNullable.<String>undefined();

  private JsonNullable<String> additionalInstructions = JsonNullable.<String>undefined();

  @Valid
  private JsonNullable<List<@Valid AssistantObjectToolsInner>> tools = JsonNullable.<List<@Valid AssistantObjectToolsInner>>undefined();

  private JsonNullable<Object> metadata = JsonNullable.<Object>undefined();

  public CreateRunRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateRunRequest(String assistantId) {
    this.assistantId = assistantId;
  }

  public CreateRunRequest assistantId(String assistantId) {
    this.assistantId = assistantId;
    return this;
  }

  /**
   * The ID of the [assistant](/docs/api-reference/assistants) to use to execute this run.
   * @return assistantId
  */
  @NotNull 
  @Schema(name = "assistant_id", description = "The ID of the [assistant](/docs/api-reference/assistants) to use to execute this run.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assistant_id")
  public String getAssistantId() {
    return assistantId;
  }

  public void setAssistantId(String assistantId) {
    this.assistantId = assistantId;
  }

  public CreateRunRequest model(String model) {
    this.model = JsonNullable.of(model);
    return this;
  }

  /**
   * The ID of the [Model](/docs/api-reference/models) to be used to execute this run. If a value is provided here, it will override the model associated with the assistant. If not, the model associated with the assistant will be used.
   * @return model
  */
  
  @Schema(name = "model", description = "The ID of the [Model](/docs/api-reference/models) to be used to execute this run. If a value is provided here, it will override the model associated with the assistant. If not, the model associated with the assistant will be used.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("model")
  public JsonNullable<String> getModel() {
    return model;
  }

  public void setModel(JsonNullable<String> model) {
    this.model = model;
  }

  public CreateRunRequest instructions(String instructions) {
    this.instructions = JsonNullable.of(instructions);
    return this;
  }

  /**
   * Overrides the [instructions](/docs/api-reference/assistants/createAssistant) of the assistant. This is useful for modifying the behavior on a per-run basis.
   * @return instructions
  */
  
  @Schema(name = "instructions", description = "Overrides the [instructions](/docs/api-reference/assistants/createAssistant) of the assistant. This is useful for modifying the behavior on a per-run basis.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("instructions")
  public JsonNullable<String> getInstructions() {
    return instructions;
  }

  public void setInstructions(JsonNullable<String> instructions) {
    this.instructions = instructions;
  }

  public CreateRunRequest additionalInstructions(String additionalInstructions) {
    this.additionalInstructions = JsonNullable.of(additionalInstructions);
    return this;
  }

  /**
   * Appends additional instructions at the end of the instructions for the run. This is useful for modifying the behavior on a per-run basis without overriding other instructions.
   * @return additionalInstructions
  */
  
  @Schema(name = "additional_instructions", description = "Appends additional instructions at the end of the instructions for the run. This is useful for modifying the behavior on a per-run basis without overriding other instructions.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additional_instructions")
  public JsonNullable<String> getAdditionalInstructions() {
    return additionalInstructions;
  }

  public void setAdditionalInstructions(JsonNullable<String> additionalInstructions) {
    this.additionalInstructions = additionalInstructions;
  }

  public CreateRunRequest tools(List<@Valid AssistantObjectToolsInner> tools) {
    this.tools = JsonNullable.of(tools);
    return this;
  }

  public CreateRunRequest addToolsItem(AssistantObjectToolsInner toolsItem) {
    if (this.tools == null || !this.tools.isPresent()) {
      this.tools = JsonNullable.of(new ArrayList<>());
    }
    this.tools.get().add(toolsItem);
    return this;
  }

  /**
   * Override the tools the assistant can use for this run. This is useful for modifying the behavior on a per-run basis.
   * @return tools
  */
  @Valid @Size(max = 20) 
  @Schema(name = "tools", description = "Override the tools the assistant can use for this run. This is useful for modifying the behavior on a per-run basis.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tools")
  public JsonNullable<List<@Valid AssistantObjectToolsInner>> getTools() {
    return tools;
  }

  public void setTools(JsonNullable<List<@Valid AssistantObjectToolsInner>> tools) {
    this.tools = tools;
  }

  public CreateRunRequest metadata(Object metadata) {
    this.metadata = JsonNullable.of(metadata);
    return this;
  }

  /**
   * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. 
   * @return metadata
  */
  
  @Schema(name = "metadata", description = "Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("metadata")
  public JsonNullable<Object> getMetadata() {
    return metadata;
  }

  public void setMetadata(JsonNullable<Object> metadata) {
    this.metadata = metadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateRunRequest createRunRequest = (CreateRunRequest) o;
    return Objects.equals(this.assistantId, createRunRequest.assistantId) &&
        equalsNullable(this.model, createRunRequest.model) &&
        equalsNullable(this.instructions, createRunRequest.instructions) &&
        equalsNullable(this.additionalInstructions, createRunRequest.additionalInstructions) &&
        equalsNullable(this.tools, createRunRequest.tools) &&
        equalsNullable(this.metadata, createRunRequest.metadata);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(assistantId, hashCodeNullable(model), hashCodeNullable(instructions), hashCodeNullable(additionalInstructions), hashCodeNullable(tools), hashCodeNullable(metadata));
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
    sb.append("class CreateRunRequest {\n");
    sb.append("    assistantId: ").append(toIndentedString(assistantId)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    instructions: ").append(toIndentedString(instructions)).append("\n");
    sb.append("    additionalInstructions: ").append(toIndentedString(additionalInstructions)).append("\n");
    sb.append("    tools: ").append(toIndentedString(tools)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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