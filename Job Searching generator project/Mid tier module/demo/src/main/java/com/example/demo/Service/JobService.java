package com.example.demo.Service;

import com.example.demo.model.Job;
import java.util.List;

public interface JobService {
    boolean addJob(Job job);
    boolean updateJob(Long id, Job changes);   // apply non-null fields from 'changes'
    List<Job> retrieveValue();
    boolean delete(Long id);
}
