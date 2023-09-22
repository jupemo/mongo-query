package com.mongo.quote.rest.controller;

import com.mongo.quote.rest.dto.QuoteResponseDTO;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{quote-id}")
  public QuoteResponseDTO getQuoteById(@PathVariable("quote-id") String quoteId) {
    return null;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<QuoteResponseDTO> getAllQuotes() {
    return null;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{author}/authors")
  public List<QuoteResponseDTO> getQuotesByAuthor(@PathVariable String author) {
    return null;
  }
}
