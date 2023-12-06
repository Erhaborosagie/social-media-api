package com.osagie.socialmediaapi.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String content;

    private long postId;
}
