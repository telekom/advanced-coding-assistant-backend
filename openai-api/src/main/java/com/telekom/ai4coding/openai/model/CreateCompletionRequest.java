package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * CreateCompletionRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateCompletionRequest {

  private CreateCompletionRequestModel model;

  private JsonNullable<CreateCompletionRequestPrompt> prompt = JsonNullable.<CreateCompletionRequestPrompt>undefined();

  private JsonNullable<@Min(0) @Max(20) Integer> bestOf = JsonNullable.<Integer>undefined();

  private JsonNullable<Boolean> echo = JsonNullable.<Boolean>undefined();

  private JsonNullable<@DecimalMin("-2") @DecimalMax("2") BigDecimal> frequencyPenalty = JsonNullable.<BigDecimal>undefined();

  @Valid
  private JsonNullable<Map<String, Integer>> logitBias = JsonNullable.<Map<String, Integer>>undefined();

  private JsonNullable<@Min(0) @Max(5) Integer> logprobs = JsonNullable.<Integer>undefined();

  private JsonNullable<@Min(0) Integer> maxTokens = JsonNullable.<Integer>undefined();

  private JsonNullable<@Min(1) @Max(128) Integer> n = JsonNullable.<Integer>undefined();

  private JsonNullable<@DecimalMin("-2") @DecimalMax("2") BigDecimal> presencePenalty = JsonNullable.<BigDecimal>undefined();

  private JsonNullable<@DecimalMin(value = "-9223372036854775808") @DecimalMax(value = "9223372036854775807") BigDecimal> seed = JsonNullable.<BigDecimal>undefined();

  private JsonNullable<CreateCompletionRequestStop> stop = JsonNullable.<CreateCompletionRequestStop>undefined();

  private JsonNullable<Boolean> stream = JsonNullable.<Boolean>undefined();

  private JsonNullable<String> suffix = JsonNullable.<String>undefined();

  private JsonNullable<@DecimalMin("0") @DecimalMax("2") BigDecimal> temperature = JsonNullable.<BigDecimal>undefined();

  private JsonNullable<@DecimalMin("0") @DecimalMax("1") BigDecimal> topP = JsonNullable.<BigDecimal>undefined();

  private String user;

  public CreateCompletionRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateCompletionRequest(CreateCompletionRequestModel model, CreateCompletionRequestPrompt prompt) {
    this.model = model;
    this.prompt = JsonNullable.of(prompt);
  }

  public CreateCompletionRequest model(CreateCompletionRequestModel model) {
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
  public CreateCompletionRequestModel getModel() {
    return model;
  }

  public void setModel(CreateCompletionRequestModel model) {
    this.model = model;
  }

  public CreateCompletionRequest prompt(CreateCompletionRequestPrompt prompt) {
    this.prompt = JsonNullable.of(prompt);
    return this;
  }

  /**
   * Get prompt
   * @return prompt
  */
  @NotNull @Valid 
  @Schema(name = "prompt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prompt")
  public JsonNullable<CreateCompletionRequestPrompt> getPrompt() {
    return prompt;
  }

  public void setPrompt(JsonNullable<CreateCompletionRequestPrompt> prompt) {
    this.prompt = prompt;
  }

  public CreateCompletionRequest bestOf(Integer bestOf) {
    this.bestOf = JsonNullable.of(bestOf);
    return this;
  }

  /**
   * Generates `best_of` completions server-side and returns the \"best\" (the one with the highest log probability per token). Results cannot be streamed.  When used with `n`, `best_of` controls the number of candidate completions and `n` specifies how many to return – `best_of` must be greater than `n`.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for `max_tokens` and `stop`. 
   * minimum: 0
   * maximum: 20
   * @return bestOf
  */
  @Min(0) @Max(20) 
  @Schema(name = "best_of", description = "Generates `best_of` completions server-side and returns the \"best\" (the one with the highest log probability per token). Results cannot be streamed.  When used with `n`, `best_of` controls the number of candidate completions and `n` specifies how many to return – `best_of` must be greater than `n`.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for `max_tokens` and `stop`. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("best_of")
  public JsonNullable<@Min(0) @Max(20) Integer> getBestOf() {
    return bestOf;
  }

  public void setBestOf(JsonNullable<Integer> bestOf) {
    this.bestOf = bestOf;
  }

  public CreateCompletionRequest echo(Boolean echo) {
    this.echo = JsonNullable.of(echo);
    return this;
  }

  /**
   * Echo back the prompt in addition to the completion 
   * @return echo
  */
  
  @Schema(name = "echo", description = "Echo back the prompt in addition to the completion ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("echo")
  public JsonNullable<Boolean> getEcho() {
    return echo;
  }

  public void setEcho(JsonNullable<Boolean> echo) {
    this.echo = echo;
  }

  public CreateCompletionRequest frequencyPenalty(BigDecimal frequencyPenalty) {
    this.frequencyPenalty = JsonNullable.of(frequencyPenalty);
    return this;
  }

  /**
   * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.  [See more information about frequency and presence penalties.](/docs/guides/text-generation/parameter-details) 
   * minimum: -2
   * maximum: 2
   * @return frequencyPenalty
  */
  @Valid @DecimalMin("-2") @DecimalMax("2") 
  @Schema(name = "frequency_penalty", description = "Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.  [See more information about frequency and presence penalties.](/docs/guides/text-generation/parameter-details) ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("frequency_penalty")
  public JsonNullable<@DecimalMin("-2") @DecimalMax("2") BigDecimal> getFrequencyPenalty() {
    return frequencyPenalty;
  }

  public void setFrequencyPenalty(JsonNullable<BigDecimal> frequencyPenalty) {
    this.frequencyPenalty = frequencyPenalty;
  }

  public CreateCompletionRequest logitBias(Map<String, Integer> logitBias) {
    this.logitBias = JsonNullable.of(logitBias);
    return this;
  }

  public CreateCompletionRequest putLogitBiasItem(String key, Integer logitBiasItem) {
    if (this.logitBias == null || !this.logitBias.isPresent()) {
      this.logitBias = JsonNullable.of(new HashMap<>());
    }
    this.logitBias.get().put(key, logitBiasItem);
    return this;
  }

  /**
   * Modify the likelihood of specified tokens appearing in the completion.  Accepts a JSON object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this [tokenizer tool](/tokenizer?view=bpe) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.  As an example, you can pass `{\"50256\": -100}` to prevent the <|endoftext|> token from being generated. 
   * @return logitBias
  */
  
  @Schema(name = "logit_bias", description = "Modify the likelihood of specified tokens appearing in the completion.  Accepts a JSON object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this [tokenizer tool](/tokenizer?view=bpe) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.  As an example, you can pass `{\"50256\": -100}` to prevent the <|endoftext|> token from being generated. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("logit_bias")
  public JsonNullable<Map<String, Integer>> getLogitBias() {
    return logitBias;
  }

  public void setLogitBias(JsonNullable<Map<String, Integer>> logitBias) {
    this.logitBias = logitBias;
  }

  public CreateCompletionRequest logprobs(Integer logprobs) {
    this.logprobs = JsonNullable.of(logprobs);
    return this;
  }

  /**
   * Include the log probabilities on the `logprobs` most likely output tokens, as well the chosen tokens. For example, if `logprobs` is 5, the API will return a list of the 5 most likely tokens. The API will always return the `logprob` of the sampled token, so there may be up to `logprobs+1` elements in the response.  The maximum value for `logprobs` is 5. 
   * minimum: 0
   * maximum: 5
   * @return logprobs
  */
  @Min(0) @Max(5) 
  @Schema(name = "logprobs", description = "Include the log probabilities on the `logprobs` most likely output tokens, as well the chosen tokens. For example, if `logprobs` is 5, the API will return a list of the 5 most likely tokens. The API will always return the `logprob` of the sampled token, so there may be up to `logprobs+1` elements in the response.  The maximum value for `logprobs` is 5. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("logprobs")
  public JsonNullable<@Min(0) @Max(5) Integer> getLogprobs() {
    return logprobs;
  }

  public void setLogprobs(JsonNullable<Integer> logprobs) {
    this.logprobs = logprobs;
  }

  public CreateCompletionRequest maxTokens(Integer maxTokens) {
    this.maxTokens = JsonNullable.of(maxTokens);
    return this;
  }

  /**
   * The maximum number of [tokens](/tokenizer) that can be generated in the completion.  The token count of your prompt plus `max_tokens` cannot exceed the model's context length. [Example Python code](https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken) for counting tokens. 
   * minimum: 0
   * @return maxTokens
  */
  @Min(0) 
  @Schema(name = "max_tokens", example = "16", description = "The maximum number of [tokens](/tokenizer) that can be generated in the completion.  The token count of your prompt plus `max_tokens` cannot exceed the model's context length. [Example Python code](https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken) for counting tokens. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("max_tokens")
  public JsonNullable<@Min(0) Integer> getMaxTokens() {
    return maxTokens;
  }

  public void setMaxTokens(JsonNullable<Integer> maxTokens) {
    this.maxTokens = maxTokens;
  }

  public CreateCompletionRequest n(Integer n) {
    this.n = JsonNullable.of(n);
    return this;
  }

  /**
   * How many completions to generate for each prompt.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for `max_tokens` and `stop`. 
   * minimum: 1
   * maximum: 128
   * @return n
  */
  @Min(1) @Max(128) 
  @Schema(name = "n", example = "1", description = "How many completions to generate for each prompt.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for `max_tokens` and `stop`. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("n")
  public JsonNullable<@Min(1) @Max(128) Integer> getN() {
    return n;
  }

  public void setN(JsonNullable<Integer> n) {
    this.n = n;
  }

  public CreateCompletionRequest presencePenalty(BigDecimal presencePenalty) {
    this.presencePenalty = JsonNullable.of(presencePenalty);
    return this;
  }

  /**
   * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.  [See more information about frequency and presence penalties.](/docs/guides/text-generation/parameter-details) 
   * minimum: -2
   * maximum: 2
   * @return presencePenalty
  */
  @Valid @DecimalMin("-2") @DecimalMax("2") 
  @Schema(name = "presence_penalty", description = "Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.  [See more information about frequency and presence penalties.](/docs/guides/text-generation/parameter-details) ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("presence_penalty")
  public JsonNullable<@DecimalMin("-2") @DecimalMax("2") BigDecimal> getPresencePenalty() {
    return presencePenalty;
  }

  public void setPresencePenalty(JsonNullable<BigDecimal> presencePenalty) {
    this.presencePenalty = presencePenalty;
  }

  public CreateCompletionRequest seed(BigDecimal seed) {
    this.seed = JsonNullable.of(seed);
    return this;
  }

  /**
   * If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same `seed` and parameters should return the same result.  Determinism is not guaranteed, and you should refer to the `system_fingerprint` response parameter to monitor changes in the backend. 
   * minimum: -9223372036854775808
   * maximum: 9223372036854775807
   * @return seed
  */
  @DecimalMin(value = "-9223372036854775808") @DecimalMax(value = "9223372036854775807")
  @Schema(name = "seed", description = "If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same `seed` and parameters should return the same result.  Determinism is not guaranteed, and you should refer to the `system_fingerprint` response parameter to monitor changes in the backend. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("seed")
  public JsonNullable<@DecimalMin(value = "-9223372036854775808") @DecimalMax(value = "9223372036854775807") BigDecimal> getSeed() {
    return seed;
  }

  public void setSeed(JsonNullable<BigDecimal> seed) {
    this.seed = seed;
  }

  public CreateCompletionRequest stop(CreateCompletionRequestStop stop) {
    this.stop = JsonNullable.of(stop);
    return this;
  }

  /**
   * Get stop
   * @return stop
  */
  @Valid 
  @Schema(name = "stop", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stop")
  public JsonNullable<CreateCompletionRequestStop> getStop() {
    return stop;
  }

  public void setStop(JsonNullable<CreateCompletionRequestStop> stop) {
    this.stop = stop;
  }

  public CreateCompletionRequest stream(Boolean stream) {
    this.stream = JsonNullable.of(stream);
    return this;
  }

  /**
   * Whether to stream back partial progress. If set, tokens will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available, with the stream terminated by a `data: [DONE]` message. [Example Python code](https://cookbook.openai.com/examples/how_to_stream_completions). 
   * @return stream
  */
  
  @Schema(name = "stream", description = "Whether to stream back partial progress. If set, tokens will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available, with the stream terminated by a `data: [DONE]` message. [Example Python code](https://cookbook.openai.com/examples/how_to_stream_completions). ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stream")
  public JsonNullable<Boolean> getStream() {
    return stream;
  }

  public void setStream(JsonNullable<Boolean> stream) {
    this.stream = stream;
  }

  public CreateCompletionRequest suffix(String suffix) {
    this.suffix = JsonNullable.of(suffix);
    return this;
  }

  /**
   * The suffix that comes after a completion of inserted text.
   * @return suffix
  */
  
  @Schema(name = "suffix", example = "test.", description = "The suffix that comes after a completion of inserted text.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("suffix")
  public JsonNullable<String> getSuffix() {
    return suffix;
  }

  public void setSuffix(JsonNullable<String> suffix) {
    this.suffix = suffix;
  }

  public CreateCompletionRequest temperature(BigDecimal temperature) {
    this.temperature = JsonNullable.of(temperature);
    return this;
  }

  /**
   * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.  We generally recommend altering this or `top_p` but not both. 
   * minimum: 0
   * maximum: 2
   * @return temperature
  */
  @Valid @DecimalMin("0") @DecimalMax("2") 
  @Schema(name = "temperature", example = "1", description = "What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.  We generally recommend altering this or `top_p` but not both. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("temperature")
  public JsonNullable<@DecimalMin("0") @DecimalMax("2") BigDecimal> getTemperature() {
    return temperature;
  }

  public void setTemperature(JsonNullable<BigDecimal> temperature) {
    this.temperature = temperature;
  }

  public CreateCompletionRequest topP(BigDecimal topP) {
    this.topP = JsonNullable.of(topP);
    return this;
  }

  /**
   * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.  We generally recommend altering this or `temperature` but not both. 
   * minimum: 0
   * maximum: 1
   * @return topP
  */
  @Valid @DecimalMin("0") @DecimalMax("1") 
  @Schema(name = "top_p", example = "1", description = "An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.  We generally recommend altering this or `temperature` but not both. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("top_p")
  public JsonNullable<@DecimalMin("0") @DecimalMax("1") BigDecimal> getTopP() {
    return topP;
  }

  public void setTopP(JsonNullable<BigDecimal> topP) {
    this.topP = topP;
  }

  public CreateCompletionRequest user(String user) {
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
    CreateCompletionRequest createCompletionRequest = (CreateCompletionRequest) o;
    return Objects.equals(this.model, createCompletionRequest.model) &&
        Objects.equals(this.prompt, createCompletionRequest.prompt) &&
        equalsNullable(this.bestOf, createCompletionRequest.bestOf) &&
        equalsNullable(this.echo, createCompletionRequest.echo) &&
        equalsNullable(this.frequencyPenalty, createCompletionRequest.frequencyPenalty) &&
        equalsNullable(this.logitBias, createCompletionRequest.logitBias) &&
        equalsNullable(this.logprobs, createCompletionRequest.logprobs) &&
        equalsNullable(this.maxTokens, createCompletionRequest.maxTokens) &&
        equalsNullable(this.n, createCompletionRequest.n) &&
        equalsNullable(this.presencePenalty, createCompletionRequest.presencePenalty) &&
        equalsNullable(this.seed, createCompletionRequest.seed) &&
        equalsNullable(this.stop, createCompletionRequest.stop) &&
        equalsNullable(this.stream, createCompletionRequest.stream) &&
        equalsNullable(this.suffix, createCompletionRequest.suffix) &&
        equalsNullable(this.temperature, createCompletionRequest.temperature) &&
        equalsNullable(this.topP, createCompletionRequest.topP) &&
        Objects.equals(this.user, createCompletionRequest.user);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, prompt, hashCodeNullable(bestOf), hashCodeNullable(echo), hashCodeNullable(frequencyPenalty), hashCodeNullable(logitBias), hashCodeNullable(logprobs), hashCodeNullable(maxTokens), hashCodeNullable(n), hashCodeNullable(presencePenalty), hashCodeNullable(seed), hashCodeNullable(stop), hashCodeNullable(stream), hashCodeNullable(suffix), hashCodeNullable(temperature), hashCodeNullable(topP), user);
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
    sb.append("class CreateCompletionRequest {\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    bestOf: ").append(toIndentedString(bestOf)).append("\n");
    sb.append("    echo: ").append(toIndentedString(echo)).append("\n");
    sb.append("    frequencyPenalty: ").append(toIndentedString(frequencyPenalty)).append("\n");
    sb.append("    logitBias: ").append(toIndentedString(logitBias)).append("\n");
    sb.append("    logprobs: ").append(toIndentedString(logprobs)).append("\n");
    sb.append("    maxTokens: ").append(toIndentedString(maxTokens)).append("\n");
    sb.append("    n: ").append(toIndentedString(n)).append("\n");
    sb.append("    presencePenalty: ").append(toIndentedString(presencePenalty)).append("\n");
    sb.append("    seed: ").append(toIndentedString(seed)).append("\n");
    sb.append("    stop: ").append(toIndentedString(stop)).append("\n");
    sb.append("    stream: ").append(toIndentedString(stream)).append("\n");
    sb.append("    suffix: ").append(toIndentedString(suffix)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    topP: ").append(toIndentedString(topP)).append("\n");
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