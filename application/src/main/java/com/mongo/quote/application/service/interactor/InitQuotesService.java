package com.mongo.quote.application.service.interactor;

import com.mongo.quote.application.service.InitQuotesUseCase;
import com.mongo.quote.persistence.repository.InitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitQuotesService implements InitQuotesUseCase {

  private final InitRepository repository;

  @Override
  public void initQuotes() {
    repository.initRepository();
  }
}
