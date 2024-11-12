package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Usage statistics related to the run step. This value will be &#x60;null&#x60; while the run step&#39;s status is &#x60;in_progress&#x60;.
 */

@Schema(name = "RunStepCompletionUsage", description = "Usage statistics related to the run step. This value will be `null` while the run step's status is `in_progress`.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepCompletionUsage {

  private Integer completionTokens;

  private Integer promptTokens;

  private Integer totalTokens;

  public RunStepCompletionUsage() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepCompletionUsage(Integer completionTokens, Integer promptTokens, Integer totalTokens) {
    this.completionTokens = completionTokens;
    this.promptTokens = promptTokens;
    this.totalTokens = totalTokens;
  }

  public RunStepCompletionUsage completionTokens(Integer completionTokens) {
    this.completionTokens = completionTokens;
    return this;
  }

  /**
   * Number of completion tokens used over the course of the run step.
   * @return completionTokens
  */
  @NotNull 
  @Schema(name = "completion_tokens", description = "Number of completion tokens used over the course of the run step.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("completion_tokens")
  public Integer getCompletionTokens() {
    return completionTokens;
  }

  public void setCompletionTokens(Integer completionTokens) {
    this.completionTokens = completionTokens;
  }

  public RunStepCompletionUsage promptTokens(Integer promptTokens) {
    this.promptTokens = promptTokens;
    return this;
  }

  /**
   * Number of prompt tokens used over the course of the run step.
   * @return promptTokens
  */
  @NotNull 
  @Schema(name = "prompt_tokens", description = "Number of prompt tokens used over the course of the run step.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prompt_tokens")
  public Integer getPromptTokens() {
    return promptTokens;
  }

  public void setPromptTokens(Integer promptTokens) {
    this.promptTokens = promptTokens;
  }

  public RunStepCompletionUsage totalTokens(Integer totalTokens) {
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
    RunStepCompletionUsage runStepCompletionUsage = (RunStepCompletionUsage) o;
    return Objects.equals(this.completionTokens, runStepCompletionUsage.completionTokens) &&
        Objects.equals(this.promptTokens, runStepCompletionUsage.promptTokens) &&
        Objects.equals(this.totalTokens, runStepCompletionUsage.totalTokens);
  }

  @Override
  public int hashCode() {
    return Objects.hash(completionTokens, promptTokens, totalTokens);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepCompletionUsage {\n");
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