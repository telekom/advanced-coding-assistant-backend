package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * CreateImageRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateImageRequest {

  private String prompt;

  private JsonNullable<CreateImageRequestModel> model = JsonNullable.<CreateImageRequestModel>undefined();

  private JsonNullable<@Min(1) @Max(10) Integer> n = JsonNullable.<Integer>undefined();

  /**
   * The quality of the image that will be generated. `hd` creates images with finer details and greater consistency across the image. This param is only supported for `dall-e-3`.
   */
  public enum QualityEnum {
    STANDARD("standard"),
    
    HD("hd");

    private String value;

    QualityEnum(String value) {
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
    public static QualityEnum fromValue(String value) {
      for (QualityEnum b : QualityEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private QualityEnum quality = QualityEnum.STANDARD;

  /**
   * The format in which the generated images are returned. Must be one of `url` or `b64_json`.
   */
  public enum ResponseFormatEnum {
    URL("url"),
    
    B64_JSON("b64_json");

    private String value;

    ResponseFormatEnum(String value) {
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
    public static ResponseFormatEnum fromValue(String value) {
      for (ResponseFormatEnum b : ResponseFormatEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      return null;
    }
  }

  private JsonNullable<ResponseFormatEnum> responseFormat = JsonNullable.<ResponseFormatEnum>undefined();

  /**
   * The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024` for `dall-e-2`. Must be one of `1024x1024`, `1792x1024`, or `1024x1792` for `dall-e-3` models.
   */
  public enum SizeEnum {
    _256X256("256x256"),
    
    _512X512("512x512"),
    
    _1024X1024("1024x1024"),
    
    _1792X1024("1792x1024"),
    
    _1024X1792("1024x1792");

    private String value;

    SizeEnum(String value) {
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
    public static SizeEnum fromValue(String value) {
      for (SizeEnum b : SizeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      return null;
    }
  }

  private JsonNullable<SizeEnum> size = JsonNullable.<SizeEnum>undefined();

  /**
   * The style of the generated images. Must be one of `vivid` or `natural`. Vivid causes the model to lean towards generating hyper-real and dramatic images. Natural causes the model to produce more natural, less hyper-real looking images. This param is only supported for `dall-e-3`.
   */
  public enum StyleEnum {
    VIVID("vivid"),
    
    NATURAL("natural");

    private String value;

    StyleEnum(String value) {
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
    public static StyleEnum fromValue(String value) {
      for (StyleEnum b : StyleEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      return null;
    }
  }

  private JsonNullable<StyleEnum> style = JsonNullable.<StyleEnum>undefined();

  private String user;

  public CreateImageRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateImageRequest(String prompt) {
    this.prompt = prompt;
  }

  public CreateImageRequest prompt(String prompt) {
    this.prompt = prompt;
    return this;
  }

  /**
   * A text description of the desired image(s). The maximum length is 1000 characters for `dall-e-2` and 4000 characters for `dall-e-3`.
   * @return prompt
  */
  @NotNull 
  @Schema(name = "prompt", example = "A cute baby sea otter", description = "A text description of the desired image(s). The maximum length is 1000 characters for `dall-e-2` and 4000 characters for `dall-e-3`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prompt")
  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public CreateImageRequest model(CreateImageRequestModel model) {
    this.model = JsonNullable.of(model);
    return this;
  }

  /**
   * Get model
   * @return model
  */
  @Valid 
  @Schema(name = "model", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("model")
  public JsonNullable<CreateImageRequestModel> getModel() {
    return model;
  }

  public void setModel(JsonNullable<CreateImageRequestModel> model) {
    this.model = model;
  }

  public CreateImageRequest n(Integer n) {
    this.n = JsonNullable.of(n);
    return this;
  }

  /**
   * The number of images to generate. Must be between 1 and 10. For `dall-e-3`, only `n=1` is supported.
   * minimum: 1
   * maximum: 10
   * @return n
  */
  @Min(1) @Max(10) 
  @Schema(name = "n", example = "1", description = "The number of images to generate. Must be between 1 and 10. For `dall-e-3`, only `n=1` is supported.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("n")
  public JsonNullable<@Min(1) @Max(10) Integer> getN() {
    return n;
  }

  public void setN(JsonNullable<Integer> n) {
    this.n = n;
  }

  public CreateImageRequest quality(QualityEnum quality) {
    this.quality = quality;
    return this;
  }

  /**
   * The quality of the image that will be generated. `hd` creates images with finer details and greater consistency across the image. This param is only supported for `dall-e-3`.
   * @return quality
  */
  
  @Schema(name = "quality", example = "standard", description = "The quality of the image that will be generated. `hd` creates images with finer details and greater consistency across the image. This param is only supported for `dall-e-3`.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("quality")
  public QualityEnum getQuality() {
    return quality;
  }

  public void setQuality(QualityEnum quality) {
    this.quality = quality;
  }

  public CreateImageRequest responseFormat(ResponseFormatEnum responseFormat) {
    this.responseFormat = JsonNullable.of(responseFormat);
    return this;
  }

  /**
   * The format in which the generated images are returned. Must be one of `url` or `b64_json`.
   * @return responseFormat
  */
  
  @Schema(name = "response_format", example = "url", description = "The format in which the generated images are returned. Must be one of `url` or `b64_json`.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("response_format")
  public JsonNullable<ResponseFormatEnum> getResponseFormat() {
    return responseFormat;
  }

  public void setResponseFormat(JsonNullable<ResponseFormatEnum> responseFormat) {
    this.responseFormat = responseFormat;
  }

  public CreateImageRequest size(SizeEnum size) {
    this.size = JsonNullable.of(size);
    return this;
  }

  /**
   * The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024` for `dall-e-2`. Must be one of `1024x1024`, `1792x1024`, or `1024x1792` for `dall-e-3` models.
   * @return size
  */
  
  @Schema(name = "size", example = "1024x1024", description = "The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024` for `dall-e-2`. Must be one of `1024x1024`, `1792x1024`, or `1024x1792` for `dall-e-3` models.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size")
  public JsonNullable<SizeEnum> getSize() {
    return size;
  }

  public void setSize(JsonNullable<SizeEnum> size) {
    this.size = size;
  }

  public CreateImageRequest style(StyleEnum style) {
    this.style = JsonNullable.of(style);
    return this;
  }

  /**
   * The style of the generated images. Must be one of `vivid` or `natural`. Vivid causes the model to lean towards generating hyper-real and dramatic images. Natural causes the model to produce more natural, less hyper-real looking images. This param is only supported for `dall-e-3`.
   * @return style
  */
  
  @Schema(name = "style", example = "vivid", description = "The style of the generated images. Must be one of `vivid` or `natural`. Vivid causes the model to lean towards generating hyper-real and dramatic images. Natural causes the model to produce more natural, less hyper-real looking images. This param is only supported for `dall-e-3`.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("style")
  public JsonNullable<StyleEnum> getStyle() {
    return style;
  }

  public void setStyle(JsonNullable<StyleEnum> style) {
    this.style = style;
  }

  public CreateImageRequest user(String user) {
    this.user = user;
    return this;
  }

  /**
   * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids). 
   * @return user
  */
  
  @Schema(name = "user", example = "user-1234", description = "A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids). ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateImageRequest createImageRequest = (CreateImageRequest) o;
    return Objects.equals(this.prompt, createImageRequest.prompt) &&
        equalsNullable(this.model, createImageRequest.model) &&
        equalsNullable(this.n, createImageRequest.n) &&
        Objects.equals(this.quality, createImageRequest.quality) &&
        equalsNullable(this.responseFormat, createImageRequest.responseFormat) &&
        equalsNullable(this.size, createImageRequest.size) &&
        equalsNullable(this.style, createImageRequest.style) &&
        Objects.equals(this.user, createImageRequest.user);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(prompt, hashCodeNullable(model), hashCodeNullable(n), quality, hashCodeNullable(responseFormat), hashCodeNullable(size), hashCodeNullable(style), user);
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
    sb.append("class CreateImageRequest {\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    n: ").append(toIndentedString(n)).append("\n");
    sb.append("    quality: ").append(toIndentedString(quality)).append("\n");
    sb.append("    responseFormat: ").append(toIndentedString(responseFormat)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    style: ").append(toIndentedString(style)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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