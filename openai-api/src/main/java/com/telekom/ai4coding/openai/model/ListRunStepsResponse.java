package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ListRunStepsResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ListRunStepsResponse {

  private String _object;

  @Valid
  private List<@Valid RunStepObject> data = new ArrayList<>();

  private String firstId;

  private String lastId;

  private Boolean hasMore;

  public ListRunStepsResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ListRunStepsResponse(String _object, List<@Valid RunStepObject> data, String firstId, String lastId, Boolean hasMore) {
    this._object = _object;
    this.data = data;
    this.firstId = firstId;
    this.lastId = lastId;
    this.hasMore = hasMore;
  }

  public ListRunStepsResponse _object(String _object) {
    this._object = _object;
    return this;
  }

  /**
   * Get _object
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", example = "list", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public String getObject() {
    return _object;
  }

  public void setObject(String _object) {
    this._object = _object;
  }

  public ListRunStepsResponse data(List<@Valid RunStepObject> data) {
    this.data = data;
    return this;
  }

  public ListRunStepsResponse addDataItem(RunStepObject dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
  */
  @NotNull @Valid 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("data")
  public List<@Valid RunStepObject> getData() {
    return data;
  }

  public void setData(List<@Valid RunStepObject> data) {
    this.data = data;
  }

  public ListRunStepsResponse firstId(String firstId) {
    this.firstId = firstId;
    return this;
  }

  /**
   * Get firstId
   * @return firstId
  */
  @NotNull 
  @Schema(name = "first_id", example = "step_abc123", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("first_id")
  public String getFirstId() {
    return firstId;
  }

  public void setFirstId(String firstId) {
    this.firstId = firstId;
  }

  public ListRunStepsResponse lastId(String lastId) {
    this.lastId = lastId;
    return this;
  }

  /**
   * Get lastId
   * @return lastId
  */
  @NotNull 
  @Schema(name = "last_id", example = "step_abc456", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("last_id")
  public String getLastId() {
    return lastId;
  }

  public void setLastId(String lastId) {
    this.lastId = lastId;
  }

  public ListRunStepsResponse hasMore(Boolean hasMore) {
    this.hasMore = hasMore;
    return this;
  }

  /**
   * Get hasMore
   * @return hasMore
  */
  @NotNull 
  @Schema(name = "has_more", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("has_more")
  public Boolean getHasMore() {
    return hasMore;
  }

  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListRunStepsResponse listRunStepsResponse = (ListRunStepsResponse) o;
    return Objects.equals(this._object, listRunStepsResponse._object) &&
        Objects.equals(this.data, listRunStepsResponse.data) &&
        Objects.equals(this.firstId, listRunStepsResponse.firstId) &&
        Objects.equals(this.lastId, listRunStepsResponse.lastId) &&
        Objects.equals(this.hasMore, listRunStepsResponse.hasMore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_object, data, firstId, lastId, hasMore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListRunStepsResponse {\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    firstId: ").append(toIndentedString(firstId)).append("\n");
    sb.append("    lastId: ").append(toIndentedString(lastId)).append("\n");
    sb.append("    hasMore: ").append(toIndentedString(hasMore)).append("\n");
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