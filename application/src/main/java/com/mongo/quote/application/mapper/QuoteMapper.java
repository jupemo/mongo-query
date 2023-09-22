package com.mongo.quote.application.mapper;

import com.mongo.quote.application.dto.PageResult;
import com.mongo.quote.application.dto.QuoteResult;
import com.mongo.quote.persistence.document.QuoteDocument;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface QuoteMapper {
  QuoteResult map(QuoteDocument document);

  PageResult<QuoteResult> map(Page<QuoteDocument> documents);
}
