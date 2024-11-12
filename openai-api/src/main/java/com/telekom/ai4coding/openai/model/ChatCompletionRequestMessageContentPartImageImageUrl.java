package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.Objects;

/**
 * ChatCompletionRequestMessageContentPartImageImageUrl
 */

@JsonTypeName("ChatCompletionRequestMessageContentPartImage_image_url")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionRequestMessageContentPartImageImageUrl {

  private URI url;

  /**
   * Specifies the detail level of the image. Learn more in the [Vision guide](/docs/guides/vision/low-or-high-fidelity-image-understanding).
   */
  public enum DetailEnum {
    AUTO("auto"),
    
    LOW("low"),
    
    HIGH("high");

    private String value;

    DetailEnum(String value) {
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
    public static DetailEnum fromValue(String value) {
      for (DetailEnum b : DetailEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private DetailEnum detail = DetailEnum.AUTO;

  public ChatCompletionRequestMessageContentPartImageImageUrl() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionRequestMessageContentPartImageImageUrl(URI url) {
    this.url = url;
  }

  public ChatCompletionRequestMessageContentPartImageImageUrl url(URI url) {
    this.url = url;
    return this;
  }

  /**
   * Either a URL of the image or the base64 encoded image data.
   * @return url
  */
  @NotNull @Valid 
  @Schema(name = "url", description = "Either a URL of the image or the base64 encoded image data.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("url")
  public URI getUrl() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }

  public ChatCompletionRequestMessageContentPartImageImageUrl detail(DetailEnum detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Specifies the detail level of the image. Learn more in the [Vision guide](/docs/guides/vision/low-or-high-fidelity-image-understanding).
   * @return detail
  */
  
  @Schema(name = "detail", description = "Specifies the detail level of the image. Learn more in the [Vision guide](/docs/guides/vision/low-or-high-fidelity-image-understanding).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detail")
  public DetailEnum getDetail() {
    return detail;
  }

  public void setDetail(DetailEnum detail) {
    this.detail = detail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionRequestMessageContentPartImageImageUrl chatCompletionRequestMessageContentPartImageImageUrl = (ChatCompletionRequestMessageContentPartImageImageUrl) o;
    return Objects.equals(this.url, chatCompletionRequestMessageContentPartImageImageUrl.url) &&
        Objects.equals(this.detail, chatCompletionRequestMessageContentPartImageImageUrl.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, detail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionRequestMessageContentPartImageImageUrl {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
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