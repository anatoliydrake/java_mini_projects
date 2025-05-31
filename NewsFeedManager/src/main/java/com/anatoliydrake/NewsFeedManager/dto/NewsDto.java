package com.anatoliydrake.NewsFeedManager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NewsDto {
    @Schema(example = "1")
    private Long id;
    @Schema(example = "Some title")
    private String title;
    @Schema(example = "Some content")
    private String text;
    @Schema(example = "2025-05-31T10:50:35.570Z")
    private Instant date;
    @Schema(example = "1")
    private Long categoryId;
}
