package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "applications")
public class Application {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "job_id")
  private Job job;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "ai_doc_id")
  private AiDocument aiDoc; // nullable

  private String status = "Applied";
  @Lob private String notes;

  private Instant appliedDate; // DB default NOW() if null
}

