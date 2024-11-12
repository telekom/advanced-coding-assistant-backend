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
import jakarta.validation.constraints.Size;
import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * CreateChatCompletionRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateChatCompletionRequest {

  @Valid
  private List<@Valid ChatCompletionRequestMessage> messages = new ArrayList<>();

  private CreateChatCompletionRequestModel model;

  private JsonNullable<@DecimalMin("-2") @DecimalMax("2") BigDecimal> frequencyPenalty = JsonNullable.<BigDecimal>undefined();

  @Valid
  private JsonNullable<Map<String, Integer>> logitBias = JsonNullable.<Map<String, Integer>>undefined();

  private JsonNullable<Boolean> logprobs = JsonNullable.<Boolean>undefined();

  private JsonNullable<@Min(0) @Max(5) Integer> topLogprobs = JsonNullable.<Integer>undefined();

  private JsonNullable<Integer> maxTokens = JsonNullable.<Integer>undefined();

  private JsonNullable<@Min(1) @Max(128) Integer> n = JsonNullable.<Integer>undefined();

  private JsonNullable<@DecimalMin("-2") @DecimalMax("2") BigDecimal> presencePenalty = JsonNullable.<BigDecimal>undefined();

  private CreateChatCompletionRequestResponseFormat responseFormat;

  private JsonNullable<@DecimalMin(value = "-9223372036854775808") @DecimalMax(value = "9223372036854775807") BigDecimal> seed = JsonNullable.<BigDecimal>undefined();

  private CreateChatCompletionRequestStop stop = null;

  private JsonNullable<Boolean> stream = JsonNullable.<Boolean>undefined();

  private JsonNullable<@DecimalMin("0") @DecimalMax("2") BigDecimal> temperature = JsonNullable.<BigDecimal>undefined();

  private JsonNullable<@DecimalMin("0") @DecimalMax("1") BigDecimal> topP = JsonNullable.<BigDecimal>undefined();

  @Valid
  private List<@Valid ChatCompletionTool> tools;

  private ChatCompletionToolChoiceOption toolChoice;

  private String user;

  @Deprecated
  private CreateChatCompletionRequestFunctionCall functionCall;

  @Deprecated
  @Valid
  private List<@Valid ChatCompletionFunctions> functions;

  public CreateChatCompletionRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateChatCompletionRequest(List<@Valid ChatCompletionRequestMessage> messages, CreateChatCompletionRequestModel model) {
    this.messages = messages;
    this.model = model;
  }

  public CreateChatCompletionRequest messages(List<@Valid ChatCompletionRequestMessage> messages) {
    this.messages = messages;
    return this;
  }

  public CreateChatCompletionRequest addMessagesItem(ChatCompletionRequestMessage messagesItem) {
    if (this.messages == null) {
      this.messages = new ArrayList<>();
    }
    this.messages.add(messagesItem);
    return this;
  }

  /**
   * A list of messages comprising the conversation so far. [Example Python code](https://cookbook.openai.com/examples/how_to_format_inputs_to_chatgpt_models).
   * @return messages
  */
  @NotNull @Valid @Size(min = 1) 
  @Schema(name = "messages", description = "A list of messages comprising the conversation so far. [Example Python code](https://cookbook.openai.com/examples/how_to_format_inputs_to_chatgpt_models).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("messages")
  public List<@Valid ChatCompletionRequestMessage> getMessages() {
    return messages;
  }

  public void setMessages(List<@Valid ChatCompletionRequestMessage> messages) {
    this.messages = messages;
  }

  public CreateChatCompletionRequest model(CreateChatCompletionRequestModel model) {
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
  public CreateChatCompletionRequestModel getModel() {
    return model;
  }

  public void setModel(CreateChatCompletionRequestModel model) {
    this.model = model;
  }

  public CreateChatCompletionRequest frequencyPenalty(BigDecimal frequencyPenalty) {
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

  public CreateChatCompletionRequest logitBias(Map<String, Integer> logitBias) {
    this.logitBias = JsonNullable.of(logitBias);
    return this;
  }

  public CreateChatCompletionRequest putLogitBiasItem(String key, Integer logitBiasItem) {
    if (this.logitBias == null || !this.logitBias.isPresent()) {
      this.logitBias = JsonNullable.of(new HashMap<>());
    }
    this.logitBias.get().put(key, logitBiasItem);
    return this;
  }

  /**
   * Modify the likelihood of specified tokens appearing in the completion.  Accepts a JSON object that maps tokens (specified by their token ID in the tokenizer) to an associated bias value from -100 to 100. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token. 
   * @return logitBias
  */
  
  @Schema(name = "logit_bias", description = "Modify the likelihood of specified tokens appearing in the completion.  Accepts a JSON object that maps tokens (specified by their token ID in the tokenizer) to an associated bias value from -100 to 100. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("logit_bias")
  public JsonNullable<Map<String, Integer>> getLogitBias() {
    return logitBias;
  }

  public void setLogitBias(JsonNullable<Map<String, Integer>> logitBias) {
    this.logitBias = logitBias;
  }

  public CreateChatCompletionRequest logprobs(Boolean logprobs) {
    this.logprobs = JsonNullable.of(logprobs);
    return this;
  }

  /**
   * Whether to return log probabilities of the output tokens or not. If true, returns the log probabilities of each output token returned in the `content` of `message`. This option is currently not available on the `gpt-4-vision-preview` model.
   * @return logprobs
  */
  
  @Schema(name = "logprobs", description = "Whether to return log probabilities of the output tokens or not. If true, returns the log probabilities of each output token returned in the `content` of `message`. This option is currently not available on the `gpt-4-vision-preview` model.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("logprobs")
  public JsonNullable<Boolean> getLogprobs() {
    return logprobs;
  }

  public void setLogprobs(JsonNullable<Boolean> logprobs) {
    this.logprobs = logprobs;
  }

  public CreateChatCompletionRequest topLogprobs(Integer topLogprobs) {
    this.topLogprobs = JsonNullable.of(topLogprobs);
    return this;
  }

  /**
   * An integer between 0 and 5 specifying the number of most likely tokens to return at each token position, each with an associated log probability. `logprobs` must be set to `true` if this parameter is used.
   * minimum: 0
   * maximum: 5
   * @return topLogprobs
  */
  @Min(0) @Max(5) 
  @Schema(name = "top_logprobs", description = "An integer between 0 and 5 specifying the number of most likely tokens to return at each token position, each with an associated log probability. `logprobs` must be set to `true` if this parameter is used.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("top_logprobs")
  public JsonNullable<@Min(0) @Max(5) Integer> getTopLogprobs() {
    return topLogprobs;
  }

  public void setTopLogprobs(JsonNullable<Integer> topLogprobs) {
    this.topLogprobs = topLogprobs;
  }

  public CreateChatCompletionRequest maxTokens(Integer maxTokens) {
    this.maxTokens = JsonNullable.of(maxTokens);
    return this;
  }

  /**
   * The maximum number of [tokens](/tokenizer) that can be generated in the chat completion.  The total length of input tokens and generated tokens is limited by the model's context length. [Example Python code](https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken) for counting tokens. 
   * @return maxTokens
  */
  
  @Schema(name = "max_tokens", description = "The maximum number of [tokens](/tokenizer) that can be generated in the chat completion.  The total length of input tokens and generated tokens is limited by the model's context length. [Example Python code](https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken) for counting tokens. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("max_tokens")
  public JsonNullable<Integer> getMaxTokens() {
    return maxTokens;
  }

  public void setMaxTokens(JsonNullable<Integer> maxTokens) {
    this.maxTokens = maxTokens;
  }

  public CreateChatCompletionRequest n(Integer n) {
    this.n = JsonNullable.of(n);
    return this;
  }

  /**
   * How many chat completion choices to generate for each input message. Note that you will be charged based on the number of generated tokens across all of the choices. Keep `n` as `1` to minimize costs.
   * minimum: 1
   * maximum: 128
   * @return n
  */
  @Min(1) @Max(128) 
  @Schema(name = "n", example = "1", description = "How many chat completion choices to generate for each input message. Note that you will be charged based on the number of generated tokens across all of the choices. Keep `n` as `1` to minimize costs.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("n")
  public JsonNullable<@Min(1) @Max(128) Integer> getN() {
    return n;
  }

  public void setN(JsonNullable<Integer> n) {
    this.n = n;
  }

  public CreateChatCompletionRequest presencePenalty(BigDecimal presencePenalty) {
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

  public CreateChatCompletionRequest responseFormat(CreateChatCompletionRequestResponseFormat responseFormat) {
    this.responseFormat = responseFormat;
    return this;
  }

  /**
   * Get responseFormat
   * @return responseFormat
  */
  @Valid 
  @Schema(name = "response_format", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("response_format")
  public CreateChatCompletionRequestResponseFormat getResponseFormat() {
    return responseFormat;
  }

  public void setResponseFormat(CreateChatCompletionRequestResponseFormat responseFormat) {
    this.responseFormat = responseFormat;
  }

  public CreateChatCompletionRequest seed(BigDecimal seed) {
    this.seed = JsonNullable.of(seed);
    return this;
  }

  /**
   * This feature is in Beta. If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same `seed` and parameters should return the same result. Determinism is not guaranteed, and you should refer to the `system_fingerprint` response parameter to monitor changes in the backend. 
   * minimum: -9223372036854775808
   * maximum: 9223372036854775807
   * @return seed
  */
  @DecimalMin(value = "-9223372036854775808") @DecimalMax(value = "9223372036854775807")
  @Schema(name = "seed", description = "This feature is in Beta. If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same `seed` and parameters should return the same result. Determinism is not guaranteed, and you should refer to the `system_fingerprint` response parameter to monitor changes in the backend. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("seed")
  public JsonNullable<@DecimalMin(value = "-9223372036854775808") @DecimalMax(value = "9223372036854775807") BigDecimal> getSeed() {
    return seed;
  }

  public void setSeed(JsonNullable<BigDecimal> seed) {
    this.seed = seed;
  }

  public CreateChatCompletionRequest stop(CreateChatCompletionRequestStop stop) {
    this.stop = stop;
    return this;
  }

  /**
   * Get stop
   * @return stop
  */
  @Valid 
  @Schema(name = "stop", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stop")
  public CreateChatCompletionRequestStop getStop() {
    return stop;
  }

  public void setStop(CreateChatCompletionRequestStop stop) {
    this.stop = stop;
  }

  public CreateChatCompletionRequest stream(Boolean stream) {
    this.stream = JsonNullable.of(stream);
    return this;
  }

  /**
   * If set, partial message deltas will be sent, like in ChatGPT. Tokens will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available, with the stream terminated by a `data: [DONE]` message. [Example Python code](https://cookbook.openai.com/examples/how_to_stream_completions). 
   * @return stream
  */
  
  @Schema(name = "stream", description = "If set, partial message deltas will be sent, like in ChatGPT. Tokens will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available, with the stream terminated by a `data: [DONE]` message. [Example Python code](https://cookbook.openai.com/examples/how_to_stream_completions). ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stream")
  public JsonNullable<Boolean> getStream() {
    return stream;
  }

  public void setStream(JsonNullable<Boolean> stream) {
    this.stream = stream;
  }

  public CreateChatCompletionRequest temperature(BigDecimal temperature) {
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

  public CreateChatCompletionRequest topP(BigDecimal topP) {
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

  public CreateChatCompletionRequest tools(List<@Valid ChatCompletionTool> tools) {
    this.tools = tools;
    return this;
  }

  public CreateChatCompletionRequest addToolsItem(ChatCompletionTool toolsItem) {
    if (this.tools == null) {
      this.tools = new ArrayList<>();
    }
    this.tools.add(toolsItem);
    return this;
  }

  /**
   * A list of tools the model may call. Currently, only functions are supported as a tool. Use this to provide a list of functions the model may generate JSON inputs for. 
   * @return tools
  */
  @Valid 
  @Schema(name = "tools", description = "A list of tools the model may call. Currently, only functions are supported as a tool. Use this to provide a list of functions the model may generate JSON inputs for. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tools")
  public List<@Valid ChatCompletionTool> getTools() {
    return tools;
  }

  public void setTools(List<@Valid ChatCompletionTool> tools) {
    this.tools = tools;
  }

  public CreateChatCompletionRequest toolChoice(ChatCompletionToolChoiceOption toolChoice) {
    this.toolChoice = toolChoice;
    return this;
  }

  /**
   * Get toolChoice
   * @return toolChoice
  */
  @Valid 
  @Schema(name = "tool_choice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tool_choice")
  public ChatCompletionToolChoiceOption getToolChoice() {
    return toolChoice;
  }

  public void setToolChoice(ChatCompletionToolChoiceOption toolChoice) {
    this.toolChoice = toolChoice;
  }

  public CreateChatCompletionRequest user(String user) {
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

  public CreateChatCompletionRequest functionCall(CreateChatCompletionRequestFunctionCall functionCall) {
    this.functionCall = functionCall;
    return this;
  }

  /**
   * Get functionCall
   * @return functionCall
   * @deprecated
  */
  @Valid 
  @Schema(name = "function_call", deprecated = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("function_call")
  @Deprecated
  public CreateChatCompletionRequestFunctionCall getFunctionCall() {
    return functionCall;
  }

  /**
   * @deprecated
  */
  @Deprecated
  public void setFunctionCall(CreateChatCompletionRequestFunctionCall functionCall) {
    this.functionCall = functionCall;
  }

  public CreateChatCompletionRequest functions(List<@Valid ChatCompletionFunctions> functions) {
    this.functions = functions;
    return this;
  }

  public CreateChatCompletionRequest addFunctionsItem(ChatCompletionFunctions functionsItem) {
    if (this.functions == null) {
      this.functions = new ArrayList<>();
    }
    this.functions.add(functionsItem);
    return this;
  }

  /**
   * Deprecated in favor of `tools`.  A list of functions the model may generate JSON inputs for. 
   * @return functions
   * @deprecated
  */
  @Valid @Size(min = 1, max = 128) 
  @Schema(name = "functions", description = "Deprecated in favor of `tools`.  A list of functions the model may generate JSON inputs for. ", deprecated = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("functions")
  @Deprecated
  public List<@Valid ChatCompletionFunctions> getFunctions() {
    return functions;
  }

  /**
   * @deprecated
  */
  @Deprecated
  public void setFunctions(List<@Valid ChatCompletionFunctions> functions) {
    this.functions = functions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateChatCompletionRequest createChatCompletionRequest = (CreateChatCompletionRequest) o;
    return Objects.equals(this.messages, createChatCompletionRequest.messages) &&
        Objects.equals(this.model, createChatCompletionRequest.model) &&
        equalsNullable(this.frequencyPenalty, createChatCompletionRequest.frequencyPenalty) &&
        equalsNullable(this.logitBias, createChatCompletionRequest.logitBias) &&
        equalsNullable(this.logprobs, createChatCompletionRequest.logprobs) &&
        equalsNullable(this.topLogprobs, createChatCompletionRequest.topLogprobs) &&
        equalsNullable(this.maxTokens, createChatCompletionRequest.maxTokens) &&
        equalsNullable(this.n, createChatCompletionRequest.n) &&
        equalsNullable(this.presencePenalty, createChatCompletionRequest.presencePenalty) &&
        Objects.equals(this.responseFormat, createChatCompletionRequest.responseFormat) &&
        equalsNullable(this.seed, createChatCompletionRequest.seed) &&
        Objects.equals(this.stop, createChatCompletionRequest.stop) &&
        equalsNullable(this.stream, createChatCompletionRequest.stream) &&
        equalsNullable(this.temperature, createChatCompletionRequest.temperature) &&
        equalsNullable(this.topP, createChatCompletionRequest.topP) &&
        Objects.equals(this.tools, createChatCompletionRequest.tools) &&
        Objects.equals(this.toolChoice, createChatCompletionRequest.toolChoice) &&
        Objects.equals(this.user, createChatCompletionRequest.user) &&
        Objects.equals(this.functionCall, createChatCompletionRequest.functionCall) &&
        Objects.equals(this.functions, createChatCompletionRequest.functions);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(messages, model, hashCodeNullable(frequencyPenalty), hashCodeNullable(logitBias), hashCodeNullable(logprobs), hashCodeNullable(topLogprobs), hashCodeNullable(maxTokens), hashCodeNullable(n), hashCodeNullable(presencePenalty), responseFormat, hashCodeNullable(seed), stop, hashCodeNullable(stream), hashCodeNullable(temperature), hashCodeNullable(topP), tools, toolChoice, user, functionCall, functions);
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
    sb.append("class CreateChatCompletionRequest {\n");
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    frequencyPenalty: ").append(toIndentedString(frequencyPenalty)).append("\n");
    sb.append("    logitBias: ").append(toIndentedString(logitBias)).append("\n");
    sb.append("    logprobs: ").append(toIndentedString(logprobs)).append("\n");
    sb.append("    topLogprobs: ").append(toIndentedString(topLogprobs)).append("\n");
    sb.append("    maxTokens: ").append(toIndentedString(maxTokens)).append("\n");
    sb.append("    n: ").append(toIndentedString(n)).append("\n");
    sb.append("    presencePenalty: ").append(toIndentedString(presencePenalty)).append("\n");
    sb.append("    responseFormat: ").append(toIndentedString(responseFormat)).append("\n");
    sb.append("    seed: ").append(toIndentedString(seed)).append("\n");
    sb.append("    stop: ").append(toIndentedString(stop)).append("\n");
    sb.append("    stream: ").append(toIndentedString(stream)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    topP: ").append(toIndentedString(topP)).append("\n");
    sb.append("    tools: ").append(toIndentedString(tools)).append("\n");
    sb.append("    toolChoice: ").append(toIndentedString(toolChoice)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    functionCall: ").append(toIndentedString(functionCall)).append("\n");
    sb.append("    functions: ").append(toIndentedString(functions)).append("\n");
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