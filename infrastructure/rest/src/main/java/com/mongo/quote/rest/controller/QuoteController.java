package com.mongo.quote.rest.controller;

import com.mongo.quote.application.service.GetAllQuotesQuery;
import com.mongo.quote.application.service.GetQuoteByAuthorQuery;
import com.mongo.quote.application.service.GetQuoteByAuthorQuery.Command;
import com.mongo.quote.application.service.GetQuoteByIdQuery;
import com.mongo.quote.rest.dto.PageResponseDTO;
import com.mongo.quote.rest.dto.QuoteResponseDTO;
import com.mongo.quote.rest.mapper.QuoteDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping
public class QuoteController {

  public static final int PAGE_SIZE = 10;
  public static final int FIRST_PAGE_NUMBER = 0;

  private final GetQuoteByIdQuery getQuoteByIdQuery;
  private final GetQuoteByAuthorQuery getQuoteByAuthorQuery;
  private final GetAllQuotesQuery getAllQuotesQuery;
  private final QuoteDTOMapper quoteDTOMapper;

  @GetMapping("/quotes/{quote-id}")
  public QuoteResponseDTO getQuoteById(@PathVariable("quote-id") String quoteId) {
    return quoteDTOMapper.map(getQuoteByIdQuery.getById(new GetQuoteByIdQuery.Command(quoteId)));
  }

  @GetMapping("/quotes")
  public PageResponseDTO<QuoteResponseDTO> getAllQuotes(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false, value = "page-size") Integer pageSize) {
    return quoteDTOMapper.map(
        getAllQuotesQuery.getAll(
            new GetAllQuotesQuery.Command(
                page != null ? page : FIRST_PAGE_NUMBER, pageSize != null ? pageSize : PAGE_SIZE)));
  }

  @GetMapping("/authors/{author}/quotes")
  public PageResponseDTO<QuoteResponseDTO> getQuotesByAuthor(
      @PathVariable String author,
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false, value = "page-size") Integer pageSize) {
    return quoteDTOMapper.map(
        getQuoteByAuthorQuery.getByAuthor(
            new Command(
                author,
                page != null ? page : FIRST_PAGE_NUMBER,
                pageSize != null ? pageSize : PAGE_SIZE)));
  }
}
