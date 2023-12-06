package com.osagie.socialmediaapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;

    private String content;

    private Date date;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;
}
