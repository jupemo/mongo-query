package com.mongo.quote.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PageResponseDTO<T>(
    List<T> content,
    Integer page,
    @JsonProperty("page_size") Integer pageSize,
    @JsonProperty("total_element") Integer totalElements) {}
