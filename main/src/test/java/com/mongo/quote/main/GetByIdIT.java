package com.mongo.quote.main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mongo.quote.rest.dto.QuoteResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetByIdIT extends BaseIT {

  private static final String GET_BY_ID_URL = "/quotes/%s";

  @Test
  @DisplayName("Should Get by ID")
  void shouldGetById() throws Exception {
    var id = "5eb17aadb69dc744b4e70d5c";
    var url = GET_BY_ID_URL.formatted(id);
    var expectedResult =
        new QuoteResponseDTO(
            id,
            "I'm not interested in age. People who tell me their age are silly. You're as old as you feel.",
            "Henri Frederic Amiel",
            "age",
            0);
    mvc.perform(get(url))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedResult)));
  }

  @Test
  @DisplayName("Should throw 404 when id not found")
  void shouldThrow404_WhenIDNotFound() throws Exception {
    var url = GET_BY_ID_URL.formatted("notFound");
    mvc.perform(get(url))
        .andDo(print())
        .andExpect(status().isNotFound());
  }


}
