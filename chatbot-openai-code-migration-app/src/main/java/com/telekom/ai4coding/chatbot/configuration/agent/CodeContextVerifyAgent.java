package com.telekom.ai4coding.chatbot.configuration.agent;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface CodeContextVerifyAgent {

    @UserMessage("""
      Is the following source code relevant to the user query?
      
      User query:
      {{userQuery}}

      codeContext:
      {{codeContext}}
        """)
    boolean isRelevant(@V("userQuery") String userQuery, @V("codeContext") String codeContext);
}
