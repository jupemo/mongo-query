package com.mongo.quote.main;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mongo.quote.rest.dto.PageResponseDTO;
import com.mongo.quote.rest.dto.QuoteResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetByAuthorIT extends BaseIT {

  private static final String GET_BY_AUTHOR_URL = "/authors/%s/quotes";

  @Test
  @DisplayName("Should return quote given author")
  void shouldReturnAuthors() throws Exception {
    var author = "William Shakespeare";
    var url = GET_BY_AUTHOR_URL.formatted(author);
    var response =
        mvc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();

    var responseDTO =
        objectMapper.readValue(
            response.getContentAsString(),
            new TypeReference<PageResponseDTO<QuoteResponseDTO>>() {});

    assertAll(
        () -> assertEquals(2, responseDTO.content().size()),
        () -> assertEquals(0, responseDTO.page()),
        () -> assertEquals(2, responseDTO.totalElements()),
        () -> assertEquals(10, responseDTO.pageSize()));
  }

  @Test
  @DisplayName("Should return quote given author page-size 1")
  void shouldReturnAuthorsPageSize1() throws Exception {
    var author = "William Shakespeare";
    var url = GET_BY_AUTHOR_URL.formatted(author);
    var response =
        mvc.perform(get(url).param("page-size", "1").param("page", "1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

    var responseDTO =
        objectMapper.readValue(
            response.getContentAsString(),
            new TypeReference<PageResponseDTO<QuoteResponseDTO>>() {});

    assertAll(
        () -> assertEquals(1, responseDTO.content().size()),
        () -> assertEquals(1, responseDTO.page()),
        () -> assertEquals(2, responseDTO.totalElements()),
        () -> assertEquals(1, responseDTO.pageSize()));
  }

  @Test
  void shouldReturnEmptyPagedWhenNoAuthor() throws Exception {
    var author = "bad author";
    var url = GET_BY_AUTHOR_URL.formatted(author);
    var response =
        mvc.perform(get(url))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

    var responseDTO =
        objectMapper.readValue(
            response.getContentAsString(),
            new TypeReference<PageResponseDTO<QuoteResponseDTO>>() {});

    assertAll(
        () -> assertNull(responseDTO.content()),
        () -> assertEquals(0, responseDTO.page()),
        () -> assertEquals(0, responseDTO.totalElements()),
        () -> assertEquals(10, responseDTO.pageSize()));
  }
}
