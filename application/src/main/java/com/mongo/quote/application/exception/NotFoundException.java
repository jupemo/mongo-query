package com.mongo.quote.application.exception;

public class NotFoundException extends RuntimeException {
  private final String code = "B0001";
  private static final String MESSAGE = "Quote not found for '%s'";

  public NotFoundException(String input) {
    super(MESSAGE.formatted(input));
  }
}
