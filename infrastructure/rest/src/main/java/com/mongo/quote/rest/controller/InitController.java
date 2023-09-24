package com.mongo.quote.rest.controller;

import com.mongo.quote.application.service.InitQuotesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InitController {

  private final InitQuotesUseCase initQuotesUseCase;

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping("/quotes/debug/init")
  void setInitQuotesUseCase() {
    initQuotesUseCase.initQuotes();
  }

}
