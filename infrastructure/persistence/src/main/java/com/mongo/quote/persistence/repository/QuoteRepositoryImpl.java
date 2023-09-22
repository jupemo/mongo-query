package com.mongo.quote.persistence.repository;

import com.mongo.quote.persistence.document.QuoteDocument;
import com.mongo.quote.persistence.repository.jpa.SpringDataQuoteRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuoteRepositoryImpl implements QuoteRepository {

  private final SpringDataQuoteRepository repository;

  @Override
  public Optional<QuoteDocument> findById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<QuoteDocument> findByAuthor(String author) {
    return repository.findByQuoteAuthor(author);
  }

  @Override
  public List<QuoteDocument> findAll() {
    return repository.findAll();
  }
}
