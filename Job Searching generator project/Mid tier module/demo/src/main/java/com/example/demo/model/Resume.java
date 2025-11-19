package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "resumes")
public class Resume {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
  private User user;  // can be null for now if you haven’t created a user

  private String name;
  @Lob private String content;

  private Instant uploadedAt; // DB default NOW() if null
}

