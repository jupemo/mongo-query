package com.mongo.quote.application.service;

import com.mongo.quote.application.dto.QuoteResult;

public interface GetQuoteByIdQuery {

  QuoteResult getById(Command command);

  record Command(String id) {}
}
