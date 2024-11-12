package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an &#x60;assistant&#x60; that can call the model and use tools.
 */

@Schema(name = "AssistantObject", description = "Represents an `assistant` that can call the model and use tools.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AssistantObject {

  private String id;

  /**
   * The object type, which is always `assistant`.
   */
  public enum ObjectEnum {
    ASSISTANT("assistant");

    private String value;

    ObjectEnum(String value) {
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
    public static ObjectEnum fromValue(String value) {
      for (ObjectEnum b : ObjectEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ObjectEnum _object;

  private Integer createdAt;

  private JsonNullable<@Size(max = 256) String> name = JsonNullable.<String>undefined();

  private JsonNullable<@Size(max = 512) String> description = JsonNullable.<String>undefined();

  private String model;

  private JsonNullable<@Size(max = 32768) String> instructions = JsonNullable.<String>undefined();

  @Valid
  private List<@Valid AssistantObjectToolsInner> tools = new ArrayList<>();

  @Valid
  private List<String> fileIds = new ArrayList<>();

  private JsonNullable<Object> metadata = JsonNullable.<Object>undefined();

  public AssistantObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AssistantObject(String id, ObjectEnum _object, Integer createdAt, String name, String description, String model, String instructions, List<@Valid AssistantObjectToolsInner> tools, List<String> fileIds, Object metadata) {
    this.id = id;
    this._object = _object;
    this.createdAt = createdAt;
    this.name = JsonNullable.of(name);
    this.description = JsonNullable.of(description);
    this.model = model;
    this.instructions = JsonNullable.of(instructions);
    this.tools = tools;
    this.fileIds = fileIds;
    this.metadata = JsonNullable.of(metadata);
  }

  public AssistantObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier, which can be referenced in API endpoints.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The identifier, which can be referenced in API endpoints.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AssistantObject _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always `assistant`.
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always `assistant`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public AssistantObject createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the assistant was created.
   * @return createdAt
  */
  @NotNull 
  @Schema(name = "created_at", description = "The Unix timestamp (in seconds) for when the assistant was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_at")
  public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public AssistantObject name(String name) {
    this.name = JsonNullable.of(name);
    return this;
  }

  /**
   * The name of the assistant. The maximum length is 256 characters. 
   * @return name
  */
  @NotNull @Size(max = 256) 
  @Schema(name = "name", description = "The name of the assistant. The maximum length is 256 characters. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public JsonNullable<@Size(max = 256) String> getName() {
    return name;
  }

  public void setName(JsonNullable<String> name) {
    this.name = name;
  }

  public AssistantObject description(String description) {
    this.description = JsonNullable.of(description);
    return this;
  }

  /**
   * The description of the assistant. The maximum length is 512 characters. 
   * @return description
  */
  @NotNull @Size(max = 512) 
  @Schema(name = "description", description = "The description of the assistant. The maximum length is 512 characters. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public JsonNullable<@Size(max = 512) String> getDescription() {
    return description;
  }

  public void setDescription(JsonNullable<String> description) {
    this.description = description;
  }

  public AssistantObject model(String model) {
    this.model = model;
    return this;
  }

  /**
   * ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see all of your available models, or see our [Model overview](/docs/models/overview) for descriptions of them. 
   * @return model
  */
  @NotNull 
  @Schema(name = "model", description = "ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see all of your available models, or see our [Model overview](/docs/models/overview) for descriptions of them. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public AssistantObject instructions(String instructions) {
    this.instructions = JsonNullable.of(instructions);
    return this;
  }

  /**
   * The system instructions that the assistant uses. The maximum length is 32768 characters. 
   * @return instructions
  */
  @NotNull @Size(max = 32768) 
  @Schema(name = "instructions", description = "The system instructions that the assistant uses. The maximum length is 32768 characters. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("instructions")
  public JsonNullable<@Size(max = 32768) String> getInstructions() {
    return instructions;
  }

  public void setInstructions(JsonNullable<String> instructions) {
    this.instructions = instructions;
  }

  public AssistantObject tools(List<@Valid AssistantObjectToolsInner> tools) {
    this.tools = tools;
    return this;
  }

  public AssistantObject addToolsItem(AssistantObjectToolsInner toolsItem) {
    if (this.tools == null) {
      this.tools = new ArrayList<>();
    }
    this.tools.add(toolsItem);
    return this;
  }

  /**
   * A list of tool enabled on the assistant. There can be a maximum of 128 tools per assistant. Tools can be of types `code_interpreter`, `retrieval`, or `function`. 
   * @return tools
  */
  @NotNull @Valid @Size(max = 128) 
  @Schema(name = "tools", description = "A list of tool enabled on the assistant. There can be a maximum of 128 tools per assistant. Tools can be of types `code_interpreter`, `retrieval`, or `function`. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tools")
  public List<@Valid AssistantObjectToolsInner> getTools() {
    return tools;
  }

  public void setTools(List<@Valid AssistantObjectToolsInner> tools) {
    this.tools = tools;
  }

  public AssistantObject fileIds(List<String> fileIds) {
    this.fileIds = fileIds;
    return this;
  }

  public AssistantObject addFileIdsItem(String fileIdsItem) {
    if (this.fileIds == null) {
      this.fileIds = new ArrayList<>();
    }
    this.fileIds.add(fileIdsItem);
    return this;
  }

  /**
   * A list of [file](/docs/api-reference/files) IDs attached to this assistant. There can be a maximum of 20 files attached to the assistant. Files are ordered by their creation date in ascending order. 
   * @return fileIds
  */
  @NotNull @Size(max = 20) 
  @Schema(name = "file_ids", description = "A list of [file](/docs/api-reference/files) IDs attached to this assistant. There can be a maximum of 20 files attached to the assistant. Files are ordered by their creation date in ascending order. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("file_ids")
  public List<String> getFileIds() {
    return fileIds;
  }

  public void setFileIds(List<String> fileIds) {
    this.fileIds = fileIds;
  }

  public AssistantObject metadata(Object metadata) {
    this.metadata = JsonNullable.of(metadata);
    return this;
  }

  /**
   * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. 
   * @return metadata
  */
  @NotNull 
  @Schema(name = "metadata", description = "Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. ", requiredMode = Schema.RequiredMode.REQUIRED)
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
    AssistantObject assistantObject = (AssistantObject) o;
    return Objects.equals(this.id, assistantObject.id) &&
        Objects.equals(this._object, assistantObject._object) &&
        Objects.equals(this.createdAt, assistantObject.createdAt) &&
        Objects.equals(this.name, assistantObject.name) &&
        Objects.equals(this.description, assistantObject.description) &&
        Objects.equals(this.model, assistantObject.model) &&
        Objects.equals(this.instructions, assistantObject.instructions) &&
        Objects.equals(this.tools, assistantObject.tools) &&
        Objects.equals(this.fileIds, assistantObject.fileIds) &&
        Objects.equals(this.metadata, assistantObject.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, _object, createdAt, name, description, model, instructions, tools, fileIds, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssistantObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    instructions: ").append(toIndentedString(instructions)).append("\n");
    sb.append("    tools: ").append(toIndentedString(tools)).append("\n");
    sb.append("    fileIds: ").append(toIndentedString(fileIds)).append("\n");
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