package com.mongo.quote.rest.dto;

public record QuoteResponseDTO(
    String id, String quoteText, String quoteAuthor, String quoteGenre, Integer version) {}
