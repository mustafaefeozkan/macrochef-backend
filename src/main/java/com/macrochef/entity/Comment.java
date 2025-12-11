package com.macrochef.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MANY COMMENTS → ONE RECIPE
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    // MANY COMMENTS → ONE USER
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "comment_text", nullable = false, length = 500)
    private String commentText;

    private Integer rating; // 1–5 (DB’de CHECK var)

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
