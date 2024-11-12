package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * MessageContentTextObjectText
 */

@JsonTypeName("MessageContentTextObject_text")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageContentTextObjectText {

  private String value;

  @Valid
  private List<@Valid MessageContentTextObjectTextAnnotationsInner> annotations = new ArrayList<>();

  public MessageContentTextObjectText() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageContentTextObjectText(String value, List<@Valid MessageContentTextObjectTextAnnotationsInner> annotations) {
    this.value = value;
    this.annotations = annotations;
  }

  public MessageContentTextObjectText value(String value) {
    this.value = value;
    return this;
  }

  /**
   * The data that makes up the text.
   * @return value
  */
  @NotNull 
  @Schema(name = "value", description = "The data that makes up the text.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public MessageContentTextObjectText annotations(List<@Valid MessageContentTextObjectTextAnnotationsInner> annotations) {
    this.annotations = annotations;
    return this;
  }

  public MessageContentTextObjectText addAnnotationsItem(MessageContentTextObjectTextAnnotationsInner annotationsItem) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<>();
    }
    this.annotations.add(annotationsItem);
    return this;
  }

  /**
   * Get annotations
   * @return annotations
  */
  @NotNull @Valid 
  @Schema(name = "annotations", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("annotations")
  public List<@Valid MessageContentTextObjectTextAnnotationsInner> getAnnotations() {
    return annotations;
  }

  public void setAnnotations(List<@Valid MessageContentTextObjectTextAnnotationsInner> annotations) {
    this.annotations = annotations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageContentTextObjectText messageContentTextObjectText = (MessageContentTextObjectText) o;
    return Objects.equals(this.value, messageContentTextObjectText.value) &&
        Objects.equals(this.annotations, messageContentTextObjectText.annotations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, annotations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageContentTextObjectText {\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    annotations: ").append(toIndentedString(annotations)).append("\n");
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