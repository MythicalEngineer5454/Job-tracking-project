package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Resume;

public interface ResumeRepo extends JpaRepository<Resume, Long> {
}