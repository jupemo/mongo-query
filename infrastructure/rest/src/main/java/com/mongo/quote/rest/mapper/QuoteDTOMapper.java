package com.mongo.quote.rest.mapper;

import com.mongo.quote.application.dto.PageResult;
import com.mongo.quote.application.dto.QuoteResult;
import com.mongo.quote.rest.dto.PageResponseDTO;
import com.mongo.quote.rest.dto.QuoteResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuoteDTOMapper {

  QuoteResponseDTO map(QuoteResult quoteResult);

  PageResponseDTO<QuoteResponseDTO> map(PageResult<QuoteResult> pagedQuoteResult);
}
