package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.exceptions.Neo4jException;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class Neo4JDatabaseServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private Neo4JDatabaseService neo4JDatabaseService;

    @Autowired
    private Driver neo4jDriver;

    @BeforeEach
    void setup() {
        // Insert some test data in the database before each test
        try (Session session = neo4jDriver.session()) {
            session.run("CREATE (:TestNode {name: 'Test Node 1'})");
            session.run("CREATE (:TestNode {name: 'Test Node 2'})");
        }
    }

    @Test
    void whenCleanupDatabase_thenAllNodesAndRelationshipsShouldBeDeleted() {
        // Given: The database has test data (already inserted in @BeforeEach)

        // When: CleanupDatabase method is called
        neo4JDatabaseService.cleanupDatabase();

        // Then: The database should be empty (no nodes or relationships)
        try (Session session = neo4jDriver.session()) {
            long nodeCount = session.run("MATCH (n) RETURN COUNT(n)").single().get(0).asLong();
            Assertions.assertThat(nodeCount).isEqualTo(0);  // Verify all nodes are deleted
        }
    }

    //Needs a mocked driver because using the real neo4j driver results in flawless execution, which means to error is thrown -> nothing to handle for the test
    @Test
    void whenDatabaseThrowsException_thenExceptionIsHandledGracefully() {
        // Given: A mock session that throws an exception
        Driver mockDriver = mock(Driver.class);
        Session mockSession = mock(Session.class);
        Transaction mockTransaction = mock(Transaction.class);

        // Mock session and transaction behavior
        when(mockDriver.session()).thenReturn(mockSession);
        when(mockSession.beginTransaction()).thenReturn(mockTransaction);
        when(mockSession.executeWrite(any())).thenThrow(Neo4jException.class);

        // Create service with the mocked driver
        Neo4JDatabaseService serviceWithMockedDriver = new Neo4JDatabaseService(mockDriver);

        // When & Then: Expect an exception when cleanupDatabase is called
        assertThatThrownBy(serviceWithMockedDriver::cleanupDatabase)
                .isInstanceOf(Neo4jException.class);
    }
}

