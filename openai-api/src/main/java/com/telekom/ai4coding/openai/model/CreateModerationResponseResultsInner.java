package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * CreateModerationResponseResultsInner
 */

@JsonTypeName("CreateModerationResponse_results_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateModerationResponseResultsInner {

  private Boolean flagged;

  private CreateModerationResponseResultsInnerCategories categories;

  private CreateModerationResponseResultsInnerCategoryScores categoryScores;

  public CreateModerationResponseResultsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateModerationResponseResultsInner(Boolean flagged, CreateModerationResponseResultsInnerCategories categories, CreateModerationResponseResultsInnerCategoryScores categoryScores) {
    this.flagged = flagged;
    this.categories = categories;
    this.categoryScores = categoryScores;
  }

  public CreateModerationResponseResultsInner flagged(Boolean flagged) {
    this.flagged = flagged;
    return this;
  }

  /**
   * Whether the content violates [OpenAI's usage policies](/policies/usage-policies).
   * @return flagged
  */
  @NotNull 
  @Schema(name = "flagged", description = "Whether the content violates [OpenAI's usage policies](/policies/usage-policies).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("flagged")
  public Boolean getFlagged() {
    return flagged;
  }

  public void setFlagged(Boolean flagged) {
    this.flagged = flagged;
  }

  public CreateModerationResponseResultsInner categories(CreateModerationResponseResultsInnerCategories categories) {
    this.categories = categories;
    return this;
  }

  /**
   * Get categories
   * @return categories
  */
  @NotNull @Valid 
  @Schema(name = "categories", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("categories")
  public CreateModerationResponseResultsInnerCategories getCategories() {
    return categories;
  }

  public void setCategories(CreateModerationResponseResultsInnerCategories categories) {
    this.categories = categories;
  }

  public CreateModerationResponseResultsInner categoryScores(CreateModerationResponseResultsInnerCategoryScores categoryScores) {
    this.categoryScores = categoryScores;
    return this;
  }

  /**
   * Get categoryScores
   * @return categoryScores
  */
  @NotNull @Valid 
  @Schema(name = "category_scores", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("category_scores")
  public CreateModerationResponseResultsInnerCategoryScores getCategoryScores() {
    return categoryScores;
  }

  public void setCategoryScores(CreateModerationResponseResultsInnerCategoryScores categoryScores) {
    this.categoryScores = categoryScores;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateModerationResponseResultsInner createModerationResponseResultsInner = (CreateModerationResponseResultsInner) o;
    return Objects.equals(this.flagged, createModerationResponseResultsInner.flagged) &&
        Objects.equals(this.categories, createModerationResponseResultsInner.categories) &&
        Objects.equals(this.categoryScores, createModerationResponseResultsInner.categoryScores);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flagged, categories, categoryScores);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateModerationResponseResultsInner {\n");
    sb.append("    flagged: ").append(toIndentedString(flagged)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    categoryScores: ").append(toIndentedString(categoryScores)).append("\n");
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