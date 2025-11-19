package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "jobs")
public class Job {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) private String title;
  private String company;
  private String location;
  @Lob private String description;
  private String link;
  private String category;
  private Boolean scraped = false;

  private Instant datePosted; // DB default NOW() if null
}

