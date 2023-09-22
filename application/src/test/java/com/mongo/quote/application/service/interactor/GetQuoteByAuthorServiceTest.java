package com.mongo.quote.application.service.interactor;

import static com.mongo.quote.application.data.DataHelper.getQuoteDocument;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mongo.quote.application.mapper.QuoteMapper;
import com.mongo.quote.application.service.GetQuoteByAuthorQuery.Command;
import com.mongo.quote.persistence.repository.QuoteRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetQuoteByAuthorServiceTest {

  @Spy QuoteMapper quoteMapper = Mappers.getMapper(QuoteMapper.class);
  @Mock QuoteRepository quoteRepository;
  @InjectMocks GetQuoteByAuthorService getQuoteByAuthorService;

  @Test
  @DisplayName("Should return result")
  void shouldReturnResult() {
    var author = "test-author";
    var quotesDocument = List.of(getQuoteDocument("id", author));

    when(quoteRepository.findByAuthor(author)).thenReturn(quotesDocument);

    var result = getQuoteByAuthorService.getByAuthor(new Command(author));

    verify(quoteRepository, times(1)).findByAuthor(author);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertAll(
        () -> assertEquals(quotesDocument.get(0).getId(), result.get(0).id()),
        () -> assertEquals(quotesDocument.get(0).getQuoteAuthor(), result.get(0).quoteAuthor()),
        () -> assertEquals(quotesDocument.get(0).getQuoteGenre(), result.get(0).quoteGenre()),
        () -> assertEquals(quotesDocument.get(0).getQuoteText(), result.get(0).quoteText()),
        () -> assertEquals(quotesDocument.get(0).getVersion(), result.get(0).version()));
  }
}
