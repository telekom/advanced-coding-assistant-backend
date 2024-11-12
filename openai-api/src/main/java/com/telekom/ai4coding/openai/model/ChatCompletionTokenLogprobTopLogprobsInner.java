package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ChatCompletionTokenLogprobTopLogprobsInner
 */

@JsonTypeName("ChatCompletionTokenLogprob_top_logprobs_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionTokenLogprobTopLogprobsInner {

  private String token;

  private BigDecimal logprob;

  @Valid
  private JsonNullable<List<Integer>> bytes = JsonNullable.<List<Integer>>undefined();

  public ChatCompletionTokenLogprobTopLogprobsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionTokenLogprobTopLogprobsInner(String token, BigDecimal logprob, List<Integer> bytes) {
    this.token = token;
    this.logprob = logprob;
    this.bytes = JsonNullable.of(bytes);
  }

  public ChatCompletionTokenLogprobTopLogprobsInner token(String token) {
    this.token = token;
    return this;
  }

  /**
   * The token.
   * @return token
  */
  @NotNull 
  @Schema(name = "token", description = "The token.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public ChatCompletionTokenLogprobTopLogprobsInner logprob(BigDecimal logprob) {
    this.logprob = logprob;
    return this;
  }

  /**
   * The log probability of this token.
   * @return logprob
  */
  @NotNull @Valid 
  @Schema(name = "logprob", description = "The log probability of this token.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("logprob")
  public BigDecimal getLogprob() {
    return logprob;
  }

  public void setLogprob(BigDecimal logprob) {
    this.logprob = logprob;
  }

  public ChatCompletionTokenLogprobTopLogprobsInner bytes(List<Integer> bytes) {
    this.bytes = JsonNullable.of(bytes);
    return this;
  }

  public ChatCompletionTokenLogprobTopLogprobsInner addBytesItem(Integer bytesItem) {
    if (this.bytes == null || !this.bytes.isPresent()) {
      this.bytes = JsonNullable.of(new ArrayList<>());
    }
    this.bytes.get().add(bytesItem);
    return this;
  }

  /**
   * A list of integers representing the UTF-8 bytes representation of the token. Useful in instances where characters are represented by multiple tokens and their byte representations must be combined to generate the correct text representation. Can be `null` if there is no bytes representation for the token.
   * @return bytes
  */
  @NotNull 
  @Schema(name = "bytes", description = "A list of integers representing the UTF-8 bytes representation of the token. Useful in instances where characters are represented by multiple tokens and their byte representations must be combined to generate the correct text representation. Can be `null` if there is no bytes representation for the token.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("bytes")
  public JsonNullable<List<Integer>> getBytes() {
    return bytes;
  }

  public void setBytes(JsonNullable<List<Integer>> bytes) {
    this.bytes = bytes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionTokenLogprobTopLogprobsInner chatCompletionTokenLogprobTopLogprobsInner = (ChatCompletionTokenLogprobTopLogprobsInner) o;
    return Objects.equals(this.token, chatCompletionTokenLogprobTopLogprobsInner.token) &&
        Objects.equals(this.logprob, chatCompletionTokenLogprobTopLogprobsInner.logprob) &&
        Objects.equals(this.bytes, chatCompletionTokenLogprobTopLogprobsInner.bytes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, logprob, bytes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionTokenLogprobTopLogprobsInner {\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    logprob: ").append(toIndentedString(logprob)).append("\n");
    sb.append("    bytes: ").append(toIndentedString(bytes)).append("\n");
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