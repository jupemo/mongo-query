package com.mongo.quote.application.service.interactor;

import static com.mongo.quote.application.data.DataHelper.getQuoteDocument;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mongo.quote.application.exception.NotFoundException;
import com.mongo.quote.application.mapper.QuoteMapper;
import com.mongo.quote.application.service.GetQuoteByIdQuery.Command;
import com.mongo.quote.persistence.repository.QuoteRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetQuoteByIdServiceTest {

  @Spy QuoteMapper quoteMapper = Mappers.getMapper(QuoteMapper.class);
  @Mock QuoteRepository quoteRepository;
  @InjectMocks GetQuoteByIdService getQuoteByIdService;

  @Test
  @DisplayName("Should return quote by Id")
  void shouldReturnQuote() {
    var id = "id";
    var quoteDocument = getQuoteDocument(id, "author");

    when(quoteRepository.findById(id)).thenReturn(Optional.of(quoteDocument));

    var result = getQuoteByIdService.getById(new Command(id));

    verify(quoteRepository, times(1)).findById(id);
    assertNotNull(result);
    assertAll(
        () -> assertEquals(quoteDocument.getId(), result.id()),
        () -> assertEquals(quoteDocument.getQuoteAuthor(), result.quoteAuthor()),
        () -> assertEquals(quoteDocument.getQuoteGenre(), result.quoteGenre()),
        () -> assertEquals(quoteDocument.getQuoteText(), result.quoteText()),
        () -> assertEquals(quoteDocument.getVersion(), result.version()));
  }

  @Test
  @DisplayName("Should throw not found exception")
  void shouldThrowNotFoundException() {
    var id = "id";

    when(quoteRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> getQuoteByIdService.getById(new Command(id)));

    verify(quoteRepository, times(1)).findById(id);
  }
}
