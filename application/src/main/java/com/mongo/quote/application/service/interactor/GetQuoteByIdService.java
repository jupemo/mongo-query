package com.mongo.quote.application.service.interactor;

import com.mongo.quote.application.dto.QuoteResult;
import com.mongo.quote.application.exception.NotFoundException;
import com.mongo.quote.application.mapper.QuoteMapper;
import com.mongo.quote.application.service.GetQuoteByIdQuery;
import com.mongo.quote.persistence.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetQuoteByIdService implements GetQuoteByIdQuery {

  private final QuoteRepository quoteRepository;
  private final QuoteMapper quoteMapper;

  @Override
  public QuoteResult getById(Command command) {
    return quoteRepository
        .findById(command.id())
        .map(quoteMapper::map)
        .orElseThrow(() -> new NotFoundException(command.id()));
  }
}
