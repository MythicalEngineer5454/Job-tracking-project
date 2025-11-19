package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Application;

public interface ApplicationRepo extends JpaRepository<Application, Long> {
}