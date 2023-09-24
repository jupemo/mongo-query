package com.mongo.quote.persistence.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.quote.persistence.document.QuoteDocument;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InitRepositoryImpl implements InitRepository {

  private final MongoTemplate mongoTemplate;

  @Override
  public void initRepository() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      var quoteDocuments =
          objectMapper.readValue(
              new File("infrastructure/persistence/src/main/resources/data/init-quotes.json"),
              QuoteDocument[].class);
      mongoTemplate.insertAll(Arrays.stream(quoteDocuments).toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
