package com.mongo.quote.persistence.repository.jpa;

import com.mongo.quote.persistence.document.QuoteDocument;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataQuoteRepository extends MongoRepository<QuoteDocument, String> {
  Page<QuoteDocument> findByQuoteAuthor(String author, Pageable pageable);
}
