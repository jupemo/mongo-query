package com.mongo.quote.main;

import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.quote.persistence.document.QuoteDocument;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("integration-test")
@AutoConfigureMockMvc
public abstract class BaseIT {

  @Autowired
  MockMvc mvc;

  @Autowired ObjectMapper objectMapper;

  private static final MongoDBContainer MONGO_DB_CONTAINER =
      new MongoDBContainer(DockerImageName.parse("mongo:latest")).withExposedPorts(27017, 27017);

  @BeforeAll
  static void init() {
    MONGO_DB_CONTAINER.start();
    initData();
  }

  @DynamicPropertySource
  static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
  }

  private static void initData() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String mongoUrl = MONGO_DB_CONTAINER.getReplicaSetUrl();
      MongoClient mongoClient = MongoClients.create(mongoUrl);

      MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "test");
      var quoteDocuments =
          objectMapper.readValue(
              new File("src/test/resources/data/quotes.json"), QuoteDocument[].class);
      mongoTemplate.insertAll(Arrays.stream(quoteDocuments).toList());
    } catch (JsonProcessingException e) {
      fail("Exception reading init document" + e.getMessage());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
