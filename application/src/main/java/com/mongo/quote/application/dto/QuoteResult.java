package com.mongo.quote.application.dto;

public record QuoteResult(
    String id, String quoteText, String quoteAuthor, String quoteGenre, Integer version) {}
