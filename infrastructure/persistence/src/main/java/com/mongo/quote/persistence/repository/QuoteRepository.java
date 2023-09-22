package com.mongo.quote.persistence.repository;

import com.mongo.quote.persistence.document.QuoteDocument;
import java.util.List;
import java.util.Optional;

public interface QuoteRepository {

  Optional<QuoteDocument> findById(String id);

  List<QuoteDocument> findByAuthor(String author);

  List<QuoteDocument> findAll();
}
