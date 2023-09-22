package com.mongo.quote.application.dto;

import java.util.List;

public record PageResult<T>(Integer page, Integer pageSize, List<T> content) {}
