package com.macrochef.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment_text", nullable = false, length = 500)
    private String text;

    @Column(nullable = false)
    private int rating; // 1–5

    private LocalDateTime createdAt = LocalDateTime.now();
}
