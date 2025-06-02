package com.anatoliydrake.NewsFeedManager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    @Schema(example = "1")
    private Long id;
    @Schema(example = "Sport")
    private String title;
}
