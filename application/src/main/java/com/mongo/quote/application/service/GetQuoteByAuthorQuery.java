package com.mongo.quote.application.service;

import com.mongo.quote.application.dto.PageResult;
import com.mongo.quote.application.dto.QuoteResult;
import java.util.List;

public interface GetQuoteByAuthorQuery {

  PageResult<QuoteResult> getByAuthor(Command command);

  record Command(String author, Integer page, Integer pageSize) {}
}
