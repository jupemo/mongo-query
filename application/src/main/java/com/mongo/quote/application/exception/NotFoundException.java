package com.mongo.quote.application.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
  private final String code = "B0001";
  private static final String MESSAGE = "Quote not found for id: '%s'";

  public NotFoundException(String input) {
    super(MESSAGE.formatted(input));
  }
}
