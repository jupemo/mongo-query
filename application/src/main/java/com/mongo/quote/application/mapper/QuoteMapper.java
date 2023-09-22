package com.mongo.quote.application.mapper;

import com.mongo.quote.application.dto.QuoteResult;
import com.mongo.quote.persistence.document.QuoteDocument;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuoteMapper {
  QuoteResult map(QuoteDocument document);

  List<QuoteResult> map(List<QuoteDocument> documents);
}
