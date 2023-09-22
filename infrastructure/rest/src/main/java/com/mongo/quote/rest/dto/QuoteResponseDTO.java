package com.mongo.quote.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuoteResponseDTO(
    @JsonProperty("_id") String id,
    String quoteText,
    String quoteAuthor,
    String quoteGenre,
    @JsonProperty("__v") Integer version) {}
