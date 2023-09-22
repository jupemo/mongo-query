package com.mongo.quote.application.service.interactor;

import static com.mongo.quote.application.data.DataHelper.getQuoteDocument;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mongo.quote.application.mapper.QuoteMapper;
import com.mongo.quote.application.service.GetAllQuotesQuery.Command;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class GetAllQuotesServiceTest {

  @Spy QuoteMapper quoteMapper = Mappers.getMapper(QuoteMapper.class);
  @Mock QuoteRepository quoteRepository;
  @InjectMocks GetAllQuotesService getAllQuotesService;

  @Test
  @DisplayName("Should return paged quoted")
  void shouldReturnPagedQuote() {

    var quotesDocument = List.of(getQuoteDocument("id", "author"));
    new PageImpl<>(quotesDocument);

    when(quoteRepository.findAll(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(quotesDocument));

    var result = getAllQuotesService.getAll(new Command(1, 10));

    verify(quoteRepository, times(1)).findAll(PageRequest.of(1, 10));
    assertNotNull(result);
    assertEquals(1, result.content().size());
    assertAll(
        () -> assertEquals(quotesDocument.get(0).getId(), result.content().get(0).id()),
        () ->
            assertEquals(
                quotesDocument.get(0).getQuoteAuthor(), result.content().get(0).quoteAuthor()),
        () ->
            assertEquals(
                quotesDocument.get(0).getQuoteGenre(), result.content().get(0).quoteGenre()),
        () ->
            assertEquals(quotesDocument.get(0).getQuoteText(), result.content().get(0).quoteText()),
        () -> assertEquals(quotesDocument.get(0).getVersion(), result.content().get(0).version()));
  }
}
