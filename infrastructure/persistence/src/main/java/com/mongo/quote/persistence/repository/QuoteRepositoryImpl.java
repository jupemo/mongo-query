package com.mongo.quote.persistence.repository;

import com.mongo.quote.persistence.document.QuoteDocument;
import com.mongo.quote.persistence.repository.jpa.SpringDataQuoteRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
  public Page<QuoteDocument> findByAuthor(String author, Integer page, Integer pageSize) {
    return repository.findByQuoteAuthor(author, PageRequest.of(page, pageSize));
  }

  @Override
  public Page<QuoteDocument> findAll(Integer page, Integer pageSize) {
    return repository.findAll(PageRequest.of(page, pageSize));
  }
}
