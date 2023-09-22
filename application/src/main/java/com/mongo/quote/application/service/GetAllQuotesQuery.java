package com.mongo.quote.application.service;

import com.mongo.quote.application.dto.PageResult;
import com.mongo.quote.application.dto.QuoteResult;
import java.util.List;

public interface GetAllQuotesQuery {

  PageResult<QuoteResult> getAll(Command command);

  record Command(Integer page, Integer pageSize) {}
}
