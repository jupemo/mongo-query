package com.mongo.quote.persistence.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.mongo.quote.persistence.BaseIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

class QuoteAdaptorTest extends BaseIT {

  @Autowired private QuoteRepository quoteRepository;

  @Test
  @DisplayName("Should return quote when find by Id")
  void shouldReturnQuoteById() {
    var id = "5eb17aadb69dc744b4e70d4a";
    var quote = quoteRepository.findById(id);

    assertTrue(quote.isPresent());
    assertAll(
        () -> assertEquals(id, quote.get().getId(), () -> "id not equal"),
        () -> assertNotNull(quote.get().getQuoteGenre(), () -> "genre null"),
        () -> assertNotNull(quote.get().getQuoteText(), () -> "quote text null"),
        () -> assertNotNull(quote.get().getQuoteAuthor(), () -> "quote author null"),
        () -> assertNotNull(quote.get().getVersion(), () -> "version null"));
  }

  @Test
  @DisplayName("Should return empty optional whe id not exits")
  void shouldReturnOptionalEmptyQuoteById() {
    var id = "1";
    var quote = quoteRepository.findById(id);
    assertFalse(quote.isPresent());
  }

  @Test
  @DisplayName("Should return quotes when find by author")
  void shouldReturnQuoteByAuthor() {
    var author = "William Shakespeare";
    var quotes = quoteRepository.findByAuthor(author, PageRequest.of(0, 10));

    assertFalse(quotes.isEmpty());
    assertEquals(2, quotes.getTotalElements());
    assertEquals(2, quotes.getContent().size());
    assertEquals(1, quotes.getTotalPages());
  }

  @Test
  @DisplayName("Should return empty collection when author not exits")
  void shouldReturnEmptyCollectionQuoteByAuthor() {
    var author = "no author";
    var quotes = quoteRepository.findByAuthor(author, PageRequest.of(0, 10));
    assertEquals(0, quotes.getContent().size());
  }

  @Test
  @DisplayName("Should return all quotes when find all")
  void shouldReturnAllQuotes() {
    var quotes = quoteRepository.findAll(PageRequest.of(0, 10));

    assertFalse(quotes.getContent().isEmpty());
    assertEquals(10, quotes.getContent().size());
    assertEquals(500, quotes.getTotalElements());
  }

  @Test
  @DisplayName("Should return two different pages")
  void shouldReturnTwoDifferentPages() {
    var quotesPage0 = quoteRepository.findAll(PageRequest.of(0, 10));
    var quotesPage1 = quoteRepository.findAll(PageRequest.of(1, 10));

    assertFalse(quotesPage0.getContent().isEmpty());
    assertFalse(quotesPage1.getContent().isEmpty());
    assertNotEquals(quotesPage0.getContent().get(0), quotesPage1.getContent().get(0));
  }
}
