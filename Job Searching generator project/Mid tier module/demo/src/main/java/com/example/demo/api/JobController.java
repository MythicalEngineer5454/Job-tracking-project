package com.example.demo.api;

import com.example.demo.model.Job;
import com.example.demo.repo.JobRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {
    private final JobRepo repo;
    public JobController(JobRepo repo){ this.repo = repo; }

    @GetMapping public List<Job> all(){ return repo.findAll(); }
    @GetMapping("/{id}") public Job one(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }
    @PostMapping public Job create(@RequestBody Job j){ return repo.save(j); }
    @PutMapping("/{id}") public Job update(@PathVariable Long id, @RequestBody Job j){
        j.setId(id); return repo.save(j);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
