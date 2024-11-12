package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an embedding vector returned by embedding endpoint. 
 */

@Schema(name = "Embedding", description = "Represents an embedding vector returned by embedding endpoint. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Embedding {

  private Integer index;

  @Valid
  private List<BigDecimal> embedding = new ArrayList<>();

  /**
   * The object type, which is always \"embedding\".
   */
  public enum ObjectEnum {
    EMBEDDING("embedding");

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

  public Embedding() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Embedding(Integer index, List<BigDecimal> embedding, ObjectEnum _object) {
    this.index = index;
    this.embedding = embedding;
    this._object = _object;
  }

  public Embedding index(Integer index) {
    this.index = index;
    return this;
  }

  /**
   * The index of the embedding in the list of embeddings.
   * @return index
  */
  @NotNull 
  @Schema(name = "index", description = "The index of the embedding in the list of embeddings.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("index")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public Embedding embedding(List<BigDecimal> embedding) {
    this.embedding = embedding;
    return this;
  }

  public Embedding addEmbeddingItem(BigDecimal embeddingItem) {
    if (this.embedding == null) {
      this.embedding = new ArrayList<>();
    }
    this.embedding.add(embeddingItem);
    return this;
  }

  /**
   * The embedding vector, which is a list of floats. The length of vector depends on the model as listed in the [embedding guide](/docs/guides/embeddings). 
   * @return embedding
  */
  @NotNull @Valid 
  @Schema(name = "embedding", description = "The embedding vector, which is a list of floats. The length of vector depends on the model as listed in the [embedding guide](/docs/guides/embeddings). ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("embedding")
  public List<BigDecimal> getEmbedding() {
    return embedding;
  }

  public void setEmbedding(List<BigDecimal> embedding) {
    this.embedding = embedding;
  }

  public Embedding _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always \"embedding\".
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always \"embedding\".", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Embedding embedding = (Embedding) o;
    return Objects.equals(this.index, embedding.index) &&
        Objects.equals(this.embedding, embedding.embedding) &&
        Objects.equals(this._object, embedding._object);
  }

  @Override
  public int hashCode() {
    return Objects.hash(index, embedding, _object);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Embedding {\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    embedding: ").append(toIndentedString(embedding)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
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