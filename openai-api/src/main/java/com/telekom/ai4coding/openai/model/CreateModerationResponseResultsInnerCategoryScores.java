package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A list of the categories along with their scores as predicted by model.
 */

@Schema(name = "CreateModerationResponse_results_inner_category_scores", description = "A list of the categories along with their scores as predicted by model.")
@JsonTypeName("CreateModerationResponse_results_inner_category_scores")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateModerationResponseResultsInnerCategoryScores {

  private BigDecimal hate;

  private BigDecimal hateThreatening;

  private BigDecimal harassment;

  private BigDecimal harassmentThreatening;

  private BigDecimal selfHarm;

  private BigDecimal selfHarmIntent;

  private BigDecimal selfHarmInstructions;

  private BigDecimal sexual;

  private BigDecimal sexualMinors;

  private BigDecimal violence;

  private BigDecimal violenceGraphic;

  public CreateModerationResponseResultsInnerCategoryScores() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateModerationResponseResultsInnerCategoryScores(BigDecimal hate, BigDecimal hateThreatening, BigDecimal harassment, BigDecimal harassmentThreatening, BigDecimal selfHarm, BigDecimal selfHarmIntent, BigDecimal selfHarmInstructions, BigDecimal sexual, BigDecimal sexualMinors, BigDecimal violence, BigDecimal violenceGraphic) {
    this.hate = hate;
    this.hateThreatening = hateThreatening;
    this.harassment = harassment;
    this.harassmentThreatening = harassmentThreatening;
    this.selfHarm = selfHarm;
    this.selfHarmIntent = selfHarmIntent;
    this.selfHarmInstructions = selfHarmInstructions;
    this.sexual = sexual;
    this.sexualMinors = sexualMinors;
    this.violence = violence;
    this.violenceGraphic = violenceGraphic;
  }

  public CreateModerationResponseResultsInnerCategoryScores hate(BigDecimal hate) {
    this.hate = hate;
    return this;
  }

  /**
   * The score for the category 'hate'.
   * @return hate
  */
  @NotNull @Valid 
  @Schema(name = "hate", description = "The score for the category 'hate'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hate")
  public BigDecimal getHate() {
    return hate;
  }

  public void setHate(BigDecimal hate) {
    this.hate = hate;
  }

  public CreateModerationResponseResultsInnerCategoryScores hateThreatening(BigDecimal hateThreatening) {
    this.hateThreatening = hateThreatening;
    return this;
  }

  /**
   * The score for the category 'hate/threatening'.
   * @return hateThreatening
  */
  @NotNull @Valid 
  @Schema(name = "hate/threatening", description = "The score for the category 'hate/threatening'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hate/threatening")
  public BigDecimal getHateThreatening() {
    return hateThreatening;
  }

  public void setHateThreatening(BigDecimal hateThreatening) {
    this.hateThreatening = hateThreatening;
  }

  public CreateModerationResponseResultsInnerCategoryScores harassment(BigDecimal harassment) {
    this.harassment = harassment;
    return this;
  }

  /**
   * The score for the category 'harassment'.
   * @return harassment
  */
  @NotNull @Valid 
  @Schema(name = "harassment", description = "The score for the category 'harassment'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("harassment")
  public BigDecimal getHarassment() {
    return harassment;
  }

  public void setHarassment(BigDecimal harassment) {
    this.harassment = harassment;
  }

  public CreateModerationResponseResultsInnerCategoryScores harassmentThreatening(BigDecimal harassmentThreatening) {
    this.harassmentThreatening = harassmentThreatening;
    return this;
  }

  /**
   * The score for the category 'harassment/threatening'.
   * @return harassmentThreatening
  */
  @NotNull @Valid 
  @Schema(name = "harassment/threatening", description = "The score for the category 'harassment/threatening'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("harassment/threatening")
  public BigDecimal getHarassmentThreatening() {
    return harassmentThreatening;
  }

  public void setHarassmentThreatening(BigDecimal harassmentThreatening) {
    this.harassmentThreatening = harassmentThreatening;
  }

  public CreateModerationResponseResultsInnerCategoryScores selfHarm(BigDecimal selfHarm) {
    this.selfHarm = selfHarm;
    return this;
  }

  /**
   * The score for the category 'self-harm'.
   * @return selfHarm
  */
  @NotNull @Valid 
  @Schema(name = "self-harm", description = "The score for the category 'self-harm'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self-harm")
  public BigDecimal getSelfHarm() {
    return selfHarm;
  }

  public void setSelfHarm(BigDecimal selfHarm) {
    this.selfHarm = selfHarm;
  }

  public CreateModerationResponseResultsInnerCategoryScores selfHarmIntent(BigDecimal selfHarmIntent) {
    this.selfHarmIntent = selfHarmIntent;
    return this;
  }

  /**
   * The score for the category 'self-harm/intent'.
   * @return selfHarmIntent
  */
  @NotNull @Valid 
  @Schema(name = "self-harm/intent", description = "The score for the category 'self-harm/intent'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self-harm/intent")
  public BigDecimal getSelfHarmIntent() {
    return selfHarmIntent;
  }

  public void setSelfHarmIntent(BigDecimal selfHarmIntent) {
    this.selfHarmIntent = selfHarmIntent;
  }

  public CreateModerationResponseResultsInnerCategoryScores selfHarmInstructions(BigDecimal selfHarmInstructions) {
    this.selfHarmInstructions = selfHarmInstructions;
    return this;
  }

  /**
   * The score for the category 'self-harm/instructions'.
   * @return selfHarmInstructions
  */
  @NotNull @Valid 
  @Schema(name = "self-harm/instructions", description = "The score for the category 'self-harm/instructions'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self-harm/instructions")
  public BigDecimal getSelfHarmInstructions() {
    return selfHarmInstructions;
  }

  public void setSelfHarmInstructions(BigDecimal selfHarmInstructions) {
    this.selfHarmInstructions = selfHarmInstructions;
  }

  public CreateModerationResponseResultsInnerCategoryScores sexual(BigDecimal sexual) {
    this.sexual = sexual;
    return this;
  }

  /**
   * The score for the category 'sexual'.
   * @return sexual
  */
  @NotNull @Valid 
  @Schema(name = "sexual", description = "The score for the category 'sexual'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sexual")
  public BigDecimal getSexual() {
    return sexual;
  }

  public void setSexual(BigDecimal sexual) {
    this.sexual = sexual;
  }

  public CreateModerationResponseResultsInnerCategoryScores sexualMinors(BigDecimal sexualMinors) {
    this.sexualMinors = sexualMinors;
    return this;
  }

  /**
   * The score for the category 'sexual/minors'.
   * @return sexualMinors
  */
  @NotNull @Valid 
  @Schema(name = "sexual/minors", description = "The score for the category 'sexual/minors'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sexual/minors")
  public BigDecimal getSexualMinors() {
    return sexualMinors;
  }

  public void setSexualMinors(BigDecimal sexualMinors) {
    this.sexualMinors = sexualMinors;
  }

  public CreateModerationResponseResultsInnerCategoryScores violence(BigDecimal violence) {
    this.violence = violence;
    return this;
  }

  /**
   * The score for the category 'violence'.
   * @return violence
  */
  @NotNull @Valid 
  @Schema(name = "violence", description = "The score for the category 'violence'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("violence")
  public BigDecimal getViolence() {
    return violence;
  }

  public void setViolence(BigDecimal violence) {
    this.violence = violence;
  }

  public CreateModerationResponseResultsInnerCategoryScores violenceGraphic(BigDecimal violenceGraphic) {
    this.violenceGraphic = violenceGraphic;
    return this;
  }

  /**
   * The score for the category 'violence/graphic'.
   * @return violenceGraphic
  */
  @NotNull @Valid 
  @Schema(name = "violence/graphic", description = "The score for the category 'violence/graphic'.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("violence/graphic")
  public BigDecimal getViolenceGraphic() {
    return violenceGraphic;
  }

  public void setViolenceGraphic(BigDecimal violenceGraphic) {
    this.violenceGraphic = violenceGraphic;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateModerationResponseResultsInnerCategoryScores createModerationResponseResultsInnerCategoryScores = (CreateModerationResponseResultsInnerCategoryScores) o;
    return Objects.equals(this.hate, createModerationResponseResultsInnerCategoryScores.hate) &&
        Objects.equals(this.hateThreatening, createModerationResponseResultsInnerCategoryScores.hateThreatening) &&
        Objects.equals(this.harassment, createModerationResponseResultsInnerCategoryScores.harassment) &&
        Objects.equals(this.harassmentThreatening, createModerationResponseResultsInnerCategoryScores.harassmentThreatening) &&
        Objects.equals(this.selfHarm, createModerationResponseResultsInnerCategoryScores.selfHarm) &&
        Objects.equals(this.selfHarmIntent, createModerationResponseResultsInnerCategoryScores.selfHarmIntent) &&
        Objects.equals(this.selfHarmInstructions, createModerationResponseResultsInnerCategoryScores.selfHarmInstructions) &&
        Objects.equals(this.sexual, createModerationResponseResultsInnerCategoryScores.sexual) &&
        Objects.equals(this.sexualMinors, createModerationResponseResultsInnerCategoryScores.sexualMinors) &&
        Objects.equals(this.violence, createModerationResponseResultsInnerCategoryScores.violence) &&
        Objects.equals(this.violenceGraphic, createModerationResponseResultsInnerCategoryScores.violenceGraphic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hate, hateThreatening, harassment, harassmentThreatening, selfHarm, selfHarmIntent, selfHarmInstructions, sexual, sexualMinors, violence, violenceGraphic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateModerationResponseResultsInnerCategoryScores {\n");
    sb.append("    hate: ").append(toIndentedString(hate)).append("\n");
    sb.append("    hateThreatening: ").append(toIndentedString(hateThreatening)).append("\n");
    sb.append("    harassment: ").append(toIndentedString(harassment)).append("\n");
    sb.append("    harassmentThreatening: ").append(toIndentedString(harassmentThreatening)).append("\n");
    sb.append("    selfHarm: ").append(toIndentedString(selfHarm)).append("\n");
    sb.append("    selfHarmIntent: ").append(toIndentedString(selfHarmIntent)).append("\n");
    sb.append("    selfHarmInstructions: ").append(toIndentedString(selfHarmInstructions)).append("\n");
    sb.append("    sexual: ").append(toIndentedString(sexual)).append("\n");
    sb.append("    sexualMinors: ").append(toIndentedString(sexualMinors)).append("\n");
    sb.append("    violence: ").append(toIndentedString(violence)).append("\n");
    sb.append("    violenceGraphic: ").append(toIndentedString(violenceGraphic)).append("\n");
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