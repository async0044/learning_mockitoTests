package com.async.learning.mockitoTests.dto;

public record ResponseDto(
        Long id,
        String title,
        String content,
        String author
)
{}
