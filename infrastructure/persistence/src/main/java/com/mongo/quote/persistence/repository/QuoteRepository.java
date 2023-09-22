package com.mongo.quote.persistence.repository;

import com.mongo.quote.persistence.document.QuoteDocument;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuoteRepository {

  Optional<QuoteDocument> findById(String id);

  Page<QuoteDocument> findByAuthor(String author, Pageable pageable);

  Page<QuoteDocument> findAll(Pageable pageable);
}
