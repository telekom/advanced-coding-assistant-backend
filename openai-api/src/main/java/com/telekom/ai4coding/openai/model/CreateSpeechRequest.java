package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * CreateSpeechRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateSpeechRequest {

  private CreateSpeechRequestModel model;

  private String input;

  /**
   * The voice to use when generating the audio. Supported voices are `alloy`, `echo`, `fable`, `onyx`, `nova`, and `shimmer`. Previews of the voices are available in the [Text to speech guide](/docs/guides/text-to-speech/voice-options).
   */
  public enum VoiceEnum {
    ALLOY("alloy"),
    
    ECHO("echo"),
    
    FABLE("fable"),
    
    ONYX("onyx"),
    
    NOVA("nova"),
    
    SHIMMER("shimmer");

    private String value;

    VoiceEnum(String value) {
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
    public static VoiceEnum fromValue(String value) {
      for (VoiceEnum b : VoiceEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private VoiceEnum voice;

  /**
   * The format to audio in. Supported formats are `mp3`, `opus`, `aac`, and `flac`.
   */
  public enum ResponseFormatEnum {
    MP3("mp3"),
    
    OPUS("opus"),
    
    AAC("aac"),
    
    FLAC("flac");

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
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ResponseFormatEnum responseFormat = ResponseFormatEnum.MP3;

  private BigDecimal speed = new BigDecimal("1.0");

  public CreateSpeechRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateSpeechRequest(CreateSpeechRequestModel model, String input, VoiceEnum voice) {
    this.model = model;
    this.input = input;
    this.voice = voice;
  }

  public CreateSpeechRequest model(CreateSpeechRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  @NotNull @Valid 
  @Schema(name = "model", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("model")
  public CreateSpeechRequestModel getModel() {
    return model;
  }

  public void setModel(CreateSpeechRequestModel model) {
    this.model = model;
  }

  public CreateSpeechRequest input(String input) {
    this.input = input;
    return this;
  }

  /**
   * The text to generate audio for. The maximum length is 4096 characters.
   * @return input
  */
  @NotNull @Size(max = 4096) 
  @Schema(name = "input", description = "The text to generate audio for. The maximum length is 4096 characters.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("input")
  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public CreateSpeechRequest voice(VoiceEnum voice) {
    this.voice = voice;
    return this;
  }

  /**
   * The voice to use when generating the audio. Supported voices are `alloy`, `echo`, `fable`, `onyx`, `nova`, and `shimmer`. Previews of the voices are available in the [Text to speech guide](/docs/guides/text-to-speech/voice-options).
   * @return voice
  */
  @NotNull 
  @Schema(name = "voice", description = "The voice to use when generating the audio. Supported voices are `alloy`, `echo`, `fable`, `onyx`, `nova`, and `shimmer`. Previews of the voices are available in the [Text to speech guide](/docs/guides/text-to-speech/voice-options).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("voice")
  public VoiceEnum getVoice() {
    return voice;
  }

  public void setVoice(VoiceEnum voice) {
    this.voice = voice;
  }

  public CreateSpeechRequest responseFormat(ResponseFormatEnum responseFormat) {
    this.responseFormat = responseFormat;
    return this;
  }

  /**
   * The format to audio in. Supported formats are `mp3`, `opus`, `aac`, and `flac`.
   * @return responseFormat
  */
  
  @Schema(name = "response_format", description = "The format to audio in. Supported formats are `mp3`, `opus`, `aac`, and `flac`.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("response_format")
  public ResponseFormatEnum getResponseFormat() {
    return responseFormat;
  }

  public void setResponseFormat(ResponseFormatEnum responseFormat) {
    this.responseFormat = responseFormat;
  }

  public CreateSpeechRequest speed(BigDecimal speed) {
    this.speed = speed;
    return this;
  }

  /**
   * The speed of the generated audio. Select a value from `0.25` to `4.0`. `1.0` is the default.
   * minimum: 0.25
   * maximum: 4.0
   * @return speed
  */
  @Valid @DecimalMin("0.25") @DecimalMax("4.0") 
  @Schema(name = "speed", description = "The speed of the generated audio. Select a value from `0.25` to `4.0`. `1.0` is the default.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("speed")
  public BigDecimal getSpeed() {
    return speed;
  }

  public void setSpeed(BigDecimal speed) {
    this.speed = speed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateSpeechRequest createSpeechRequest = (CreateSpeechRequest) o;
    return Objects.equals(this.model, createSpeechRequest.model) &&
        Objects.equals(this.input, createSpeechRequest.input) &&
        Objects.equals(this.voice, createSpeechRequest.voice) &&
        Objects.equals(this.responseFormat, createSpeechRequest.responseFormat) &&
        Objects.equals(this.speed, createSpeechRequest.speed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, input, voice, responseFormat, speed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateSpeechRequest {\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("    voice: ").append(toIndentedString(voice)).append("\n");
    sb.append("    responseFormat: ").append(toIndentedString(responseFormat)).append("\n");
    sb.append("    speed: ").append(toIndentedString(speed)).append("\n");
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