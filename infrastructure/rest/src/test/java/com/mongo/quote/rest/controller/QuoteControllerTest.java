package com.mongo.quote.rest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
@ContextConfiguration(classes = QuoteController.class)
class QuoteControllerTest {

  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;

  @Test
  void shouldReturn200_WhenGetById() throws Exception {
    var request =
        MockMvcRequestBuilders.get("/quotes/%s".formatted("1"));

    mockMvc.perform(request)
        .andDo(print())
        .andExpect(status().isOk());
  }
  @Test
  void shouldReturn200_WhenGetAll() throws Exception {
    var request =
        MockMvcRequestBuilders.get("/quotes");

    mockMvc.perform(request)
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturn200_WhenGetByAuthor() throws Exception {
    var request =
        MockMvcRequestBuilders.get("/quotes/%s/authors".formatted("test"));

    mockMvc.perform(request)
        .andDo(print())
        .andExpect(status().isOk());
  }


}
