package com.telekom.ai4coding.chatbot.controller;

import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import com.telekom.ai4coding.chatbot.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping("/{conversationId}")
    public ResponseEntity<Map<String, String>> getConversation(@PathVariable String conversationId) {
        return ResponseEntity.ok(conversationService.getConversation(conversationId));
    }

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getConversations() {
        return ResponseEntity.ok(conversationService.getAllConversations());
    }

    @GetMapping("/{conversationId}/messages")
    public ResponseEntity<List<Map<String, String>>> getConversationMessages(@PathVariable String conversationId) {
        return ResponseEntity.ok(conversationService.getConversationMessages(conversationId));
    }

    @DeleteMapping("/{conversationId}")
    public ResponseEntity<Void> deleteConversation(@PathVariable String conversationId) {
        conversationService.deleteConversation(conversationId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{conversationId}/rename")
    public ResponseEntity<String> renameConversation(@PathVariable String conversationId, @RequestParam("newTitle") String newTitle) {
        if (!StringUtils.hasText(newTitle)) {
            return ResponseEntity.badRequest().body("New title cannot be empty.");
        } else {
            try {
                String updatedTitle = conversationService.renameConversation(conversationId, newTitle);
                return ResponseEntity.ok(updatedTitle);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while renaming the conversation.");
            }
        }
    }
}
