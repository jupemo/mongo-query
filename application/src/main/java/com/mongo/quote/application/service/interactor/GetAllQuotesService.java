package com.mongo.quote.application.service.interactor;

import com.mongo.quote.application.dto.QuoteResult;
import com.mongo.quote.application.mapper.QuoteMapper;
import com.mongo.quote.application.service.GetAllQuotesQuery;
import com.mongo.quote.persistence.repository.QuoteRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAllQuotesService implements GetAllQuotesQuery {

  private final QuoteRepository quoteRepository;
  private final QuoteMapper quoteMapper;

  @Override
  public List<QuoteResult> getAll() {
    return quoteMapper.map(quoteRepository.findAll());
  }
}
