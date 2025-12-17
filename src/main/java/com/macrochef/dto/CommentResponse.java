package com.macrochef.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String username;
    private String text;
    private int rating;
    private String createdAt;
}
