package com.telekom.ai4coding.chatbot.controller.openai;

import com.telekom.ai4coding.chatbot.service.Neo4JDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/graphdb")
public class Neo4JDatabaseController {

    private final Neo4JDatabaseService databaseService;

    @DeleteMapping("/cleanup")
    public ResponseEntity<Void> cleanupDatabase() {
        try {
            databaseService.cleanupDatabase();
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
