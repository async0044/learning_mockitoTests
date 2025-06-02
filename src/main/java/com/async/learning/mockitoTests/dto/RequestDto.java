package com.async.learning.mockitoTests.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestDto(
        @NotBlank(message = "Title cannot be empty")
        String title,

        String content,

        @NotBlank(message = "Title cannot be empty")
        String author
)
{   //место для простого маппера
}
