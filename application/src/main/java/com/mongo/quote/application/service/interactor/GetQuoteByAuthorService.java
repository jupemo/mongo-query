package com.mongo.quote.application.service.interactor;

import com.mongo.quote.application.dto.PageResult;
import com.mongo.quote.application.dto.QuoteResult;
import com.mongo.quote.application.mapper.QuoteMapper;
import com.mongo.quote.application.service.GetQuoteByAuthorQuery;
import com.mongo.quote.persistence.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetQuoteByAuthorService implements GetQuoteByAuthorQuery {

  private final QuoteRepository quoteRepository;
  private final QuoteMapper quoteMapper;

  @Override
  public PageResult<QuoteResult> getByAuthor(Command command) {
    return quoteMapper.map(
        quoteRepository.findByAuthor(command.author(), command.page(), command.pageSize()));
  }
}
