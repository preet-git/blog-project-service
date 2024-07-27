package com.lernen.blogproject.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.Instant;

@Entity
@Table
@Data
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column
    private String title;
    @Lob
    @NotEmpty
    @Column
    private String Content;
    @Column
    private Instant createdOn;
    @Column
    private Instant updatedOn;
    @NotEmpty
    @Column
    private String userName;
}
