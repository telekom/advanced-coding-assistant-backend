package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Usage statistics related to the run. This value will be &#x60;null&#x60; if the run is not in a terminal state (i.e. &#x60;in_progress&#x60;, &#x60;queued&#x60;, etc.).
 */

@Schema(name = "RunCompletionUsage", description = "Usage statistics related to the run. This value will be `null` if the run is not in a terminal state (i.e. `in_progress`, `queued`, etc.).")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunCompletionUsage {

  private Integer completionTokens;

  private Integer promptTokens;

  private Integer totalTokens;

  public RunCompletionUsage() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunCompletionUsage(Integer completionTokens, Integer promptTokens, Integer totalTokens) {
    this.completionTokens = completionTokens;
    this.promptTokens = promptTokens;
    this.totalTokens = totalTokens;
  }

  public RunCompletionUsage completionTokens(Integer completionTokens) {
    this.completionTokens = completionTokens;
    return this;
  }

  /**
   * Number of completion tokens used over the course of the run.
   * @return completionTokens
  */
  @NotNull 
  @Schema(name = "completion_tokens", description = "Number of completion tokens used over the course of the run.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("completion_tokens")
  public Integer getCompletionTokens() {
    return completionTokens;
  }

  public void setCompletionTokens(Integer completionTokens) {
    this.completionTokens = completionTokens;
  }

  public RunCompletionUsage promptTokens(Integer promptTokens) {
    this.promptTokens = promptTokens;
    return this;
  }

  /**
   * Number of prompt tokens used over the course of the run.
   * @return promptTokens
  */
  @NotNull 
  @Schema(name = "prompt_tokens", description = "Number of prompt tokens used over the course of the run.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prompt_tokens")
  public Integer getPromptTokens() {
    return promptTokens;
  }

  public void setPromptTokens(Integer promptTokens) {
    this.promptTokens = promptTokens;
  }

  public RunCompletionUsage totalTokens(Integer totalTokens) {
    this.totalTokens = totalTokens;
    return this;
  }

  /**
   * Total number of tokens used (prompt + completion).
   * @return totalTokens
  */
  @NotNull 
  @Schema(name = "total_tokens", description = "Total number of tokens used (prompt + completion).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("total_tokens")
  public Integer getTotalTokens() {
    return totalTokens;
  }

  public void setTotalTokens(Integer totalTokens) {
    this.totalTokens = totalTokens;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunCompletionUsage runCompletionUsage = (RunCompletionUsage) o;
    return Objects.equals(this.completionTokens, runCompletionUsage.completionTokens) &&
        Objects.equals(this.promptTokens, runCompletionUsage.promptTokens) &&
        Objects.equals(this.totalTokens, runCompletionUsage.totalTokens);
  }

  @Override
  public int hashCode() {
    return Objects.hash(completionTokens, promptTokens, totalTokens);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunCompletionUsage {\n");
    sb.append("    completionTokens: ").append(toIndentedString(completionTokens)).append("\n");
    sb.append("    promptTokens: ").append(toIndentedString(promptTokens)).append("\n");
    sb.append("    totalTokens: ").append(toIndentedString(totalTokens)).append("\n");
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