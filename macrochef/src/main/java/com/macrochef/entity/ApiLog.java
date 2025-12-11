package com.macrochef.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endpoint;

    @Column(columnDefinition = "TEXT")
    private String requestPayload;

    @Column(columnDefinition = "LONGTEXT")
    private String responsePayload;

    private Integer statusCode;

    private LocalDateTime createdAt = LocalDateTime.now();
}
