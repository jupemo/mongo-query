package com.mongo.quote.rest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.quote.application.dto.PageResult;
import com.mongo.quote.application.dto.QuoteResult;
import com.mongo.quote.application.service.GetAllQuotesQuery;
import com.mongo.quote.application.service.GetQuoteByAuthorQuery;
import com.mongo.quote.application.service.GetQuoteByIdQuery;
import com.mongo.quote.rest.dto.PageResponseDTO;
import com.mongo.quote.rest.dto.QuoteResponseDTO;
import com.mongo.quote.rest.mapper.QuoteMapperImpl;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
@ContextConfiguration(classes = QuoteController.class)
class QuoteControllerTest {

  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean GetQuoteByIdQuery getQuoteByIdQuery;
  @MockBean GetQuoteByAuthorQuery getQuoteByAuthorQuery;
  @MockBean GetAllQuotesQuery getAllQuotesQuery;
  @SpyBean QuoteMapperImpl quoteMapper;
  @Captor ArgumentCaptor<GetQuoteByAuthorQuery.Command> commandAuthorCaptor;
  @Captor ArgumentCaptor<GetAllQuotesQuery.Command> commandAllCaptor;

  @Test
  @DisplayName("Should return 200 when get by Id")
  void shouldReturn200_WhenGetById() throws Exception {
    var id = "1";
    var request = MockMvcRequestBuilders.get("/quotes/%s".formatted(id));
    var expectedResult = getQuoteResult(id, "author");

    when(getQuoteByIdQuery.getById(any())).thenReturn(expectedResult);

    var response =
        mockMvc
            .perform(request)
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

    verify(getQuoteByIdQuery).getById(new GetQuoteByIdQuery.Command(id));
    assertNotNull(response);
    var quoteResponse =
        objectMapper.readValue(response.getContentAsString(), QuoteResponseDTO.class);

    assertAll(
        () -> assertEquals(expectedResult.id(), quoteResponse.id()),
        () -> assertEquals(expectedResult.quoteAuthor(), quoteResponse.quoteAuthor()),
        () -> assertEquals(expectedResult.quoteGenre(), quoteResponse.quoteGenre()),
        () -> assertEquals(expectedResult.quoteText(), quoteResponse.quoteText()),
        () -> assertEquals(expectedResult.version(), quoteResponse.version()));
  }

  @Test
  @DisplayName("Should return 200 when get all")
  void shouldReturn200_WhenGetAll() throws Exception {
    var expectedResult = getQuoteResult("id", "author");
    var expectedResultQuote = new PageResult<>(0, 10, List.of(expectedResult), 1);
    var request = MockMvcRequestBuilders.get("/quotes");

    when(getAllQuotesQuery.getAll(any())).thenReturn(expectedResultQuote);

    var response =
        mockMvc
            .perform(request)
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

    assertNotNull(response);
    var quotePage =
        objectMapper.readValue(
            response.getContentAsString(),
            new TypeReference<PageResponseDTO<QuoteResponseDTO>>() {});
    assertEquals(1, quotePage.content().size());
    var quoteResponse = quotePage.content().get(0);

    assertAll(
        () -> assertEquals(10, quotePage.pageSize()),
        () -> assertEquals(0, quotePage.page()),
        () -> assertEquals(1, quotePage.totalElements()));

    assertAll(
        () -> assertEquals(expectedResult.id(), quoteResponse.id()),
        () -> assertEquals(expectedResult.quoteAuthor(), quoteResponse.quoteAuthor()),
        () -> assertEquals(expectedResult.quoteGenre(), quoteResponse.quoteGenre()),
        () -> assertEquals(expectedResult.quoteText(), quoteResponse.quoteText()),
        () -> assertEquals(expectedResult.version(), quoteResponse.version()));

    verify(getAllQuotesQuery).getAll(new GetAllQuotesQuery.Command(0, 10));
  }

  @Test
  @DisplayName("Should return 200 when get by author")
  void shouldReturn200_WhenGetByAuthor() throws Exception {
    var author = "test";
    var expectedResult = getQuoteResult("id", author);
    var expectedResultQuote = new PageResult<>(0, 10, List.of(expectedResult), 1);

    var request = MockMvcRequestBuilders.get("/quotes/%s/authors".formatted(author));

    when(getQuoteByAuthorQuery.getByAuthor(any())).thenReturn(expectedResultQuote);

    var response =
        mockMvc
            .perform(request)
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

    assertNotNull(response);

    var quotePage =
        objectMapper.readValue(
            response.getContentAsString(),
            new TypeReference<PageResponseDTO<QuoteResponseDTO>>() {});

    assertEquals(1, quotePage.content().size());
    var quoteResponse = quotePage.content().get(0);

    assertAll(
        () -> assertEquals(10, quotePage.pageSize()),
        () -> assertEquals(0, quotePage.page()),
        () -> assertEquals(1, quotePage.totalElements()));

    assertAll(
        () -> assertEquals(expectedResult.id(), quoteResponse.id()),
        () -> assertEquals(expectedResult.quoteAuthor(), quoteResponse.quoteAuthor()),
        () -> assertEquals(expectedResult.quoteGenre(), quoteResponse.quoteGenre()),
        () -> assertEquals(expectedResult.quoteText(), quoteResponse.quoteText()),
        () -> assertEquals(expectedResult.version(), quoteResponse.version()));

    verify(getQuoteByAuthorQuery).getByAuthor(new GetQuoteByAuthorQuery.Command(author, 0, 10));
  }

  @Test
  void shouldReturn200GetAll_WhenPageAndSize() throws Exception {
    var request = MockMvcRequestBuilders.get("/quotes?page-size=20&page=2");

    mockMvc.perform(request).andDo(print()).andExpect(status().isOk());

    verify(getAllQuotesQuery, times(1)).getAll(commandAllCaptor.capture());

    assertNotNull(commandAllCaptor.getValue());
    assertEquals(20, commandAllCaptor.getValue().pageSize());
    assertEquals(2, commandAllCaptor.getValue().page());
  }

  @Test
  void shouldReturn200GetByAuthor_WhenPageAndSize() throws Exception {
    var request = MockMvcRequestBuilders.get("/quotes/test/authors?page-size=20&page=2");

    mockMvc.perform(request).andDo(print()).andExpect(status().isOk());

    verify(getQuoteByAuthorQuery, times(1)).getByAuthor(commandAuthorCaptor.capture());

    assertNotNull(commandAuthorCaptor.getValue());
    assertEquals(20, commandAuthorCaptor.getValue().pageSize());
    assertEquals(2, commandAuthorCaptor.getValue().page());
  }

  private QuoteResult getQuoteResult(String id, String author) {
    return new QuoteResult(id, "some quote", author, "genre", 0);
  }
}
