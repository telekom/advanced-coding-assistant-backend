package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Describes an OpenAI model offering that can be used with the API.
 */

@Schema(name = "Model", description = "Describes an OpenAI model offering that can be used with the API.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Model {

  private String id;

  private Integer created;

  /**
   * The object type, which is always \"model\".
   */
  public enum ObjectEnum {
    MODEL("model");

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

  private String ownedBy;

  public Model() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Model(String id, Integer created, ObjectEnum _object, String ownedBy) {
    this.id = id;
    this.created = created;
    this._object = _object;
    this.ownedBy = ownedBy;
  }

  public Model id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The model identifier, which can be referenced in the API endpoints.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The model identifier, which can be referenced in the API endpoints.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Model created(Integer created) {
    this.created = created;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) when the model was created.
   * @return created
  */
  @NotNull 
  @Schema(name = "created", description = "The Unix timestamp (in seconds) when the model was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created")
  public Integer getCreated() {
    return created;
  }

  public void setCreated(Integer created) {
    this.created = created;
  }

  public Model _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always \"model\".
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always \"model\".", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public Model ownedBy(String ownedBy) {
    this.ownedBy = ownedBy;
    return this;
  }

  /**
   * The organization that owns the model.
   * @return ownedBy
  */
  @NotNull 
  @Schema(name = "owned_by", description = "The organization that owns the model.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("owned_by")
  public String getOwnedBy() {
    return ownedBy;
  }

  public void setOwnedBy(String ownedBy) {
    this.ownedBy = ownedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Model model = (Model) o;
    return Objects.equals(this.id, model.id) &&
        Objects.equals(this.created, model.created) &&
        Objects.equals(this._object, model._object) &&
        Objects.equals(this.ownedBy, model.ownedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, created, _object, ownedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Model {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    ownedBy: ").append(toIndentedString(ownedBy)).append("\n");
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