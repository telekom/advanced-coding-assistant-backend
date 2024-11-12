package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * A list of the categories, and whether they are flagged or not.
 */

@Schema(name = "CreateModerationResponse_results_inner_categories", description = "A list of the categories, and whether they are flagged or not.")
@JsonTypeName("CreateModerationResponse_results_inner_categories")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateModerationResponseResultsInnerCategories {

  private Boolean hate;

  private Boolean hateThreatening;

  private Boolean harassment;

  private Boolean harassmentThreatening;

  private Boolean selfHarm;

  private Boolean selfHarmIntent;

  private Boolean selfHarmInstructions;

  private Boolean sexual;

  private Boolean sexualMinors;

  private Boolean violence;

  private Boolean violenceGraphic;

  public CreateModerationResponseResultsInnerCategories() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateModerationResponseResultsInnerCategories(Boolean hate, Boolean hateThreatening, Boolean harassment, Boolean harassmentThreatening, Boolean selfHarm, Boolean selfHarmIntent, Boolean selfHarmInstructions, Boolean sexual, Boolean sexualMinors, Boolean violence, Boolean violenceGraphic) {
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

  public CreateModerationResponseResultsInnerCategories hate(Boolean hate) {
    this.hate = hate;
    return this;
  }

  /**
   * Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. Hateful content aimed at non-protected groups (e.g., chess players) is harassment.
   * @return hate
  */
  @NotNull 
  @Schema(name = "hate", description = "Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. Hateful content aimed at non-protected groups (e.g., chess players) is harassment.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hate")
  public Boolean getHate() {
    return hate;
  }

  public void setHate(Boolean hate) {
    this.hate = hate;
  }

  public CreateModerationResponseResultsInnerCategories hateThreatening(Boolean hateThreatening) {
    this.hateThreatening = hateThreatening;
    return this;
  }

  /**
   * Hateful content that also includes violence or serious harm towards the targeted group based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.
   * @return hateThreatening
  */
  @NotNull 
  @Schema(name = "hate/threatening", description = "Hateful content that also includes violence or serious harm towards the targeted group based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hate/threatening")
  public Boolean getHateThreatening() {
    return hateThreatening;
  }

  public void setHateThreatening(Boolean hateThreatening) {
    this.hateThreatening = hateThreatening;
  }

  public CreateModerationResponseResultsInnerCategories harassment(Boolean harassment) {
    this.harassment = harassment;
    return this;
  }

  /**
   * Content that expresses, incites, or promotes harassing language towards any target.
   * @return harassment
  */
  @NotNull 
  @Schema(name = "harassment", description = "Content that expresses, incites, or promotes harassing language towards any target.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("harassment")
  public Boolean getHarassment() {
    return harassment;
  }

  public void setHarassment(Boolean harassment) {
    this.harassment = harassment;
  }

  public CreateModerationResponseResultsInnerCategories harassmentThreatening(Boolean harassmentThreatening) {
    this.harassmentThreatening = harassmentThreatening;
    return this;
  }

  /**
   * Harassment content that also includes violence or serious harm towards any target.
   * @return harassmentThreatening
  */
  @NotNull 
  @Schema(name = "harassment/threatening", description = "Harassment content that also includes violence or serious harm towards any target.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("harassment/threatening")
  public Boolean getHarassmentThreatening() {
    return harassmentThreatening;
  }

  public void setHarassmentThreatening(Boolean harassmentThreatening) {
    this.harassmentThreatening = harassmentThreatening;
  }

  public CreateModerationResponseResultsInnerCategories selfHarm(Boolean selfHarm) {
    this.selfHarm = selfHarm;
    return this;
  }

  /**
   * Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.
   * @return selfHarm
  */
  @NotNull 
  @Schema(name = "self-harm", description = "Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self-harm")
  public Boolean getSelfHarm() {
    return selfHarm;
  }

  public void setSelfHarm(Boolean selfHarm) {
    this.selfHarm = selfHarm;
  }

  public CreateModerationResponseResultsInnerCategories selfHarmIntent(Boolean selfHarmIntent) {
    this.selfHarmIntent = selfHarmIntent;
    return this;
  }

  /**
   * Content where the speaker expresses that they are engaging or intend to engage in acts of self-harm, such as suicide, cutting, and eating disorders.
   * @return selfHarmIntent
  */
  @NotNull 
  @Schema(name = "self-harm/intent", description = "Content where the speaker expresses that they are engaging or intend to engage in acts of self-harm, such as suicide, cutting, and eating disorders.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self-harm/intent")
  public Boolean getSelfHarmIntent() {
    return selfHarmIntent;
  }

  public void setSelfHarmIntent(Boolean selfHarmIntent) {
    this.selfHarmIntent = selfHarmIntent;
  }

  public CreateModerationResponseResultsInnerCategories selfHarmInstructions(Boolean selfHarmInstructions) {
    this.selfHarmInstructions = selfHarmInstructions;
    return this;
  }

  /**
   * Content that encourages performing acts of self-harm, such as suicide, cutting, and eating disorders, or that gives instructions or advice on how to commit such acts.
   * @return selfHarmInstructions
  */
  @NotNull 
  @Schema(name = "self-harm/instructions", description = "Content that encourages performing acts of self-harm, such as suicide, cutting, and eating disorders, or that gives instructions or advice on how to commit such acts.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self-harm/instructions")
  public Boolean getSelfHarmInstructions() {
    return selfHarmInstructions;
  }

  public void setSelfHarmInstructions(Boolean selfHarmInstructions) {
    this.selfHarmInstructions = selfHarmInstructions;
  }

  public CreateModerationResponseResultsInnerCategories sexual(Boolean sexual) {
    this.sexual = sexual;
    return this;
  }

  /**
   * Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness).
   * @return sexual
  */
  @NotNull 
  @Schema(name = "sexual", description = "Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sexual")
  public Boolean getSexual() {
    return sexual;
  }

  public void setSexual(Boolean sexual) {
    this.sexual = sexual;
  }

  public CreateModerationResponseResultsInnerCategories sexualMinors(Boolean sexualMinors) {
    this.sexualMinors = sexualMinors;
    return this;
  }

  /**
   * Sexual content that includes an individual who is under 18 years old.
   * @return sexualMinors
  */
  @NotNull 
  @Schema(name = "sexual/minors", description = "Sexual content that includes an individual who is under 18 years old.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sexual/minors")
  public Boolean getSexualMinors() {
    return sexualMinors;
  }

  public void setSexualMinors(Boolean sexualMinors) {
    this.sexualMinors = sexualMinors;
  }

  public CreateModerationResponseResultsInnerCategories violence(Boolean violence) {
    this.violence = violence;
    return this;
  }

  /**
   * Content that depicts death, violence, or physical injury.
   * @return violence
  */
  @NotNull 
  @Schema(name = "violence", description = "Content that depicts death, violence, or physical injury.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("violence")
  public Boolean getViolence() {
    return violence;
  }

  public void setViolence(Boolean violence) {
    this.violence = violence;
  }

  public CreateModerationResponseResultsInnerCategories violenceGraphic(Boolean violenceGraphic) {
    this.violenceGraphic = violenceGraphic;
    return this;
  }

  /**
   * Content that depicts death, violence, or physical injury in graphic detail.
   * @return violenceGraphic
  */
  @NotNull 
  @Schema(name = "violence/graphic", description = "Content that depicts death, violence, or physical injury in graphic detail.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("violence/graphic")
  public Boolean getViolenceGraphic() {
    return violenceGraphic;
  }

  public void setViolenceGraphic(Boolean violenceGraphic) {
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
    CreateModerationResponseResultsInnerCategories createModerationResponseResultsInnerCategories = (CreateModerationResponseResultsInnerCategories) o;
    return Objects.equals(this.hate, createModerationResponseResultsInnerCategories.hate) &&
        Objects.equals(this.hateThreatening, createModerationResponseResultsInnerCategories.hateThreatening) &&
        Objects.equals(this.harassment, createModerationResponseResultsInnerCategories.harassment) &&
        Objects.equals(this.harassmentThreatening, createModerationResponseResultsInnerCategories.harassmentThreatening) &&
        Objects.equals(this.selfHarm, createModerationResponseResultsInnerCategories.selfHarm) &&
        Objects.equals(this.selfHarmIntent, createModerationResponseResultsInnerCategories.selfHarmIntent) &&
        Objects.equals(this.selfHarmInstructions, createModerationResponseResultsInnerCategories.selfHarmInstructions) &&
        Objects.equals(this.sexual, createModerationResponseResultsInnerCategories.sexual) &&
        Objects.equals(this.sexualMinors, createModerationResponseResultsInnerCategories.sexualMinors) &&
        Objects.equals(this.violence, createModerationResponseResultsInnerCategories.violence) &&
        Objects.equals(this.violenceGraphic, createModerationResponseResultsInnerCategories.violenceGraphic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hate, hateThreatening, harassment, harassmentThreatening, selfHarm, selfHarmIntent, selfHarmInstructions, sexual, sexualMinors, violence, violenceGraphic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateModerationResponseResultsInnerCategories {\n");
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