package com.mongo.quote.application.service;

import com.mongo.quote.application.dto.QuoteResult;
import java.util.List;

public interface GetAllQuotesQuery {

  List<QuoteResult> getAll();
}
