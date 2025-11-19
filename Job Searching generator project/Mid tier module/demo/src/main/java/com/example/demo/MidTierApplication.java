/*package com.example.demo;

import com.example.demo.model.Job;               // ⬅️ updated import
import com.example.demo.Service.JobService;     // keep your existing package if that's what you use
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class MidTierApplication {

    private final JobService service;

    public MidTierApplication(JobService service) {
        this.service = service;
    }

    // Create
    @PostMapping("/addJob")
    public boolean addJob(@RequestBody Job job) {
        // If you rely on DB default for datePosted, you can leave it null.
        // If you want to set it when missing, uncomment:
        // if (job.getDatePosted() == null) job.setDatePosted(Instant.now());
        return service.addJob(job);
    }

    // Read
    @GetMapping("/jobs")
    public List<Job> retrieveValue() {
        return service.retrieveValue();
    }

    // Update
    @PutMapping("/updateJob/{id}")
    public boolean updateJob(@PathVariable Long id, @RequestBody Job changes) {
        return service.updateJob(id, changes);
    }

    // Delete
    @DeleteMapping("/deleteJob/{id}")
    public boolean deleteJob(@PathVariable Long id) {
        return service.delete(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(MidTierApplication.class, args);
    }
}*/
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MidTierApplication {
    public static void main(String[] args) {
        SpringApplication.run(MidTierApplication.class, args);
    }
}

