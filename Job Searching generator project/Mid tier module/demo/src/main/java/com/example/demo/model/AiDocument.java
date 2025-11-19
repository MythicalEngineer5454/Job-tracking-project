package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "ai_documents")
public class AiDocument {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "job_id")
  private Job job;

  @Lob private String resumeText;
  @Lob private String coverLetterText;
  private String tone;

  private Instant createdAt; // DB default NOW() if null
}

