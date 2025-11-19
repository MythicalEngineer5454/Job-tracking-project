package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true) private String username;
  @Column(nullable = false, unique = true) private String email;

  // store a hash later; keep nullable for now
  private String passwordHash;

  // DB default NOW() will fill this when null
  private Instant createdAt;
}

