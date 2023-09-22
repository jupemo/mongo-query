package com.mongo.quote.persistence.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.mongo.quote.persistence.BaseIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    var quotes = quoteRepository.findByAuthor(author);

    assertFalse(quotes.isEmpty());
    assertEquals(2, quotes.size());
  }

  @Test
  @DisplayName("Should return empty collection when author not exits")
  void shouldReturnEmptyCollectionQuoteByAuthor() {
    var author = "no author";
    var quotes = quoteRepository.findByAuthor(author);
    assertTrue(quotes.isEmpty());
  }

  @Test
  @DisplayName("Should return all quotes when find all")
  void shouldReturnAllQuotes() {
    var quotes = quoteRepository.findAll();

    assertFalse(quotes.isEmpty());
    assertEquals(500, quotes.size());
  }
}
