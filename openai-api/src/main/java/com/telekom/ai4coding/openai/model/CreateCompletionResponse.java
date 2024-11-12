package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a completion response from the API. Note: both the streamed and non-streamed response objects share the same shape (unlike the chat endpoint). 
 */

@Schema(name = "CreateCompletionResponse", description = "Represents a completion response from the API. Note: both the streamed and non-streamed response objects share the same shape (unlike the chat endpoint). ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateCompletionResponse {

  private String id;

  @Valid
  private List<@Valid CreateCompletionResponseChoicesInner> choices = new ArrayList<>();

  private Integer created;

  private String model;

  private String systemFingerprint;

  /**
   * The object type, which is always \"text_completion\"
   */
  public enum ObjectEnum {
    TEXT_COMPLETION("text_completion");

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

  private CompletionUsage usage;

  public CreateCompletionResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateCompletionResponse(String id, List<@Valid CreateCompletionResponseChoicesInner> choices, Integer created, String model, ObjectEnum _object) {
    this.id = id;
    this.choices = choices;
    this.created = created;
    this.model = model;
    this._object = _object;
  }

  public CreateCompletionResponse id(String id) {
    this.id = id;
    return this;
  }

  /**
   * A unique identifier for the completion.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "A unique identifier for the completion.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CreateCompletionResponse choices(List<@Valid CreateCompletionResponseChoicesInner> choices) {
    this.choices = choices;
    return this;
  }

  public CreateCompletionResponse addChoicesItem(CreateCompletionResponseChoicesInner choicesItem) {
    if (this.choices == null) {
      this.choices = new ArrayList<>();
    }
    this.choices.add(choicesItem);
    return this;
  }

  /**
   * The list of completion choices the model generated for the input prompt.
   * @return choices
  */
  @NotNull @Valid 
  @Schema(name = "choices", description = "The list of completion choices the model generated for the input prompt.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("choices")
  public List<@Valid CreateCompletionResponseChoicesInner> getChoices() {
    return choices;
  }

  public void setChoices(List<@Valid CreateCompletionResponseChoicesInner> choices) {
    this.choices = choices;
  }

  public CreateCompletionResponse created(Integer created) {
    this.created = created;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) of when the completion was created.
   * @return created
  */
  @NotNull 
  @Schema(name = "created", description = "The Unix timestamp (in seconds) of when the completion was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created")
  public Integer getCreated() {
    return created;
  }

  public void setCreated(Integer created) {
    this.created = created;
  }

  public CreateCompletionResponse model(String model) {
    this.model = model;
    return this;
  }

  /**
   * The model used for completion.
   * @return model
  */
  @NotNull 
  @Schema(name = "model", description = "The model used for completion.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public CreateCompletionResponse systemFingerprint(String systemFingerprint) {
    this.systemFingerprint = systemFingerprint;
    return this;
  }

  /**
   * This fingerprint represents the backend configuration that the model runs with.  Can be used in conjunction with the `seed` request parameter to understand when backend changes have been made that might impact determinism. 
   * @return systemFingerprint
  */
  
  @Schema(name = "system_fingerprint", description = "This fingerprint represents the backend configuration that the model runs with.  Can be used in conjunction with the `seed` request parameter to understand when backend changes have been made that might impact determinism. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("system_fingerprint")
  public String getSystemFingerprint() {
    return systemFingerprint;
  }

  public void setSystemFingerprint(String systemFingerprint) {
    this.systemFingerprint = systemFingerprint;
  }

  public CreateCompletionResponse _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always \"text_completion\"
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always \"text_completion\"", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public CreateCompletionResponse usage(CompletionUsage usage) {
    this.usage = usage;
    return this;
  }

  /**
   * Get usage
   * @return usage
  */
  @Valid 
  @Schema(name = "usage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("usage")
  public CompletionUsage getUsage() {
    return usage;
  }

  public void setUsage(CompletionUsage usage) {
    this.usage = usage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCompletionResponse createCompletionResponse = (CreateCompletionResponse) o;
    return Objects.equals(this.id, createCompletionResponse.id) &&
        Objects.equals(this.choices, createCompletionResponse.choices) &&
        Objects.equals(this.created, createCompletionResponse.created) &&
        Objects.equals(this.model, createCompletionResponse.model) &&
        Objects.equals(this.systemFingerprint, createCompletionResponse.systemFingerprint) &&
        Objects.equals(this._object, createCompletionResponse._object) &&
        Objects.equals(this.usage, createCompletionResponse.usage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, choices, created, model, systemFingerprint, _object, usage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCompletionResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    choices: ").append(toIndentedString(choices)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    systemFingerprint: ").append(toIndentedString(systemFingerprint)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
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